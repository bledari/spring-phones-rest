/**
 * @author Bledar B
 */
package org.phones.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bledar B
 * Simple test to make sure that Bean Validation is working
 * (useful when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    private static PhoneApiInfo makePhoneApiInfo() {
        PhoneApiInfo phoneApiInfo = new PhoneApiInfo();
        phoneApiInfo.setId(1);
        phoneApiInfo.setPhoneId("iPhone X");
        phoneApiInfo.setBrand("Apple");
        phoneApiInfo.setTechnology("LTE");
        phoneApiInfo.setGprs("Yes");
        phoneApiInfo.setEdge("Yes");
        phoneApiInfo.setAnnounced("2022");
        phoneApiInfo.setStatus("Available");
        phoneApiInfo.setDimensions("146.7 x 71.5 x 7.4 mm (5.78 x 2.81 x 0.29 in)");
        phoneApiInfo.setWeight("220g");
        phoneApiInfo.setSim("nano");
        phoneApiInfo.setDisplayType("OLED");
        phoneApiInfo.setDisplaySize("1080p");
        return phoneApiInfo;
    }

    @Test
    void shouldValidateOnlyWhenPhoneIdIsValid() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        PhoneApiInfo phoneApiInfo = makePhoneApiInfo();

        Validator validator = createValidator();
        Set<ConstraintViolation<PhoneApiInfo>> constraintViolations = validator.validate(phoneApiInfo);
        assertThat(constraintViolations.size()).isEqualTo(0);

        phoneApiInfo.setEdge(null);
        constraintViolations = validator.validate(phoneApiInfo);
        assertThat(constraintViolations.size()).isEqualTo(1);
        Iterator<ConstraintViolation<PhoneApiInfo>> iterator = constraintViolations.iterator();
        ConstraintViolation<PhoneApiInfo> violation = iterator.next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("edge");
        assertThat(violation.getMessage()).isEqualTo("edge name is mandatory");

        phoneApiInfo.setEdge("Yes");
        phoneApiInfo.setPhoneId("KO");
        constraintViolations = validator.validate(phoneApiInfo);
        assertThat(constraintViolations.size()).isEqualTo(1);
        iterator = constraintViolations.iterator();
        violation = iterator.next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("phoneId");
        assertThat(violation.getMessage()).isEqualTo("Phone ID name must be between 5 and 200 characters");
    }
}
