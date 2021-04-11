package by.task.telephoneexchange.repository;

import by.task.telephoneexchange.domain.Entity;
import by.task.telephoneexchange.domain.TariffPlan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TariffPlanRepository.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public class TariffPlanRepository implements ITariffPlanRepository {

    private static final String FIND_ALL_TARIFF_PLAN_SQL =
            "SELECT tariff_plan.id, tariff_plan.name, tariff_plan.cost_per_month FROM tariff_plan";
    private Connection connection;

    /**
     * Constructor.
     *
     * @param connection connection to database
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Entity entity) throws RepositoryException {
        throw new RuntimeException("Метод не поддерживается");
    }

    @Override
    public Entity read(String id) throws RepositoryException {
        throw new RuntimeException("Метод не поддерживается");
    }

    @Override
    public void update(Entity entity) throws RepositoryException {
        throw new RuntimeException("Метод не поддерживается");
    }

    @Override
    public void delete(String id) throws RepositoryException {
        throw new RuntimeException("Метод не поддерживается");
    }

    @Override
    public List<TariffPlan> readAll() throws RepositoryException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_TARIFF_PLAN_SQL);
            List<TariffPlan> tariffPlans = new ArrayList<>();
            while (resultSet.next()) {
                tariffPlans.add(buildTariffPlan(resultSet));
            }
            return tariffPlans;
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

    private TariffPlan buildTariffPlan(ResultSet resultSet) throws SQLException {
        TariffPlan tariffPlan = new TariffPlan();
        tariffPlan.setId(resultSet.getString("id"));
        tariffPlan.setName(resultSet.getString("name"));
        tariffPlan.setCostPerMonth(resultSet.getLong("cost_per_month"));
        return tariffPlan;
    }
}
