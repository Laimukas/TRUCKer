package com.example.trucker.exceptions;

import com.example.trucker.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws TRUCKerListError {

//
        System.out.println("-----------------------------------------------------------------------------------------");
//
//        Date date = new Date();
//        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd,hh.mm.ss");
//        String stringDate= DateFor.format(date);
//        System.out.println(stringDate);
//        System.out.println("-----------------");
//        driverList.ListOfDriversName();
//
//        DriverList driverlist = new DriverList();
//        Driver naujas = new Driver("Pukuotukas","pukasas","1979-04-20","Rumsiskes","865554321","nezinomi","6543");
//        driverlist.addDriverToList(naujas);
//        driverlist.printDrivers();
//driverlist.ListOfDriversName();

//        UserList list = new UserList();
//        User check = new User("jonjon","9876");
//        list.getOneUser(check.getUserName(), check.getPassword());


//        DataClass data = new DataClass();
//
////        System.out.println(data.getDataNow());
//        String laikas = String.format(""+data.getDataNow());
//        System.out.println(laikas);
        //-----------------
//        String driver = "Saulius Sauliauskas";
//        String truck ="MAN 3251 KHD827";
//        String trailer = "Krone Tentine LK655";
//        GeneratorList list = new GeneratorList();
////        list.printLineUps();
//
//        list.getLineUpByOrdersId("2");

//        list.getListLineUpsWhoMachesDriverTruckTrailer(driver,truck,trailer);
//        list.printLineUps();
        //---------------------

//        OrderList list = new OrderList();
//        list.getOrderById("2");
//        list.getOrderById("3");

        FinishedOrderList list = new FinishedOrderList();
        list.printFinishedOrders();
        System.out.println("---------------------");
        System.out.println( "Order by Id 1 is:");
        System.out.println(list.getFinishedOrderById(1));


    }
}
