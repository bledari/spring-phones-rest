/**
 * @author Bledar B
 */
package org.phones.service;

import org.phones.model.PhoneApiInfo;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PhoneApiInfoService {

    PhoneApiInfo findPhoneById(int id) throws DataAccessException;

    Collection<PhoneApiInfo> findAllPhones() throws DataAccessException;

    void savePhoneApiInfo(PhoneApiInfo phoneApiInfo) throws DataAccessException;

    void deletePhoneApiInfo(PhoneApiInfo phoneApiInfo) throws DataAccessException;

}
