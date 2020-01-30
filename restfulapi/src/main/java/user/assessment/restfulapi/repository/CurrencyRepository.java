package user.assessment.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.assessment.restfulapi.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>
{

}
