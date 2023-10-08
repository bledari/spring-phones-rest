/**
 * @author Bledar B
 */
package org.phones.repository.springdatajpa;

import org.phones.model.PhoneApiInfo;
import org.phones.repository.PhoneApiInfoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

/**
 * Spring Data JPA specialization of the {@link PhoneApiInfoRepository} interface
 */

@Profile("spring-data-jpa")
public interface SpringDataPhoneApiInfoRepository extends PhoneApiInfoRepository, Repository<PhoneApiInfo, Integer> {
}
