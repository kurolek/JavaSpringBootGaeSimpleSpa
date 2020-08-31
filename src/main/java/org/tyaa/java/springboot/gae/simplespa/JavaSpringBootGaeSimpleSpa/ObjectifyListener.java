package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ObjectifyListener implements ServletContextListener {

    private static final Logger log =
            Logger.getLogger(ObjectifyListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (System.getenv("SPRING_PROFILES_ACTIVE") == null) {
                // local
                ObjectifyService.init(new ObjectifyFactory(
                        DatastoreOptions.newBuilder()
                                .setHost("http://localhost:8484")
                                .setProjectId("springboot-gae-simplespa")
                                .build()
                                .getService()
                ));
            }
            else {
                // GAE
                ObjectifyService.init(new ObjectifyFactory(
                        DatastoreOptions.getDefaultInstance().getService()
                ));
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
        }
        // ObjectifyService.register(Article.class);
        // ObjectifyService.register(Author.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
