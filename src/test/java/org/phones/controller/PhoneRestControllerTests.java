/**
 * @author Bledar B
 */

package org.phones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.phones.dtos.PhoneApiInfoDto;
import org.phones.dtos.PhoneDto;
import org.phones.dtos.requests.BookPhoneRequest;
import org.phones.mapper.PhoneMapper;
import org.phones.model.Phone;
import org.phones.rest.advice.ExceptionControllerAdvice;
import org.phones.rest.controller.PhoneRestController;
import org.phones.service.ApplicationTestConfig;
import org.phones.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for {@link PhoneRestController}
 *
 * @author @author Bledar B
 */

@SpringBootTest
@ContextConfiguration(classes = ApplicationTestConfig.class)
@WebAppConfiguration
class PhoneRestControllerTests {

    @MockBean
    protected PhoneService phoneService;
    @Autowired
    private PhoneRestController phoneRestController;
    @Autowired
    private PhoneMapper phoneMapper;
    private MockMvc mockMvc;

    private List<PhoneDto> phoneDtos;

    @BeforeEach
    void initPhones() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(phoneRestController)
            .setControllerAdvice(new ExceptionControllerAdvice())
            .build();

        phoneDtos = new ArrayList<>();

        PhoneApiInfoDto infoDto = new PhoneApiInfoDto();
        infoDto.setId(1);
        infoDto.setPhoneId("iPhone X");
        infoDto.setBrand("Apple");
        infoDto.setTechnology("LTE");
        infoDto.setGprs("Yes");
        infoDto.setEdge("Yes");
        infoDto.setAnnounced("2022");
        infoDto.setStatus("Available");
        infoDto.setDimensions("146.7 x 71.5 x 7.4 mm (5.78 x 2.81 x 0.29 in)");
        infoDto.setWeight("220g");
        infoDto.setSim("nano");
        infoDto.setDisplayType("OLED");
        infoDto.setDisplaySize("1080p");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setId(1);
        phoneDto.setBooker("name");
        phoneDto.setInfo(infoDto);
        phoneDto.setAvailability(false);


        PhoneApiInfoDto infoDto1 = new PhoneApiInfoDto();
        infoDto1.setId(2);
        infoDto1.setPhoneId("S10");
        infoDto1.setBrand("Samsung");
        infoDto1.setTechnology("LTE");
        infoDto1.setGprs("Yes");
        infoDto1.setEdge("Yes");
        infoDto1.setAnnounced("2022");
        infoDto1.setStatus("Available");
        infoDto1.setDimensions("146.7 x 71.5 x 7.4 mm (5.78 x 2.81 x 0.29 in)");
        infoDto1.setWeight("220g");
        infoDto1.setSim("nano");
        infoDto1.setDisplayType("OLED");
        infoDto1.setDisplaySize("1080p");
        PhoneDto phoneDto1 = new PhoneDto();
        phoneDto1.setId(2);
        phoneDto1.setBooker("name2");
        phoneDto1.setInfo(infoDto1);
        phoneDto1.setAvailability(false);


        PhoneApiInfoDto infoDto2 = new PhoneApiInfoDto();
        infoDto2.setId(3);
        infoDto2.setPhoneId("S10 Note");
        infoDto2.setBrand("Samsung");
        infoDto2.setTechnology("LTE");
        infoDto2.setGprs("Yes");
        infoDto2.setEdge("Yes");
        infoDto2.setAnnounced("2022");
        infoDto2.setStatus("Available");
        infoDto2.setDimensions("146.7 x 71.5 x 7.4 mm (5.78 x 2.81 x 0.29 in)");
        infoDto2.setWeight("220g");
        infoDto2.setSim("nano");
        infoDto2.setDisplayType("OLED");
        infoDto2.setDisplaySize("1080p");
        PhoneDto phoneDto2 = new PhoneDto();
        phoneDto2.setId(3);
        phoneDto2.setBooker(null);
        phoneDto2.setInfo(infoDto2);
        phoneDto2.setAvailability(true);

        PhoneDto phoneDto3 = new PhoneDto();
        phoneDto3.setId(4);
        phoneDto3.setBooker(null);
        phoneDto3.setInfo(infoDto2);
        phoneDto3.setAvailability(true);

        phoneDtos.add(phoneDto);
        phoneDtos.add(phoneDto1);
        phoneDtos.add(phoneDto2);
        phoneDtos.add(phoneDto3);
    }


    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testGetAllPhones() throws Exception {
        final Collection<Phone> phones = phoneMapper.toPhones(this.phoneDtos);
        when(this.phoneService.findAllPhones()).thenReturn(phones);
        this.mockMvc.perform(get("/api/phones/")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].booker").value("name"))
            .andExpect(jsonPath("$.[0].info.phoneId").value("iPhone X"))
            .andExpect(jsonPath("$.[1].id").value(2))
            .andExpect(jsonPath("$.[1].booker").value("name2"))
            .andExpect(jsonPath("$.[1].info.phoneId").value("S10"))
            .andExpect(jsonPath("$.[2].info.phoneId").value("S10 Note"))
            .andExpect(jsonPath("$.[3].info.phoneId").value("S10 Note"))
        ;
    }

    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testBookPhoneSuccess() throws Exception {
        final int ID = 4;
        final String BOOKER_NAME = "booker ok";
        given(this.phoneService.findPhoneById(ID)).willReturn(phoneMapper.toPhone(phoneDtos.get(3)));
        BookPhoneRequest bookPhoneRequest = new BookPhoneRequest();
        bookPhoneRequest.setBooker(BOOKER_NAME);
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(bookPhoneRequest);
        this.mockMvc.perform(put("/api/phones/book/" + ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPayload)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(ID))
            .andExpect(jsonPath("$.booker").value(BOOKER_NAME))
            .andExpect(jsonPath("$.availability").value(false))
            .andExpect(jsonPath("$.info.phoneId").value("S10 Note"));
    }

    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testReturnPhoneSuccess() throws Exception {
        final int ID = 1;
        given(this.phoneService.findPhoneById(ID)).willReturn(phoneMapper.toPhone(phoneDtos.get(0)));
        BookPhoneRequest bookPhoneRequest = new BookPhoneRequest();
        this.mockMvc.perform(put("/api/phones/return/" + ID)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(ID))
            .andExpect(jsonPath("$.availability").value(true))
            .andExpect(jsonPath("$.info.phoneId").value("iPhone X"));
    }


    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testReturnReturnedPhoneSuccess() throws Exception {
        final int ID = 4;
        given(this.phoneService.findPhoneById(ID)).willReturn(phoneMapper.toPhone(phoneDtos.get(3)));
        this.mockMvc.perform(put("/api/phones/return/" + ID)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(ID))
            .andExpect(jsonPath("$.availability").value(true))
            .andExpect(jsonPath("$.info.phoneId").value("S10 Note"));
    }


    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testBookPhoneAlreadyBookedError() throws Exception {
        final int ID = 1;
        final String BOOKER_NAME = "booker ok";
        given(this.phoneService.findPhoneById(ID)).willReturn(phoneMapper.toPhone(phoneDtos.get(0)));
        BookPhoneRequest bookPhoneRequest = new BookPhoneRequest();
        bookPhoneRequest.setBooker(BOOKER_NAME);
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(bookPhoneRequest);
        this.mockMvc.perform(put("/api/phones/book/" + ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPayload)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.type").value("generic.error"))
            .andExpect(jsonPath("$.errorMessage").value("Phone already booked by someone else."));
    }

    @Test
    @WithMockUser(roles = "PHONES_ADMIN")
    void testBookPhoneInvalidNameError() throws Exception {
        final int ID = 4;
        final String BOOKER_NAME = "booker ok2";
        given(this.phoneService.findPhoneById(ID)).willReturn(phoneMapper.toPhone(phoneDtos.get(3)));
        BookPhoneRequest bookPhoneRequest = new BookPhoneRequest();
        bookPhoneRequest.setBooker(BOOKER_NAME);
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(bookPhoneRequest);
        this.mockMvc.perform(put("/api/phones/book/" + ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPayload)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.type").value("method.argument.not.valid"))
            .andExpect(jsonPath("$.parameter").value("booker"))
            .andExpect(jsonPath("$.errorMessage").value("Booker name must contain only English alphabet characters and spaces (after first letter only)"));
    }

}
