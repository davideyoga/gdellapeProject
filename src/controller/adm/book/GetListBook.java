package controller.adm.book;

import controller.BaseController;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 * Servlet che passa al template tutta la lista dei libri fino ad ora inseriti nel sistema
 *
 * PARAMETRI: non richiede nessun parametro
 */
public class GetListBook extends BaseController {


    /**
     * Metodo che estrae del db tutti i libri e li inserisce nel datamodel, dopo di che lancia il template list_book
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controllo sessione
        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio per aggiungere un libro
                Service addBook = this.getServiceAndCreate(request, response, ds, "addBook", "Permitted for add book at course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio updateCourse...
                if (((List<Service>) request.getSession().getAttribute("services")).contains(addBook)) {

                    //se non si ha il permesso

                }else{
                    this.processNotPermitted(request, response);
                }

            //se sessione non valida
            }else{

            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


    }

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }
}
