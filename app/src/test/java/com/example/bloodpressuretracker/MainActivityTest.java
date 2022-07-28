package com.example.bloodpressuretracker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivityTest {
    @Test
    public void testInsert(){
        Date date= Calendar.getInstance().getTime();
        ArrayList<User> list=new ArrayList<>();
        User user=new User("80","60","65","Test",date.toString(),"Normal","testinsert");
        list.add(user);
        assertEquals(1, list.size());

        User user1=new User("90","70","80","Test2",date.toString(),"Normal","testinsert");
        list.add(user1);
        assertEquals(2, list.size());
    }

}
