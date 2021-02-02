
import java.time.DayOfWeek;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mac
 */
public class testAddDate {
    public static void main(String [] args){
        String date = "2022-05-31";
        int days = 60;

        //default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(date);
        
        localDate = add(localDate, days);
        
        System.out.println(localDate);
    
    }
    
    public static LocalDate add(LocalDate date, int workdays) {
    if (workdays < 1) {
        return date;
    }

    LocalDate result = date;
    int addedDays = 0;
    while (addedDays < workdays) {
        result = result.plusDays(1);
        if (result.getDayOfWeek() != DayOfWeek.SUNDAY) {
            ++addedDays;
        }
    }

    return result;
}
}
