package org.woehlke.jakartaee.petclinic.oodm.entities.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.jakartaee.petclinic.oodm.entities.Vet;

import javax.persistence.*;
import java.io.Serializable;

import static org.woehlke.jakartaee.petclinic.oodm.entities.listener.ListenerLogger.logIt;

public class VetListener implements Serializable {

    private static final long serialVersionUID = 1624676127692597414L;

    private static Logger log = LogManager.getLogger(VetListener.class.getName());

    @PrePersist
    public void onPrePersist(Vet domainObject) {
        logIt("try to Persist: ",StandardLevel.DEBUG,log,domainObject);
    }

    @PreUpdate
    public void onPreUpdate(Vet domainObject) {
        logIt("try to Update: ",StandardLevel.DEBUG,log,domainObject);
    }

    @PreRemove
    public void onPreRemove(Vet domainObject) {
        logIt("try to Remove: ",StandardLevel.INFO,log,domainObject);
    }

    @PostPersist
    public void onPostPersist(Vet domainObject) {
        logIt("Persisted: ",StandardLevel.INFO,log,domainObject);
    }

    @PostUpdate
    public void onPostUpdate(Vet domainObject) {
        logIt("Updated: ",StandardLevel.INFO,log,domainObject);
    }

    @PostRemove
    public void onPostRemove(Vet domainObject) {
        logIt("Removed: ",StandardLevel.INFO,log,domainObject);
    }

    @PostLoad
    public void onPostLoad(Vet domainObject) {
        logIt("Loaded: ",StandardLevel.TRACE,log,domainObject);
    }

}
