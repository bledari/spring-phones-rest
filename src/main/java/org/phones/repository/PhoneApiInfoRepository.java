/**
 * @author Bledar B
 */
package org.phones.repository;

import org.phones.model.PhoneApiInfo;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PhoneApiInfoRepository {

    /**
     * Retrieve all <code>phoneApiInfo</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>phoneApiInfo</code>s
     */
    Collection<PhoneApiInfo> findAll() throws DataAccessException;

    PhoneApiInfo findById(int id) throws DataAccessException;

    void save(PhoneApiInfo phoneApiInfo) throws DataAccessException;

    void delete(PhoneApiInfo phoneApiInfo) throws DataAccessException;


}
