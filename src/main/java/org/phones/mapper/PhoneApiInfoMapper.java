/**
 * @author Bledar B
 */
package org.phones.mapper;

import org.phones.dtos.PhoneApiInfoDto;
import org.phones.model.PhoneApiInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PhoneApiInfoMapper {
    public PhoneApiInfoDto toPhoneApiInfoDto(PhoneApiInfo phoneInfo) {
        PhoneApiInfoDto dto = new PhoneApiInfoDto();
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

    public Collection<PhoneApiInfoDto> toPhoneApiInfoDtos(Collection<PhoneApiInfo> phones) {
        return phones.stream().map(phone -> toPhoneApiInfoDto(phone)).toList();
    }

    public PhoneApiInfo toPhoneApiInfo(PhoneApiInfoDto dto) {
        PhoneApiInfo apiInfo = new PhoneApiInfo();
        apiInfo.setId(dto.getId());
        apiInfo.setPhoneId(dto.getPhoneId());
        apiInfo.setBrand(dto.getBrand());
        apiInfo.setTechnology(dto.getTechnology());
        apiInfo.setGprs(dto.getGprs());
        apiInfo.setEdge(dto.getEdge());
        apiInfo.setAnnounced(dto.getAnnounced());
        apiInfo.setStatus(dto.getStatus());
        apiInfo.setDimensions(dto.getDimensions());
        apiInfo.setWeight(dto.getWeight());
        apiInfo.setSim(dto.getSim());
        apiInfo.setDisplayType(dto.getDisplayType());
        apiInfo.setDisplaySize(dto.getDisplaySize());
        return apiInfo;
    }

}
