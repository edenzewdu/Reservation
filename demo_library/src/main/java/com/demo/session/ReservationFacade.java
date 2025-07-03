package com.demo.session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.demo.entity.Reservation;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Eden
 */
@Stateless
public class ReservationFacade extends AbstractFacadeSavvy<Reservation> {

    @PersistenceContext(unitName = "com.web_library_war_1.0-SNAPSHOTPU")
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
            return em.createQuery("SELECT r FROM Reservation r WHERE r.reservedTable = :table AND r.rowId = :rowId", Reservation.class)
                    .setParameter("table", tableName)
                    .setParameter("rowId", rowId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
