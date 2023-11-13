package com.evan.HTTPCatClone;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import com.evan.HTTPCatClone.service.HttpStatusService;
import com.evan.HTTPCatClone.web.HttpStatusController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.extension.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class statusGroupEndpointTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HttpStatusRepository repository;

    @MockBean
    @InjectMocks
    private HttpStatusService service;

    @MockBean
    @InjectMocks
    HttpStatusController controller;

    private byte[] image ;
    private HttpStatus testStatus;
    private HttpStatus testStatusSecond;
    private List<HttpStatus> testStatusList;

    private static final List<String> validStatusGroupCodes = new ArrayList<>();
    static {
        Collections.addAll(validStatusGroupCodes,"100","200","300","400","500");
    }

    @BeforeEach
    void setup(){
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        image = new byte[1];
        testStatus = new HttpStatus("500", image);
        testStatusSecond = new HttpStatus("599", image);
        testStatusList = new ArrayList<>();
        repository = mock(HttpStatusRepository.class);
        service = new HttpStatusService(repository);
        System.out.println("Service initialized: " + service);
        Mockito.when(repository.save(Mockito.any(HttpStatus.class))).thenAnswer(invocation -> {
            HttpStatus argument = invocation.getArgument(0);
            testStatusList.add(argument);
            return argument;
        });
        service.save(testStatus);
        service.save(testStatusSecond);
        System.out.println("Setup complete!");
    }

    @AfterEach
    void cleanup(){
        service = null;
        testStatusList = null;
        repository = null;
        System.out.println("Cleanup complete!");
    }

    //-----------------------------------------------------------------
    // UNIT TESTS

    @Test
    void shouldReturnTrueIfStatusGroupFound() {
        assertThat(service.getStatusGroup("400")).isNotNull();
    }

    @Test
    void shouldReturnResponseStatusExceptionIfStatusGroupInvalid(){
        assertThrows(ResponseStatusException.class, () -> {
           service.getStatusGroup("599");
        });
    }

    @Test
    void shouldReturnFalseIfListNotEmpty() {
        if (testStatusList.isEmpty()) {
            System.out.println("Empty!");
            System.out.println(testStatusList);
        }
        assertThat(testStatusList.isEmpty()).isFalse();
    }

    @Test
    void shouldReturnTrueIfListHasMultipleElements(){
        assertThat(testStatusList.contains(testStatus)).isTrue();
        assertThat(testStatusList.contains(testStatusSecond)).isTrue();
    }

    @Test
    void shouldThrowNullPointerExceptionIfRepositoryEmpty(){
        cleanup();
        assertThrows(NullPointerException.class, () -> {
            service.getByStatus("500");
        });
    }

    @Test
    void shouldReturnTrueIfNewStatusAddedViaGetStatusGroupMethod() {
        Mockito.when(service.getByStatus(Mockito.anyString())).thenReturn(new HttpStatus("585", image));
        List<HttpStatus> result = service.getStatusGroup("500");
        HttpStatus testStatusNew = new HttpStatus("585", image);
        assertThat(result).contains(testStatusNew);
    }

    // ----------------------------------------------------------------
    // INTEGRATION TESTS


    @Test
    void shouldPerformGetRequestSuccessfullyWithStatusGroupEndpoint() throws Exception {
        System.out.println("Post Setup?");

        Random random = new Random();
        String randomValidStatusCode = validStatusGroupCodes.get(random.nextInt(validStatusGroupCodes.size()));

        System.out.println("Service: " + service);

        Mockito.when(service.getStatusGroup(randomValidStatusCode)).thenAnswer(invocation -> {
            String argument = invocation.getArgument(0);
            service.getStatusGroup(argument);
            return argument;
        });

        System.out.println("Service Check After Mockito.when but before mockMvc.perform(): " + service);

        mockMvc.perform(MockMvcRequestBuilders.get("/statusGroup/{status}", "500")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        System.out.println("Service State After mockMvc.perform: " + service);

    }
}
