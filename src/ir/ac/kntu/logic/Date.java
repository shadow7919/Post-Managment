package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerHelper;

import java.io.Serializable;

public class Date implements Comparable<Date>, Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(boolean haveTime) {
        setDate();
        if (haveTime) {
            setTime();
        }
    }

    private void setDate() {
        System.out.print("Enter Year : ");
        this.year = ScannerHelper.nextInt();
        System.out.print("Enter Month : ");
        this.month = ScannerHelper.nextInt(12);
        System.out.print("Enter Day : ");
        this.day = setDay();
    }

    private void setTime() {
        while (true) {
            System.out.print("Enter Hour : ");
            this.hour = ScannerHelper.nextNonNegativeInt();
            if (hour < 24) {
                break;
            } else {
                System.out.println("maximum hour is 12");
            }
        }
        while (true) {
            System.out.print("Enter Minute : ");
            minute = ScannerHelper.nextNonNegativeInt();
            if (minute <= 60) {
                break;
            } else {
                System.out.println("maximum minute is 60");
            }
        }
    }

    private int setDay() {
        while (true) {
            int input = ScannerHelper.nextInt();
            if (this.month < 7 && input > 31) {
                System.out.println("In first half of year maximum day is 31");
                continue;
            }
            if (this.month >= 7 && input > 30) {
                System.out.println("In second half of year maximum day is 30");
                continue;
            }
            if ((this.month == 12 && isLeapYear(this.year)) && input == 30) {
                System.out.println("in leap year at at last month you only have 29 day");
                continue;
            }
            return input;
        }
    }

    public static long howManyDays(Date date) {
        long number = 0;
        number += (date.year - 1) / 4;
        number += (date.year - 1) * 365;
        if (date.month < 7) {
            number += (date.month - 1) * 31;
        } else {
            number += (6 * 31) + ((date.month - 7) * 30);
        }
        number += date.day;
        return number;
    }

    public static int compareDates(Date first, Date second) {
        return (int) (howManyDays(first) - howManyDays(second));
    }

    private boolean isLeapYear(int year) {
        double a = 0.025;
        double b = 266;
        double leapDays0, leapDays1;
        int frac0, frac1;
        if (year > 0) {
            leapDays0 = ((year + 38) % 2820) * 0.24219 + a;
            leapDays1 = ((year + 39) % 2820) * 0.24219 + a;
        } else if (year < 0) {
            leapDays0 = ((year + 39) % 2820) * 0.24219 + a;
            leapDays1 = ((year + 40) % 2820) * 0.24219 + a;
        } else {
            return false;
        }

        frac0 = (int) ((leapDays0 - (int) (leapDays0)) * 1000);
        frac1 = (int) ((leapDays1 - (int) (leapDays1)) * 1000);

        return frac0 <= b && frac1 > b;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day + "\t" +
                String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    @Override
    public int compareTo(Date date) {
        if (compareDates(this, date) != 0) {
            return compareDates(this, date);
        }
        if (this.hour != date.hour) {
            return this.hour - date.hour;
        }
        return this.minute - date.minute;
    }
}
