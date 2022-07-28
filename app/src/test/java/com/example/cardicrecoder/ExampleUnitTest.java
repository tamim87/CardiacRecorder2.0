package com.example.cardicrecoder;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Calendar calendar;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testaddrecord(){
        ArrayList<Recorditem> recorditems = new ArrayList<>();
        calendar= Calendar.getInstance();
        String date=new java.text.SimpleDateFormat("dd-MMM-yyyy").format(calendar.getTime());
        String time=new java.text.SimpleDateFormat("hh:mm a").format(calendar.getTime());
        Recorditem record=new Recorditem( "afgagawgaw","120","120","80","Normal pressure",date,time,"After Dinner");
        recorditems.add(record);
        assertEquals(1,recorditems.size());
        assertTrue(recorditems.contains(record));
    }

    @Test public voidtestdelete()
    {

        
    }

}

