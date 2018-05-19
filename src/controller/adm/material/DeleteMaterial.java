package controller.adm.material;

import controller.BaseController;
import controller.utility.SecurityLayer;
import dao.exception.DaoException;
import dao.implementation.MaterialDaoImpl;
import dao.interfaces.MaterialDao;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 */
public class DeleteMaterial extends BaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        if(request.getParameter("idMaterial")!= null){
            try {

                //inizializzo il dao del materiale e dei corsi
                MaterialDao materialDao = new MaterialDaoImpl(ds);
                materialDao.init();

                //casto l'id del materiale
                int idMaterial = SecurityLayer.checkNumeric(request.getParameter("idMaterial"));

                //estraggo il materiale
                Material material = materialDao.getMaterialById(idMaterial);



                //elimino il materiale
                materialDao.deleteMaterial(material, getServletContext().getInitParameter("uploads.directory"));

                materialDao.destroy();

                //torno OK alla chiamata servlet se arrivo alla fine senza problemi
                response.getWriter().write("OK");




            } catch (DaoException | NumberFormatException e) {
                e.printStackTrace();

                //torno KO alla chiamata servlet se ho avuto problemi
                response.getWriter().write("KO");
            }

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
