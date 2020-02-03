package user.assessment.restfulapi.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.assessment.restfulapi.exception.ResourceNotFoundException;
import user.assessment.restfulapi.model.Currency;
import user.assessment.restfulapi.model.ExchangeCurrency;
import user.assessment.restfulapi.repository.CurrencyRepository;

@RestController
@RequestMapping("api/v1")
public class CurrencyController
{
    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping("/currency")
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @GetMapping("/currency/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable(value = "id") Long currencyId)
            throws ResourceNotFoundException {
        
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found: " + currencyId));
        
        return ResponseEntity.ok().body(currency);
    }

    @PostMapping("/currency")
    public Currency createCurrency(@Valid @RequestBody Currency currency) {
        return currencyRepository.save(currency);
    }

    @PutMapping("/currency/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable(value = "id") Long currencyId,
            @Valid @RequestBody Currency currencyDetails) throws ResourceNotFoundException {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found: " + currencyId));

        currency.setName(currencyDetails.getName());
        
        final Currency updatedCurrency = currencyRepository.save(currency);
        
        return ResponseEntity.ok(updatedCurrency);
    }

    @DeleteMapping("/currency/{id}")
    public Map<String, Boolean> deleteCurrency(@PathVariable(value = "id") Long currencyId)
            throws ResourceNotFoundException {
        
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found: " + currencyId));

        currencyRepository.delete(currency);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
    }
    
    @GetMapping("/currency/getExchangeRate")
    public BigDecimal getExchangeRate(@Valid @RequestBody ExchangeCurrency targetCurrency)
            throws ResourceNotFoundException {
        
        
        return targetCurrency.getExchangeRate(targetCurrency);
    }
    
    @GetMapping("/currency/getExchangeAmount/{amount}")
    public BigDecimal getExchangeAmount(@PathVariable(value = "amount") Double amount,
        @Valid @RequestBody ExchangeCurrency targetCurrency)
            throws ResourceNotFoundException {
        
        
        return targetCurrency.getExchangeAmount(targetCurrency, amount);
    }


}
