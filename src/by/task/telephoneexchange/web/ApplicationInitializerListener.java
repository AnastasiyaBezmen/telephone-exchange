package by.task.telephoneexchange.web;

import by.task.telephoneexchange.pool.ConnectionPool;
import by.task.telephoneexchange.pool.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

/**
 * ApplicationInitializerListener.
 * Date: 01/05/2021
 *
 * @author Anastasiya Bezmen
 */
public class ApplicationInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/telephoneexchange?useUnicode=true&serverTimezone=" +
                            TimeZone.getDefault().getID(), "root", "root", 5, 100, 0);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
