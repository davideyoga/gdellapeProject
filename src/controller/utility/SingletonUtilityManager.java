package controller.utility;

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
}
