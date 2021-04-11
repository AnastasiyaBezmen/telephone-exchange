package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * ExecuteRefillAccountServlet.
 * Date: 01/27/2021
 *
 * @author Anastasiya Bezmen
 */
public class ExecuteRefillAccountServlet extends HttpServlet {

    private static final String ACCOUNT_ID = "accountId";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal sum = (new BigDecimal(req.getParameter("sumRefill")).multiply(new BigDecimal(100))
                .add(new BigDecimal(req.getParameter("balance"))));
        String accountId = req.getParameter(ACCOUNT_ID);
        String userId = req.getParameter("userId");
        try (ServiceCreator creator = new ServiceCreator()) {
            IUserService userService = creator.getUserService();
            userService.refillAccount(sum.longValue(), accountId, userId);
        } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/user/personalAccount.html");
    }
}
