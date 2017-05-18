package services.impl;

import exceptions.RepositoryException;
import exceptions.ServiceException;
import models.BaseModel;
import repositories.Repository;
import repositories.filters.Filter;
import services.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * The type Base service.
 *
 * @param <R> the repository
 * @param <M> the model
 * @author Vedad
 */
public class BaseServiceImpl<R extends Repository<M>, M extends BaseModel> implements Service<R, M> {

    protected R repository;

    /**
     * Sets repository.
     *
     * @param repository the repository
     */
    @Inject
    public void setRepository(R repository) {
        this.repository = repository;
    }

    @Override
    public M findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Collection<M> find(Filter filter) {
        return repository.find(filter);
    }

    @Override
    public M create(M model) throws ServiceException {
        try {
            return repository.create(model);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public M update(Long id, M model) throws ServiceException {
        try {
            M original = findById(id);
            original.update(model);
            return repository.update(original);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            repository.delete(findById((id)));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
