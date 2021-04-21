package com.meeting.demo.util;

import com.meeting.demo.entity.MeetingUsers;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ImportExcel implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public List<MeetingUsers> importXLS(String fileName) {

            List<MeetingUsers> list = new java.util.ArrayList<MeetingUsers>();

            try {
                // 1、获取文件输入流
                InputStream inputStream = new FileInputStream(
                        fileName);
                // 2、获取Excel工作簿对象
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                // 3、得到Excel工作表对象
                HSSFSheet sheetAt = workbook.getSheetAt(0);
                // 4、循环读取表格数据
                for (Row row : sheetAt) {
                    // 首行（即表头）不读取
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    MeetingUsers newStudent = new MeetingUsers();
                    // 每一行有几个单元格
//				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
//				for(int i=0 ; i<physicalNumberOfCells ; i++) {
//					;
//				}
                    // 读取当前行中单元格数据，索引从0开始
                    String userName = row.getCell(0).getStringCellValue();
                   // String mobile = row.getCell(1).getStringCellValue();
                    String mobile = String.valueOf(row.getCell(1).getNumericCellValue());
                    BigDecimal bd = new BigDecimal(mobile);
                    mobile = bd.toString();
                    String role = row.getCell(2).getStringCellValue();
                    String password = row.getCell(3).getStringCellValue();
                    newStudent.setUserName(userName);
                    newStudent.setMobile(mobile);
                    newStudent.setRole(role);
                    newStudent.setPassword(password);
                    list.add(newStudent);
                }
                // 5、关闭流
                workbook.close();

            } catch (IOException e) {
                System.out.println("出错了");
                e.printStackTrace();
            }
            System.out.println("sss");
            System.out.println(list.size());
            return list;
        }

    }