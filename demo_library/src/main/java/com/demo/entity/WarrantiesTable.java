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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@Table(name = "warranties_table")
@NamedQueries({
    @NamedQuery(name = "WarrantiesTable.findAll", query = "SELECT w FROM WarrantiesTable w"),
    @NamedQuery(name = "WarrantiesTable.findById", query = "SELECT w FROM WarrantiesTable w WHERE w.id = :id"),
    @NamedQuery(name = "WarrantiesTable.findByContractId", query = "SELECT w FROM WarrantiesTable w WHERE w.contractId = :contractId"),
    @NamedQuery(name = "WarrantiesTable.findByWarrantedItemDescription", query = "SELECT w FROM WarrantiesTable w WHERE w.warrantedItemDescription = :warrantedItemDescription"),
    @NamedQuery(name = "WarrantiesTable.findByManufacturerModel", query = "SELECT w FROM WarrantiesTable w WHERE w.manufacturerModel = :manufacturerModel"),
    @NamedQuery(name = "WarrantiesTable.findBySerialNumber", query = "SELECT w FROM WarrantiesTable w WHERE w.serialNumber = :serialNumber"),
    @NamedQuery(name = "WarrantiesTable.findByWarrantyType", query = "SELECT w FROM WarrantiesTable w WHERE w.warrantyType = :warrantyType"),
    @NamedQuery(name = "WarrantiesTable.findByWarrantyProviderPartyId", query = "SELECT w FROM WarrantiesTable w WHERE w.warrantyProviderPartyId = :warrantyProviderPartyId"),
    @NamedQuery(name = "WarrantiesTable.findByStartDate", query = "SELECT w FROM WarrantiesTable w WHERE w.startDate = :startDate"),
    @NamedQuery(name = "WarrantiesTable.findByEndDate", query = "SELECT w FROM WarrantiesTable w WHERE w.endDate = :endDate"),
    @NamedQuery(name = "WarrantiesTable.findByIsActive", query = "SELECT w FROM WarrantiesTable w WHERE w.isActive = :isActive"),
    @NamedQuery(name = "WarrantiesTable.findByIsVoided", query = "SELECT w FROM WarrantiesTable w WHERE w.isVoided = :isVoided"),
    @NamedQuery(name = "WarrantiesTable.findByCreatedAt", query = "SELECT w FROM WarrantiesTable w WHERE w.createdAt = :createdAt"),
    @NamedQuery(name = "WarrantiesTable.findByUpdatedAt", query = "SELECT w FROM WarrantiesTable w WHERE w.updatedAt = :updatedAt")})
public class WarrantiesTable implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "warranted_item_description")
    private String warrantedItemDescription;
    @Size(max = 100)
    @Column(name = "manufacturer_model")
    private String manufacturerModel;
    @Size(max = 100)
    @Column(name = "serial_number")
    private String serialNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "warranty_type")
    private String warrantyType;
    @Column(name = "warranty_provider_party_id")
    private Integer warrantyProviderPartyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "coverage_scope_text")
    private String coverageScopeText;
    @Lob
    @Size(max = 65535)
    @Column(name = "exclusions_text")
    private String exclusionsText;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_voided")
    private Boolean isVoided;
    @Lob
    @Size(max = 65535)
    @Column(name = "void_reason")
    private String voidReason;
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
    @OneToMany(mappedBy = "warrantyId")
    private Collection<IssuesDefectsTable> issuesDefectsTableCollection;
    
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

    public WarrantiesTable() {
    }

    public WarrantiesTable(Integer id) {
        this.id = id;
    }

    public WarrantiesTable(Integer id, int contractId, String warrantedItemDescription, String warrantyType, Date startDate, Date endDate, Date createdAt, Date updatedAt) {
        this.id = id;
        this.contractId = contractId;
        this.warrantedItemDescription = warrantedItemDescription;
        this.warrantyType = warrantyType;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getWarrantedItemDescription() {
        return warrantedItemDescription;
    }

    public void setWarrantedItemDescription(String warrantedItemDescription) {
        this.warrantedItemDescription = warrantedItemDescription;
    }

    public String getManufacturerModel() {
        return manufacturerModel;
    }

    public void setManufacturerModel(String manufacturerModel) {
        this.manufacturerModel = manufacturerModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Integer getWarrantyProviderPartyId() {
        return warrantyProviderPartyId;
    }

    public void setWarrantyProviderPartyId(Integer warrantyProviderPartyId) {
        this.warrantyProviderPartyId = warrantyProviderPartyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCoverageScopeText() {
        return coverageScopeText;
    }

    public void setCoverageScopeText(String coverageScopeText) {
        this.coverageScopeText = coverageScopeText;
    }

    public String getExclusionsText() {
        return exclusionsText;
    }

    public void setExclusionsText(String exclusionsText) {
        this.exclusionsText = exclusionsText;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsVoided() {
        return isVoided;
    }

    public void setIsVoided(Boolean isVoided) {
        this.isVoided = isVoided;
    }

    public String getVoidReason() {
        return voidReason;
    }

    public void setVoidReason(String voidReason) {
        this.voidReason = voidReason;
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

    public Collection<IssuesDefectsTable> getIssuesDefectsTableCollection() {
        return issuesDefectsTableCollection;
    }

    public void setIssuesDefectsTableCollection(Collection<IssuesDefectsTable> issuesDefectsTableCollection) {
        this.issuesDefectsTableCollection = issuesDefectsTableCollection;
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
        if (!(object instanceof WarrantiesTable)) {
            return false;
        }
        WarrantiesTable other = (WarrantiesTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.WarrantiesTable[ id=" + id + " ]";
    }
    
}
