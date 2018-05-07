package controller;

import controller.utility.StreamResult;
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
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 *
 * Da qui ogni utente puo' visualizzare il materiale, ma non puo' eliminare ne aggiungere alto materiale
 *
 */
public class ListMaterial extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, Course course, List<Material> materials) throws ServletException, IOException {

        //inserisco nel datamodel il corso e il materiale
        datamodel.put("course", course);
        datamodel.put("materials", materials);

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template con lingua appropriata
        super.processTemplate(request, response, "list_material", datamodel );

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.processMaterial(request, response);
    }

    /**
     * Prendo il parametro get che rappresenta l'id del metariale da scaricare,
     * estraggo il materiale dalla pagina,
     * poi come parametro get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            if(request.getParameter("idMaterial")!=null){

                MaterialDao materialDao = new MaterialDaoImpl(ds);

                Material material = materialDao.getMaterialById(Integer.parseInt(request.getParameter("idMaterial")));

                //apro uno stream
                StreamResult result = new StreamResult(getServletContext());

                //estraggo il materiale
                File in = new File(material.getRoute());

                //mando il file al client
                result.activate(in, request, response);

                //riestraggo il materiale e lancio il template
                this.processMaterial(request, response);

            }else{
                this.processError(request, response);
            }


        }catch (NumberFormatException | DaoException e){

            e.printStackTrace();

            this.processError(request, response);

        }


    }


    /**
     * Si occupda di estrarre il materiale del corso (dal param id), dopo passa il controllo al processTemplate
     */
    private void processMaterial( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        try {

            //inizializzo il dao dei corsi
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            //estraggo il corso dal codice passato
            int id = Integer.parseInt(request.getParameter("id"));

            //estraggo il corso con tale codice
            Course course = courseDao.getCourseById(id);

            System.out.println(course);

            //se il corso esiste
            if(course != null){

                //inizializzo il dao del materiale
                MaterialDao materialDao = new MaterialDaoImpl(ds);
                materialDao.init();

                //estraggo tutto il materiale inerente al corso
                List<Material> materials = materialDao.getMaterialByCourse(course);

                System.out.println(materials);

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

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            this.processError(request, response);
        } catch (ServletException e) {
            e.printStackTrace();

            this.processError(request, response);
        } catch (IOException e) {
            e.printStackTrace();

            this.processError(request, response);
        }
    }
}
