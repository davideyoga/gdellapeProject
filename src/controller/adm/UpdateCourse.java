package controller.adm;

import controller.BaseController;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 * Unico modo per aggiornare un corso da un anno precedente,
 * dato un corso con id passato tramite get, se il corso non e' di questo anno,
 * viene creato copiando tutti i suoi dati tranne l'anno e l'id,
 *
 */
public class UpdateCourse extends BaseController {

    /**
     * Controllo
     * @param request
     * @return
     */
    private boolean checkValue(HttpServletRequest request){




        return true;
    }



    /**
     * Controlli su validita' della sessione e permesso,
     * prendo l'id del corso passato come parametro get,
     * estatto tale corso controllo se e' di un anno precedente a questo,
     * oppure non e' stato riaggiornato,
     * prendo il corso con tale id, e lo carico nel datamodel
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pulisco messaggio
        datamodel.put("message",null);

        //se sessione valida
        if (this.sessionManager.isValid(request)) {

            //estraggo il servizio di aggiornare un corso
            Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
                    datamodel, getServletContext());

            //se l'utente in sessione possiede il servizio updateCourse...
            if (((List<Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {


                //controllo dei parametri get



                //se l'utente non possiede il servizio
            }else{

            }

            //se l'utente non ha una sessione valida
        }else{

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
