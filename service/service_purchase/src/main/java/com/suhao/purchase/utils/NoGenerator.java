package com.suhao.purchase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NoGenerator {

    public static String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
