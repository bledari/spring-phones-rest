/**
 * @author Bledar B
 */
package org.phones.service;

import org.phones.model.Phone;
import org.phones.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class PhoneServiceImpl implements PhoneService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Phone> findAllPhones() throws DataAccessException {
        return phoneRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePhone(Phone phone) throws DataAccessException {
        phoneRepository.delete(phone);
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public Phone findPhoneById(int id) throws DataAccessException {
        Phone phone = null;
        try {
            phone = phoneRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            logger.warn(e.getClass().getCanonicalName());
            logger.warn(e.getMessage());
            return null;
        }
        return phone;
    }

}
