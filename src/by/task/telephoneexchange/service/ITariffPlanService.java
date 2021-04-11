package by.task.telephoneexchange.service;

import by.task.telephoneexchange.domain.TariffPlan;

import java.util.List;

/**
 * ITariffPlanService.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public interface ITariffPlanService {

    // TODO Add JavaDoc
    List<TariffPlan> getAll() throws ServiceException;
}
