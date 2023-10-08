/**
 * @author Bledar B
 */
package org.phones.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * <p> Integration test using the 'Spring Data' profile.
 *
 * @author Bledar B
 * @see AbstractPhonesServiceTests AbstractPhonesServiceTests for more details. </p>
 */

@SpringBootTest
@ActiveProfiles({"spring-data-jpa", "hsqldb"})
public class PhonesServiceSpringDataJpaTests extends AbstractPhonesServiceTests {

}
