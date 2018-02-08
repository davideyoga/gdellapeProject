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
import javax.servlet.http.Part;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Davide Micarelli
 */
public class AddMaterial extends BaseController {

    private void processTemplate(HttpServletRequest request, HttpServletResponse response, Course courseById) throws ServletException, IOException{

        //inserisco id del corso nella sessione da usare nel parametro post
        request.getSession().setAttribute("idCourse", courseById.getIdCourse());

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template con il corso caricato
        datamodel.put("course", courseById);
        TemplateController.process("add_material.ftl", datamodel, response, getServletContext());

    }

    /**
     * Metodo chiamato nel momento in cui non si possiedono i permessi per una tale operazione
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void notPermit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //lancio la servlet di non permesso
        response.sendRedirect("NotPermit");

    }


    /**
     * Prendo il parametro GET dell'id del corso,
     * controllo se possiede il permesso di aggiungere tutti i corsi oppure se puo' modificare questo specifico corso,
     * se si lancio il template di aggiunta del materiale
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

                if(request.getParameter("idCourse") != null) {

                    courseById = courseDao.getCourseById(SecurityLayer.checkNumeric(request.getParameter("idCourse")));
                }

                //se il corso esiste
                if(courseById != null) {


                    /*
                        ADMIN
                     */

                    //se l'utente in sessione possiede il servizio addMaterialAllCourse, quindi puo' modificare ogni corso
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(addMaterialAllCourse)) {

                        this.processTemplate(request, response, courseById);

                        /*
                            DOCENTE
                         */

                        //se non ha il permesso di modificare tutti i corsi controllo se questo corso gli appartiene
                    } else {

                        //estraggo tutti i corsi appartenenti all'utente
                        List <Course> coursesByUser = courseDao.getCoursesByUser(sessionManager.getUser(request));

                        //controllo se il corso con id passato e' tra i corsi dell'utente e se ha il permesso di modificare corsi Lorenzo1
                        if (coursesByUser != null && coursesByUser.contains(courseById) &&
                                ((List <Service>) request.getSession().getAttribute("services")).contains(addMaterial)) {

                            //se si lancio il template
                            this.processTemplate(request, response, courseById);

                            //se il corso non e' tra i corsi dell'utente
                        }else{

                            //metodo per non permesso
                            this.notPermit(request, response);

                        }

                    }

                    //se non e' presente nel parametro get l'id del corso oppure non esista un corso con tale id
                }else {

                    //lancio servlet di errore
                    this.processError(request, response);

                }

                //se non si possiede una sessione valida
            }else{

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException e) {
            e.printStackTrace();

            //lancio servlet di errore
            this.processError(request, response);

        }
    }

    /**
     * Vado ad aggiungere il materiale al corso,
     * vado solo ad effettuare i controlli sulla sessione con isValid
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //se sessione valida,
            if (sessionManager.isValid(request)) {

                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                Course courseById = null;

                //se e' presente il parametro nella sessione
                if(request.getSession().getAttribute("idCourse") != null) {

                    String idCourse = String.valueOf(request.getSession().getAttribute("idCourse"));

                    courseById = courseDao.getCourseById(SecurityLayer.checkNumeric(idCourse));
                }

                //controllo nel caso in cui il corso non esistesse piu'
                if( courseById != null) {

                    /*
                        INIZIO UPLOAD
                     */

                    Part file_to_upload = request.getPart("filetoupload");

                    //we want the sha-1 file digest of the uploaded file
                    //vogliamo creare il digest sha-1 del file
                    MessageDigest md = MessageDigest.getInstance("SHA-1");

                    //create a file (with a unique name) and copy the uploaded file to it
                    //creiamo un nuovo file (con nome univoco) e copiamoci il file scaricato, definisco la directory
                    File uploaded_file = File.createTempFile("upload_", "", new File(getServletContext().getInitParameter("uploads.directory")));
                    try (InputStream is = file_to_upload.getInputStream();
                         OutputStream os = new FileOutputStream(uploaded_file)) {
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = is.read(buffer)) > 0) {
                            //durante la copia, aggreghiamo i byte del file nel digest sha-1
                            //while copying, we aggregate the file bytes in the sha-1 digest
                            md.update(buffer, 0, read);
                            os.write(buffer, 0, read);
                        }
                    }

                    //covertiamo il digest in una stringa
                    byte[] digest = md.digest();
                    String sdigest = "";
                    for (byte b : digest) {
                        sdigest += String.valueOf(b);
                    }


                    /*
                        FINE UPLOAD
                     */



                    /*
                        INIZIO INSERIMENTO DATI DB
                     */
                    //inserisco i dati del file nel db
                    MaterialDao materialDao = new MaterialDaoImpl(ds);
                    materialDao.init();

                    //inizializzo il nuovo material
                    Material material = materialDao.getMaterial();

                    //setto il material
                    material.setName(request.getParameter("name"));
                    material.setDescription_ita(request.getParameter("description_ita"));
                    material.setDescription_eng(request.getParameter("description_eng"));
                    //data aggiunta dalla query
                    material.setType(file_to_upload.getContentType());
                    material.setSize(file_to_upload.getSize());
                    material.setData(new Timestamp(System.currentTimeMillis()));
                    material.setRoute(uploaded_file.getName());


                    //inserisco nel db il materiale
                    int idMaterial = materialDao.storeMaterial(material);

                    //setto l'id del materiale dopo averlo appena inserito
                    material.setId(idMaterial);

                    //aggiungo la connessione tra corso e materiale
                    materialDao.addConnectionWithCourseMaterial(courseById, material);

                    /*
                        FINE INSERIMENTO DATI DB
                     */


                    //chiudo il dao
                    materialDao.destroy();


                    //lancio il template di aggiunta materiale
                    this.processTemplate(request, response, courseById);


                    //se parametro id non presente nella sessione o corso non esistente
                }else{

                    //lancio processo di errore
                    this.processError(request, response);
                }


                //se sessione non valida
            }else{

                //reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }
        } catch (DaoException e) {
            e.printStackTrace();
            this.processError(request,response);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            this.processError(request,response);

        } catch (NumberFormatException e){
            e.printStackTrace();
            this.processError(request,response);
        }
    }
}
