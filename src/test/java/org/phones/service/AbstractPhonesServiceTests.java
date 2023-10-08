/**
 * @author Bledar B
 */
package org.phones.service;

import org.junit.jupiter.api.Test;
import org.phones.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractPhonesServiceTests {

    @Autowired
    protected PhoneService phoneService;

    @Test
    void shouldFindPhoneWithId() {
        Phone phone = this.phoneService.findPhoneById(1);
        assertThat(phone.getId()).isEqualTo(1);
        assertThat(phone.getAvailability()).isEqualTo(true);
    }
}
