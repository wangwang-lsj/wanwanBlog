package com.wanwan.springboot.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 玩玩
 * @description
 * @since 2024/3/19  16:20
 */
@Slf4j
public class IpUtil {
    private static final String localIp = "127.0.0.1";
    private static Searcher searcher = null;
    /**
     *   在服务启动时加载 ip2region.db 到内存中
     *   解决打包jar后找不到 ip2region.db 的问题
     */
    static {
        try {
            InputStream ris = IpUtil.class.getResourceAsStream("/ip2region/ip2region.xdb");
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            searcher = Searcher.newWithBuffer(dbBinStr);
            //注意：不能使用文件类型，打成jar包后，会找不到文件
            log.debug("缓存成功！！！！");
        } catch (IOException e) {
            log.error("解析ip地址失败,无法创建搜索器: {}", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ipAddress;
        try {
            // 以下两个获取在k8s中，将真实的客户端IP，放到了x-Original-Forwarded-For。而将WAF的回源地址放到了 x-Forwarded-For了。
            ipAddress = request.getHeader("X-Original-Forwarded-For");
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("X-Forwarded-For");
            }

            //获取nginx等代理的ip
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("x-forwarded-for");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
            }

            // 2.如果没有转发的ip，则取当前通信的请求端的ip(兼容k8s集群获取ip)
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                // 如果是127.0.0.1，则取本地真实ip
                if (localIp.equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("解析请求IP失败", e);
            ipAddress = "";
        }
        return "0:0:0:0:0:0:0:1".equals(ipAddress) ? localIp : ipAddress;
    }

    // /**
    //  * 获取IP地址
    //  * <p>
    //  * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
    //  * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
    //  */
    // public static String getIpAddr(HttpServletRequest request) {
    //     if (request == null) {
    //         return "unknown";
    //     }
    //     String ip = request.getHeader("x-forwarded-for");
    //     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    //         ip = request.getHeader("Proxy-Client-IP");
    //     }
    //     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    //         ip = request.getHeader("X-Forwarded-For");
    //     }
    //     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    //         ip = request.getHeader("WL-Proxy-Client-IP");
    //     }
    //     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    //         ip = request.getHeader("X-Real-IP");
    //     }
    //
    //     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    //         ip = request.getRemoteAddr();
    //     }
    //
    //     return "0:0:0:0:0:0:0:1".equals(ip) ? localIp : ip;
    // }
    /**
     * 根据IP地址获取城市
     * @param ipAddress
     * @return
     */
    public static String getCityInfo(String ipAddress){
        String cityInfo = null;
        try {
            return searcher.search(ipAddress);
        } catch (Exception e) {
            log.error("搜索:{} 失败: {}", ipAddress, e);
        }
        return null;
    }

    /**
     * 根据ip2region解析ip地址
     * @param ipAddress ip地址
     * @return 解析后的ip地址信息
     */
    public static Map<String, String> getIp2region(String ipAddress)  {
        if(searcher == null){
            log.error("Error: DbSearcher is null");
            return null;
        }
        try {
            String ipInfo = searcher.search(ipAddress);
            if (!StrUtil.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            // 中国|湖南省|衡阳市|电信
            String[] parts = ipInfo.split("\\|");
            Map<String, String> map = new HashMap<>();
            map.put("nation", parts[0]);
            map.put("province", parts[1]);
            map.put("city", parts[2]);
            map.put("isp", parts[3]);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断给定的IP地址是否为内部IP（通常指的是私有IP地址，主要用于局域网）
     * @param ip
     * @return
     */
    public static boolean internalIp(String ip) {
        boolean res = false;
        byte[] addr = textToNumericFormatV4(ip);
        if (addr != null && ip != null) {
            res = internalIp(addr) || localIp.equals(ip);
        }
        return res;
    }

    private static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        boolean flag = false;
        switch (b0) {
            case SECTION_1:
                flag = true;
                break;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    flag = true;
                }
                break;
            case SECTION_5:
                if (b1 == SECTION_6) {
                    flag = true;
                }
                break;
            default:
                break;
        }
        return flag;
    }

    /**
     * 将IPv4地址转换成字节
     * IPv4地址
     *
     * @param text
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L))
                        return null;
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L))
                        return null;
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L))
                        return null;
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L))
                        return null;
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            log.error("数字格式化异常", e);
            return null;
        }
        return bytes;
    }

    /**
     * 获取本机IP地址
     * @return
     */
    public static String getLocalIP() {
        String ip = "";
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            InetAddress addr;
            try {
                addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (UnknownHostException e) {
                log.error("获取失败", e);
            }
            return ip;
        } else {
            try {
                Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                        .getNetworkInterfaces();
                while (e1.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) e1.nextElement();
                    if (!ni.getName().equals("eth0")) {
                        continue;
                    } else {
                        Enumeration<?> e2 = ni.getInetAddresses();
                        while (e2.hasMoreElements()) {
                            InetAddress ia = (InetAddress) e2.nextElement();
                            if (ia instanceof Inet6Address)
                                continue;
                            ip = ia.getHostAddress();
                            return ip;
                        }
                        break;
                    }
                }
            } catch (SocketException e) {
                log.error("获取失败", e);
            }
        }
        return "";
    }
}
