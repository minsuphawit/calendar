package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Appointment;
import models.ResourceAppoint;

import javax.swing.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Observable;

public class Controller {


    ResourceAppoint resourceAppoint = new ResourceAppoint();
    @FXML
    private TextField textField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox comboBoxHr;
    @FXML
    private ComboBox comboBoxMin;
    @FXML
    private Button buttonAdd;
    @FXML
    private ListView listView;
    ObservableList<String> hr = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
            "15","16","17","18","19","20","21","22","23");
    ObservableList<String> min = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
            "15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42",
            "43","44","45","46","47","48","49","50","51","52","53","54","56","57","58","59");

    @FXML
    private void  initialize(){
        comboBoxHr.setItems(hr);
        comboBoxMin.setItems(min);
        comboBoxMin.setValue("00");
        comboBoxHr.setValue("00");
        datePicker.setValue(LocalDate.now());

    }public void handleBtnAdd(ActionEvent e)throws  ParseException{
        //System.out.println("1");
        LocalDate time = datePicker.getValue();
        Appointment event =  new Appointment(textField.getText(),time.getDayOfMonth(),time.getMonthValue()
                    ,time.getYear(),comboBoxHr.getValue().toString(),comboBoxMin.getValue().toString());

        resourceAppoint.AddAppoint(event);

        System.out.println(event);
        System.out.println(resourceAppoint.getList());
        listView.setItems(resourceAppoint.getList());



    }



}
