package com.example.demo;

import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.example.demo.util.document.PoiUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

/**
 * word文档测试
 *
 * @author luox
 * @date 2022/4/11
 */
public class WordTest {

    public static void main(String[] args) {
        try {

            //test1();
            test2();
            //test3();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void test3(){

        FileOutputStream os =null;
        try {
            //增加license，否则有水印
            InputStream stream = new ClassPathResource("license.xml").getInputStream();
            License license = new License();
            license.setLicense(stream);
            stream.close();
            //File file = new ClassPathResource("result.pdf").getFile(); // 新建一个空白pdf文档
            os = new FileOutputStream("D:\\result.pdf");

            com.aspose.words.Document doc = new com.aspose.words.Document(new ClassPathResource("test1.docx").getInputStream());

            doc.save(os, SaveFormat.PDF);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void test2() throws IOException, InvalidFormatException, XmlException {
        XWPFDocument document = new XWPFDocument(new ClassPathResource("documentTemplates/周报管理导出模板V2.0.docx").getInputStream());
        //设置追加内容的默认字体大小
        XWPFStyles xwpfStyles = document.getStyles();
        CTStyles ctStyles = document.getStyle();
        List<CTStyle> ctStyleList = ctStyles.getStyleList();
        CTStyle ctStyle = ctStyleList.get(0);
        CTRPr rpr = ctStyle.getRPr();
        rpr.getSz().setVal(BigInteger.valueOf(24));
        xwpfStyles.setStyles(ctStyles);
        //页眉
        String projectName = "测试";
        XWPFRun headerRun1 = document.getHeaderArray(1).getParagraphArray(0).getRuns().get(0);
        headerRun1.setText(headerRun1.getText(0).replace("${projectName}",projectName),0);
        XWPFRun headerRun0 = document.getHeaderArray(0).getParagraphArray(0).getRuns().get(0);
        headerRun0.setText(headerRun0.getText(0).replace("${projectName}",projectName),0);

        //测试换行符
        String newLineStr = "\\n1、与业主和商务人员一起沟通补充协议内容及修正、沟通补充协议图纸设计与打印邮寄等工作\\n\\n2、客户现场进行每周试运行报告编写";
        String[] arr = newLineStr.split("\\\\n");

        XWPFTable produceProgressTable = document.getTables().get(2);

        XWPFTableCell cell = produceProgressTable.getRow(0).getCell(0);

        for(int i = 0;i < arr.length;i++){
            cell.getParagraphArray(i).createRun().setText(arr[i]);
            cell.addParagraph();
        }


        List<XWPFParagraph> paragraphList = document.getParagraphs();
        for(int i = 0,size = paragraphList.size();i < size;i++){
            XWPFParagraph paragraph = paragraphList.get(i);
            String text = paragraph.getText();
            //XWPFRun run = paragraph.getRuns().get(0);
            //String text = run.getText(0);

            if(text.contains("${projectName}")){
                //text.replace("${projectName}",projectName);
                //paragraph.getRuns().get(0).setText(text,0);
                replaceParagraphText(paragraph,"${projectName}",projectName);
            }

            if(text.contains("${totalTerm}")){
                replaceParagraphText(paragraph,"${totalTerm}","0008期");
            }
            if(text.contains("${weekTerm}")){
                replaceParagraphText(paragraph,"${weekTerm}","2022年3月第4周");
            }

            //表格样式过于难调整，表格就利用先设置好的样式表格进行操作
            if(text.contains("${weekStaffTable}")){

                XmlCursor cursor= paragraph.getCTP().newCursor();
                XWPFTable weekStaffTable = document.insertNewTbl(cursor);

                CTTblPr tblPr = weekStaffTable.getCTTbl().getTblPr();

                CTString styleStr = tblPr.addNewTblStyle();

                styleStr.setVal("StyledTable");


                weekStaffTable.setRowBandSize(100);


                XWPFTableCell indexCell = weekStaffTable.getRow(0).getCell(0);
                indexCell.setWidth("1500");
                indexCell.setWidthType(TableWidthType.DXA);
                XWPFParagraph indexParagraph = indexCell.getParagraphArray(0);
                indexParagraph.setAlignment(ParagraphAlignment.CENTER);
                indexParagraph.createRun().setText("序号");

                XWPFTableCell categoryCell = weekStaffTable.getRow(0).addNewTableCell();
                categoryCell.setWidth("4000");
                categoryCell.setWidthType(TableWidthType.DXA);
                XWPFParagraph categoryParagraph = categoryCell.getParagraphArray(0);
                categoryParagraph.createRun().setText("类别");

                XWPFTableCell amountCell = weekStaffTable.getRow(0).addNewTableCell();
                amountCell.setWidth("3000");
                amountCell.setWidthType(TableWidthType.DXA);
                XWPFParagraph amountParagraph = amountCell.getParagraphArray(0);
                amountParagraph.setAlignment(ParagraphAlignment.CENTER);
                amountParagraph.createRun().setText("数量（人）");

                XWPFTableCell remarksCell = weekStaffTable.getRow(0).addNewTableCell();
                remarksCell.setWidth("5000");
                remarksCell.setWidthType(TableWidthType.DXA);
                XWPFParagraph remarksParagraph = remarksCell.getParagraphArray(0);
                remarksParagraph.createRun().setText("备注");

                weekStaffTable.createRow();
                XWPFParagraph indexParagraph1 = weekStaffTable.getRow(1).getCell(0).getParagraphArray(0);
                indexParagraph1.setAlignment(ParagraphAlignment.CENTER);
                indexParagraph1.createRun().setText("1");

                XWPFParagraph categoryParagraph1 = weekStaffTable.getRow(1).getCell(1).getParagraphArray(0);
                categoryParagraph1.createRun().setText("catge");

                XWPFParagraph amountParagraph1 = weekStaffTable.getRow(1).getCell(2).getParagraphArray(0);
                amountParagraph1.setAlignment(ParagraphAlignment.CENTER);
                amountParagraph1.createRun().setText("22");

                XWPFParagraph remarksParagraph1 = weekStaffTable.getRow(1).getCell(3).getParagraphArray(0);
                remarksParagraph1.createRun().setText("dashdj");

                //设置表格居中
                weekStaffTable.setTableAlignment(TableRowAlign.CENTER);
                //设置单元格内容纵向居中
                PoiUtil.setCellVertCenter(weekStaffTable);

                document.removeBodyElement(document.getPosOfParagraph(paragraph));
                //删除的是段落，新增了表格，表格size增加，段落size减少
                size--;
                continue;
            }
        }

        String url = System.getProperty("user.dir") + "/src/test/resources/result.docx";
        File resultFile = new File(url);
        OutputStream outputStream = new FileOutputStream(resultFile);
        document.write(outputStream);
        document.close();
    }

    private static void replaceParagraphText(XWPFParagraph paragraph,String replaceText,String resultText){
        List<XWPFRun> runList = paragraph.getRuns();
        for (XWPFRun run : runList) {
            if(run.getText(0).contains(replaceText)){
                run.setText(run.getText(0).replace(replaceText,resultText),0);
            }
        }
    }



    public static void test1() throws IOException, InvalidFormatException, XmlException {
        XWPFDocument document = new XWPFDocument(new ClassPathResource("wordTest.docx").getInputStream());
        //设置追加内容的默认字体大小
        XWPFStyles xwpfStyles = document.getStyles();

        print("default font size:" + xwpfStyles.getDefaultRunStyle().getFontSize());
        CTStyles ctStyles = document.getStyle();
        List<CTStyle> ctStyleList = ctStyles.getStyleList();
        CTStyle ctStyle = ctStyleList.get(0);

        CTRPr rpr = ctStyle.getRPr();
        //SchemaType schemaType = ctStyle.getRPr().schemaType();
        //CTFontsImpl ctFonts = new CTFontsImpl(schemaType);
        //ctFonts.setIntValue(20);

        print("rpr sz size:" + rpr.getSz().getVal());
        //value / 2 -> font size
        rpr.getSz().setVal(BigInteger.valueOf(40));
        xwpfStyles.setStyles(ctStyles);

        xwpfStyles.setDefaultFonts(rpr.getRFonts());
        print("default font size:" + xwpfStyles.getDefaultRunStyle().getFontSize());


        //页眉
        //document.getHeaderList()
        document.getHeaderArray(0).getParagraphArray(0).getRuns().get(0).setText("XX项目项目施工周报",0);

        List<XWPFParagraph> paragraphList = document.getParagraphs();
        for(int i = 0,size = paragraphList.size();i < size;i++){
            XWPFParagraph paragraph = paragraphList.get(i);
            String text=paragraph.getText();
            //插入段落
            if(text.equals("${paragraph1}")){
                XmlCursor cursor = paragraph.getCTP().newCursor();
                XWPFParagraph newParagraph = document.insertNewParagraph(cursor);
                XWPFRun run = newParagraph.createRun();
                run.setText("插入段落内容");
                run.setFontSize(20);
                run.setFontFamily("黑体");
                run.setBold(true);
                //run.setVerticalAlignment("center");
                //newParagraph.setVerticalAlignment(TextAlignment.CENTER);
                newParagraph.setAlignment(ParagraphAlignment.CENTER);
                //先移除节点后段落size不变
                document.removeBodyElement(document.getPosOfParagraph(paragraph));
                continue;
            }
            //替换段落
            if(text.equals("${paragraph2}")){
                //pos参数代表从第一几个字符开始，不写则是追加
                paragraph.getRuns().get(0).setText("替换段落内容",0);
                //段落size不变
                continue;
            }

            //插入表格
            if(text.equals("${table1}")){
                XmlCursor cursor= paragraph.getCTP().newCursor();
                XWPFTable table = document.insertNewTbl(cursor);

                XWPFTableRow row_0 = table.getRow(0);
                row_0.getCell(0).setText("姓名");
                row_0.addNewTableCell().setText("图片");

                XWPFTableRow row_1 = table.createRow();
                row_1.getCell(0).setText("隔壁老王");

                XWPFParagraph paragraph1 = row_1.getCell(1).getParagraphs().get(0);
                //XWPFParagraph paragraph1 = row_1.addNewTableCell().getParagraphs().get(0);
                paragraph1.createRun().addPicture(
                        new ClassPathResource("code.jpeg").getInputStream(),
                        //new FileInputStream("code.jpeg"),
                        XWPFDocument.PICTURE_TYPE_JPEG,"code.jpeg,",
                        Units.toEMU(100), Units.toEMU(50));
                //test
                XWPFTableRow row_2 = table.createRow();
                row_2.getCell(0).getParagraphs().get(0).createRun().setBold(true);
                row_2.getCell(0).getParagraphs().get(0).getRuns().get(0).setText("姓名");
                XWPFTableRow row_3 = table.createRow();
                XWPFTableRow row_4 = table.createRow();
                XWPFTableRow row_5 = table.createRow();

                //table.setTableAlignment(TableRowAlign.CENTER);
                setTableLocation(table,"center");
                setCellLocation(table,"CENTER","center");

                mergeCellsHorizontal(table,2,0,1);
                mergeCellsVertically(table,0,3,5);

                document.removeBodyElement(document.getPosOfParagraph(paragraph));
                //删除的是段落，新增了表格，表格size增加，段落size减少
                size--;
            }
        }

        List<XWPFTable> tableList = document.getTables();

        XWPFTable imageTable = tableList.get(1);

        print("imageTable style : " + document.getTblStyle(imageTable));

        imageTable.getRow(0).getCell(0).getParagraphArray(0).createRun()
                .addPicture(new ClassPathResource("code.jpeg").getInputStream(),
                            XWPFDocument.PICTURE_TYPE_JPEG,"code.jpeg,",
                            Units.toEMU(200), Units.toEMU(200));

        XWPFRun run1 = imageTable.getRow(1).getCell(0).getParagraphArray(0).createRun();
        run1.setBold(true);
        run1.setText("少年出招吧");


        imageTable.getRow(0).getCell(1).getParagraphArray(0).createRun()
                .addPicture(new ClassPathResource("code.jpeg").getInputStream(),
                        XWPFDocument.PICTURE_TYPE_JPEG,"code.jpeg,",
                        Units.toEMU(100), Units.toEMU(100));

        imageTable.getRow(1).getCell(1).getParagraphArray(0).createRun()
                .setText("少年出招吧");

        //文档对象保存到本地word
        String url = System.getProperty("user.dir") + "/src/test/resources/result.docx";
        print("resultUrl:" + url);
        File resultFile = new File(url);
        OutputStream outputStream = new FileOutputStream(resultFile);

        document.write(outputStream);
        document.close();
    }

    public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * 设置单元格水平位置和垂直位置
     *
     * @param verticalLocation    单元格中内容垂直上TOP，下BOTTOM，居中CENTER，BOTH两端对齐
     * @param horizontalLocation 单元格中内容水平居中center,left居左，right居右，both两端对齐
     */
    public static void setCellLocation(XWPFTable xwpfTable, String verticalLocation, String horizontalLocation) {
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
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
                ctjc.setVal(STJc.Enum.forString(horizontalLocation)); //水平居中
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.valueOf(verticalLocation));//垂直居中
            }
        }
    }

    /**
     * 设置表格位置
     *
     * @param xwpfTable
     * @param location  整个表格居中center,left居左，right居右，both两端对齐
     */
    public static void setTableLocation(XWPFTable xwpfTable, String location) {
        CTTbl cttbl = xwpfTable.getCTTbl();
        CTTblPr tblpr = cttbl.getTblPr() == null ? cttbl.addNewTblPr() : cttbl.getTblPr();
        CTJc cTJc = tblpr.addNewJc();
        cTJc.setVal(STJc.Enum.forString(location));
    }

    private static void print(Object obj){
        System.out.println(obj);
    }
}
