/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "payment_frequencies_table")
@NamedQueries({
    @NamedQuery(name = "PaymentFrequenciesTable.findAll", query = "SELECT p FROM PaymentFrequenciesTable p"),
    @NamedQuery(name = "PaymentFrequenciesTable.findById", query = "SELECT p FROM PaymentFrequenciesTable p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentFrequenciesTable.findByFrequencyName", query = "SELECT p FROM PaymentFrequenciesTable p WHERE p.frequencyName = :frequencyName")})
public class PaymentFrequenciesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "frequency_name")
    private String frequencyName;
    
    @jakarta.persistence.Transient
    private Integer tempId;
    @jakarta.persistence.Transient
    private Boolean validCell = false;

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public Boolean getValidCell() {
        return validCell;
    }

    public void setValidCell(Boolean validCell) {
        this.validCell = validCell;
    }

    public PaymentFrequenciesTable() {
    }

    public PaymentFrequenciesTable(Integer id) {
        this.id = id;
    }

    public PaymentFrequenciesTable(Integer id, String frequencyName) {
        this.id = id;
        this.frequencyName = frequencyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrequencyName() {
        return frequencyName;
    }

    public void setFrequencyName(String frequencyName) {
        this.frequencyName = frequencyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentFrequenciesTable)) {
            return false;
        }
        PaymentFrequenciesTable other = (PaymentFrequenciesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.PaymentFrequenciesTable[ id=" + id + " ]";
    }
    
}
