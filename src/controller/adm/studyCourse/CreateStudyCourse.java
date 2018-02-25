package controller.adm.studyCourse;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.StudyCourseDao;
import model.Service;
import model.StudyCourse;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */
public class CreateStudyCourse extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Lancia il template di creazione di un corso di studi
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //cancello eventuale messaggio di errore
        datamodel.put("message", null);

        //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
        if(sessionManager.isHardValid(request)) {

            //estraggo il servizio di creazione dei corsi di studi
            Service createGroups = this.getServiceAndCreate(request,response,ds,"createStudyCourse","Service to create study course",datamodel, this.getServletContext());

            //se l'utente in sessione possiede il servizio
            if (((List<Service>) request.getSession().getAttribute("services")).contains(createGroups)) {

                //setto l'utente in sessione
                this.datamodel.put("user", sessionManager.getUser(request));

                //lancio il template di creazione utente
                TemplateController.process( "create_study_course.ftl", datamodel ,response, getServletContext() );


            } else {

                //lancio il messaggio di servizio non permesso
                this.processNotPermitted(request, response);

            }


            //se la sessione non e' valida
        }else{

            //setto la previous page e reindirizzo alla login
            createPreviousPageAndRedirectToLogin(request, response, "CreateStudyCourse");

        }

    }

    /**
     * Riceve i dati dalla form e inserisce il corso di studi dopo i dovuti controlli
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //cancello eventuale messaggio di errore
            datamodel.put("message", null);

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if(sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei corsi di studi
                Service createGroups = this.getServiceAndCreate(request,response,ds,"createStudyCourse","Service to create study course",datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio
                if (((List<Service>) request.getSession().getAttribute("services")).contains(createGroups)) {

                    //inizializo il dao dei corsi di studio
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //inizializzo un corso di studi
                    StudyCourse studyCourseByForm = studyCourseDao.getStudyCouse();

                    //riempio il corso di studi con i dati dalla form
                    studyCourseByForm = this.getStudyCourseByForm(request, studyCourseByForm, 0 );


                    //CONTROLLO SE I DATI NON SONO NULLI (non permetto che il codice possa essere 0)
                    //se il nome estratto e' vuoto, oppure se il nome estratto e' = a null oppure se il codice estratto e' = a 0
                    if( studyCourseByForm.getName() == null || studyCourseByForm.getName().equals("") ||
                            studyCourseByForm.getCode() == null || studyCourseByForm.getCode().equals("") ){

                        //aggiungo il messaggio di valori non esistenti
                        datamodel.put("message", "Error: Missing values or not correct" );

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio template con messaggi di errore
                        TemplateController.process("create_study_course.ftl", datamodel,response,getServletContext());
                        return;
                    }


                    //controllo se il codice inserito e il nome non esistano
                    //estraggo i corsi di studi con codice e nome uguale a quello modificato
                    StudyCourse studyCourseWithCode = studyCourseDao.getStudyCourseByCode(studyCourseByForm.getCode());

                    StudyCourse studyCourseWithName = studyCourseDao.getStudyCourseByName(studyCourseByForm.getName());

                    //se esiste un corso di studi con lo stesso codice o nome (questi due meotdi restituiscono null in caso di non esistenza di elementi con tali parametyri nel db)
                    if( studyCourseWithCode!=null || studyCourseWithName!=null ){

                        //se esiste un corso di studi con lo stesso codice
                        if( studyCourseWithCode != null ){

                            //inserisco messaggio di errore di codice esistente
                            datamodel.put("message", "Error: Existing Code. " );
                        }

                        if(studyCourseWithName != null){

                            //concateno al messaggio di prima(se esiste) il messaggio di errore di nome fia' esistente
                            datamodel.put("message", datamodel.get("message") + "Error: Existing Name." );
                        }

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio template con messaggi di errore
                        TemplateController.process("create_study_course.ftl", datamodel,response,getServletContext());
                        return;

                    }


                    //se non esistono corsi di studio con stesso codice e nome inserisco nel db il corso di studi
                    studyCourseDao.storeStudyCourse(studyCourseByForm);

                    //aggiungo un log di avvenuta creazione del corso di studi
                    logManager.addLog(sessionManager.getUser(request),"STUDY COURSES CREATED: " + studyCourseByForm.toStringForLog(), ds);


                    //inserisco il messaggio corso di studi creato
                    datamodel.put("message", "Study Course Created");

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio la pagina di creazione di un gruppo
                    TemplateController.process("create_study_course.ftl", datamodel, response, getServletContext());
                } else {

                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);

                }


                //se la sessione non e' valida
            }else{

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateStudyCourse");

            }


        }catch (DaoException e) {
            e.printStackTrace();
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
        } catch (LogException e) {
            e.printStackTrace();

            //inserisco il messaggio corso di studi creato ma di non avvenuto inserimento del log
            datamodel.put("message", "Study Course Created but not insert the log");

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //lancio la pagina di creazione di un gruppo
            TemplateController.process("create_study_course.ftl", datamodel, response, getServletContext());

            //commento a cazzo
        }

    }
}
