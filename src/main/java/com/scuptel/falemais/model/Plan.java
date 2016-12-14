package com.scuptel.falemais.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Plan implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer franchise;

    @Column(name="addition_tax", precision = 10, scale = 2, nullable = false)
    private BigDecimal additionTax;

    public Plan() {
    }

    public Plan(Long id, BigDecimal additionTax, Integer franchise, String name) {
        this.id = id;
        this.additionTax = additionTax;
        this.franchise = franchise;
        this.name = name;
    }

    public BigDecimal getAdditionTax() {
        return additionTax;
    }

    public void setAdditionTax(BigDecimal additionTax) {
        this.additionTax = additionTax;
    }

    public Integer getFranchise() {
        return franchise;
    }

    public void setFranchise(Integer franchise) {
        this.franchise = franchise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
