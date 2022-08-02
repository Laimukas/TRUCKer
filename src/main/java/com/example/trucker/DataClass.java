package com.example.trucker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataClass {

    public String getDataNow(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd,hh.mm.ss");
        String stringDate= DateFor.format(date);
//        System.out.println(stringDate);
        return stringDate;

    }


}
