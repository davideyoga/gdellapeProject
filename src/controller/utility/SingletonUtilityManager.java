package controller.utility;

import dao.exception.DaoException;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.ServiceDao;
import model.Course;
import model.Service;
import model.User;
import view.TemplateController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

/**
 * @author Davide Micarelli
 */
public class SingletonUtilityManager implements UtilityManager {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final int PASSWORD_LENGTH = 6;

    private static SingletonUtilityManager utilityManager;

    private SingletonUtilityManager(){}

    /**
     * Singleton constructor
     */
    public static SingletonUtilityManager getUtilityManager(){

        if(utilityManager == null){
            utilityManager = new SingletonUtilityManager();
        }

        return utilityManager;
    }


    @Override
    public void removePassword(User user) {
        user.setPassword(null);
    }

    @Override
    public void removePassword(List <User> users) {
        for (User curr : users){
            curr.setPassword(null);
        }
    }

    /**
     * Controlla tramite regular expression la validita' della email
     * @param email
     * @return
     */
    @Override
    public boolean isCorrectEmail(String email) {

        if (email.matches(EMAIL_PATTERN)) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getPasswordLength() {
        return PASSWORD_LENGTH;
    }


    /**
     *
     * Data un'istanza di calendar, mi restituisce l'anno accademico corrispondente
     * @param calendar
     * @return
     */
    @Override
    public String getCurrentAccademicYear(Calendar calendar) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        String risultato = null;

        //se il mese e' prima di settembre
        if (month < 9){

            //ritorna anno attuale - 1 / anno attuale
            return year - 1 + "/" + year;

            //se e' un mese maggiore o = a settembre
        }else{
            return year + "/" + year + 1;
        }
    }

    /**
     * Torna la differenza annop di corso - anno attuale
     * @param course
     * @param accademicAge
     * @return
     */
    @Override
    public int getDifferenceByAccademicAge(Course course, String accademicAge) {

        //estraggo l'anno iniziale e l'anno attuale
        int courseYear = Integer.parseInt( course.getYear().substring(0, 3) );
        int actualYear = Integer.parseInt(accademicAge.substring(0, 3));

        return courseYear - actualYear;
    }

    @Override
    public String getAccademicYearByYear(int year) {

        return year + "/" + year + 1;

    }

    @Override
    public int getFirstParameterAccademicYear(String accademicYear) {

        return Integer.parseInt( accademicYear.substring(0,3) );

    }

    /**
     * codifica la stringa in sha1
     * @param x stringa da codificare
     * @return sha1(x) oppure null
     */
    public String sha1Encrypt(String x) {
        if(isNull(x) || x.equals("")){
            Logger.getAnonymousLogger().log(Level.WARNING, "SecurityException: parametro null" );
            return null;
        }
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(x.getBytes("UTF-8"), 0, x.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
            sha1=sha1.toLowerCase();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "SecurityException: " + e.getMessage());
        }
        return sha1;
    }

    /**
     * Torna oggetti presenti nella lista a e non presenti nella lista b
     * Per non stravolgere tutto gli passo una lista di utenti invece che un oggetto iterabile
     * @param a
     * @param b
     * @return
     */
    @Override
    public List<User> getContentInAButNotInB(List<User> a, List<User> b){

        List<User> list = new ArrayList <>();

        //ciclo lista a
        for( User o : a){

            //se b non contiene l'oggetto o
            if(!b.contains(o)){
                list.add(o);
            }

        }

        return  list;
    }


}
