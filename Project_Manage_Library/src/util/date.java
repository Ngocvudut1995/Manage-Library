/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Dang
 */
public class date {
    public static String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
		* like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		trong java mm <> MM (mm: minutes - MM: Month)
		*/
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
    public static Date convertDatetoString(String date) throws ParseException{
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date d = new Date(df.parse(date).getTime());
         return d;
    }
    
}
