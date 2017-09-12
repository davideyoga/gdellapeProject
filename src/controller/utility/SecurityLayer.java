package controller.utility;

/**
 * @author Davide Micarelli
 */
public class SecurityLayer {

    /**
     * @param s
     * @return
     * @throws NumberFormatException
     */
    public static int checkNumeric(String s) throws NumberFormatException {
        //convertiamo la stringa in numero, ma assicuriamoci prima che sia valida
        //convert the string to a number, ensuring its validity
        if (s != null) {
            //se la conversione fallisce, viene generata un'eccezione
            //if the conversion fails, an exception is raised
            return Integer.parseInt(s);
        } else {
            throw new NumberFormatException("String argument is null");
        }
    }

}
