package com.amsolver.semente.utilities;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {

    @SuppressLint("SimpleDateFormat")
    public static String convertFromDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date convertFromStringToDate(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(stringDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getTodayToShow() {
        Date today = new Date();
        String todayStr = convertFromDateToString(today);
        String[] parts = todayStr.split("/");
        String month = parts[1];
        String day = parts[0];
        String result = day + " de " + getMonthNameByNumber(month);
        return result;
    }

    public static String getMonthNameByNumber(String month) {
        if (month.equals("01")) {
           return "Xaneiro";
        } else if (month.equals("02")) {
            return "Febreiro";
        } else if (month.equals("03")) {
            return "Marzo";
        } else if (month.equals("04")) {
            return "Abril";
        } else if (month.equals("05")) {
            return "Maio";
        } else if (month.equals("06")) {
            return "Xuño";
        } else if (month.equals("07")) {
            return "Xullo";
        } else if (month.equals("08")) {
            return "Agosto";
        } else if (month.equals("09")) {
            return "Setembro";
        } else if (month.equals("10")) {
            return "Outubro";
        } else if (month.equals("11")) {
            return "Novembr8";
        } else if (month.equals("12")) {
            return "Decembro";
        } else {
            return "";
        }
    }

}
