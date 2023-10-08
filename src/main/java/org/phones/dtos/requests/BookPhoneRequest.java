/**
 * @author Bledar B
 */
package org.phones.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookPhoneRequest {

    @NotBlank(message = "Booker name is mandatory")
    @Pattern(regexp = "^[A-Za-z]+[A-Z a-z]+$", message = "Username must contain only English alphabet characters and spaces (after first letter only)")
    @Size(min = 5, max = 200, message = "Booker name must be between 5 and 200 characters")
    private String booker;

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}
