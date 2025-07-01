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
@Table(name = "milestone_statuses_table")
@NamedQueries({
    @NamedQuery(name = "MilestoneStatusesTable.findAll", query = "SELECT m FROM MilestoneStatusesTable m"),
    @NamedQuery(name = "MilestoneStatusesTable.findById", query = "SELECT m FROM MilestoneStatusesTable m WHERE m.id = :id"),
    @NamedQuery(name = "MilestoneStatusesTable.findByStatusName", query = "SELECT m FROM MilestoneStatusesTable m WHERE m.statusName = :statusName")})
public class MilestoneStatusesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status_name")
    private String statusName;
    @OneToMany(mappedBy = "statusId")
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

    public MilestoneStatusesTable() {
    }

    public MilestoneStatusesTable(Integer id) {
        this.id = id;
    }

    public MilestoneStatusesTable(Integer id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
        if (!(object instanceof MilestoneStatusesTable)) {
            return false;
        }
        MilestoneStatusesTable other = (MilestoneStatusesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.MilestoneStatusesTable[ id=" + id + " ]";
    }
    
}
