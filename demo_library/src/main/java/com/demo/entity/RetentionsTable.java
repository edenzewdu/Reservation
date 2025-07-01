/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Lucy
 */
@Entity
@Table(name = "retentions_table")
@NamedQueries({
    @NamedQuery(name = "RetentionsTable.findAll", query = "SELECT r FROM RetentionsTable r"),
    @NamedQuery(name = "RetentionsTable.findById", query = "SELECT r FROM RetentionsTable r WHERE r.id = :id"),
    @NamedQuery(name = "RetentionsTable.findByContractId", query = "SELECT r FROM RetentionsTable r WHERE r.contractId = :contractId"),
    @NamedQuery(name = "RetentionsTable.findByRetentionReason", query = "SELECT r FROM RetentionsTable r WHERE r.retentionReason = :retentionReason"),
    @NamedQuery(name = "RetentionsTable.findByRetainedPercentageApplied", query = "SELECT r FROM RetentionsTable r WHERE r.retainedPercentageApplied = :retainedPercentageApplied"),
    @NamedQuery(name = "RetentionsTable.findByInitialRetainedAmount", query = "SELECT r FROM RetentionsTable r WHERE r.initialRetainedAmount = :initialRetainedAmount"),
    @NamedQuery(name = "RetentionsTable.findByCurrency", query = "SELECT r FROM RetentionsTable r WHERE r.currency = :currency"),
    @NamedQuery(name = "RetentionsTable.findByAmountReleasedSoFar", query = "SELECT r FROM RetentionsTable r WHERE r.amountReleasedSoFar = :amountReleasedSoFar"),
    @NamedQuery(name = "RetentionsTable.findByStatus", query = "SELECT r FROM RetentionsTable r WHERE r.status = :status"),
    @NamedQuery(name = "RetentionsTable.findByScheduledReleaseDate", query = "SELECT r FROM RetentionsTable r WHERE r.scheduledReleaseDate = :scheduledReleaseDate"),
    @NamedQuery(name = "RetentionsTable.findByActualFirstReleaseDate", query = "SELECT r FROM RetentionsTable r WHERE r.actualFirstReleaseDate = :actualFirstReleaseDate"),
    @NamedQuery(name = "RetentionsTable.findByCreatedAt", query = "SELECT r FROM RetentionsTable r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "RetentionsTable.findByUpdatedAt", query = "SELECT r FROM RetentionsTable r WHERE r.updatedAt = :updatedAt")})
public class RetentionsTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contract_id")
    private int contractId;
    @Size(max = 255)
    @Column(name = "retention_reason")
    private String retentionReason;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "retained_percentage_applied")
    private BigDecimal retainedPercentageApplied;
    @Basic(optional = false)
    @NotNull
    @Column(name = "initial_retained_amount")
    private BigDecimal initialRetainedAmount;
    @Size(max = 5)
    @Column(name = "currency")
    private String currency;
    @Column(name = "amount_released_so_far")
    private BigDecimal amountReleasedSoFar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "status")
    private String status;
    @Lob
    @Size(max = 65535)
    @Column(name = "release_condition_description")
    private String releaseConditionDescription;
    @Column(name = "scheduled_release_date")
    @Temporal(TemporalType.DATE)
    private Date scheduledReleaseDate;
    @Column(name = "actual_first_release_date")
    @Temporal(TemporalType.DATE)
    private Date actualFirstReleaseDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
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

    public RetentionsTable() {
    }

    public RetentionsTable(Integer id) {
        this.id = id;
    }

    public RetentionsTable(Integer id, int contractId, BigDecimal initialRetainedAmount, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.contractId = contractId;
        this.initialRetainedAmount = initialRetainedAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getRetentionReason() {
        return retentionReason;
    }

    public void setRetentionReason(String retentionReason) {
        this.retentionReason = retentionReason;
    }

    public BigDecimal getRetainedPercentageApplied() {
        return retainedPercentageApplied;
    }

    public void setRetainedPercentageApplied(BigDecimal retainedPercentageApplied) {
        this.retainedPercentageApplied = retainedPercentageApplied;
    }

    public BigDecimal getInitialRetainedAmount() {
        return initialRetainedAmount;
    }

    public void setInitialRetainedAmount(BigDecimal initialRetainedAmount) {
        this.initialRetainedAmount = initialRetainedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmountReleasedSoFar() {
        return amountReleasedSoFar;
    }

    public void setAmountReleasedSoFar(BigDecimal amountReleasedSoFar) {
        this.amountReleasedSoFar = amountReleasedSoFar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseConditionDescription() {
        return releaseConditionDescription;
    }

    public void setReleaseConditionDescription(String releaseConditionDescription) {
        this.releaseConditionDescription = releaseConditionDescription;
    }

    public Date getScheduledReleaseDate() {
        return scheduledReleaseDate;
    }

    public void setScheduledReleaseDate(Date scheduledReleaseDate) {
        this.scheduledReleaseDate = scheduledReleaseDate;
    }

    public Date getActualFirstReleaseDate() {
        return actualFirstReleaseDate;
    }

    public void setActualFirstReleaseDate(Date actualFirstReleaseDate) {
        this.actualFirstReleaseDate = actualFirstReleaseDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetentionsTable)) {
            return false;
        }
        RetentionsTable other = (RetentionsTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.RetentionsTable[ id=" + id + " ]";
    }
    
}
