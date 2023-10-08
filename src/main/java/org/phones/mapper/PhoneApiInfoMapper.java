/**
 * @author Bledar B
 */
package org.phones.mapper;

import org.phones.model.PhoneApiInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PhoneApiInfoMapper {
    public org.phones.dtos.PhoneApiInfoDto toPhoneApiInfoDto(PhoneApiInfo phoneInfo) {
        org.phones.dtos.PhoneApiInfoDto dto = new org.phones.dtos.PhoneApiInfoDto();
        dto.setId(phoneInfo.getId());
        dto.setPhoneId(phoneInfo.getPhoneId());
        dto.setBrand(phoneInfo.getBrand());
        dto.setTechnology(phoneInfo.getTechnology());
        dto.setGprs(phoneInfo.getGprs());
        dto.setEdge(phoneInfo.getEdge());
        dto.setAnnounced(phoneInfo.getAnnounced());
        dto.setStatus(phoneInfo.getStatus());
        dto.setDimensions(phoneInfo.getDimensions());
        dto.setWeight(phoneInfo.getWeight());
        dto.setSim(phoneInfo.getSim());
        dto.setDisplayType(phoneInfo.getDisplayType());
        dto.setDisplaySize(phoneInfo.getDisplaySize());
        return dto;
    }

    public Collection<org.phones.dtos.PhoneApiInfoDto> toPhoneApiInfoDtos(Collection<PhoneApiInfo> phones) {
        return phones.stream().map(phone -> toPhoneApiInfoDto(phone)).toList();
    }
}
