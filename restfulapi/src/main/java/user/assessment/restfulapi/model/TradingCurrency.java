package user.assessment.restfulapi.model;

import java.math.BigDecimal;

public interface TradingCurrency
{

    public BigDecimal getExchangeRate(Currency targetCurrency);
    public BigDecimal getExchangeAmount(Currency targetCurrency, double amount);
}
