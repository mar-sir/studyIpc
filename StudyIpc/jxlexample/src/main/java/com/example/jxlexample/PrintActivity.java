
package com.example.jxlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jxlexample.enums.EnumPrint;
import com.example.jxlexample.model.MoudleInstance;
import com.example.jxlexample.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PrintActivity extends AppCompatActivity {
    private String name;
    private String fileName;
    private String trueFileName = null;//真正操作的文件路径

    private ArrayList<EnumPrint> enums = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        name = getIntent().getStringExtra("name");

        //生成新的文件名和文件
        fileName = FileUtils.getModelPath(new Date().toString().trim(), name);
        creatSheet();
        //找到模版表格对应的位置
        initEnums();

        doworkSheet();
    }

    private void doworkSheet() {
        try {
            //打开要修改的xls文件
            Workbook workbook = Workbook.getWorkbook(new File(trueFileName));
            //创建可写入的Excel工作薄对象(生成新文件)
            WritableWorkbook wb = Workbook.createWorkbook(new File(trueFileName), workbook);
            WritableCellFormat firstLineformat = MoudleInstance.getFirstLineFormat();
            WritableCellFormat format = MoudleInstance.getNormalFormat();
            //错误的格式
            WritableCellFormat errorFormat = MoudleInstance.getBaseFormat();
            //第一张工作表
            WritableSheet ws = wb.getSheet(0);
            for (int i = 0; i < enums.size(); i++) {
                EnumPrint enumPrint = enums.get(i);
                Label label = null;
                //给String 附值
                switch (enumPrint) {
                    case ENUM_1:
                        WritableFont font_title = new WritableFont(WritableFont.createFont("宋体"), 18, WritableFont.BOLD);
                        WritableCellFormat format_title = new WritableCellFormat(font_title);
                        format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format_title.setAlignment(Alignment.CENTRE);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_1.name, format_title);
                        ws.addCell(label);
                        break;
                    case ENUM_2:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_2.name, firstLineformat);
                        ws.addCell(label);
                        break;
                    case ENUM_3:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_3.name, firstLineformat);
                        ws.addCell(label);
                        break;
                    case ENUM_4:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_4.name, firstLineformat);
                        ws.addCell(label);
                        break;
                    case ENUM_5:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_5.name, firstLineformat);
                        ws.addCell(label);
                        break;
                    case ENUM_6:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_6.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_7:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_7.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_8:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_8.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_9:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_9.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_10:
                        WritableFont font10 = new WritableFont(WritableFont.createFont("宋体"), 8);
                        font10.setItalic(true);
                        WritableCellFormat format10 = new WritableCellFormat(font10);
                        format10.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format10.setAlignment(Alignment.CENTRE);
                        format10.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format10.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_10.name, format10);
                        ws.addCell(label);
                        break;
                    case ENUM_11:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_11.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_12:
                        WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 8);
                        font1.setItalic(true);
                        WritableCellFormat format1 = new WritableCellFormat(font1);
                        format1.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format1.setAlignment(Alignment.CENTRE);
                        format1.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format1.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_12.name, format1);
                        ws.addCell(label);
                        break;
                    case ENUM_13:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_13.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_14:
                        WritableFont font14 = new WritableFont(WritableFont.createFont("宋体"), 8);
                        font14.setItalic(true);
                        WritableCellFormat format14 = new WritableCellFormat(font14);
                        format14.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format14.setAlignment(Alignment.CENTRE);
                        format14.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format14.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_14.name, format14);
                        ws.addCell(label);
                        break;
                    case ENUM_15:
                        WritableFont font15 = new WritableFont(WritableFont.createFont("宋体"), 8);
                        font15.setItalic(true);
                        WritableCellFormat format15 = new WritableCellFormat(font15);
                        format15.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format15.setAlignment(Alignment.CENTRE);
                        format15.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format15.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_15.name, format15);
                        ws.addCell(label);
                        break;
                    case ENUM_16:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_16.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_17:
                        WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 11);
                        WritableCellFormat format2 = new WritableCellFormat(font2);
                        format2.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format2.setAlignment(Alignment.CENTRE);
                        format2.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format2.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_17.name, format2);
                        ws.addCell(label);
                        break;
                    case ENUM_18:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_18.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_19:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_19.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_20:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_20.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_21:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_21.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_22:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_22.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_23:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_23.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_24:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_24.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_25:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_25.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_26:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_26.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_27:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_27.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_28:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_28.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_29:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_29.name, format);
                        ws.addCell(label);
                        break;
                    case ENUM_30:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_30.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_31:
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_31.name, errorFormat);
                        ws.addCell(label);
                        break;
                    case ENUM_32:
                        WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 11);
                        WritableCellFormat format_summary = new WritableCellFormat(font3);
                        format_summary.setVerticalAlignment(VerticalAlignment.CENTRE);
                        format_summary.setAlignment(Alignment.LEFT);
                        format_summary.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format_summary.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_32.name, format_summary);
                        ws.addCell(label);
                        break;
                    case ENUM_33:
                        WritableFont font_stage = new WritableFont(WritableFont.createFont("宋体"), 11);
                        WritableCellFormat format_summary_stage = new WritableCellFormat(font_stage);
                        format_summary_stage.setVerticalAlignment(VerticalAlignment.TOP);
                        format_summary_stage.setAlignment(Alignment.LEFT);
                        format_summary_stage.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
                        format_summary_stage.setWrap(true);
                        label = new Label(enumPrint.column, enumPrint.row, EnumPrint.ENUM_33.name, format_summary_stage);
                        ws.addCell(label);
                        break;
                    default:
                        break;
                }
            }
            wb.write();
            wb.close();
            Toast.makeText(this, "打印完成", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void creatSheet() {
        InputStream inStream = null;
        try {
            inStream = getResources().getAssets().open("model.xls");
            File newFile = new File(fileName + ".xls");
            trueFileName = newFile.getAbsolutePath();
            FileUtils.writeFile(newFile, inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initEnums() {
        enums.add(EnumPrint.ENUM_1);
        enums.add(EnumPrint.ENUM_2);
        enums.add(EnumPrint.ENUM_3);
        enums.add(EnumPrint.ENUM_4);
        enums.add(EnumPrint.ENUM_5);
        enums.add(EnumPrint.ENUM_6);
        enums.add(EnumPrint.ENUM_7);
        enums.add(EnumPrint.ENUM_8);
        enums.add(EnumPrint.ENUM_9);
        enums.add(EnumPrint.ENUM_10);
        enums.add(EnumPrint.ENUM_11);
        enums.add(EnumPrint.ENUM_12);
        enums.add(EnumPrint.ENUM_13);
        enums.add(EnumPrint.ENUM_14);
        enums.add(EnumPrint.ENUM_15);
        enums.add(EnumPrint.ENUM_16);
        enums.add(EnumPrint.ENUM_17);
        enums.add(EnumPrint.ENUM_18);
        enums.add(EnumPrint.ENUM_19);
        enums.add(EnumPrint.ENUM_20);
        enums.add(EnumPrint.ENUM_21);
        enums.add(EnumPrint.ENUM_22);
        enums.add(EnumPrint.ENUM_23);
        enums.add(EnumPrint.ENUM_24);
        enums.add(EnumPrint.ENUM_25);
        enums.add(EnumPrint.ENUM_26);
        enums.add(EnumPrint.ENUM_27);
        enums.add(EnumPrint.ENUM_28);
        enums.add(EnumPrint.ENUM_29);
        enums.add(EnumPrint.ENUM_30);
        enums.add(EnumPrint.ENUM_31);
        enums.add(EnumPrint.ENUM_32);
        enums.add(EnumPrint.ENUM_33);
    }

}
