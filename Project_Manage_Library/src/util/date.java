/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vu Dang
 */
public class date {
    public static String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
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
}
