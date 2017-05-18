package controllers;

import exceptions.ServiceException;
import models.BaseModel;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.Repository;
import services.Service;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;

/**
 * The type Base controller.
 *
 * @param <S> the type parameter
 * @param <M> the type parameter
 * @param <R> the type parameter
 * @author Vedad
 */
public abstract class BaseController<S extends Service<R, M>, M extends BaseModel, R extends Repository<M>> extends Controller {

    protected S service;

    protected FormFactory formFactory;

    /**
     * Sets form factory.
     *
     * @param formFactory the form factory
     */
    @Inject
    public void setFormFactory(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(S service) {
        this.service = service;
    }

    /**
     * Find by id result.
     *
     * @param id the id
     * @return the result
     */
    public Result findById(Long id) {
        try {
            return ok(Json.toJson(service.findById(id)));
        } catch (Exception e) {
            Logger.error("Failed to fetch requested model, for given id", e, id);
            return internalServerError();
        }
    }

    /**
     * Create result.
     *
     * @return the result
     */
    public Result create() {
        try {
            Form<M> form = formFactory.form(model()).bindFromRequest();
            if (form.hasErrors()) {
                Logger.error("Invalid request submitted for create request.");
                return badRequest();
            }
            return ok(Json.toJson(service.create(null)));
        } catch (ServiceException e) {
            Logger.error("Failed to create requested entity due the wrong request.");
            return badRequest();
        } catch (Exception e) {
            Logger.error("Failed to create requested entity due the internal server error.");
            return internalServerError();
        }
    }

    /**
     * Update result.
     *
     * @param id the id
     * @return the result
     */
    public Result update(Long id) {
        try {
            Form<M> form = formFactory.form(model()).bindFromRequest();
            if (form.hasErrors()) {
                Logger.error("Invalid request submitted for update request.");
                return badRequest();
            }
            return ok(Json.toJson(service.update(id, null)));
        } catch (ServiceException e) {
            Logger.error("Failed to update requested entity due the wrong request.");
            return badRequest();
        } catch (Exception e) {
            Logger.error("Failed to update requested entity due the internal server error.");
            return internalServerError();
        }
    }

    /**
     * Delete result.
     *
     * @param id the id
     * @return the result
     */
    public Result delete(Long id) {
        try {
            service.delete(id);
            return ok();
        } catch (ServiceException e) {
            Logger.error("Failed to delete requested entity due the wrong request.");
            return badRequest();
        } catch (Exception e) {
            Logger.error("Failed to delete requested entity due the internal server error.");
            return internalServerError();
        }
    }

    protected String getQueryString(String key) {
        return request().getQueryString(key);
    }

    private Class<M> model() {
        return (Class<M>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
