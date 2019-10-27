package com.currency.thaibath;

import java.math.BigDecimal;
import java.text.DecimalFormat;

class ConvertCurrency {

    private double money;

    public ConvertCurrency(double money){
        this.money = money;
    }

    public ConvertCurrency(){}

    public String getBathText(){

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String stringFormat = decimalFormat.format(this.money);

        int index = stringFormat.indexOf(".");

        String bathString = "บาท";
        String bathOnlyString = "บาทถ้วน";

        long bath = Long.parseLong(stringFormat.substring(0, index));
        int satang = Integer.parseInt(stringFormat.substring(index + 1));

        if (stringFormat.endsWith(".00")){
            return convert(convertCurrency(bath)).concat(bathOnlyString);
        }else {
            String satangString = "สตางค์";
            return convert(convertCurrency(bath)).concat(bathString)
                    .concat(convert(convertCurrency(satang))).concat(satangString);
        }
    }

    private String convert(String str) {
        StringBuilder sb=new StringBuilder(str);

        int len=sb.length()+1;

        String one = "หนึ่ง";
        if(sb.length()>5 && sb.substring(sb.length()-5,sb.length()).equals(one)){
            String oneString = "เอ็ด";
            sb.replace(len-6, len, oneString);
        }

        String oneTen = "หนึ่งสิบ";
        if(sb.lastIndexOf(oneTen) != -1){
            sb.delete(sb.lastIndexOf(oneTen), sb.lastIndexOf(oneTen) + 5);
        }

        String twoTen = "สองสิบ";
        if(sb.indexOf(twoTen) != -1){
            String twoString = "ยี่สิบ";
            do{
                sb.indexOf(twoTen);
                sb.replace(sb.indexOf(twoTen),sb.indexOf(twoTen)+6, twoString);
            }while(sb.indexOf(twoTen) > -1);
        }

        String oneTenOne = "หนึ่งสิบหนึ่ง";
        String oneTenOneString = "สิบเอ็ด";
        if(sb.indexOf(oneTenOne) != -1){
            do {
                sb.indexOf(oneTenOne);
                sb.replace(sb.indexOf(oneTenOne),sb.indexOf(oneTenOne) + 13, oneTenOneString);
            } while(sb.indexOf(oneTenOne) > -1);
        }

        String one_ten = "สิบหนึ่ง";
        if (sb.indexOf(one_ten) != -1){
            do {
                sb.indexOf(one_ten);
                sb.replace(sb.indexOf(one_ten), sb.indexOf(one_ten) + 8, oneTenOneString);
            } while (sb.indexOf(one_ten) > -1);
        }
        return sb.toString();
    }

    private String convertCurrency(long money) {

        String[] digit = {"", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า"};

        if (money < 10){
            return digit[(int)money];
        }

        if (money < 100) {
            String ten = "สิบ";
            return convertCurrency( money / 10).concat(ten).concat(convertCurrency(money % 10));
        }

        if (money < 1000){
            String hundred = "ร้อย";
            return convertCurrency( money / 100).concat(hundred).concat(convertCurrency(money % 100));
        }

        if (money < 10000){
            String thousand = "พัน";
            return convertCurrency( money / 1000).concat(thousand).concat(convertCurrency(money % 1000));
        }

        if (money < 100000){
            String tenThousand = "หมื่น";
            return convertCurrency( money / 10000).concat(tenThousand).concat(convertCurrency(money % 10000));
        }

        if (money < 1000000){
            String hundredThousand = "แสน";
            return convertCurrency(money / 100000).concat(hundredThousand).concat(convertCurrency(money % 100000));
        }
        String million = "ล้าน";
        return convertCurrency(money / 1000000).concat(million).concat(convertCurrency(money % 1000000));
    }
}
