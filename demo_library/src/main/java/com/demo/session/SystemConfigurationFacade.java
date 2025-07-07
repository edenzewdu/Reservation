package com.demo.session;

import com.demo.entity.SystemConfiguration;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class SystemConfigurationFacade {

    @PersistenceContext(unitName = "YourPU")
    private EntityManager em;

    public SystemConfiguration getConfig() {
        return em.find(SystemConfiguration.class, 1);
    }
}
