package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.MaterialDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.MaterialDao;
import model.Course;
import model.Material;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Restituisce la lista del materiuale,
 * questa servlet lancia il template che permette di aggiungere ed eliminare materiale
 *
 * @author Davide Micarelli
 */
public class ListMaterialNotAnonimus extends BaseController {


    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, Course course, List<Material> materials) throws ServletException, IOException {

        //inserisco nel datamodel il corso e il materiale
        datamodel.put("course", course);
        datamodel.put("material", materials);

        //lancio il template
        TemplateController.process("list_material.ftl", datamodel, response, getServletContext());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //inizializzo il dao dei corsi
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            //estraggo il corso a seconda del codice passato come parametro get
            String code = request.getParameter("code");

            System.out.println("code: " + code);

            //estraggo il corso con tale codice
            Course course = courseDao.getCourseByCode(code);

            //se il corso esiste
            if(course != null){

                //inizializzo il dao del materiale
                MaterialDao materialDao = new MaterialDaoImpl(ds);
                materialDao.init();

                //estraggo tutto il materiale inerente al corso
                List<Material> materials = materialDao.getMaterialByCourse(course);

                //chiudo i dao
                materialDao.destroy();
                courseDao.destroy();

                System.out.println(materials);

                this.processTemplate(request, response, course, materials);


                //se il corso non esiste
            }else{

                System.out.println("corso non esiste");

                courseDao.destroy();

                this.processError(request, response);

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
