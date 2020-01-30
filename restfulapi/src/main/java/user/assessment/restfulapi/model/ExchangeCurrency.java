package user.assessment.restfulapi.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="exchange_currency")
public class ExchangeCurrency extends Currency
{

    @Override
    public BigDecimal getExchangeRate(Currency targetCurrency)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BigDecimal getExchangeAmount(Currency targetCurrency)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
