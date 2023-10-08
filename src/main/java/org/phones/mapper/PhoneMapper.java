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

}
