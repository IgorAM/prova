package com.scuptel.falemais.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class CallTax{

    @EmbeddedId
    private CallTaxId id;

    @MapsId("originCityCode")
    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_city_code_id")
    private CityCode originCityCode;

    @MapsId("destinationCityCode")
    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_city_code_id")
    private CityCode destinationCityCode;


    @Column(name = "tax_per_minute", precision = 10, scale = 2, nullable = false)
    private BigDecimal taxPerMinute;


    public CallTax() {
    }

    public CallTax(CityCode destinationCityCode, CityCode originCityCode, BigDecimal taxPerMinute) {
        this.destinationCityCode = destinationCityCode;
        this.originCityCode = originCityCode;
        this.taxPerMinute = taxPerMinute;
    }

    public CityCode getDestinationCityCode() {
        return destinationCityCode;
    }

    public void setDestinationCityCode(CityCode destinationCityCode) {
        this.destinationCityCode = destinationCityCode;
    }

    public CityCode getOriginCityCode() {
        return originCityCode;
    }

    public void setOriginCityCode(CityCode originCityCode) {
        this.originCityCode = originCityCode;
    }

    public BigDecimal getTaxPerMinute() {
        return taxPerMinute;
    }

    public void setTaxPerMinute(BigDecimal taxPerMinute) {
        this.taxPerMinute = taxPerMinute;
    }

    public CallTaxId getId() {
        return id;
    }

    public void setId(CallTaxId id) {
        this.id = id;
    }
}
