package repositories;

import exceptions.RepositoryException;
import repositories.filters.Filter;

import java.util.Collection;

/**
 * @author Vedad
 *
 * @param <T> Model/Entity that repository is created for.
 */
public interface Repository<T> {
    /**
     * Finds entity by ID.
     *
     * @return the entity found by ID
     */
    T findById(Long id);

    /**
     * Find collection.
     *
     * @return the collection
     */
    Collection<T> find(Filter filter);

    /**
     * Create entity provided as param.
     *
     * @param model the model
     * @return the created model
     */
    T create(T model) throws RepositoryException;

    /**
     * Update model.
     *
     * @param model the model
     * @return the t
     */
    T update(T model) throws RepositoryException;

    /**
     * Deletes model.
     *
     * @param model the model
     */
    void delete(T model) throws RepositoryException;
}
