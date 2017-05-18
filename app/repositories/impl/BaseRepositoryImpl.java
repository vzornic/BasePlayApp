package repositories.impl;

import exceptions.RepositoryException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPAApi;
import repositories.Repository;
import repositories.filters.Filter;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * Base implementation of Repository.
 * This repository provides CRUD operators for any models.
 * In case additiona business logic is needed, methods must be override.
 *
 * @param <T> the type parameter
 * @author Vedad
 */
public class BaseRepositoryImpl<T> implements Repository<T> {

    private JPAApi jpa;

    /**
     * Sets JPAApi
     *
     * @param jpa the jpa
     */
    @Inject
    public void setJpa(JPAApi jpa) {
        this.jpa = jpa;
    }

    @Override
    public T findById(Long id) {
        return (T) base().add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public Collection<T> find(Filter filter) {
        return base().add(filter.filter()).list();
    }

    @Override
    public T create(T model) throws RepositoryException {
        jpa.em().persist(model);
        return model;
    }

    @Override
    public T update(T model) throws RepositoryException {
        return jpa.em().merge(model);
    }

    @Override
    public void delete(T model) throws RepositoryException {
        jpa.em().remove(model);
    }

    /**
     * Creates base criteria for entity.
     *
     * @return the criteria
     */
    protected Criteria base() {
        return ((HibernateEntityManager)jpa.em()).getSession().createCriteria(clazz());
    }

    private Class<T> clazz() {
        return (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
