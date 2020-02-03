package user.assessment.restfulapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import user.assessment.restfulapi.model.ExchangeCurrency;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest
{

    @Autowired
    private MockMvc mvc;
     
    @Test
    public void testGetAllCurrencies() throws Exception 
    {
      mvc.perform( MockMvcRequestBuilders
          .get("api/v1/currency")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].currencyId").isNotEmpty());
    }
     
    @Test
    public void testGetCurrencyById() throws Exception 
    {
      mvc.perform( MockMvcRequestBuilders
          .get("api/v1/currency/{id}", 1)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.currencyId").value(1));
    }
    
    @Test
    public void testCreateCurrency() throws Exception 
    {
      mvc.perform( MockMvcRequestBuilders
          .post("/currency")
          .content(asJsonString(new ExchangeCurrency()))        
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.currencyId").exists());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testGetExchangeRate() throws Exception
    {
        ExchangeCurrency targetCurrency = new ExchangeCurrency();
        targetCurrency.setName("USD");
        mvc.perform( MockMvcRequestBuilders
            .post("/currency/getExchangeRate")
            .content(asJsonString(targetCurrency.getExchangeRate(targetCurrency)))        
            .contentType(MediaType.ALL)
            .accept(MediaType.ALL));
    }
    
    @Test
    public void testGetExchangeAmount() throws Exception 
    {
        ExchangeCurrency targetCurrency = new ExchangeCurrency();
        targetCurrency.setName("USD");
        mvc.perform( MockMvcRequestBuilders
            .post("/currency/getExchangeAmount/{amount}")
            .content(asJsonString(targetCurrency.getExchangeAmount(targetCurrency, 1000)))        
            .contentType(MediaType.ALL)
            .accept(MediaType.ALL));
    }
}
