package services;

import exceptions.ServiceException;
import models.BaseModel;
import repositories.Repository;
import repositories.filters.Filter;

import java.util.Collection;

/**
 * The interface Service.
 *
 * @param <R> the type parameter
 * @param <M> the type parameter
 * @author Vedad
 */
public interface Service<R extends Repository<M>, M extends BaseModel> {
    /**
     * Find by id m.
     *
     * @param id the id
     * @return the m
     */
    M findById(Long id);

    /**
     * Filter collection.
     *
     * @param filter the find
     * @return the collection
     */
    Collection<M> find(Filter filter);

    /**
     * Create m.
     *
     * @param model the model
     * @return the m
     */
    M create(M model) throws ServiceException;

    /**
     * Update m.
     *
     * @param model the model
     * @return the m
     */
    M update(Long id, M model) throws ServiceException;

    /**
     * Delete.
     *
     * @param model the model
     */
    void delete(Long id) throws ServiceException;
}
