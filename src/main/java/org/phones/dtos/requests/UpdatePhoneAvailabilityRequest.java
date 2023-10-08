/**
 * @author Bledar B
 */
package org.phones.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdatePhoneAvailabilityRequest {
    @NotNull
    private Boolean availability;

    @Size(min = 5, max = 200, message = "Booker name must be between 5 and 200 characters")
    private String booker;

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}
