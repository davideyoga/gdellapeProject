package controller.utility;

import java.util.Calendar;

/**
 * Classe che rappresenta un anno accademico
 * @author Davide Micarelli
 */
public class AccademicYear {

    private int firstYear;

    private int secondYear;

    public AccademicYear( int firstYear ){

        this.firstYear = firstYear;
        this.secondYear = firstYear + 1;

    }

    public AccademicYear(Calendar calendar){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        //se il mese e' prima di settembre
        if (month < 9){

            //ritorna anno attuale - 1 / anno attuale
            this.firstYear = year - 1;
            this.secondYear = year;

            //se e' un mese maggiore o = a settembre
        }else{

            this.firstYear = year;
            this.secondYear = year + 1;

        }
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public int getSecondYear() {
        return secondYear;
    }

    public void setSecondYear(int secondYear) {
        this.secondYear = secondYear;
    }

    @Override
    public String toString() {
        return this.getFirstYear() + "/" + this.getSecondYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccademicYear that = (AccademicYear) o;

        if (getFirstYear() != that.getFirstYear()) return false;
        return getSecondYear() == that.getSecondYear();
    }

    @Override
    public int hashCode() {
        int result = getFirstYear();
        result = 31 * result + getSecondYear();
        return result;
    }
}
