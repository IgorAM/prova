package com.scuptel.falemais.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CallTaxId  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long originCityCode;
    private Long destinationCityCode;

    public CallTaxId() {
    }

    public CallTaxId(Long destinationCityCode, Long originCityCode) {
        this.destinationCityCode = destinationCityCode;
        this.originCityCode = originCityCode;
    }

    public Long getDestinationCityCode() {
        return destinationCityCode;
    }

    public Long getOriginCityCode() {
        return originCityCode;
    }

    public void setDestinationCityCode(Long destinationCityCode) {
        this.destinationCityCode = destinationCityCode;
    }

    public void setOriginCityCode(Long originCityCode) {
        this.originCityCode = originCityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallTaxId callTaxId = (CallTaxId) o;

        if (!originCityCode.equals(callTaxId.originCityCode)) return false;
        return destinationCityCode.equals(callTaxId.destinationCityCode);

    }

    @Override
    public int hashCode() {
        int result = originCityCode.hashCode();
        result = 31 * result + destinationCityCode.hashCode();
        return result;
    }
}
