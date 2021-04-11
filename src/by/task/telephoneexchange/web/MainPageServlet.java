package by.task.telephoneexchange.web;

import by.task.telephoneexchange.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * MainPageServlet.
 * Date: 01/05/2021
 *
 * @author Anastasiya Bezmen
 */
public class MainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMINISTRATOR:
                        resp.sendRedirect(req.getContextPath() + "/user/list.html");
                        return;
                    case CLIENT:
                        resp.sendRedirect(req.getContextPath() + "/user/personalAccount.html");
                        return;
                }
            }
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }
}
