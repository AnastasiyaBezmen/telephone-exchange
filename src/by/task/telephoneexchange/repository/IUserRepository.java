package by.task.telephoneexchange.repository;

import by.task.telephoneexchange.domain.User;

import java.util.List;

/**
 * IUserRepository.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IUserRepository extends IRepository<User> {

    //TODO Add JavaDoc
    List<User> readAll() throws RepositoryException;

    User readByLoginAndPassword(String login, String password) throws RepositoryException;

    List<User> readNegativeBalance() throws RepositoryException;

    void lockUser(String id) throws RepositoryException;

    void refillAccount(Long sum, String accountId) throws RepositoryException;

    void changeTariffPlan(String accountId, String tariffPlanId) throws RepositoryException;

    void unlockUser(String userId) throws RepositoryException;
}
