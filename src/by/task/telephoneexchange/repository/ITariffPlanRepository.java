package by.task.telephoneexchange.repository;

import by.task.telephoneexchange.domain.TariffPlan;

import java.util.List;

/**
 * ITariffPlanRepository.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public interface ITariffPlanRepository extends IRepository {

    // TODO Add JavaDoc
    List<TariffPlan> readAll() throws RepositoryException;
}
