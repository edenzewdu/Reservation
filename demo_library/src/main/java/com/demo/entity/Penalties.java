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
@Table(name = "penalties")
@NamedQueries({
    @NamedQuery(name = "Penalties.findAll", query = "SELECT p FROM Penalties p"),
    @NamedQuery(name = "Penalties.findById", query = "SELECT p FROM Penalties p WHERE p.id = :id"),
    @NamedQuery(name = "Penalties.findByContractId", query = "SELECT p FROM Penalties p WHERE p.contractId = :contractId"),
    @NamedQuery(name = "Penalties.findByBreachType", query = "SELECT p FROM Penalties p WHERE p.breachType = :breachType"),
    @NamedQuery(name = "Penalties.findByIncurredDate", query = "SELECT p FROM Penalties p WHERE p.incurredDate = :incurredDate"),
    @NamedQuery(name = "Penalties.findByPenaltyAmount", query = "SELECT p FROM Penalties p WHERE p.penaltyAmount = :penaltyAmount"),
    @NamedQuery(name = "Penalties.findByCurrency", query = "SELECT p FROM Penalties p WHERE p.currency = :currency"),
    @NamedQuery(name = "Penalties.findByRelatedClauseRef", query = "SELECT p FROM Penalties p WHERE p.relatedClauseRef = :relatedClauseRef"),
    @NamedQuery(name = "Penalties.findByGracePeriodDaysApplied", query = "SELECT p FROM Penalties p WHERE p.gracePeriodDaysApplied = :gracePeriodDaysApplied"),
    @NamedQuery(name = "Penalties.findByStatus", query = "SELECT p FROM Penalties p WHERE p.status = :status"),
    @NamedQuery(name = "Penalties.findByResolutionDate", query = "SELECT p FROM Penalties p WHERE p.resolutionDate = :resolutionDate"),
    @NamedQuery(name = "Penalties.findByResolvedByUserId", query = "SELECT p FROM Penalties p WHERE p.resolvedByUserId = :resolvedByUserId"),
    @NamedQuery(name = "Penalties.findByProofDocumentId", query = "SELECT p FROM Penalties p WHERE p.proofDocumentId = :proofDocumentId"),
    @NamedQuery(name = "Penalties.findByCreatedAt", query = "SELECT p FROM Penalties p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Penalties.findByUpdatedAt", query = "SELECT p FROM Penalties p WHERE p.updatedAt = :updatedAt")})
public class Penalties implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "breach_type")
    private String breachType;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "incurred_date")
    @Temporal(TemporalType.DATE)
    private Date incurredDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "penalty_amount")
    private BigDecimal penaltyAmount;
    @Size(max = 5)
    @Column(name = "currency")
    private String currency;
    @Size(max = 100)
    @Column(name = "related_clause_ref")
    private String relatedClauseRef;
    @Column(name = "grace_period_days_applied")
    private Integer gracePeriodDaysApplied;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Column(name = "resolution_date")
    @Temporal(TemporalType.DATE)
    private Date resolutionDate;
    @Column(name = "resolved_by_user_id")
    private Integer resolvedByUserId;
    @Lob
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Column(name = "proof_document_id")
    private Integer proofDocumentId;
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

    public Penalties() {
    }

    public Penalties(Integer id) {
        this.id = id;
    }

    public Penalties(Integer id, int contractId, String breachType, String description, Date incurredDate, BigDecimal penaltyAmount, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.contractId = contractId;
        this.breachType = breachType;
        this.description = description;
        this.incurredDate = incurredDate;
        this.penaltyAmount = penaltyAmount;
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

    public String getBreachType() {
        return breachType;
    }

    public void setBreachType(String breachType) {
        this.breachType = breachType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIncurredDate() {
        return incurredDate;
    }

    public void setIncurredDate(Date incurredDate) {
        this.incurredDate = incurredDate;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRelatedClauseRef() {
        return relatedClauseRef;
    }

    public void setRelatedClauseRef(String relatedClauseRef) {
        this.relatedClauseRef = relatedClauseRef;
    }

    public Integer getGracePeriodDaysApplied() {
        return gracePeriodDaysApplied;
    }

    public void setGracePeriodDaysApplied(Integer gracePeriodDaysApplied) {
        this.gracePeriodDaysApplied = gracePeriodDaysApplied;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public Integer getResolvedByUserId() {
        return resolvedByUserId;
    }

    public void setResolvedByUserId(Integer resolvedByUserId) {
        this.resolvedByUserId = resolvedByUserId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getProofDocumentId() {
        return proofDocumentId;
    }

    public void setProofDocumentId(Integer proofDocumentId) {
        this.proofDocumentId = proofDocumentId;
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
        if (!(object instanceof Penalties)) {
            return false;
        }
        Penalties other = (Penalties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.Penalties[ id=" + id + " ]";
    }
    
}
