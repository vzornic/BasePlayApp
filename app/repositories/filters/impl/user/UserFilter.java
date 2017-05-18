package repositories.filters.impl.user;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import repositories.filters.Filter;

/**
 * @author Vedad
 */
public class UserFilter implements Filter {
    private static final String EMAIL = "email";
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Criterion filter() {
        Conjunction result = Restrictions.and();
        if (email != null) {
            result.add(Restrictions.eq(EMAIL, email));
        }
        return result;
    }
}
