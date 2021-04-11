package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.domain.Account;
import by.task.telephoneexchange.domain.Address;
import by.task.telephoneexchange.domain.PersonalInformation;
import by.task.telephoneexchange.domain.Role;
import by.task.telephoneexchange.domain.TariffPlan;
import by.task.telephoneexchange.domain.User;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserSaveServlet.
 * Date: 12/26/2020
 *
 * @author Anastasiya Bezmen
 */
public class UserSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffPlan tariffPlan = buildTariffPlan(req);
        Account account = buildAccount(req, tariffPlan);
        Address address = buildAddress(req);
        PersonalInformation personalInformation = buildPersonalInformation(req, address);
        User user = buildUser(req, personalInformation, account);
        try (ServiceCreator creator = new ServiceCreator()) {
            IUserService userService = creator.getUserService();
            userService.save(user);
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }

    private User buildUser(HttpServletRequest req, PersonalInformation personalInformation, Account account) {
        User user = new User();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            String id = null;
            try {
                id = req.getParameter("id");
            } catch (NumberFormatException e) {
            }
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            Role role = null;
            try {
                role = Role.valueOf(req.getParameter("role"));
            } catch (NullPointerException | IllegalArgumentException e) {
            }
            user.setRole(role);
            user.setPersonalInformation(personalInformation);
            user.setAccount(account);
        }
        return user;
    }

    private Account buildAccount(HttpServletRequest req, TariffPlan tariffPlan) {
        Account account = new Account();
        account.setId(req.getParameter("accountId"));
        account.setTariffPlan(tariffPlan);
        return account;
    }

    private TariffPlan buildTariffPlan(HttpServletRequest req) {
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setId(req.getParameter("tariffPlanId"));
        return tariffPlan;
    }

    private Address buildAddress(HttpServletRequest req) {
        Address address = new Address();
        address.setId(req.getParameter("addressId"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        String houseStr = req.getParameter("houseNumber");
        Integer houseNumber = 0;
        if (houseStr != null && !houseStr.isEmpty()) {
            houseNumber = Integer.valueOf(houseStr);
        }
        address.setHouseNumber(houseNumber);
        String blockStr = req.getParameter("block");
        Integer block = 0;
        if (blockStr != null && !blockStr.isEmpty()) {
            block = Integer.valueOf(blockStr);
        }
        address.setBlock(block);
        String flatStr = req.getParameter("flat");
        Integer flat = 0;
        if (flatStr != null && !flatStr.isEmpty()) {
            flat = Integer.valueOf(flatStr);
        }
        address.setFlat(flat);
        return address;
    }

    private PersonalInformation buildPersonalInformation(HttpServletRequest req, Address address) {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(req.getParameter("personalInfoId"));
        personalInformation.setFirstName(req.getParameter("firstName"));
        personalInformation.setLastName(req.getParameter("lastName"));
        personalInformation.setPatronymicName(req.getParameter("patronymicName"));
        personalInformation.setPhoneNumber(req.getParameter("phoneNumber"));
        personalInformation.setAddress(address);
        return personalInformation;
    }
}