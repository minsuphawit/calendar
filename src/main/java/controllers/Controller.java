package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import models.Appointment;
import models.Database;
import models.ResourceAppoint;
import javafx.scene.input.MouseEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Controller {


    ResourceAppoint resourceAppoint ;
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
    private Database database;
    private Appointment edit;
    private boolean checkE;


    public Controller(){
        resourceAppoint = new ResourceAppoint();
        database = new Database();
        resourceAppoint.setListAppoint(database.readFile());
        //System.out.println(resourceAppoint.getList());
        //database.readFile();
//        try {
//            Appointment ap = new Appointment("WatchTv",1,7,2016,"17","20");
//            System.out.println(ap.getDate());
//        }catch (ParseException e){
//            e.printStackTrace();
//        }

    }


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
        listView.setItems(resourceAppoint.getList());
        ContextMenu cm = new ContextMenu();
        MenuItem menu1 = new MenuItem("Edit");
        cm.getItems().add(menu1);
        MenuItem menu2 = new MenuItem("Delete");
        cm.getItems().add(menu2);
        menu1.setOnAction(event -> edit());
        menu2.setOnAction(event -> delete());
        listView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton()== MouseButton.SECONDARY){
                    cm.show(listView,event.getScreenX(),event.getScreenY());
                }
                else{
                    cm.hide();
                }

            }
        });
    }public void handleBtnAdd(ActionEvent e)throws  ParseException{
        LocalDate time = datePicker.getValue();
        if(checkE){
            String t = edit.getTitle();
            DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            edit.setMyTime(dateTimeFormat.parse( time.getDayOfMonth()+ "/" + time.getMonthValue() + "/" + time.getYear() + "  " + comboBoxHr.getValue().toString() + ":" + comboBoxMin.getValue().toString()));
            edit.setTitle(textField.getText());
            edit.setHour(comboBoxHr.getValue().toString());
            edit.setMinute(comboBoxMin.getValue().toString());
            database.editData(edit,t);
            checkE = false;

        }else {
            //System.out.println("1");

            Appointment event = new Appointment(textField.getText(), time.getDayOfMonth(), time.getMonthValue()
                    , time.getYear(), comboBoxHr.getValue().toString(), comboBoxMin.getValue().toString());

            resourceAppoint.AddAppoint(event);
            database.addData(event);
            //System.out.println(event);
            //System.out.println(resourceAppoint.getList());


        }
        listView.setItems(resourceAppoint.getList());
        resetField();
        listView.refresh();

    }

    public void edit(){


        edit = (Appointment) listView.getSelectionModel().getSelectedItem();
        if(edit != null){
            checkE = true;
            textField.setText(edit.getTitle());
            if(edit.getHour().length() == 1){
                comboBoxHr.setValue("0"+edit.getHour());
            }else{
                comboBoxHr.setValue(edit.getHour());
            }
            comboBoxMin.setValue(edit.getMinute());

            //LocalDate dateEdit = LocalDate.parse(select.getDate().substring(6,10)+"-"+select.getDate().substring(0,2)+"-"+select.getDate().substring(3,5));
            //datePicker.setValue(dateEdit);

        }

    }

    public void delete(){
        Appointment select = (Appointment) listView.getSelectionModel().getSelectedItem();
        if(select != null){
            resourceAppoint.remove(select);
            database.Remove(select);

        }

    }

    public void resetField(){
        textField.setText("");
        comboBoxHr.setItems(hr);
        comboBoxMin.setItems(min);
        comboBoxMin.setValue("00");
        comboBoxHr.setValue("00");
        datePicker.setValue(LocalDate.now());
    }






}
