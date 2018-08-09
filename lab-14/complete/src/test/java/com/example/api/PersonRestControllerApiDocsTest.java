package com.example.api;

import com.example.entity.AddressBuilder;
import com.example.entity.PersonBuilder;
import com.example.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = PersonRestController.class)
class PersonRestControllerApiDocsTest {

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private PersonService personService;

  @BeforeEach
  void setUp(
      WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint()))
            .build();
  }

  @Test
  void getAllPersons() throws Exception {

    given(personService.findAll())
        .willReturn(
            Collections.singletonList(
                PersonBuilder.person().addAddress(AddressBuilder.address().build()).build()));
    mockMvc.perform(get("/persons")).andExpect(status().isOk()).andDo(document("get-persons"));
  }

  @Test
  void getPerson() throws Exception {

    UUID identifier = UUID.randomUUID();
    given(personService.findOneByIdentifier(eq(identifier)))
        .willReturn(
            PersonBuilder.person()
                .withIdentifier(identifier)
                .addAddress(AddressBuilder.address().build())
                .build());
    mockMvc
        .perform(get("/persons/{personId}", identifier.toString()))
        .andExpect(status().isOk())
        .andDo(document("get-person"));
  }

  @Test
  void getAddresses() throws Exception {

    UUID identifier = UUID.randomUUID();
    given(personService.findOneByIdentifier(eq(identifier)))
        .willReturn(
            PersonBuilder.person()
                .withIdentifier(identifier)
                .addAddress(AddressBuilder.address().build())
                .build());
    mockMvc
        .perform(get("/persons/{personId}/addresses", identifier.toString()))
        .andExpect(status().isOk())
        .andDo(document("get-addresses"));
  }

  @Test
  void createPerson() throws Exception {
    UUID identifier = UUID.randomUUID();
    given(personService.save(any())).willAnswer(a -> PersonBuilder.person().build());
    PersonResource personResource = new PersonResource(identifier, "Hans", "Mustermann");
    mockMvc
        .perform(
            post("/persons")
                .content(objectMapper.writeValueAsString(personResource))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andDo(document("create-person"));
  }

  @Test
  void deletePerson() throws Exception {
    UUID identifier = UUID.randomUUID();
    mockMvc
        .perform(delete("/persons/{personId}", identifier.toString()))
        .andExpect(status().isNoContent())
        .andDo(document("delete-person"));
  }
}
