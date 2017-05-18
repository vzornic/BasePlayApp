package controllers.user;

import controllers.BaseController;
import models.user.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import repositories.filters.impl.user.UserFilter;
import repositories.impl.user.UserRepository;
import services.impl.user.UserService;

/**
 * The type User controller.
 *
 * @author Vedad
 */
public class UserController extends BaseController<UserService, User, UserRepository> {
    private static final String EMAIL = "email";

    /**
     * Simple implementation of filter request.
     *
     * @return the result
     */
    public Result find() {
      try {
          UserFilter filter = new UserFilter();
          filter.setEmail(getQueryString(EMAIL));
          return ok(Json.toJson(service.find(filter)));
      } catch (Exception e) {
          Logger.error("An error occurred while executing find request.");
          return internalServerError();
      }
    }
}
