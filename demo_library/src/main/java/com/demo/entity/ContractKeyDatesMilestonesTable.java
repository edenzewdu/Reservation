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
import jakarta.persistence.Lob;
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
@Table(name = "contract_key_dates_milestones_table")
@NamedQueries({
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findAll", query = "SELECT c FROM ContractKeyDatesMilestonesTable c"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findById", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findByDueDate", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.dueDate = :dueDate"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findByAssignedContactId", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.assignedContactId = :assignedContactId"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findByReminderLeadDays", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.reminderLeadDays = :reminderLeadDays"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findByCompletedDate", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.completedDate = :completedDate"),
    @NamedQuery(name = "ContractKeyDatesMilestonesTable.findByUsersId", query = "SELECT c FROM ContractKeyDatesMilestonesTable c WHERE c.usersId = :usersId")})
public class ContractKeyDatesMilestonesTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "milestone_name")
    private String milestoneName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "assigned_contact_id")
    private Integer assignedContactId;
    @Column(name = "reminder_lead_days")
    private Integer reminderLeadDays;
    @Column(name = "completed_date")
    @Temporal(TemporalType.DATE)
    private Date completedDate;
    @Size(max = 255)
    @Column(name = "users_id")
    private String usersId;
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    @ManyToOne
    private ContractsTable contractId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne
    private MilestoneStatusesTable statusId;
    @JoinColumn(name = "responsible_party_id", referencedColumnName = "id")
    @ManyToOne
    private ContractPartyRolesTable responsiblePartyId;
    
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

    public ContractKeyDatesMilestonesTable() {
    }

    public ContractKeyDatesMilestonesTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getAssignedContactId() {
        return assignedContactId;
    }

    public void setAssignedContactId(Integer assignedContactId) {
        this.assignedContactId = assignedContactId;
    }

    public Integer getReminderLeadDays() {
        return reminderLeadDays;
    }

    public void setReminderLeadDays(Integer reminderLeadDays) {
        this.reminderLeadDays = reminderLeadDays;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public ContractsTable getContractId() {
        return contractId;
    }

    public void setContractId(ContractsTable contractId) {
        this.contractId = contractId;
    }

    public MilestoneStatusesTable getStatusId() {
        return statusId;
    }

    public void setStatusId(MilestoneStatusesTable statusId) {
        this.statusId = statusId;
    }

    public ContractPartyRolesTable getResponsiblePartyId() {
        return responsiblePartyId;
    }

    public void setResponsiblePartyId(ContractPartyRolesTable responsiblePartyId) {
        this.responsiblePartyId = responsiblePartyId;
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
        if (!(object instanceof ContractKeyDatesMilestonesTable)) {
            return false;
        }
        ContractKeyDatesMilestonesTable other = (ContractKeyDatesMilestonesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractKeyDatesMilestonesTable[ id=" + id + " ]";
    }
    
}
