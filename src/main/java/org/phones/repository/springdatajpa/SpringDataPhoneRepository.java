/**
 * @author Bledar B
 */
package org.phones.repository.springdatajpa;

import org.phones.model.Phone;
import org.phones.repository.PhoneRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

/**
 * Spring Data JPA specialization of the {@link PhoneRepository} interface
 */

@Profile("spring-data-jpa")
public interface SpringDataPhoneRepository extends PhoneRepository, Repository<Phone, Integer> {
}
