package api.carrito.compras.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * FormatDates class
 *
 * @author edwin.lopezb.1297
 * @project shoppingcart
 * @since v1.0.0 - aug. 2021
 */

public class FormatDates {

    private FormatDates() {
    }

    /**
     * Instant to string string.
     *
     * @param instant the instant
     * @return the string
     */
    public static String instantToString(Instant instant) {
        Date date = Date.from(instant);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * Values to simple date date.
     *
     * @param month the month
     * @param year  the year
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date valuesToMonthAndYearDate(String month, String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
        return formatter.parse(month + "-" + year);
    }

    /**
     * Values to date date.
     *
     * @param month the month
     * @param year  the year
     * @param day   the day
     * @return the date
     * @throws ParseException the parse exception
     */
    public static Date valuesToDate(String month, String year, String day) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(day + "-" + month + "-" + year);
    }

}
