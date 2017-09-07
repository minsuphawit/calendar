package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;

public class Database {
    private Connection conn;
    public Database(){
        try{
           Class.forName("org.sqlite.JDBC") ;
           String dbURL = "jdbc:sqlite:calender.db";
           conn = DriverManager.getConnection(dbURL);
           if(conn != null){
               System.out.println("Connect to Database!!");
           }
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public ObservableList<Appointment> readFile(){
        ObservableList<Appointment> listAppoint = FXCollections.observableArrayList();
        try {


            String query = "Select * from Appointment";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String event  = resultSet.getString(1);
                String date = resultSet.getString(2);
                String time = resultSet.getString(3);
                String month =  date.substring(0,2);
                String day = date.substring(3,5);
                String year = date.substring(6,8);
                String hour = time.substring(0,2);
                String min = time.substring(3,5);
//                System.out.println("month: "+month);
//                System.out.println("day: "+day);
//                System.out.println("year: "+year);
//                System.out.println("hour: "+hour);
//                System.out.println("min: "+min);


                listAppoint.add(new Appointment(event,Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year),hour,min));

               // System.out.println("Event: " +event+" Date: "+date+" Time: "+time);

            }


        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        catch (ParseException ex){
            ex.printStackTrace();
        }
        return  listAppoint;
    }
    public void Remove(Appointment appointment){
        try{
            String query = "Delete from Appointment where Event = '"+appointment.getTitle()+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        //readFile();
    }

    public void addData(Appointment appointment){
        try{
            String query = "insert into Appointment (Event,'Date','Time') values ("+"'"+appointment.getTitle()+"','"+appointment.getDate()
                    +"','"+appointment.getTime()+"');";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void editData(Appointment appointment , String title){
        String query = "update Appointment set Event = '"+appointment.getTitle()+"', 'Date' = '"+appointment.getDate()+
                "', 'Time' = '"+appointment.getTime()+"' where Event = '"+title+"'";
        try{

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
