package com.demo.session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.demo.entity.Reservation;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Eden
 */
@Stateless
public class ReservationFacade extends AbstractFacadeSavvy<Reservation> {

    @PersistenceContext(unitName = "com.demo_library_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    // Custom method: find reservation by table name and row ID
    public Reservation findActiveReservation(String tableName, Integer rowId) {
        try {
            Reservation reservation = em.createQuery(
                            "SELECT r FROM Reservation r WHERE r.reservedTable = :table AND r.rowId = :rowId",
                            Reservation.class)
                    .setParameter("table", tableName)
                    .setParameter("rowId", rowId)
                    .setMaxResults(1)
                    .getSingleResult();

            // Check expiration (10 minutes = 600000 ms)
            long now = System.currentTimeMillis();
            if (reservation.getReservedStartTime() != null &&
                    now - reservation.getReservedStartTime().getTime() > 600_000) {
                // Reservation expired
                em.remove(reservation);
                return null;
            }

            return reservation;

        } catch (Exception e) {
            return null;
        }
    }

    public List<Reservation> findByReservedBy(String reservedBy) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.reservedBy = :reservedBy", Reservation.class)
                .setParameter("reservedBy", reservedBy)
                .getResultList();
    }

    public List<Reservation> findExpiredReservations(Date beforeTime) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.reservedStartTime < :beforeTime", Reservation.class)
                .setParameter("beforeTime", beforeTime)
                .getResultList();
    }

    public List<Reservation> findInactiveSince(Date lastActiveBefore) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.lastActivityTime < :time", Reservation.class)
                .setParameter("time", lastActiveBefore)
                .getResultList();
    }



}
