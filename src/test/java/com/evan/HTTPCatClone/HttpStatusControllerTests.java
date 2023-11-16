package com.evan.HTTPCatClone;

import com.evan.HTTPCatClone.service.HttpStatusService;
import com.evan.HTTPCatClone.web.HttpStatusController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HttpStatusController.class)
class HttpStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpStatusService httpStatusService;

    @Test
    public void testGetStatusGroup_whenStatusIsValid_return200Status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statusGroup/200"))
                .andExpect(status().isOk());
    }

}
