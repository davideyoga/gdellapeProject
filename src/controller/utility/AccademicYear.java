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

        System.out.println("toString: " + this.toString());

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

    /**
     * Costruttore dell'anno accademico passato come stringa
     * @param accademicYear
     * @throws AccademicYearException
     */
    public AccademicYear(String accademicYear) throws AccademicYearException{

        System.out.println("accademicYear:" + accademicYear);

        try {
            //estraggo il primo anno (primi 4 caratteri seguiti da uno /)
            //non uso espressione regolare per la semplicita' dell'operazione

            String firstYear = new String();

            for (int x = 0; x < 4; x++) {

                firstYear = firstYear + accademicYear.charAt(x);

            }

            String secondYear = new String();

            for (int x = 5; x < 9; x++) {

                secondYear = secondYear + accademicYear.charAt(x);
            }

            //controllo se la stringa passata corrisponde ad un anno accademico
            if (Integer.parseInt(firstYear) == Integer.parseInt(secondYear) - 1 && accademicYear.length() == 9 &&
                    accademicYear.charAt(4) == '/') {

                System.out.println("firsYear: " + firstYear);

                this.firstYear = Integer.parseInt(firstYear);
                this.secondYear = Integer.parseInt(secondYear);

            } else {
                throw new AccademicYearException("Error in AccademicYear(String accademicYear)");
            }


            //se i primi e gli ultimi 4 caratteri non sono numeri
        }catch (NumberFormatException e){
            throw  new AccademicYearException("Error in AccademicYear(String accademicYear)", e);
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
