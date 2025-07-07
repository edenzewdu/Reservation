package com.demo.jsf;

import com.demo.entity.SystemConfiguration;
import com.demo.session.SystemConfigurationFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("configController")
@ApplicationScoped
public class ConfigController {

    @EJB
    private SystemConfigurationFacade configFacade;

    private SystemConfiguration config;

    @PostConstruct
    public void init() {
        config = configFacade.findConfig();

        if (config == null || config.getId() == null) {
            config = new SystemConfiguration();
            config.setFixedDuration(20);
            config.setAfterFixedDuration(10);
            config.setNoActivityForReserved(5);
            configFacade.create(config);
            System.out.println("✅ System Configuration created with defaults.");
        } else {
            System.out.println("✅ System Configuration loaded with ID: " + config.getId());
        }
    }

    public SystemConfiguration getConfig() {
        return config;
    }

    public int getFixedDuration() {
        return config != null ? config.getFixedDuration() : 20;
    }

    public int getAfterFixedDuration() {
        return config != null ? config.getAfterFixedDuration() : 10;
    }

    public int getNoActivityForReserved() {
        return config != null ? config.getNoActivityForReserved() : 5;
    }

    public void save() {
        if (config != null && config.getId() != null) {
            configFacade.edit(config);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "✅ Success", "Configuration saved."));
            System.out.println("💾 Configuration updated.");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "❌ Error", "Configuration or ID is null."));
        }
    }


    public void reload() {
        config = configFacade.findConfig();
        System.out.println("🔁 Configuration reloaded.");
    }
}
