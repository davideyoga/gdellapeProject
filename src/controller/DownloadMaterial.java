package controller;

import controller.utility.StreamResult;
import dao.exception.DaoException;
import dao.implementation.MaterialDaoImpl;
import dao.interfaces.MaterialDao;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.io.File;


/**
 * @author Davide Micarelli
 *
 * Passato il parametro POST corrispondente all'id del materiale, fa partire il download
 */
public class DownloadMaterial extends BaseController {

    private void action_download(HttpServletRequest request, HttpServletResponse response, String route) throws IOException {
        StreamResult result = new StreamResult(getServletContext());

        File in = new File( getServletContext().getInitParameter("uploads.directory") + "/" + route);

        result.activate(in, request, response);

    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //estraggo il parametro post con l'id del materiale
            int id = Integer.parseInt(request.getParameter("id"));

            MaterialDao materialDao = new MaterialDaoImpl(ds);
            materialDao.init();

            //estraggo material
            Material material = materialDao.getMaterialById(id);

            materialDao.destroy();

            request.setAttribute("contentType", material.getType() );

            this.action_download(request, response, material.getRoute());

            material=null;

        }catch (DaoException | NumberFormatException | FileNotFoundException e){
            //se trovo un errore lancio processo di errore
            this.processError(request, response);
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
