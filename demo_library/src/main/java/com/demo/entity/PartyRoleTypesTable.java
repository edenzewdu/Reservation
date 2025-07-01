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
@Table(name = "party_role_types_table")
@NamedQueries({
    @NamedQuery(name = "PartyRoleTypesTable.findAll", query = "SELECT p FROM PartyRoleTypesTable p"),
    @NamedQuery(name = "PartyRoleTypesTable.findById", query = "SELECT p FROM PartyRoleTypesTable p WHERE p.id = :id"),
    @NamedQuery(name = "PartyRoleTypesTable.findByRoleName", query = "SELECT p FROM PartyRoleTypesTable p WHERE p.roleName = :roleName")})
public class PartyRoleTypesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "roleInContractId")
    private Collection<ContractPartyRolesTable> contractPartyRolesTableCollection;
    
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

    public PartyRoleTypesTable() {
    }

    public PartyRoleTypesTable(Integer id) {
        this.id = id;
    }

    public PartyRoleTypesTable(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<ContractPartyRolesTable> getContractPartyRolesTableCollection() {
        return contractPartyRolesTableCollection;
    }

    public void setContractPartyRolesTableCollection(Collection<ContractPartyRolesTable> contractPartyRolesTableCollection) {
        this.contractPartyRolesTableCollection = contractPartyRolesTableCollection;
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
        if (!(object instanceof PartyRoleTypesTable)) {
            return false;
        }
        PartyRoleTypesTable other = (PartyRoleTypesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.PartyRoleTypesTable[ id=" + id + " ]";
    }
    
}
