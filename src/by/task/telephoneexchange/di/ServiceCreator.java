package by.task.telephoneexchange.di;

import by.task.telephoneexchange.pool.ConnectionPool;
import by.task.telephoneexchange.pool.ConnectionPoolException;
import by.task.telephoneexchange.repository.ITariffPlanRepository;
import by.task.telephoneexchange.repository.IUserRepository;
import by.task.telephoneexchange.repository.TariffPlanRepository;
import by.task.telephoneexchange.repository.UserRepository;
import by.task.telephoneexchange.service.ITariffPlanService;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.TariffPlanService;
import by.task.telephoneexchange.service.UserService;

import java.sql.Connection;

/**
 * ServiceCreator.
 * Date: 12/22/2020
 *
 * @author Anastasiya Bezmen
 */
public class ServiceCreator implements AutoCloseable {

    IUserService userService = null;
    ITariffPlanService tariffPlanService = null;
    private IUserRepository userRepository = null;
    private ITariffPlanRepository tariffPlanRepository = null;
    private Connection connection = null;

    /**
     * Creates {@link UserService}.
     *
     * @return an {@link UserService}
     * @throws ServiceCreationException in case of some exception inside the service
     */
    public IUserService getUserService() throws ServiceCreationException {
        if (userService == null) {
            UserService userService = new UserService();
            userService.setUserRepository(getUserRepository());
            this.userService = userService;
        }
        return userService;
    }

    /**
     * Creates {@link TariffPlanService}.
     *
     * @return an {@link TariffPlanService}
     * @throws ServiceCreationException in case of some exception inside the service
     */
    public ITariffPlanService getTariffPlanService() throws ServiceCreationException {
        if (tariffPlanService == null) {
            TariffPlanService tariffPlanService = new TariffPlanService();
            tariffPlanService.setTariffPlanRepository(getTariffPlanRepository());
            this.tariffPlanService = tariffPlanService;
        }
        return tariffPlanService;
    }

    /**
     * Creates {@link UserRepository}.
     *
     * @return an {@link UserRepository}
     * @throws ServiceCreationException in case of some exception inside the repository
     */
    private IUserRepository getUserRepository() throws ServiceCreationException {
        if (userRepository == null) {
            UserRepository userRepository = new UserRepository();
            userRepository.setConnection(getConnection());
            this.userRepository = userRepository;
        }
        return userRepository;
    }

    /**
     * Creates {@link TariffPlanRepository}.
     *
     * @return an {@link TariffPlanRepository}
     * @throws ServiceCreationException in case of some exception inside the repository
     */
    private ITariffPlanRepository getTariffPlanRepository() throws ServiceCreationException {
        if (tariffPlanRepository == null) {
            TariffPlanRepository tariffPlanRepository = new TariffPlanRepository();
            tariffPlanRepository.setConnection(getConnection());
            this.tariffPlanRepository = tariffPlanRepository;
        }
        return tariffPlanRepository;
    }

    /**
     * Creates {@link Connection}.
     *
     * @return an {@link Connection}
     * @throws ServiceCreationException in case of some exception inside the connection
     */
    private Connection getConnection() throws ServiceCreationException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceCreationException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }
}
