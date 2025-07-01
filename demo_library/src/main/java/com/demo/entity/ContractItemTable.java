/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "contract_item_table")
@NamedQueries({
    @NamedQuery(name = "ContractItemTable.findAll", query = "SELECT c FROM ContractItemTable c"),
    @NamedQuery(name = "ContractItemTable.findById", query = "SELECT c FROM ContractItemTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractItemTable.findByAmountQuantity", query = "SELECT c FROM ContractItemTable c WHERE c.amountQuantity = :amountQuantity"),
    @NamedQuery(name = "ContractItemTable.findByItemsTable", query = "SELECT c FROM ContractItemTable c WHERE c.itemsTable = :itemsTable"),
    @NamedQuery(name = "ContractItemTable.findByUnitOfMeasure", query = "SELECT c FROM ContractItemTable c WHERE c.unitOfMeasure = :unitOfMeasure"),
    @NamedQuery(name = "ContractItemTable.findByUnitAmount", query = "SELECT c FROM ContractItemTable c WHERE c.unitAmount = :unitAmount"),
    @NamedQuery(name = "ContractItemTable.findByExtendedAmount", query = "SELECT c FROM ContractItemTable c WHERE c.extendedAmount = :extendedAmount"),
    @NamedQuery(name = "ContractItemTable.findByUsersId", query = "SELECT c FROM ContractItemTable c WHERE c.usersId = :usersId"),
    @NamedQuery(name = "ContractItemTable.findByDateEnered", query = "SELECT c FROM ContractItemTable c WHERE c.dateEnered = :dateEnered"),
    @NamedQuery(name = "ContractItemTable.findByDateUpdated", query = "SELECT c FROM ContractItemTable c WHERE c.dateUpdated = :dateUpdated")})
public class ContractItemTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_quantity")
    private Double amountQuantity;
    @Column(name = "items_table")
    private Integer itemsTable;
    @Column(name = "unit_of_measure")
    private Integer unitOfMeasure;
    @Column(name = "unit_amount")
    private Double unitAmount;
    @Column(name = "extended_amount")
    private Double extendedAmount;
    @Size(max = 255)
    @Column(name = "users_id")
    private String usersId;
    @Column(name = "date_enered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnered;
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    @ManyToOne
    private ContractsTable contractId;
    
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

    public ContractItemTable() {
    }

    public ContractItemTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmountQuantity() {
        return amountQuantity;
    }

    public void setAmountQuantity(Double amountQuantity) {
        this.amountQuantity = amountQuantity;
    }

    public Integer getItemsTable() {
        return itemsTable;
    }

    public void setItemsTable(Integer itemsTable) {
        this.itemsTable = itemsTable;
    }

    public Integer getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(Integer unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public Double getExtendedAmount() {
        return extendedAmount;
    }

    public void setExtendedAmount(Double extendedAmount) {
        this.extendedAmount = extendedAmount;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public Date getDateEnered() {
        return dateEnered;
    }

    public void setDateEnered(Date dateEnered) {
        this.dateEnered = dateEnered;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ContractsTable getContractId() {
        return contractId;
    }

    public void setContractId(ContractsTable contractId) {
        this.contractId = contractId;
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
        if (!(object instanceof ContractItemTable)) {
            return false;
        }
        ContractItemTable other = (ContractItemTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractItemTable[ id=" + id + " ]";
    }
    
}
