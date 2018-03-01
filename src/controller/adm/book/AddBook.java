package controller.adm.book;

import controller.BaseController;
import controller.utility.SecurityLayer;
import dao.exception.DaoException;
import dao.implementation.BookDaoImpl;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.BookDao;
import dao.interfaces.CourseDao;
import model.Book;
import model.Course;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet per l'aggiunta di un libro ad un corso
 * @author Davide Micarelli
 */
public class AddBook extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, Course course) throws ServletException, IOException {

        //carico il corso nel datamodel
        datamodel.put("course", course);

        //inserisco in sessione l'id del corso
        request.getSession().setAttribute("idCourse", course.getIdCourse());

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template
        TemplateController.process("add_book.ftl", datamodel, response, getServletContext());

    }

    /**
     * Controllo i permessi di sessione e sessione,
     * estraggo id del corso per estrarlo dal database(per controllare se esiste),
     * se tutto va bene carico il corso nel datamodel e lancio il template
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                if (((List <Service>) request.getSession().getAttribute("services")).contains(addBook)) {

                    //estraggo il parametro get di id del corso dalla richiesta (se parametro = null o non numero genera NumberFormatException)
                    int idCourse = SecurityLayer.checkNumeric(request.getParameter("idCourse"));

                    //inizializzo un dao
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo il corso con tale id
                    Course courseById = courseDao.getCourseById(idCourse);

                    //se il corso esiste
                    if (courseById != null) {

                        //chiudo il dao
                        courseDao.destroy();

                        //carico dati nel datamodel e lancio il template
                        this.processTemplate(request, response, courseById);

                        //se il corso non esiste
                    }else{

                        //chiudo il dao
                        courseDao.destroy();

                        this.processError(request, response);

                    }

                    //se non si ha il permesso
                }else{

                    this.processNotPermitted(request, response);

                }

                //se non si ha un sessione valida
            }else {

                //rimando alla pagina di login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        }catch (NumberFormatException | DaoException  e) {

            this.processError(request, response);

        }
    }


    /**
     * Controllo correttezza sessione e permessi,
     * raccoldo dati dalla form e eseguo update,
     * lancio processTemplate alla fine
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                if (((List <Service>) request.getSession().getAttribute("services")).contains(addBook)) {

                    //estraggo il parametro get di id del corso dalla richiesta (se parametro = null o non numero genera NumberFormatException)
                    int idCourse = SecurityLayer.checkNumeric(String.valueOf(request.getSession().getAttribute("idCourse")));

                    //inizializzo un dao
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo il corso con tale id
                    Course courseById = courseDao.getCourseById(idCourse);

                    //se il corso esiste
                    if (courseById != null) {

                        //inizializzo un nuovo bookDao
                        BookDao bookDao = new BookDaoImpl(ds);
                        bookDao.init();

                        //inizializzo un nuovo libro
                        Book book = bookDao.getBook();

                        //setto il libro con i dati dalla form
                        book.setCode(request.getParameter("code"));
                        book.setAuthor(request.getParameter("author"));
                        book.setTitle(request.getParameter("title"));
                        book.setVolume(request.getParameter("volume"));

                        //se il parametro age non e' presente o non e' un numero non lo setto

                        try {

                            if(request.getParameter("age")!=null  ){

                                book.setAge(SecurityLayer.checkNumeric(request.getParameter("age")));
                            }

                        }catch (NumberFormatException e){

                            //se non e' un numero non faccio nulla

                        }

                        book.setEditor(request.getParameter("editor"));
                        book.setLink(request.getParameter("link"));

                        System.out.println("libro: " + book);

                        //setto a 0 il libro per sicurezza
                        book.setId(0);

                        //salvo il libro e lo collego al corso
                        int idBook = bookDao.storeBook(book);
                        book.setId(idBook);
                        bookDao.addLinkBookToCourse(courseById, book );

                        //chiudo i dao
                        bookDao.destroy();
                        courseDao.destroy();

                        //carico un messaggio di avvenuto inserimento
                        datamodel.put("message", "BOOK INSERTED WITH SUCCESS");

                        //carico dati nel datamodel e lancio il template
                        this.processTemplate(request, response, courseById);


                        //se il corso non esiste
                    }else{

                        //chiudo il dao
                        courseDao.destroy();

                        this.processError(request, response);

                    }

                    //se non si ha il permesso
                }else{

                    this.processNotPermitted(request, response);

                }

                //se non si ha un sessione valida
            }else {

                //rimando alla pagina di login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        }catch (NumberFormatException | DaoException  e) {

            this.processError(request, response);

        }

    }
}
