package by.task.telephoneexchange.service;

import by.task.telephoneexchange.domain.User;
import by.task.telephoneexchange.repository.IUserRepository;
import by.task.telephoneexchange.repository.RepositoryException;

import java.util.List;

/**
 * UserService.
 * Date: 12/22/2020
 *
 * @author Anastasiya Bezmen
 */
public class UserService implements IUserService {

    private IUserRepository userRepository;

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userRepository.readAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(String id) throws ServiceException {
        try {
            return userRepository.read(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User authorize(String login, String password) throws ServiceException {
        try {
            return userRepository.readByLoginAndPassword(login, password);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            if (user.getId() != null) {
                userRepository.update(user);
            } else {
                userRepository.create(user);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<String> ids) throws ServiceException {
        try {
            for (String id : ids) {
                userRepository.delete(id);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findNegativeBalance() throws ServiceException {
        try {
            return userRepository.readNegativeBalance();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void lockUser(List<String> ids) throws ServiceException {
        try {
            for (String id : ids) {
                userRepository.lockUser(id);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void refillAccount(Long sum, String accountId, String userId) throws ServiceException {
        try {
            userRepository.refillAccount(sum, accountId);
            userRepository.unlockUser(userId);
        } catch (
                RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeTariffPlan(String accountId, String tariffPlanId) throws ServiceException {
        try {
            userRepository.changeTariffPlan(accountId, tariffPlanId);
        } catch (
                RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}