package com.demo.jsf;

import com.demo.entity.SystemConfiguration;
import com.demo.session.SystemConfigurationFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("configController")
@ApplicationScoped
public class ConfigController {

    @EJB
    private SystemConfigurationFacade configFacade;

    public int getNoActivityForReserved() {
        SystemConfiguration config = configFacade.getConfig();
        return config != null ? config.getNoActivityForReserved() : 5;
    }

    public int getAfterFixedDuration() {
        SystemConfiguration config = configFacade.getConfig();
        return config != null ? config.getAfterFixedDuration() : 8;
    }

    public int getFixedDuration() {
        SystemConfiguration config = configFacade.getConfig();
        return config != null ? config.getFixedDuration() : 10;
    }
}
