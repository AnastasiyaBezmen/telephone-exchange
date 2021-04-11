package by.task.telephoneexchange.service;

import by.task.telephoneexchange.domain.User;

import java.util.List;

/**
 * IUserService.
 * Date: 12/22/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IUserService {

    List<User> findAll() throws ServiceException;

    User findById(String id) throws ServiceException;

    User authorize(String login, String password) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(List<String> ids) throws ServiceException;

    List<User> findNegativeBalance() throws ServiceException;

    void lockUser(List<String> ids) throws ServiceException;

    void refillAccount(Long sum, String accountId, String userId) throws ServiceException;

    void changeTariffPlan(String accountId, String tariffPlanId) throws ServiceException;
}
