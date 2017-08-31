package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceAppointTest {
    @Test
    public void addAppoint() throws Exception {
        ResourceAppoint ra = new ResourceAppoint();
        Appointment ap = new Appointment("WatchTv",1,7,2016,"17","20");
        ra.AddAppoint(ap);
        assertEquals(ap,ra.getList().get(0));
    }


}