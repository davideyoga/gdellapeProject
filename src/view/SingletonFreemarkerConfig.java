package view;

import freemarker.template.*;

import javax.servlet.ServletContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Creator Davide Micarelli
 */
public enum SingletonFreemarkerConfig {

    INSTANCE;

    private Configuration cfg = null;

    SingletonFreemarkerConfig() {
    }

    public Configuration getCfg(ServletContext context) {

        if (cfg == null) {
            //istanzio oggetto configuration
            cfg = new Configuration(new Version("2.3.26"));

            //impostiamo l'encoding di default per l'input e l'output
            //set the default input and outpout encoding
            if (context.getInitParameter("view.encoding") != null) {
                cfg.setOutputEncoding(context.getInitParameter("view.encoding"));
                cfg.setDefaultEncoding(context.getInitParameter("view.encoding"));
            }

            //impostiamo la directory (relativa al contesto) da cui caricare i templates
            //set the (context relative) directory for template loading
            if (context.getInitParameter("view.template_directory") != null) {
                cfg.setServletContextForTemplateLoading(context, context.getInitParameter("view.template_directory"));
            } else {
                cfg.setServletContextForTemplateLoading(context, "templates");
            }
            if (context.getInitParameter("view.debug") != null && context.getInitParameter("view.debug").equals("true")) {
                //impostiamo un handler per gli errori nei template - utile per il debug
                //set an error handler for debug purposes
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
            } else {
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            }

            //formato di default per data/ora
            //date/time default formatting
            if (context.getInitParameter("view.date_format") != null) {
                cfg.setDateTimeFormat(context.getInitParameter("view.date_format"));
            }

            //impostiamo il gestore degli oggetti - trasformer� in hash i Java beans
            //set the object handler that allows us to "view" Java beans as hashes
            DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_26);
            owb.setForceLegacyNonListCollections(false);
            owb.setDefaultDateType(TemplateDateModel.DATETIME);
            cfg.setObjectWrapper(owb.build());


        }
        return cfg;

    }
}