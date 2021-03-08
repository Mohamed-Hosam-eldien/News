package test.coding.hosam.newsapp.utils;

import org.ocpsoft.prettytime.PrettyTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Utils {


    public static String DateToTimeFormat(String oldstringDate){
        PrettyTime p = new PrettyTime(new Locale(getCountry()));
        String isTime ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(oldstringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isTime = p.format(date);

        return isTime;
    }

    public static String DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}