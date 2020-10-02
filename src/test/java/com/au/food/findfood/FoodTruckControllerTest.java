package com.au.food.findfood;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodTruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindFoodTrucks() throws Exception{

        String testUrl = "/findfoodtruck?longitude=-122.397726709152&latitude=37.7875398934675&noOfResults=5";
        this.mockMvc.perform(get(testUrl).headers(getHttpHeader())).andExpect(status().isOk());

    }

    @Test
    public void testFindFoodTrucks_InvalidInput() throws Exception{

        String testUrl = "/findfoodtruck?longitude=-122.397726709152&latitude=37.7875398934675";
        this.mockMvc.perform(get(testUrl).headers(getHttpHeader())).andExpect(status().is4xxClientError());

    }

    private HttpHeaders getHttpHeader(){
    HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("Accept", "application/json");
        return httpHeader;
}
}
