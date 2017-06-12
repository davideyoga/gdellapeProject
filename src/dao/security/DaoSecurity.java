package dao.security;

import static java.util.Objects.isNull;


public class DaoSecurity {

    /**
     * questa funzione aggiunge un backslash davanti a
     * tutti i caratteri "pericolosi", usati per eseguire
     * SQL injection attraverso i parametri delle form
     * @param s stringa da modificare
     * @return stringa pulita
     */
    public static String addSlashes(String s) {

        if(isNull(s)){
            return "";
        }
        return s.replaceAll("(['\"\\\\])", "\\\\$1");
    }

    /**
     * questa funzione rimuove gli slash aggiunti da addSlashes
     * @param s stringa da modificare
     * @return stringa pulita
     */
    public static String stripSlashes(String s) {
        if(isNull(s)){
            return "";
        }

        return s.replaceAll("\\\\(['\"\\\\])", "$1");
    }

}
