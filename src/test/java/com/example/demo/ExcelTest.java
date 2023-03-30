package com.example.demo;

import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * excel测试
 *
 * @author luox
 * @date 2022/4/26
 */
public class ExcelTest {

    public static void main(String[] args) {

        try {
            test1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void test1() throws Exception{
        FileOutputStream os = null;
        try {
            InputStream is = new ClassPathResource("/static/license.xml").getInputStream();
            License excelLicense = new License();
            excelLicense.setLicense(is);
            is.close();
            //Workbook wb = new Workbook(new ClassPathResource("test1.xlsx").getInputStream());
            Workbook wb = new Workbook(new ClassPathResource("test2.xls").getInputStream());

            os = new FileOutputStream("D:\\result.pdf");
            //PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            ////参数true把内容放在一张PDF页面上；
            //pdfSaveOptions.setOnePagePerSheet(true);
            //wb.save(fileOS, pdfSaveOptions);
            wb.save(os, SaveFormat.PDF);
        }finally {
            if(os!=null){
                os.close();
            }
        }


    }


    private static void print(Object obj){
        System.out.println(obj);
    }
}
