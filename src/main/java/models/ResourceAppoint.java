package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ResourceAppoint {
    private ObservableList<Appointment> listAppoint = FXCollections.observableArrayList();
    public void AddAppoint(Appointment ap){
        listAppoint.add(ap);
    }
    public ObservableList getList(){
        return listAppoint;
    }

    public void setListAppoint(ObservableList<Appointment> listAppoint) {
        this.listAppoint = listAppoint;
    }
    public void remove(Appointment appointment){
        listAppoint.remove(appointment);
    }
    public ObservableList<Appointment> searchAppoint(LocalDate date){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        for(int i = 0; i < listAppoint.size();i++){
            String da = listAppoint.get(i).getDates();
            LocalDate d = LocalDate.parse(da.substring(6,10)+"-"+da.substring(3,5)+"-"+da.substring(0,2));
            if(!listAppoint.get(i).getRepeat().equals("None")){
                if(listAppoint.get(i).getRepeat().equals("Daily")){
                    setD(date,listAppoint.get(i));
                    list.add(listAppoint.get(i));

                }else if(listAppoint.get(i).getRepeat().equals("Weekly")){
                    if(d.getDayOfWeek() == date.getDayOfWeek()) {
                        if(d.getDayOfMonth()!= date.getDayOfMonth()){
                            setD(date, listAppoint.get(i));
                            }
                        list.add(listAppoint.get(i));
                    }

                }else if(listAppoint.get(i).getRepeat().equals("Monthly")){
                    if(d.getDayOfMonth() == date.getDayOfMonth()) {
                        if (d.getMonthValue() != date.getMonthValue()) {
                            setD(date, listAppoint.get(i));
                        }
                        list.add(listAppoint.get(i));
                    }
                }

            }
        }
        return list;
    }

    public void setD(LocalDate date,Appointment ap){
        try {
            ap.setCheck(true);
            DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
            ap.setSearch(dateTimeFormat.parse(date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear()+" "+ap.getTime()));
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    public void reSet(){
        for(int i = 0 ; i < listAppoint.size() ; i++){
            listAppoint.get(i).setCheck(false);
        }
    }


}
