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
@Table(name = "contracts_table")
@NamedQueries({
    @NamedQuery(name = "ContractsTable.findAll", query = "SELECT c FROM ContractsTable c"),
    @NamedQuery(name = "ContractsTable.findById", query = "SELECT c FROM ContractsTable c WHERE c.id = :id"),
    @NamedQuery(name = "ContractsTable.findByReferenceNumber", query = "SELECT c FROM ContractsTable c WHERE c.referenceNumber = :referenceNumber"),
    @NamedQuery(name = "ContractsTable.findByEffectiveDate", query = "SELECT c FROM ContractsTable c WHERE c.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "ContractsTable.findByInitialExpiryDate", query = "SELECT c FROM ContractsTable c WHERE c.initialExpiryDate = :initialExpiryDate"),
    @NamedQuery(name = "ContractsTable.findByCurrentExpiryDate", query = "SELECT c FROM ContractsTable c WHERE c.currentExpiryDate = :currentExpiryDate"),
    @NamedQuery(name = "ContractsTable.findByNoticePeriodDaysForTermination", query = "SELECT c FROM ContractsTable c WHERE c.noticePeriodDaysForTermination = :noticePeriodDaysForTermination"),
    @NamedQuery(name = "ContractsTable.findByAutoRenewalEnabled", query = "SELECT c FROM ContractsTable c WHERE c.autoRenewalEnabled = :autoRenewalEnabled"),
    @NamedQuery(name = "ContractsTable.findByRenewalTermMonths", query = "SELECT c FROM ContractsTable c WHERE c.renewalTermMonths = :renewalTermMonths"),
    @NamedQuery(name = "ContractsTable.findByContractValue", query = "SELECT c FROM ContractsTable c WHERE c.contractValue = :contractValue"),
    @NamedQuery(name = "ContractsTable.findByCurrencyCode", query = "SELECT c FROM ContractsTable c WHERE c.currencyCode = :currencyCode"),
    @NamedQuery(name = "ContractsTable.findByGoverningLawJurisdiction", query = "SELECT c FROM ContractsTable c WHERE c.governingLawJurisdiction = :governingLawJurisdiction"),
    @NamedQuery(name = "ContractsTable.findByParentContractId", query = "SELECT c FROM ContractsTable c WHERE c.parentContractId = :parentContractId"),
    @NamedQuery(name = "ContractsTable.findByCreatedByUserId", query = "SELECT c FROM ContractsTable c WHERE c.createdByUserId = :createdByUserId"),
    @NamedQuery(name = "ContractsTable.findByCreatedAt", query = "SELECT c FROM ContractsTable c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "ContractsTable.findByUpdatedByUserId", query = "SELECT c FROM ContractsTable c WHERE c.updatedByUserId = :updatedByUserId"),
    @NamedQuery(name = "ContractsTable.findByUpdatedAt", query = "SELECT c FROM ContractsTable c WHERE c.updatedAt = :updatedAt"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberCustomer1", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberCustomer1 = :addressNumberCustomer1"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberRep1", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberRep1 = :addressNumberRep1"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberPrepared", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberPrepared = :addressNumberPrepared"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberAproved", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberAproved = :addressNumberAproved"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberChecked", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberChecked = :addressNumberChecked"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberCustomer2", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberCustomer2 = :addressNumberCustomer2"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberCustomer3", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberCustomer3 = :addressNumberCustomer3"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberRep2", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberRep2 = :addressNumberRep2"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberRep3", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberRep3 = :addressNumberRep3"),
    @NamedQuery(name = "ContractsTable.findByAddressNumberRep4", query = "SELECT c FROM ContractsTable c WHERE c.addressNumberRep4 = :addressNumberRep4"),
    @NamedQuery(name = "ContractsTable.findByContractCurrency", query = "SELECT c FROM ContractsTable c WHERE c.contractCurrency = :contractCurrency"),
    @NamedQuery(name = "ContractsTable.findByTaxRate", query = "SELECT c FROM ContractsTable c WHERE c.taxRate = :taxRate"),
    @NamedQuery(name = "ContractsTable.findByInitalStartDate", query = "SELECT c FROM ContractsTable c WHERE c.initalStartDate = :initalStartDate"),
    @NamedQuery(name = "ContractsTable.findByEndStartDate", query = "SELECT c FROM ContractsTable c WHERE c.endStartDate = :endStartDate"),
    @NamedQuery(name = "ContractsTable.findByCancellationFee", query = "SELECT c FROM ContractsTable c WHERE c.cancellationFee = :cancellationFee"),
    @NamedQuery(name = "ContractsTable.findByRetinageAmount", query = "SELECT c FROM ContractsTable c WHERE c.retinageAmount = :retinageAmount"),
    @NamedQuery(name = "ContractsTable.findByContractIdentitySide", query = "SELECT c FROM ContractsTable c WHERE c.contractIdentitySide = :contractIdentitySide")})
public class ContractsTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "contract_title")
    private String contractTitle;
    @Size(max = 100)
    @Column(name = "reference_number")
    private String referenceNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "description_scope")
    private String descriptionScope;
    @Column(name = "effective_date")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "initial_expiry_date")
    @Temporal(TemporalType.DATE)
    private Date initialExpiryDate;
    @Column(name = "current_expiry_date")
    @Temporal(TemporalType.DATE)
    private Date currentExpiryDate;
    @Column(name = "notice_period_days_for_termination")
    private Integer noticePeriodDaysForTermination;
    @Column(name = "auto_renewal_enabled")
    private Boolean autoRenewalEnabled;
    @Column(name = "renewal_term_months")
    private Integer renewalTermMonths;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "contract_value")
    private Double contractValue;
    @Size(max = 3)
    @Column(name = "currency_code")
    private String currencyCode;
    @Size(max = 100)
    @Column(name = "governing_law_jurisdiction")
    private String governingLawJurisdiction;
    @Column(name = "parent_contract_id")
    private Integer parentContractId;
    @Size(max = 255)
    @Column(name = "created_by_user_id")
    private String createdByUserId;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 255)
    @Column(name = "updated_by_user_id")
    private String updatedByUserId;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "address_number_customer1")
    private Integer addressNumberCustomer1;
    @Column(name = "address_number_rep1")
    private Integer addressNumberRep1;
    @Column(name = "address_number_prepared")
    private Integer addressNumberPrepared;
    @Column(name = "address_number_aproved")
    private Integer addressNumberAproved;
    @Column(name = "address_number_checked")
    private Integer addressNumberChecked;
    @Column(name = "address_number_customer2")
    private Integer addressNumberCustomer2;
    @Column(name = "address_number_customer3")
    private Integer addressNumberCustomer3;
    @Column(name = "address_number_rep2")
    private Integer addressNumberRep2;
    @Column(name = "address_number_rep3")
    private Integer addressNumberRep3;
    @Column(name = "address_number_rep4")
    private Integer addressNumberRep4;
    @Column(name = "contract_currency")
    private Integer contractCurrency;
    @Column(name = "tax_rate")
    private Integer taxRate;
    @Column(name = "inital_start_date")
    @Temporal(TemporalType.DATE)
    private Date initalStartDate;
    @Column(name = "end_start_date")
    @Temporal(TemporalType.DATE)
    private Date endStartDate;
    @Column(name = "cancellation_fee")
    private Double cancellationFee;
    @Column(name = "retinage_amount")
    private Double retinageAmount;
    @Size(max = 45)
    @Column(name = "contract_identity_side")
    private String contractIdentitySide;
    @OneToMany(mappedBy = "contractId")
    private Collection<ContractItemTable> contractItemTableCollection;
    @OneToMany(mappedBy = "contractId")
    private Collection<ContractPartyRolesTable> contractPartyRolesTableCollection;
    @JoinColumn(name = "contract_status_id", referencedColumnName = "id")
    @ManyToOne
    private ContractStatusesTable contractStatusId;
    @JoinColumn(name = "contract_type_id", referencedColumnName = "id")
    @ManyToOne
    private ContractTypesTable contractTypeId;
    @OneToMany(mappedBy = "contractId")
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

    public ContractsTable() {
    }

    public ContractsTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getDescriptionScope() {
        return descriptionScope;
    }

    public void setDescriptionScope(String descriptionScope) {
        this.descriptionScope = descriptionScope;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getInitialExpiryDate() {
        return initialExpiryDate;
    }

    public void setInitialExpiryDate(Date initialExpiryDate) {
        this.initialExpiryDate = initialExpiryDate;
    }

    public Date getCurrentExpiryDate() {
        return currentExpiryDate;
    }

    public void setCurrentExpiryDate(Date currentExpiryDate) {
        this.currentExpiryDate = currentExpiryDate;
    }

    public Integer getNoticePeriodDaysForTermination() {
        return noticePeriodDaysForTermination;
    }

    public void setNoticePeriodDaysForTermination(Integer noticePeriodDaysForTermination) {
        this.noticePeriodDaysForTermination = noticePeriodDaysForTermination;
    }

    public Boolean getAutoRenewalEnabled() {
        return autoRenewalEnabled;
    }

    public void setAutoRenewalEnabled(Boolean autoRenewalEnabled) {
        this.autoRenewalEnabled = autoRenewalEnabled;
    }

    public Integer getRenewalTermMonths() {
        return renewalTermMonths;
    }

    public void setRenewalTermMonths(Integer renewalTermMonths) {
        this.renewalTermMonths = renewalTermMonths;
    }

    public Double getContractValue() {
        return contractValue;
    }

    public void setContractValue(Double contractValue) {
        this.contractValue = contractValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getGoverningLawJurisdiction() {
        return governingLawJurisdiction;
    }

    public void setGoverningLawJurisdiction(String governingLawJurisdiction) {
        this.governingLawJurisdiction = governingLawJurisdiction;
    }

    public Integer getParentContractId() {
        return parentContractId;
    }

    public void setParentContractId(Integer parentContractId) {
        this.parentContractId = parentContractId;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(String updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getAddressNumberCustomer1() {
        return addressNumberCustomer1;
    }

    public void setAddressNumberCustomer1(Integer addressNumberCustomer1) {
        this.addressNumberCustomer1 = addressNumberCustomer1;
    }

    public Integer getAddressNumberRep1() {
        return addressNumberRep1;
    }

    public void setAddressNumberRep1(Integer addressNumberRep1) {
        this.addressNumberRep1 = addressNumberRep1;
    }

    public Integer getAddressNumberPrepared() {
        return addressNumberPrepared;
    }

    public void setAddressNumberPrepared(Integer addressNumberPrepared) {
        this.addressNumberPrepared = addressNumberPrepared;
    }

    public Integer getAddressNumberAproved() {
        return addressNumberAproved;
    }

    public void setAddressNumberAproved(Integer addressNumberAproved) {
        this.addressNumberAproved = addressNumberAproved;
    }

    public Integer getAddressNumberChecked() {
        return addressNumberChecked;
    }

    public void setAddressNumberChecked(Integer addressNumberChecked) {
        this.addressNumberChecked = addressNumberChecked;
    }

    public Integer getAddressNumberCustomer2() {
        return addressNumberCustomer2;
    }

    public void setAddressNumberCustomer2(Integer addressNumberCustomer2) {
        this.addressNumberCustomer2 = addressNumberCustomer2;
    }

    public Integer getAddressNumberCustomer3() {
        return addressNumberCustomer3;
    }

    public void setAddressNumberCustomer3(Integer addressNumberCustomer3) {
        this.addressNumberCustomer3 = addressNumberCustomer3;
    }

    public Integer getAddressNumberRep2() {
        return addressNumberRep2;
    }

    public void setAddressNumberRep2(Integer addressNumberRep2) {
        this.addressNumberRep2 = addressNumberRep2;
    }

    public Integer getAddressNumberRep3() {
        return addressNumberRep3;
    }

    public void setAddressNumberRep3(Integer addressNumberRep3) {
        this.addressNumberRep3 = addressNumberRep3;
    }

    public Integer getAddressNumberRep4() {
        return addressNumberRep4;
    }

    public void setAddressNumberRep4(Integer addressNumberRep4) {
        this.addressNumberRep4 = addressNumberRep4;
    }

    public Integer getContractCurrency() {
        return contractCurrency;
    }

    public void setContractCurrency(Integer contractCurrency) {
        this.contractCurrency = contractCurrency;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public Date getInitalStartDate() {
        return initalStartDate;
    }

    public void setInitalStartDate(Date initalStartDate) {
        this.initalStartDate = initalStartDate;
    }

    public Date getEndStartDate() {
        return endStartDate;
    }

    public void setEndStartDate(Date endStartDate) {
        this.endStartDate = endStartDate;
    }

    public Double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(Double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public Double getRetinageAmount() {
        return retinageAmount;
    }

    public void setRetinageAmount(Double retinageAmount) {
        this.retinageAmount = retinageAmount;
    }

    public String getContractIdentitySide() {
        return contractIdentitySide;
    }

    public void setContractIdentitySide(String contractIdentitySide) {
        this.contractIdentitySide = contractIdentitySide;
    }

    public Collection<ContractItemTable> getContractItemTableCollection() {
        return contractItemTableCollection;
    }

    public void setContractItemTableCollection(Collection<ContractItemTable> contractItemTableCollection) {
        this.contractItemTableCollection = contractItemTableCollection;
    }

    public Collection<ContractPartyRolesTable> getContractPartyRolesTableCollection() {
        return contractPartyRolesTableCollection;
    }

    public void setContractPartyRolesTableCollection(Collection<ContractPartyRolesTable> contractPartyRolesTableCollection) {
        this.contractPartyRolesTableCollection = contractPartyRolesTableCollection;
    }

    public ContractStatusesTable getContractStatusId() {
        return contractStatusId;
    }

    public void setContractStatusId(ContractStatusesTable contractStatusId) {
        this.contractStatusId = contractStatusId;
    }

    public ContractTypesTable getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(ContractTypesTable contractTypeId) {
        this.contractTypeId = contractTypeId;
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
        if (!(object instanceof ContractsTable)) {
            return false;
        }
        ContractsTable other = (ContractsTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.contract.entity.ContractsTable[ id=" + id + " ]";
    }
    
}
