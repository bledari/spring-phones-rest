/**
 * @author Bledar B
 */
package org.phones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "phone_id")
    private PhoneApiInfo info;

    @Column(name = "availability")
    @NotNull
    private Boolean availability;

    @Column(name = "bookedAt", columnDefinition = "DATETIME")
    private LocalDateTime bookedAt;

    @Column(name = "booker")
    @Pattern(regexp = "^[A-Za-z]+[A-Z a-z]+$", message = "Username must contain only English alphabet characters and spaces (after first letter only)")
    @Size(min = 5, max = 200, message = "Booker name must be between 5 and 200 characters")
    private String booker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhoneApiInfo getInfo() {
        return info;
    }

    public void setInfo(PhoneApiInfo info) {
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

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", this.getId())
            .append("info", this.getInfo())
            .append("availability", this.getAvailability())
            .append("bookedAt", this.getBookedAt())
            .append("booker", this.getBooker())
            .toString();
    }
}
