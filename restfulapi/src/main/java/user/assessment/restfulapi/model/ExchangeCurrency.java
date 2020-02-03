package user.assessment.restfulapi.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExchangeCurrency extends Currency
{

    @Override
    public BigDecimal getExchangeRate(Currency targetCurrency)
    {
        if ("USD".equals(targetCurrency.getName())) 
            return new BigDecimal(0.742164);
        else if ("SGD".equals(targetCurrency.getName())) 
            return new BigDecimal(1.34782);
        
        return BigDecimal.ONE;
    }

    @Override
    public BigDecimal getExchangeAmount(Currency targetCurrency, double amount)
    {
        double result = amount * this.getExchangeRate(targetCurrency).doubleValue();
        return new BigDecimal(result);
    }

}
