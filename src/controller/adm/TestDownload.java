package controller.adm;

import controller.BaseController;
import controller.utility.SecurityLayer;
import controller.utility.StreamResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Davide Micarelli
 */
public class TestDownload extends BaseController{

    private static final String GET_FILE_QUERY = "SELECT * FROM material WHERE ID=?";

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
    }

    private void action_download(HttpServletRequest request, HttpServletResponse response) throws IOException, NamingException, SQLException {
        Integer res = (Integer) request.getAttribute("resid");
        if (res != null) {

            //classe in framework/result
            StreamResult result = new StreamResult(getServletContext());

            try (Connection c = ds.getConnection(); PreparedStatement s = c.prepareStatement(GET_FILE_QUERY)) {

                s.setInt(1, res);

                try (ResultSet rs = s.executeQuery()) {

                    if (rs.next()) {

                        try (InputStream is = new FileInputStream(
                                getServletContext().getInitParameter("uploads.directory") + File.separatorChar + rs.getString("localfile"))) {

                            request.setAttribute("contentType", rs.getString("type"));
                            result.activate(is, rs.getLong("size"), rs.getString("name"), request, response);

                        }

                    } else {
                        request.setAttribute("exception", new Exception("Resurce not found in file database"));
                        action_error(request, response);
                    }
                }
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
            //ceckNumeric esegue la conversione di un intero, se non e' un intero solleva un eccezzione
            int res = SecurityLayer.checkNumeric(request.getParameter("res"));
            request.setAttribute("resid", res);
            action_download(request, response);

        } catch (NumberFormatException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        } catch (NamingException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        } catch (SQLException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
