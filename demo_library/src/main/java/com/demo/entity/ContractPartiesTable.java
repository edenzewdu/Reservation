/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "contract_parties_table")
@NamedQueries({
    @NamedQuery(name = "ContractPartiesTable.findAll", query = "SELECT c FROM ContractPartiesTable c"),
    @NamedQuery(name = "ContractPartiesTable.findById", query = "SELECT c FROM ContractPartiesTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractPartiesTable.findByShortName", query = "SELECT c FROM ContractPartiesTable c WHERE c.shortName = :shortName"),
    @NamedQuery(name = "ContractPartiesTable.findByRegistrationNumber", query = "SELECT c FROM ContractPartiesTable c WHERE c.registrationNumber = :registrationNumber"),
    @NamedQuery(name = "ContractPartiesTable.findByCreatedAt", query = "SELECT c FROM ContractPartiesTable c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "ContractPartiesTable.findByUpdatedAt", query = "SELECT c FROM ContractPartiesTable c WHERE c.updatedAt = :updatedAt"),
    @NamedQuery(name = "ContractPartiesTable.findByAddressNumberAb", query = "SELECT c FROM ContractPartiesTable c WHERE c.addressNumberAb = :addressNumberAb")})
public class ContractPartiesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "legal_name")
    private String legalName;
    @Size(max = 100)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 100)
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "address_number_ab")
    private Integer addressNumberAb;
    @OneToMany(mappedBy = "partyId")
    private Collection<ContractPartyRolesTable> contractPartyRolesTableCollection;
    @JoinColumn(name = "party_type_id", referencedColumnName = "id")
    @ManyToOne
    private PartyTypesTable partyTypeId;

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
    
    public ContractPartiesTable() {
    }

    public ContractPartiesTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getAddressNumberAb() {
        return addressNumberAb;
    }

    public void setAddressNumberAb(Integer addressNumberAb) {
        this.addressNumberAb = addressNumberAb;
    }

    public Collection<ContractPartyRolesTable> getContractPartyRolesTableCollection() {
        return contractPartyRolesTableCollection;
    }

    public void setContractPartyRolesTableCollection(Collection<ContractPartyRolesTable> contractPartyRolesTableCollection) {
        this.contractPartyRolesTableCollection = contractPartyRolesTableCollection;
    }

    public PartyTypesTable getPartyTypeId() {
        return partyTypeId;
    }

    public void setPartyTypeId(PartyTypesTable partyTypeId) {
        this.partyTypeId = partyTypeId;
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
        if (!(object instanceof ContractPartiesTable)) {
            return false;
        }
        ContractPartiesTable other = (ContractPartiesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractPartiesTable[ id=" + id + " ]";
    }
    
}
