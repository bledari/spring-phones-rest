/**
 * @author Bledar B
 */

package org.phones.mapper;

import org.phones.dtos.PhoneDto;
import org.phones.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PhoneMapper {

    @Autowired
    private PhoneApiInfoMapper phoneApiInfoMapper;

    public PhoneDto toPhoneDto(Phone phone) {
        PhoneDto dto = new PhoneDto();
        dto.setId(phone.getId());
        dto.setInfo(phoneApiInfoMapper.toPhoneApiInfoDto(phone.getInfo()));
        dto.setAvailability(phone.getAvailability());
        dto.setBookedAt(phone.getBookedAt());
        dto.setBooker(phone.getBooker());
        return dto;
    }

    public Collection<PhoneDto> toPhoneDtos(Collection<Phone> phones) {
        return phones.stream().map(phone -> toPhoneDto(phone)).toList();
    }

    public Phone toPhone(PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setId(phoneDto.getId());
        phone.setInfo(phoneApiInfoMapper.toPhoneApiInfo(phoneDto.getInfo()));
        phone.setAvailability(phoneDto.getAvailability());
        phone.setBookedAt(phoneDto.getBookedAt());
        phone.setBooker(phoneDto.getBooker());
        return phone;
    }

    public Collection<Phone> toPhones(Collection<PhoneDto> phoneDtos) {
        return phoneDtos.stream().map(phoneDto -> toPhone(phoneDto)).toList();
    }

}
