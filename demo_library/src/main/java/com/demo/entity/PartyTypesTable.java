/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "party_types_table")
@NamedQueries({
    @NamedQuery(name = "PartyTypesTable.findAll", query = "SELECT p FROM PartyTypesTable p"),
    @NamedQuery(name = "PartyTypesTable.findById", query = "SELECT p FROM PartyTypesTable p WHERE p.id = :id"),
    @NamedQuery(name = "PartyTypesTable.findByTypeName", query = "SELECT p FROM PartyTypesTable p WHERE p.typeName = :typeName")})
public class PartyTypesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(mappedBy = "partyTypeId")
    private Collection<ContractPartiesTable> contractPartiesTableCollection;
    
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

    public PartyTypesTable() {
    }

    public PartyTypesTable(Integer id) {
        this.id = id;
    }

    public PartyTypesTable(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Collection<ContractPartiesTable> getContractPartiesTableCollection() {
        return contractPartiesTableCollection;
    }

    public void setContractPartiesTableCollection(Collection<ContractPartiesTable> contractPartiesTableCollection) {
        this.contractPartiesTableCollection = contractPartiesTableCollection;
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
        if (!(object instanceof PartyTypesTable)) {
            return false;
        }
        PartyTypesTable other = (PartyTypesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.PartyTypesTable[ id=" + id + " ]";
    }
    
}
