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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "contract_party_roles_table")
@NamedQueries({
    @NamedQuery(name = "ContractPartyRolesTable.findAll", query = "SELECT c FROM ContractPartyRolesTable c"),
    @NamedQuery(name = "ContractPartyRolesTable.findById", query = "SELECT c FROM ContractPartyRolesTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractPartyRolesTable.findByIsPrimaryCounterparty", query = "SELECT c FROM ContractPartyRolesTable c WHERE c.isPrimaryCounterparty = :isPrimaryCounterparty")})
public class ContractPartyRolesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_primary_counterparty")
    private Boolean isPrimaryCounterparty;
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    @ManyToOne
    private ContractsTable contractId;
    @JoinColumn(name = "party_id", referencedColumnName = "id")
    @ManyToOne
    private ContractPartiesTable partyId;
    @JoinColumn(name = "role_in_contract_id", referencedColumnName = "id")
    @ManyToOne
    private PartyRoleTypesTable roleInContractId;
    @OneToMany(mappedBy = "responsiblePartyId")
    private Collection<ContractKeyDatesMilestonesTable> contractKeyDatesMilestonesTableCollection;

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
    
    public ContractPartyRolesTable() {
    }

    public ContractPartyRolesTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsPrimaryCounterparty() {
        return isPrimaryCounterparty;
    }

    public void setIsPrimaryCounterparty(Boolean isPrimaryCounterparty) {
        this.isPrimaryCounterparty = isPrimaryCounterparty;
    }

    public ContractsTable getContractId() {
        return contractId;
    }

    public void setContractId(ContractsTable contractId) {
        this.contractId = contractId;
    }

    public ContractPartiesTable getPartyId() {
        return partyId;
    }

    public void setPartyId(ContractPartiesTable partyId) {
        this.partyId = partyId;
    }

    public PartyRoleTypesTable getRoleInContractId() {
        return roleInContractId;
    }

    public void setRoleInContractId(PartyRoleTypesTable roleInContractId) {
        this.roleInContractId = roleInContractId;
    }

    public Collection<ContractKeyDatesMilestonesTable> getContractKeyDatesMilestonesTableCollection() {
        return contractKeyDatesMilestonesTableCollection;
    }

    public void setContractKeyDatesMilestonesTableCollection(Collection<ContractKeyDatesMilestonesTable> contractKeyDatesMilestonesTableCollection) {
        this.contractKeyDatesMilestonesTableCollection = contractKeyDatesMilestonesTableCollection;
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
        if (!(object instanceof ContractPartyRolesTable)) {
            return false;
        }
        ContractPartyRolesTable other = (ContractPartyRolesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractPartyRolesTable[ id=" + id + " ]";
    }
    
}
