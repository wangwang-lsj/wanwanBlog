package com.wanwan.springboot.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 玩玩
 * @description
 * @since 2024/4/25  15:46
 */
public class GeneratorDataToWordUtil {
    public static final String driverUrl = "jdbc:mysql://127.0.0.1:3306/wanwan?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=false";
    public static final String username = "root";
    public static final String password = "root";
    /**
     * 导出数据库需要与driverUrl中连接的数据库一致
     */
    public static final String dataBase = "wanwan";
    /**
     * 不需要导出的表，可为null "'gen_table','gen_table_column'"
     */
    public static final String notTbales = null;
    /**
     * 匹配前缀不导出，可为null
     */
    public static final String notLike = "'qrtz_%'";
    /**
     * 文档标题
     */
    public static final String title = "个人博客数据库设计详细说明书";
    /**
     * 输出文档地址
     */
    public static final String path = "d:\\";
    /**
     * 输出文档名称
     */
    public static final String fileName = "个人博客数据库设计详细说明书V1.0.docx";

    public static void main(String[] args)throws Exception {
        //Blank Document
        XWPFDocument document= new XWPFDocument();
        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText(title);
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);
        GeneratorDataToWordUtil we = new GeneratorDataToWordUtil();
        List<JSONObject> list= we.getTables(dataBase);
        for (JSONObject json : list) {
            List<String[]> columns = we.getTablesDetail(dataBase, json.getString("name"));
            addTable(document, json.getString("name"), json.getString("remark"), columns);
        }
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(path+fileName));
        document.write(out);
        out.close();
        System.out.println("create_table document written success.");
    }

    private  List<String[]> getTablesDetail(String schema, String tableName){
        List<String[]> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection(driverUrl, username, password);
            //定义sql语句 ?表示占位符
            String sql = "SELECT COLUMN_NAME  , COLUMN_TYPE  ,  COLUMN_DEFAULT  , if(is_nullable='YES','是','否') IS_NULLABLE  ,if(column_key='PRI','是','否' ) COLUMN_KEY,  COLUMN_COMMENT "
                    +" FROM INFORMATION_SCHEMA.COLUMNS    WHERE  table_schema = ? and table_name = ?  ";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, schema);
            preparedStatement.setString(2, tableName);
            //向数据库发出sql执行查询，查询出结果集
            resultSet =  preparedStatement.executeQuery();
            int i = 1;
            //遍历查询结果集
            while(resultSet.next()){
                String[] str = new String[4];
                str[0] = i+"";
                str[1] = resultSet.getString("COLUMN_NAME");
                str[2] = resultSet.getString("COLUMN_TYPE");
                //str[3] = resultSet.getString("COLUMN_DEFAULT");
                //str[3] = resultSet.getString("IS_NULLABLE");
                //str[5] = resultSet.getString("COLUMN_KEY");
                str[3] = resultSet.getString("COLUMN_COMMENT");
                list.add(str);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }

    private  List<JSONObject> getTables(String schema){
        List<JSONObject> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection(driverUrl, username, password);
            //定义sql语句 ?表示占位符
            StringBuffer sql = new StringBuffer();
            sql.append("select TABLE_NAME,TABLE_COMMENT from information_schema.tables where table_schema= ? ");
            if(null != notLike){
                sql.append(" AND table_name NOT LIKE "+notLike);
            }
            if(null != notTbales){
                sql.append(" AND table_name NOT IN ("+notTbales+")");
            }
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql.toString());
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, schema);
            //向数据库发出sql执行查询，查询出结果集
            resultSet =  preparedStatement.executeQuery();
            //遍历查询结果集
            while(resultSet.next()){
                JSONObject j = new JSONObject();
                j.put("name", resultSet.getString("TABLE_NAME"));
                j.put("remark", resultSet.getString("TABLE_COMMENT"));
                list.add(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }

    private static void addTable(XWPFDocument document,String tableName,String remark, List<String[]> columns){

        //两个表格之间加个换行
        document.createParagraph().createRun().setText("\r");
        // 标题1，1级大纲
        document.createParagraph().createRun().setText(remark+"("+tableName+")");
        //工作经历表格
        XWPFTable ComTable = document.createTable();
//        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

       /* CTTbl ttbl = ComTable.getCTTbl();
        int[] COLUMN_WIDTHS = new int[] {572,2072,1372,872,672,672,2572};
        CTTblGrid tblGrid = ttbl.getTblGrid() != null ? ttbl.getTblGrid()
                : ttbl.addNewTblGrid();
        for (int j = 0, len = COLUMN_WIDTHS.length; j < len; j++) {
            CTTblGridCol gridCol = tblGrid.addNewGridCol();
            gridCol.setW(new BigInteger(String.valueOf(COLUMN_WIDTHS[j])));
        }*/
        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        setCellvalue(comTableRowOne.getCell(0), "序号","DCDCDC");
        setCellvalue(comTableRowOne.addNewTableCell(),"字段名","DCDCDC");
        setCellvalue(comTableRowOne.addNewTableCell(),"类型","DCDCDC");
        //setCellvalue(comTableRowOne.addNewTableCell(),"默认值","DCDCDC");
        //setCellvalue(comTableRowOne.addNewTableCell(),"是否为空","DCDCDC");
        //setCellvalue(comTableRowOne.addNewTableCell(),"是否主键","DCDCDC");
        setCellvalue(comTableRowOne.addNewTableCell(),"注释","DCDCDC");
        for (String[] str : columns) {
            //表格第二行
            XWPFTableRow comTableRowTwo = ComTable.createRow();
            for (int j = 0; j < str.length; j++) {
                //if(j==0 || j==3 || j==4 || j==5){
                if(j==0 || j==3){
                    setCellvalue(comTableRowTwo.getCell(j),str[j]);
                }else{
                    comTableRowTwo.getCell(j).setText(str[j]);
                }
            }
        }

    }

    private static void setCellvalue(XWPFTableCell cell, String text){
        cell.setText(text);
        //垂直居中
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        CTTc cttc = cell.getCTTc();
        CTP ctp = cttc.getPList().get(0);
        CTPPr ctppr = ctp.getPPr();
        if (ctppr == null) {
            ctppr = ctp.addNewPPr();
        }
        CTJc ctjc = ctppr.getJc();
        if (ctjc == null) {
            ctjc = ctppr.addNewJc();
        }
        //水平居中
        ctjc.setVal(STJc.CENTER);
    }
    private static void setCellvalue(XWPFTableCell cell, String text,String color){
        cell.setText(text);
        //垂直居中
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        CTTc cttc = cell.getCTTc();
        cttc.addNewTcPr().addNewShd().setFill(color);
        CTP ctp = cttc.getPList().get(0);
        CTPPr ctppr = ctp.getPPr();
        if (ctppr == null) {
            ctppr = ctp.addNewPPr();
        }
        CTJc ctjc = ctppr.getJc();
        if (ctjc == null) {
            ctjc = ctppr.addNewJc();
        }
        //水平居中
        ctjc.setVal(STJc.CENTER);
    }

}
