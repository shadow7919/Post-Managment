package ir.ac.kntu;

public class Date implements Comparable<Date> {
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
            int input = ScannerHelper.nextInt(31);
            if (this.month > 7 && input == 31) {
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

    private boolean isLeapYear(int year) {
        double a = 0.025;
        double b = 266;
        double leapDays0, leapDays1;
        int frac0, frac1;
        if (year > 0) {
            leapDays0 = ((year + 38) % 2820) * 0.24219 + a;  //0.24219 ~ extra days of one year
            leapDays1 = ((year + 39) % 2820) * 0.24219 + a;  //38 days is the difference of epoch to
            //2820-year cycle
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
        if (this.year != date.year) {
            return this.year - date.year;
        }
        if (this.month != date.month) {
            return this.month - date.month;
        }
        if (this.day != date.day) {
            return this.day - date.day;
        }
        if (this.hour != date.hour) {
            return this.hour - date.hour;
        }
        return this.minute - date.minute;
    }
}
