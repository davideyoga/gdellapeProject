package controller.adm;

import controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 * Unico modo per aggiornare un corso da un anno precedente,
 * dato un corso con id passato tramite get, se il corso non e' di questo anno,
 * viene creato copiando tutti i suoi dati tranne l'anno e l'id,
 *
 */
public class UpdateCourse extends BaseController {


    /**
     * Controlli su validita' della sessione e permesso,
     * prendoo l'id del corso passato come parametro get,
     * estatto tale corso controllo se e' di un anno precedente a questo,
     * oppure non e' stato riaggiornato,
     * prendo il corso con tale id, e lo carico nel datamodel
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
