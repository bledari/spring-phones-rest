/**
 * @author Bledar B
 */
package org.phones.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDto {
    private Integer id;
    private PhoneApiInfoDto info;

    private Boolean availability;

    private LocalDateTime bookedAt;

    private String booker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhoneApiInfoDto getInfo() {
        return info;
    }

    public void setInfo(PhoneApiInfoDto info) {
        this.info = info;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}

