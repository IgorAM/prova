package com.scuptel.falemais.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CityCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false, length = 2)
    private Integer code;

    public CityCode() {
    }

    public CityCode(Integer code) {
        this.code = code;
    }

    public CityCode(Long id, Integer code) {
        this.id = id;
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
