package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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



}
