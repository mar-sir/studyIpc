package com.example.jxlexample.enums;

/**
 * Created by huangcl on 2016/10/17.
 */
public enum EnumPrint {

    ENUM_1(0, 0, "ENUM_1"),

    ENUM_2(0, 1, "ENUM_2"),
    ENUM_3(2, 1, "ENUM_3"),
    ENUM_4(5, 1, "ENUM_4"),
    ENUM_5(8, 1, "ENUM_5"),

    ENUM_6(2, 3, "ENUM_6"),
    ENUM_7(5, 3, "ENUM_7"),
    ENUM_8(7, 3, "ENUM_8"),
    ENUM_9(8, 3, "ENUM_9"),
    ENUM_10(9, 3, "ENUM_10"),

    ENUM_11(2, 4, "ENUM_11"),
    ENUM_12(5, 4, "ENUM_12"),
    ENUM_13(7, 4, "ENUM_13"),
    ENUM_14(8, 4, "ENUM_14"),
    ENUM_15(9, 4, "ENUM_15"),


    ENUM_16(6, 3, "ENUM_16"),

    ENUM_17(2, 5, "ENUM_17"),
    ENUM_18(4, 6, "ENUM_18"),
    ENUM_19(4, 7, "ENUM_19"),

    ENUM_20(4, 8, "ENUM_20"),
    ENUM_21(4, 9, "ENUM_21"),
    ENUM_22(4, 10, "ENUM_22"),

    ENUM_23(4, 11, "ENUM_23"),
    ENUM_24(4, 12, "ENUM_24"),

    ENUM_25(4, 13, "ENUM_25"),
    ENUM_26(4, 14, "ENUM_26"),

    ENUM_27(2, 15, "ENUM_27"),
    ENUM_28(2, 16, "ENUM_28"),
    ENUM_29(2, 17, "ENUM_29"),
    ENUM_30(2, 18, "ENUM_30"),
    ENUM_31(2, 19, "ENUM_31"),
    ENUM_32(2, 20, "ENUM_32"),
    ENUM_33(2, 21, "ENUM_33");


    public String name;
    public int column;
    public int row;

    EnumPrint(int column, int row, String name) {
        this.column = column;
        this.name = name;
        this.row = row;
    }
}
