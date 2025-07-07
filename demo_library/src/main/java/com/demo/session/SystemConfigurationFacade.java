package com.demo.session;

import com.demo.entity.SystemConfiguration;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class SystemConfigurationFacade {

    @PersistenceContext(unitName = "com.demo_library_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SystemConfiguration getConfig() {
        return em.find(SystemConfiguration.class, 1);
    }

    public void create(SystemConfiguration config) {
        em.persist(config);
    }

    public void edit(SystemConfiguration config) {
        em.merge(config);
    }

    public SystemConfiguration findConfig() {
        try {
            return em.createQuery("SELECT c FROM SystemConfiguration c", SystemConfiguration.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
