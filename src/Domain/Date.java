package Domain;

import java.io.Serializable;

public class Date implements Serializable {
    protected int day = 0;
    protected int month = 0;

    public Date(int day, int month) {
        if (month < 0 || month > 12) {
            throw new IllegalArgumentException("Invalid month");
        }
        if (day < 0 || day > 31) {
            throw new IllegalArgumentException("Invalid day");
        }
        this.day = day;
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day");
        }
        this.day = day;
    }
    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month");
        }
        this.month = month;
    }

    @Override
    public String toString() {
        return day + "." + month;
    }

    public boolean isEmpty() {
        return day == 0 && month == 0;
    }
}
