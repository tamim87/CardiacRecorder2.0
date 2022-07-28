package com.example.cardicrecoder;

public class Recorditem {
    String Id;
    String heart_rate;
    String systolic_pressure;
    String diastolic_pressure;
    String status;
    String date;
    String time;
    String comment;

     public Recorditem()
        {

        }

    public Recorditem(String id, String heart_rate, String systolic_pressure, String diastolic_pressure, String status, String date, String time, String comment) {
        this.Id=id;
        this.heart_rate = heart_rate;
        this.systolic_pressure = systolic_pressure;
        this.diastolic_pressure = diastolic_pressure;
        this.status=status;
        this.date = date;
        this.time = time;
        this.comment = comment;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setSystolic_pressure(String systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public String getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public void setDiastolic_pressure(String diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
