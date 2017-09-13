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
    private String repeat;

    private Date search;
    private boolean check;

    public Appointment(String event, int day, int month, int year, String hour, String minute,String repeat) throws ParseException {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        myTime = dateTimeFormat.parse(day + "/" + month + "/" + year + "  " + hour + ":" + minute);
        title = event;
        this.hour = hour;
        this.minute = minute;
        this.repeat = repeat;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(myTime);
        //return DateToStr;
        return  DateToStr.substring(0,3)+myTime.toString().substring(4,7)+DateToStr.substring(5,10);
    }

    public String getDates(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(myTime);
        return DateToStr;
    }
    public String getTime(){
        SimpleDateFormat formatT = new SimpleDateFormat("HH:mm");
        String time = formatT.format(myTime);
        return time;
    }

    @Override
    public String toString() {
        if(check){
            return "Event : " + title + " | Date : " + getSearch()+" | Time: "+getTime()+" | Repeat:"+repeat;
        }
        return "Event : " + title + " | Date : " + getDate()+" | Time: "+getTime()+" | Repeat:"+repeat;
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

    public Date getMyTime() {
        return myTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }


    public String getSearch() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String DateToStr = format.format(search);
        //return DateToStr;
        return  DateToStr.substring(0,3)+search.toString().substring(4,7)+DateToStr.substring(5,10);
    }

    public void setSearch(Date search) {
        this.search = search;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}