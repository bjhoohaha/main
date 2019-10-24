package seedu.address.calendar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DateUtil {
    private static int FIRST_DAY_OF_MONTH = 1;

    /* The following deals with more specific month-related methods. */

    public static List<Day> getDaysOfMonth(MonthOfYear monthOfYear, Year year) {
        int monthOfYearAsInt = monthOfYear.getNumericalVal();
        int yearAsInt = year.getNumericalValue();
        int firstDayOfWeekAsNum = findFirstDayOfWeekAsNum(monthOfYearAsInt, yearAsInt);
        int daysInMonth = monthOfYear.getNumDaysInMonth(year);
        List<Day> daysOfMonth = new ArrayList<>();

        IntStream.range(0, daysInMonth)
                .mapToObj(dayOfMonth -> getDayGivenFirstDayOfWeek(firstDayOfWeekAsNum, dayOfMonth))
                .forEach(day -> daysOfMonth.add(day));
        return daysOfMonth;
    }

    public static Day getDay(int dayOfMonthOneBased, MonthOfYear monthOfYear, Year year) {
        int monthOfYearAsInt = monthOfYear.getNumericalVal();
        int yearAsInt = year.getNumericalValue();
        int firstDayOfWeekAsNum = findFirstDayOfWeekAsNum(monthOfYearAsInt, yearAsInt);
        int dayOfMonth = dayOfMonthOneBased - 1;
        return getDayGivenFirstDayOfWeek(firstDayOfWeekAsNum, dayOfMonth);
    }

    private static Day getDayGivenFirstDayOfWeek(int firstDayOfWeekAsNum, int dayOfMonth) {
        int dayOfWeekAsNum = (firstDayOfWeekAsNum + dayOfMonth) % 7;
        DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekAsNum);
        return Day.getOneBased(dayOfWeek, dayOfMonth);
    }

    /**
     * Computes which day (of week) {@code} month starts on
     * @return day (of week) {@code this} month starts on
     */
    private static int findFirstDayOfWeekAsNum(int monthOfYear, int year) {
        int monthNumerical = monthOfYear;
        int zellerMonth = findZellerMonth(monthNumerical);
        int zellerYear = findZellerYear(zellerMonth, year);
        int zellerCentury = zellerYear / 100;
        int lastTwoDigitsOfYear = zellerYear % 100;

        // use Zeller's formula
        int day = ((FIRST_DAY_OF_MONTH + (13 * zellerMonth - 1) / 5 + lastTwoDigitsOfYear + (lastTwoDigitsOfYear / 4)
                + (zellerCentury / 4) - 2 * zellerCentury)) % 7;
        int positiveDay = day < 0 ? (day + 7) : day;

        return positiveDay;
    }

    /**
     * Computes the numerical value of {@code this} month such that it can be easily used with Zeller's rule.
     *
     * @param monthNumerical numerical representation of {@code this} month
     * @return numerical value of {@code this} month such that it can be easily used with Zeller's rule
     */
    private static int findZellerMonth(int monthNumerical) {
        int shiftedMonth = ((monthNumerical - 2) + MonthOfYear.getNumMonthsInYear()) % MonthOfYear.getNumMonthsInYear();
        return shiftedMonth;
    }

    /**
     * Computes the year such that it can be easily used with Zeller's rule.
     *
     * @param zellerMonth {@code this} month such that it can be easily used with Zeller's rule
     * @return year such that it can be easily used with Zeller's rule.
     */
    private static int findZellerYear(int zellerMonth, int year) {
        return zellerMonth > 10 ? (year - 1) : year;
    }
}
