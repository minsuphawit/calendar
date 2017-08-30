package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private String title;
    private Date myTime;

    public Appointment(String event, int day, int month, int year, String hour, String minute) throws ParseException {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        myTime = dateTimeFormat.parse(day+"/"+month+"/"+year+"  "+hour+":"+minute);
        title = event;
    }
    public String getDate(){
        String strDate = DateFormat.getInstance().format(myTime);
        return strDate;
    }

    @Override
    public String toString() {
        return "Event : "+title +" | Date&Time : "+getDate();
    }
}