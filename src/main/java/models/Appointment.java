package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private String title;
    private Date myTime;
    private String hour;
    private String minute;

    public Appointment(String event, int day, int month, int year, String hour, String minute) throws ParseException {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        myTime = dateTimeFormat.parse(day + "/" + month + "/" + year + "  " + hour + ":" + minute);
        title = event;
        this.hour = hour;
        this.minute = minute;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String DateToStr = format.format(myTime);
        return  DateToStr;
    }

    public String getTime(){
        SimpleDateFormat formatT = new SimpleDateFormat("HH:mm");
        String time = formatT.format(myTime);
        return time;
    }

    @Override
    public String toString() {
        return "Event : " + title + " | Date : " + getDate()+" | Time: "+getTime();
    }

    public String getTitle() {
        return title;
    }

    public String getHour() {
        return hour;
    }
    public String getMinute(){
        return minute;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public void setMyTime(Date myTime) {
        this.myTime = myTime;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}