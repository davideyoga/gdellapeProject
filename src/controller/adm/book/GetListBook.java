package controller.adm.book;

import controller.BaseController;
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
import java.util.Map;

/**
 * @author Davide Micarelli
 * Servlet che passa al template tutta la lista dei libri fino ad ora inseriti nel sistema
 *
 * PARAMETRI: non richiede nessun parametro
 */
public class GetListBook extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response, List<Book> listBook, List<Book> listBookByCourse ) {

        //inserisco i libri nel datamodel
        datamodel.put("books", listBook);

        //inserisco i libri nel datamodel
        datamodel.put("booksByCourse", listBookByCourse);

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template
        TemplateController.process("list_book.ftl", datamodel, response, getServletContext());
    }

    protected Course ceckCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            int id = Integer.parseInt(request.getParameter("idCourse"));

            Course course = courseDao.getCourseById(id);

            if (course!= null && course.getIdCourse()>0){

                datamodel.put("course",course);

                return course;

            }else{

                course = null;

                System.out.println("Corso non esistente");
                this.processError(request, response);
            }

            course = null;

            courseDao.destroy();
            courseDao=null;


        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            this.processError(request, response);

            return null;
        }

        return null;

    }

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


                    //estraggo tutti i libri
                    BookDao bookDao = new BookDaoImpl(ds);
                    bookDao.init();

                    Course course = this.ceckCourse(request, response );

                    //estraggo tutti i libri
                    List<Book> bookList = bookDao.getBooks();

                    //estraggo i libri collegati al corso
                    List<Book> bookListByCourse = bookDao.getBookByCourse(course);

                    //chiudo dao
                    bookDao.destroy();

                    //passo i libri al template
                    this.processTemplate(request, response, bookList, bookListByCourse);

                    //se non si ha il permesso
                }else{
                    this.processNotPermitted(request, response);
                }
            //se sessione non valida
            }else{
                //rimando alla pagina di login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        }catch (NumberFormatException | DaoException e){
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
