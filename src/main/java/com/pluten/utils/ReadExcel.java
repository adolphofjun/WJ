package com.pluten.utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ReadExcel {
    private static Logger logger = Logger.getLogger(ReadExcel.class);
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath){
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    /**
     * &#x8bfb;EXCEL&#x6587;&#x4ef6;&#xff0c;&#x83b7;&#x53d6;&#x5ba2;&#x6237;&#x4fe1;&#x606f;&#x96c6;&#x5408;
     * @return
     */
    public List<Map> getExcelInfo(String fileName, MultipartFile Mfile){
        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
        File file = new  File("E:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("E:\\fileupload" + new Date().getTime() + ".xlsx");
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化客户信息的集合
        List<Map> customerList=new ArrayList<Map>();
        //初始化输入流
        InputStream is = null;
        try{
            //验证文件名是否合格
            if(!validateExcel(fileName)){
                return null;
            }
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(WDWUtil.isExcel2007(fileName)){
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息
            customerList = getExcelInfo(is, isExcel2003);
            //is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
           if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }
    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public  List<Map> getExcelInfo(InputStream is,boolean isExcel2003){
        List<Map> customerList=null;
        try{
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            customerList=readExcelValue(wb);
        }
        catch (IOException e)  {
            e.printStackTrace();
        }
        return customerList;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<Map> readExcelValue(Workbook wb){
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        this.totalRows=sheet.getPhysicalNumberOfRows();
        //得到Excel的列数(前提是有行数)
        if(totalRows>=1 && sheet.getRow(0) != null){
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map> customerList=new ArrayList<Map>();
        Map map;
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            map = new HashMap();
            Cell number = row.getCell(0);
            number.setCellType(Cell.CELL_TYPE_STRING);
            map.put("number",number.getStringCellValue());//序号
            Cell bank_id = row.getCell(1);
            bank_id.setCellType(Cell.CELL_TYPE_STRING);
            map.put("bank_id",bank_id.getStringCellValue());//
            Cell type_id = row.getCell(2);
            type_id.setCellType(Cell.CELL_TYPE_STRING);
            map.put("type_id",type_id.getStringCellValue());//
            Cell keyWord = row.getCell(3);
            keyWord.setCellType(Cell.CELL_TYPE_STRING);
            map.put("keyWord",keyWord.getStringCellValue());//
            Cell must = row.getCell(4);
            must.setCellType(Cell.CELL_TYPE_STRING);
            map.put("must",must.getStringCellValue());//
            Cell visibility = row.getCell(5);
            visibility.setCellType(Cell.CELL_TYPE_STRING);
            map.put("visibility",visibility.getStringCellValue());//
            Cell name = row.getCell(6);
            name.setCellType(Cell.CELL_TYPE_STRING);
            map.put("name",name.getStringCellValue());//题目
            Cell maxScore = row.getCell(18);
            maxScore.setCellType(Cell.CELL_TYPE_STRING);
            map.put("maxScore",maxScore.getStringCellValue());//题目
            getSelect(map,row);
            //添加客户
            customerList.add(map);
        }
        return customerList;
    }

    public void getSelect(Map map,Row row){
        int initN = 7;
        Cell count = row.getCell(initN);
        count.setCellType(Cell.CELL_TYPE_STRING);
        String countStr = count.getStringCellValue();
        if(countStr!=null || "".endsWith(countStr)){
            Integer integer = Integer.parseInt(countStr);
            map.put("RowNum",integer);
            logger.info("========="+countStr);
            for(int i=initN+1; i<=(initN+integer*2); i++){
                Cell cell = row.getCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                if(i==initN+1){
                    map.put("A",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("AScore",score.getStringCellValue());//序号
                }else  if(i==initN+3){
                    map.put("B",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("BScore",score.getStringCellValue());//序号
                }else  if(i==initN+5){
                    map.put("C",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("CScore",score.getStringCellValue());//序号
                }
                else  if(i==initN+7){
                    map.put("D",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("DScore",score.getStringCellValue());//序号
                }
                else  if(i==initN+9){
                    map.put("E",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("EScore",score.getStringCellValue());//序号
                }
                if(i==initN+11){
                    map.put("F",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("FScore",score.getStringCellValue());//序号
                }
                else  if(i==initN+13){
                    map.put("G",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("GScore",score.getStringCellValue());//序号
                }
                else  if(i==initN+15){
                    map.put("H",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("HScore",score.getStringCellValue());//序号
                }
                else  if(i==initN+17){
                    map.put("I",cell.getStringCellValue());//序号
                    Cell score = row.getCell(i+1);
                    score.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("IScore",score.getStringCellValue());//序号
                }
            }
        }


    }
}
