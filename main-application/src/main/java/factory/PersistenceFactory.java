package factory;

import configuration.Configuration;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PersistenceFactory {

    public static Object build(){

        Object componentPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.commonPathToJavaArchive + "/persistence.jar").toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls,PersistenceFactory.class.getClassLoader());
            Class persistenceClass = Class.forName("main.Database", true, urlClassLoader);
            Object persistenceInstance = persistenceClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            componentPort = persistenceClass.getDeclaredField("port").get(persistenceInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return componentPort;
    }
}
