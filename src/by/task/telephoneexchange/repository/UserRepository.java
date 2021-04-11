package by.task.telephoneexchange.repository;

import by.task.telephoneexchange.domain.Account;
import by.task.telephoneexchange.domain.Address;
import by.task.telephoneexchange.domain.PersonalInformation;
import by.task.telephoneexchange.domain.Role;
import by.task.telephoneexchange.domain.TariffPlan;
import by.task.telephoneexchange.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * UserRepository.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
public class UserRepository implements IUserRepository {

    private static final String READ_LIST_USER_SQL = "SELECT " +
            "user.id as user_id," +
            "user.login as login," +
            "user.password as password," +
            "user.role as role," +
            "personal_info.id as personal_info_id," +
            "personal_info.first_name as first_name," +
            "personal_info.last_name as last_name," +
            "personal_info.patronymic_name as patronymic_name," +
            "personal_info.phone_number as phone_number," +
            "address.id as address_id," +
            "address.city as city," +
            "address.street as street," +
            "address.house_number as house_number," +
            "address.block as block," +
            "address.flat as flat," +
            "account.id as account_id," +
            "account.balance as balance, " +
            "tariff_plan.id as tariff_plan_id," +
            "tariff_plan.name as tariff_plan_name," +
            "tariff_plan.cost_per_month as cost_per_month " +
            "FROM user " +
            "LEFT JOIN account ON user.account_id=account.id " +
            "LEFT JOIN personal_info ON personal_info.id=user.personal_info_id " +
            "LEFT JOIN address ON address.id=personal_info.address_id " +
            "LEFT JOIN tariff_plan ON tariff_plan.id=account.tariff_plan_id ";

    private static final String UNLOCK_USER_SQL = "UPDATE user " +
            "INNER JOIN account ON account.id = user.account_id " +
            "SET user.blocked = CASE " +
            "WHEN account.balance > 0 THEN false " +
            "WHEN account.balance <= 0 THEN true " +
            "END " +
            "WHERE user.id = ?; ";

    private static final String CHANGE_TARIFF_PLAN_SQL = "UPDATE account SET account.tariff_plan_id = ? WHERE account" +
            ".id  = ?";

    private static final String REFILL_BALANCE_SQL = "UPDATE account SET balance = ? WHERE id = ?";

    private static final String LOCK_USER_SQL = "UPDATE user SET blocked = true WHERE id = ?";

    private static final String READ_USER_WHERE_NEGATIVE_BALANCE = "SELECT " +
            "user.id as user_id," +
            "user.blocked as blocked," +
            "personal_info.id as personal_info_id," +
            "personal_info.first_name as first_name," +
            "personal_info.last_name as last_name," +
            "personal_info.patronymic_name as patronymic_name," +
            "personal_info.phone_number as phone_number," +
            "account.id as account_id," +
            "account.balance as balance " +
            "FROM user " +
            "LEFT JOIN account ON user.account_id=account.id " +
            "LEFT JOIN personal_info ON personal_info.id=user.personal_info_id " +
            "WHERE balance < 0";

    private static final String READ_USER_BY_LOGIN_AND_PASSWORD_SQL = "SELECT " +
            "user.id as user_id," +
            "user.role as role," +
            "personal_info.id as personal_info_id," +
            "personal_info.first_name as first_name," +
            "personal_info.last_name as last_name," +
            "personal_info.patronymic_name as patronymic_name," +
            "personal_info.phone_number as phone_number," +
            "account.id as account_id," +
            "account.balance as balance, " +
            "tariff_plan.id as tariff_plan_id," +
            "tariff_plan.name as tariff_plan_name," +
            "tariff_plan.cost_per_month as cost_per_month " +
            "FROM user " +
            "LEFT JOIN account ON user.account_id=account.id " +
            "LEFT JOIN personal_info ON personal_info.id=user.personal_info_id " +
            "LEFT JOIN tariff_plan ON tariff_plan.id=account.tariff_plan_id " +
            "WHERE user.login = ? AND user.password = ?";

    private static final String READ_USER_SQL = "SELECT " +
            "user.id as user_id," +
            "user.login as login," +
            "user.password as password," +
            "user.role as role," +
            "personal_info.id as personal_info_id," +
            "personal_info.first_name as first_name," +
            "personal_info.last_name as last_name," +
            "personal_info.patronymic_name as patronymic_name," +
            "personal_info.phone_number as phone_number," +
            "address.id as address_id," +
            "address.city as city," +
            "address.street as street," +
            "address.house_number as house_number," +
            "address.block as block," +
            "address.flat as flat," +
            "account.id as account_id," +
            "account.balance as balance, " +
            "tariff_plan.id as tariff_plan_id," +
            "tariff_plan.name as tariff_plan_name," +
            "tariff_plan.cost_per_month as cost_per_month " +
            "FROM user " +
            "LEFT JOIN account ON user.account_id=account.id " +
            "LEFT JOIN personal_info ON personal_info.id=user.personal_info_id " +
            "LEFT JOIN address ON address.id=personal_info.address_id " +
            "LEFT JOIN tariff_plan ON tariff_plan.id=account.tariff_plan_id " +
            "WHERE user.id= ?";

    private static final String CREATE_USER_ACCOUNT_SQL =
            "INSERT INTO account (id, tariff_plan_id) VALUES (?, ?)";

    private static final String CREATE_USER_ADDRESS_SQL = "INSERT INTO address (id, city, street, house_number, " +
            "block, flat) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String CREATE_USER_PERSONAL_INFO_SQL = "INSERT INTO personal_info (id, first_name, " +
            "last_name, patronymic_name, phone_number, address_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String CREATE_USER_SQL = "INSERT INTO user (id, login, password, role, personal_info_id, " +
            "account_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_ACCOUNT_SQL = "UPDATE account SET tariff_plan_id = ? WHERE id = ?";

    private static final String UPDATE_USER_ADDRESS_SQL = "UPDATE address SET city = ?, street = ?, house_number = ?," +
            " block = ?, flat = ? WHERE id = ?";

    private static final String UPDATE_USER_PERSONAL_INFO_SQL = "UPDATE personal_info SET first_name = ?, last_name =" +
            " ?, patronymic_name = ?, phone_number = ?, address_id = ? WHERE id = ?";

    private static final String UPDATE_USER_SQL = "UPDATE user SET login = ?, password = ?, role = ?, " +
            "personal_info_id = ?, " +
            "account_id = ? WHERE id = ?";

    private static final String DELETE_USER_SQL = "DELETE user, personal_info, account, address " +
            "FROM user " +
            "inner join personal_info " +
            "inner join address " +
            "inner join account ON user.account_id=account.id AND personal_info.id=user.personal_info_id AND " +
            "personal_info.address_id=address.id " +
            "where user.id = ?";

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User read(String id) throws RepositoryException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_USER_SQL);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws RepositoryException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_USER_BY_LOGIN_AND_PASSWORD_SQL);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUserWithRole(resultSet);
                user.setLogin(login);
                user.setPassword(password);
            }
            return user;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<User> readNegativeBalance() throws RepositoryException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ_USER_WHERE_NEGATIVE_BALANCE);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = buildUserWithIdAccountAndPersonalInfo(resultSet);
                user.setBlocked(resultSet.getBoolean("blocked"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void lockUser(String id) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LOCK_USER_SQL);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void refillAccount(Long sum, String accountId) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REFILL_BALANCE_SQL);
            statement.setLong(1, sum);
            statement.setString(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void changeTariffPlan(String accountId, String tariffPlanId) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_TARIFF_PLAN_SQL);
            statement.setString(1, tariffPlanId);
            statement.setString(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void unlockUser(String userId) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UNLOCK_USER_SQL);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<User> readAll() throws RepositoryException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ_LIST_USER_SQL);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void create(User user) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            String newAccountId = createUserAccount(user);
            String newAddressId = createUserAddress(user);
            String newPersonalInfoId = createPersonalInfo(user, newAddressId);
            createUser(user, newAccountId, newPersonalInfoId);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }

    }

    @Override
    public void update(User user) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            updateUserAccount(user);
            updateUserAddress(user);
            updateUserPersonalInfo(user);
            updateUser(user);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(String id) throws RepositoryException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_USER_SQL);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    private User buildUserWithIdAccountAndPersonalInfo(ResultSet resultSet) throws SQLException {
        PersonalInformation personalInformation = buildPersonalInformation(resultSet);
        Account account = buildAccount(resultSet);
        User user = new User();
        user.setId(resultSet.getString("user_id"));
        user.setPersonalInformation(personalInformation);
        user.setAccount(account);
        return user;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = buildUserWithRole(resultSet);
        user.getPersonalInformation().setAddress(buildAddress(resultSet));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    private User buildUserWithRole(ResultSet resultSet) throws SQLException {
        User user = buildUserWithIdAccountAndPersonalInfo(resultSet);
        user.getAccount().setTariffPlan(buildTariffPlan(resultSet));
        user.setRole(Role.values()[resultSet.getInt("role")]);
        return user;
    }

    private Account buildAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getString("account_id"));
        account.setBalance(resultSet.getLong("balance"));
        return account;
    }

    private TariffPlan buildTariffPlan(ResultSet resultSet) throws SQLException {
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setId(resultSet.getString("tariff_plan_id"));
        tariffPlan.setName(resultSet.getString("tariff_plan_name"));
        tariffPlan.setCostPerMonth(resultSet.getLong("cost_per_month"));
        return tariffPlan;
    }

    private PersonalInformation buildPersonalInformation(ResultSet resultSet) throws SQLException {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(resultSet.getString("personal_info_id"));
        personalInformation.setFirstName(resultSet.getString("first_name"));
        personalInformation.setLastName(resultSet.getString("last_name"));
        personalInformation.setPatronymicName(resultSet.getString("patronymic_name"));
        personalInformation.setPhoneNumber(resultSet.getString("phone_number"));
        return personalInformation;
    }

    private Address buildAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getString("address_id"));
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setHouseNumber(resultSet.getInt("house_number"));
        address.setBlock(resultSet.getInt("block"));
        address.setFlat(resultSet.getInt("flat"));
        return address;
    }

    private String createUserAccount(User user) throws SQLException {
        String newAccountId = UUID.randomUUID().toString();
        Account account = user.getAccount();
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_ACCOUNT_SQL);
        statement.setString(1, newAccountId);
        statement.setString(2, account.getTariffPlan().getId());
        statement.executeUpdate();
        return newAccountId;
    }

    private String createUserAddress(User user) throws SQLException {
        String newAddressId = UUID.randomUUID().toString();
        Address address = user.getPersonalInformation().getAddress();
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_ADDRESS_SQL);
        statement.setString(1, newAddressId);
        statement.setString(2, address.getCity());
        statement.setString(3, address.getStreet());
        statement.setInt(4, address.getHouseNumber());
        statement.setInt(5, address.getBlock());
        statement.setInt(6, address.getFlat());
        statement.executeUpdate();
        return newAddressId;
    }

    private String createPersonalInfo(User user, String newAddressId) throws SQLException {
        String newPersonalInfoId = UUID.randomUUID().toString();
        PersonalInformation personalInformation = user.getPersonalInformation();
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_PERSONAL_INFO_SQL);
        statement.setString(1, newPersonalInfoId);
        statement.setString(2, personalInformation.getFirstName());
        statement.setString(3, personalInformation.getLastName());
        statement.setString(4, personalInformation.getPatronymicName());
        statement.setString(5, personalInformation.getPhoneNumber());
        statement.setString(6, newAddressId);
        statement.executeUpdate();
        return newPersonalInfoId;
    }

    private void createUser(User user, String newAccountId, String newPersonalInfoId) throws SQLException {
        String newUserId = UUID.randomUUID().toString();
        PreparedStatement statement = connection.prepareStatement(CREATE_USER_SQL);
        statement.setString(1, newUserId);
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getRole().ordinal());
        statement.setString(5, newPersonalInfoId);
        statement.setString(6, newAccountId);
        statement.executeUpdate();
    }

    private void updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getRole().ordinal());
        statement.setString(4, user.getPersonalInformation().getId());
        statement.setString(5, user.getAccount().getId());
        statement.setString(6, user.getId());
        statement.executeUpdate();
    }

    private void updateUserPersonalInfo(User user) throws SQLException {
        PersonalInformation personalInformation = user.getPersonalInformation();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PERSONAL_INFO_SQL);
        statement.setString(1, personalInformation.getFirstName());
        statement.setString(2, personalInformation.getLastName());
        statement.setString(3, personalInformation.getPatronymicName());
        statement.setString(4, personalInformation.getPhoneNumber());
        statement.setString(5, personalInformation.getAddress().getId());
        statement.setString(6, personalInformation.getId());
        statement.executeUpdate();
    }

    private void updateUserAddress(User user) throws SQLException {
        Address address = user.getPersonalInformation().getAddress();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ADDRESS_SQL);
        statement.setString(1, address.getCity());
        statement.setString(2, address.getStreet());
        statement.setInt(3, address.getHouseNumber());
        statement.setInt(4, address.getBlock());
        statement.setInt(5, address.getFlat());
        statement.setString(6, address.getId());
        statement.executeUpdate();
    }

    private void updateUserAccount(User user) throws SQLException {
        Account account = user.getAccount();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ACCOUNT_SQL);
        System.out.println(account.getTariffPlan().getId());
        statement.setString(1, account.getTariffPlan().getId());
        statement.setString(2, account.getId());
        statement.executeUpdate();
    }
}




