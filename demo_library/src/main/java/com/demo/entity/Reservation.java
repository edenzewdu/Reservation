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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Eden
 */
@Entity
@Table(name = "reservation")
@NamedQueries({
        @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
        @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
        @NamedQuery(name = "Reservation.findByReservedTable", query = "SELECT r FROM Reservation r WHERE r.reservedTable = :reservedTable"),
        @NamedQuery(name = "Reservation.findByRowId", query = "SELECT r FROM Reservation r WHERE r.rowId = :rowId"),
        @NamedQuery(name = "Reservation.findByRowIdString", query = "SELECT r FROM Reservation r WHERE r.rowIdString = :rowIdString"),
        @NamedQuery(name = "Reservation.findByReservedBy", query = "SELECT r FROM Reservation r WHERE r.reservedBy = :reservedBy"),
        @NamedQuery(name = "Reservation.findByReservedStartTime", query = "SELECT r FROM Reservation r WHERE r.reservedStartTime = :reservedStartTime")
})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255)
    @Column(name = "reserved_table")
    private String reservedTable;

    @Column(name = "row_id")
    private Integer rowId;

    @Size(max = 255)
    @Column(name = "row_id_string")
    private String rowIdString;

    @Size(max = 255)
    @Column(name = "reserved_by")
    private String reservedBy;

    @Column(name = "reserved_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservedStartTime;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer id, String reservedTable, Integer rowId, String reservedBy, Date reservedStartTime) {
        this.id = id;
        this.reservedTable = reservedTable;
        this.rowId = rowId;
        this.reservedBy = reservedBy;
        this.reservedStartTime = reservedStartTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(String reservedTable) {
        this.reservedTable = reservedTable;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getRowIdString() {
        return rowIdString;
    }

    public void setRowIdString(String rowIdString) {
        this.rowIdString = rowIdString;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public Date getReservedStartTime() {
        return reservedStartTime;
    }

    public void setReservedStartTime(Date reservedStartTime) {
        this.reservedStartTime = reservedStartTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.web.entity.Reservation[ id=" + id + " ]";
    }

}
