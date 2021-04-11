package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.domain.Role;
import by.task.telephoneexchange.domain.TariffPlan;
import by.task.telephoneexchange.domain.User;
import by.task.telephoneexchange.service.ITariffPlanService;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * UserEditServlet.
 * Date: 12/25/2020
 *
 * @author Anastasiya Bezmen
 */
public class UserEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            try (ServiceCreator creator = new ServiceCreator()) {
                IUserService userService = creator.getUserService();
                User user = userService.findById(id);
                if (user != null) {
                    req.setAttribute("user", user);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            } catch (IllegalArgumentException e) {
                resp.sendError(404);
                return;
            }
        }
        try (ServiceCreator creator = new ServiceCreator()) {
            ITariffPlanService tariffPlanService = creator.getTariffPlanService();
            List<TariffPlan> tariffPlans = tariffPlanService.getAll();
            if (tariffPlans != null) {
                req.setAttribute("tariffPlans", tariffPlans);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        } catch (IllegalArgumentException e) {
            resp.sendError(404);
            return;
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}
