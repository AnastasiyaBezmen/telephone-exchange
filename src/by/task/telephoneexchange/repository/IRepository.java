package by.task.telephoneexchange.repository;

import by.task.telephoneexchange.domain.Entity;

/**
 * IRepository.
 * Date: 12/20/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IRepository<T extends Entity> {

    // TODO Add JavaDoc
    void create(T Entity) throws RepositoryException;

    T read(String id) throws RepositoryException;

    void update(T Entity) throws RepositoryException;

    void delete(String id) throws RepositoryException;
}
