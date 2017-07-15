package view;

import freemarker.template.*;
import model.User;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Creator Davide Micarelli
 */
public class TemplateController {

    /**
     * prepara l'ambiente di Freemarker e processa della template
     * @param templateName nome della template
     * @param data Map contenente i dati da iniettare nella template
     * @param response risposta servlet
     * @param servlet_context contesto della servlet
     */
    public static void process(String templateName, Map data, HttpServletResponse response, ServletContext servlet_context) {

        //setto il tipo del contenuto di ritorno
        response.setContentType("text/html; charset=UTF-8");

        //ottengo oggetto cfg dal singleton
        Configuration cfg = SingletonFreemarkerConfig.INSTANCE.getCfg(servlet_context);

        //creo oggetto template
        Template template;

        PrintWriter out = null;

        try {

            template = cfg.getTemplate(templateName);

            //ottengo lo stream della risposta
            out = response.getWriter();

            //processo la template con la Map
            template.process(data, out);

            //esplicito pulizia da garbage collection
            data = null;

        } catch (TemplateException | IOException ex) {

            //log dei dettagli dell'eccezione
            Logger.getAnonymousLogger().log(Level.SEVERE, "Templating exception: " + ex.getMessage());

        } finally {

            //provo a comletare il processamento a prescindere
            assert out != null;

            out.flush();
            out.close();
        }

    }
}