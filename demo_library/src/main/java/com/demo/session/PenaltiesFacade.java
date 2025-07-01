/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.session;

import com.demo.entity.Penalties;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Lucy
 */
@Stateless
public class PenaltiesFacade extends AbstractFacadeSavvy<Penalties> {

    @PersistenceContext(unitName = "com.demo_library_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PenaltiesFacade() {
        super(Penalties.class);
    }
    
}
