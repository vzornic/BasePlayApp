package repositories.filters;

import org.hibernate.criterion.Criterion;

/**
 * Filter interface.
 * Filters are used in repositories for filtering data by single or multiple conditions.
 * Filters may contain any Business Logic needed.
 *
 * @author Vedad
 */
public interface Filter {

    /**
     * Produces {@link Criterion} which is used by Hibernates EM.
     *
     * @return the criterion
     */
    Criterion filter();
}
