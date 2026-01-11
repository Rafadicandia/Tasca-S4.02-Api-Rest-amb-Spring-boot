package cat.itacademy.s04.t02.n02.fruit.provider.controller;

import cat.itacademy.s04.t02.n02.fruit.provider.model.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.provider.service.ProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@WebMvcTest(ProviderController.class)
class ProviderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProviderService fruitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnBadRequestWhenProviderNameIsEmpty() throws Exception {
        ProviderDTO invalidProvider = new ProviderDTO("", "Spain");

        mockMvc.perform(post("/providers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProvider)))
                .andExpect(status().isBadRequest());
    }

}