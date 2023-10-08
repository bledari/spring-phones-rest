/**
 * @author Bledar B
 */
package org.phones.service;

import org.phones.model.Phone;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PhoneService {

    Phone findPhoneById(int id) throws DataAccessException;

    Collection<Phone> findAllPhones() throws DataAccessException;

    void savePhone(Phone phone) throws DataAccessException;

    void deletePhone(Phone phone) throws DataAccessException;

}
