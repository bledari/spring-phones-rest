/**
 * @author Bledar B
 */
package org.phones.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.phones.dtos.PhoneDto;
import org.phones.dtos.requests.BookPhoneRequest;
import org.phones.mapper.PhoneMapper;
import org.phones.model.Phone;
import org.phones.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhoneRestController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneRestController.class);
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneRestController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @PreAuthorize("hasRole(@roles.PHONES_ADMIN)")
    @GetMapping("/")
    public ResponseEntity<List<PhoneDto>> listPhones() {
        List<PhoneDto> phones = new ArrayList<>();
        phones.addAll(phoneMapper.toPhoneDtos(this.phoneService.findAllPhones()));
        if (phones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.PHONES_ADMIN)")
    @PutMapping("/book/{phoneId}")
    public ResponseEntity<PhoneDto> bookPhone(@PathVariable @NotNull @Positive(message = "Phone ID must be a positive integer") Integer phoneId, @Valid @RequestBody BookPhoneRequest bookPhoneRequest) throws Exception {
        Phone currentPhone = this.phoneService.findPhoneById(phoneId);
        if (currentPhone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!currentPhone.getAvailability()) {
            if (bookPhoneRequest.getBooker().equals(currentPhone.getBooker())) {
                return new ResponseEntity<>(phoneMapper.toPhoneDto(currentPhone), HttpStatus.OK);
            } else throw new Exception("Phone already booked by someone else.");
        }
        currentPhone.setAvailability(false);
        currentPhone.setBooker(bookPhoneRequest.getBooker());
        currentPhone.setBookedAt(LocalDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0));
        this.phoneService.savePhone(currentPhone);
        return new ResponseEntity<>(phoneMapper.toPhoneDto(currentPhone), HttpStatus.OK);
    }

    @PreAuthorize("hasRole(@roles.PHONES_ADMIN)")
    @PutMapping("/return/{phoneId}")
    public ResponseEntity<PhoneDto> returnPhone(@PathVariable @NotNull @Positive(message = "Phone ID must be a positive integer") Integer phoneId) {
        Phone currentPhone = this.phoneService.findPhoneById(phoneId);
        if (currentPhone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // This can be changed to return BadRequest (as the endpoint above) if the application specification requires it
        currentPhone.setAvailability(true);
        currentPhone.setBooker(null);
        currentPhone.setBookedAt(null);
        this.phoneService.savePhone(currentPhone);
        return new ResponseEntity<>(phoneMapper.toPhoneDto(currentPhone), HttpStatus.OK);
    }
}




