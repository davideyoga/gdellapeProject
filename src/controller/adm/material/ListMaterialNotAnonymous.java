package controller.adm.material;

import controller.BaseController;
import controller.utility.SecurityLayer;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.MaterialDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.MaterialDao;
import model.Course;
import model.Material;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Restituisce la lista del materiale,
 * questa servlet lancia il template che permette di aggiungere ed eliminare materiale
 *
 * @author Davide Micarelli
 */
public class ListMaterialNotAnonymous extends BaseController {


    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, Course course, List<Material> materials) throws ServletException, IOException {

        //inserisco nel datamodel il corso e il materiale
        datamodel.put("course", course);
        datamodel.put("material", materials);

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template
        TemplateController.process("list_material_not_anonymous.ftl", datamodel, response, getServletContext());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //controlli su sessione
        //controllo se puo' modificare tutti i corsi oppure il corso gli appartiene

        try {

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {


                //estraggo il servizio di aggiunta del materiale ad un solo corso
                Service addMaterial = this.getServiceAndCreate(request, response, ds, "addMaterial", "Permission to add material to a course",
                        datamodel, getServletContext());

                //estraggo il servizio di aggiunta del materiale per tutti i corsi
                Service addMaterialAllCourse = this.getServiceAndCreate(request, response, ds, "addMaterialAllCourse", "Permission to add material in all course",
                        datamodel, getServletContext());


                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                Course courseById = null;

                if (request.getParameter("id") != null) {

                    courseById = courseDao.getCourseById(SecurityLayer.checkNumeric(request.getParameter("id")));
                }


                //se il corso esiste
                if (courseById != null) {

                    //controllo se l'utente puo' modificare tutti i corsi o quel corso gli appartiene
                    if ( ((List <Service>) request.getSession().getAttribute("services")).contains(addMaterialAllCourse) ||
                            (  courseDao.getCoursesByUser(sessionManager.getUser(request)).contains(courseById) &&
                                    ((List <Service>) request.getSession().getAttribute("services")).contains(addMaterial)) ) {

                        //inizializzo il dao del materiale
                        MaterialDao materialDao = new MaterialDaoImpl(ds);
                        materialDao.init();

                        //estraggo tutto il materiale inerente al corso
                        List <Material> materials = materialDao.getMaterialByCourse(courseById);

                        //chiudo i dao
                        materialDao.destroy();
                        courseDao.destroy();

                        this.processTemplate(request, response, courseById, materials);


                        //se non ha il permesso di modificare il corso
                    } else {

                        courseDao.destroy();

                        response.sendRedirect("NotPermitted");

                    }

                    //se il corso non esiste
                }else{

                    this.processError(request, response);
                }

                //se sessione non valida
            }else{

                //reindirizzo al login e subito dopo alla lista dei corsi
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        } catch (DaoException e) {
            e.printStackTrace();

            this.processError(request, response);
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
}
