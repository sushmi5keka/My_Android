package com.israt.mydailyexpense;

public class Expenses {

    private int id;
    private String type;
    private int amount;
    private String date;
    private String time;

    public Expenses(int id, String type, int amount, String date, String time) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


}
