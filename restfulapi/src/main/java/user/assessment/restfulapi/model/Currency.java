package user.assessment.restfulapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="currency")
public abstract class Currency implements TradingCurrency
{
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Getter
    @Setter
    @Column(name="name")
    private String name;
}
