package com.wanwan.springboot.common.enums;

/**
 * @author：玩玩
 * @since：2024/3/7 16:41
 * @description:
 */
public enum ResultCodeEnum {

    // String CODE_400 = "400";    //参数错误

    // String CODE_401 = "401";    //权限不足
    // String CODE_500 = "500";    //系统错误
    // String CODE_600 = "600";    //其它业务异常
    SUCCESS("200", "成功"),
    PARAM_ERROR("400", "参数异常"),
    TOKEN_INVALID_ERROR("401", "token验证失败，请重新登陆"),
    TOKEN_EMPTY_ERROR("402", "token为空，请登陆"),
    TOKEN_EXPIRED_ERROR("403", "token过期,请重新登陆"),
    TOKEN_SIGNATURE_ERROR("404", "token签名无效"),
    TOKEN_ALGORITHM_ERROR("405","token算法不匹配"),
    PARAM_LOST_ERROR("4001","参数缺失"),
    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIT_ERROR("5001", "用户已存在"),
    USER_NO_LOGIN("5002","用户未登录"),
    USER_ACCOUNT_ERROR("5003","用户账号或密码错误"),
    USER_NO_EXIT_ERROR("5004","用户不存在"),
    USER_EMAIL_CODE_ERROR("5005", "用户邮箱验证码错误"),
    PARAM_PASSWORD_ERROR("5006","用户密码错误"),
    ERROR("500", "失败");
    public String code;
    public String msg;
    ResultCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
