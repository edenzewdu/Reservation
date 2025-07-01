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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "contract_types_table")
@NamedQueries({
    @NamedQuery(name = "ContractTypesTable.findAll", query = "SELECT c FROM ContractTypesTable c"),
    @NamedQuery(name = "ContractTypesTable.findById", query = "SELECT c FROM ContractTypesTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractTypesTable.findByTypeName", query = "SELECT c FROM ContractTypesTable c WHERE c.typeName = :typeName"),
    @NamedQuery(name = "ContractTypesTable.findByContractSide", query = "SELECT c FROM ContractTypesTable c WHERE c.contractSide = :contractSide")})
public class ContractTypesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "type_name")
    private String typeName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "contract_side")
    private String contractSide;
    @OneToMany(mappedBy = "contractTypeId")
    private Collection<ContractsTable> contractsTableCollection;
    
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

    public ContractTypesTable() {
    }

    public ContractTypesTable(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContractSide() {
        return contractSide;
    }

    public void setContractSide(String contractSide) {
        this.contractSide = contractSide;
    }

    public Collection<ContractsTable> getContractsTableCollection() {
        return contractsTableCollection;
    }

    public void setContractsTableCollection(Collection<ContractsTable> contractsTableCollection) {
        this.contractsTableCollection = contractsTableCollection;
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
        if (!(object instanceof ContractTypesTable)) {
            return false;
        }
        ContractTypesTable other = (ContractTypesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractTypesTable[ id=" + id + " ]";
    }
    
}
