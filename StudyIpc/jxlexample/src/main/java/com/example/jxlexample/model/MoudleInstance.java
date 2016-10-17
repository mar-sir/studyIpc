package com.example.jxlexample.model;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * Created by huangcl on 2016/10/17.
 */
public class MoudleInstance {

    public static WritableCellFormat getBaseFormat() {
        WritableFont font_title = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
        WritableCellFormat format_title = new WritableCellFormat(font_title);
        try {
            format_title.setVerticalAlignment(VerticalAlignment.CENTRE);
            format_title.setAlignment(Alignment.CENTRE);
            format_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format_title;
    }

    public static WritableCellFormat getFirstLineFormat() {
        WritableCellFormat format = new WritableCellFormat();
        try {
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
            format.setAlignment(Alignment.LEFT);
            format.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
            format.setWrap(true);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    public static WritableCellFormat getNormalFormat() {
        WritableCellFormat format = new WritableCellFormat();
        try {
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
            format.setAlignment(Alignment.CENTRE);
            format.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
            format.setWrap(true);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

}
