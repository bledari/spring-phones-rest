/**
 * @author Bledar B
 */
package org.phones.repository;

import org.phones.model.Phone;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PhoneRepository {

    /**
     * Retrieve all <code>Phone</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Phone</code>s
     */
    @Query("SELECT DISTINCT p FROM Phone p LEFT JOIN FETCH p.info")
    Collection<Phone> findAll() throws DataAccessException;

    Phone findById(int id) throws DataAccessException;

    void save(Phone phone) throws DataAccessException;

    void delete(Phone phone) throws DataAccessException;


}
