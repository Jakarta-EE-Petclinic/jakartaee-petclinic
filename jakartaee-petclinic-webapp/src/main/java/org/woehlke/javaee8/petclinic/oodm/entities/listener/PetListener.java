package org.woehlke.javaee8.petclinic.oodm.entities.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.javaee8.petclinic.oodm.entities.Pet;

import javax.persistence.*;
import java.io.Serializable;

import static org.woehlke.javaee8.petclinic.oodm.entities.listener.ListenerLogger.logIt;

public class PetListener  implements Serializable {

    private static Logger log = LogManager.getLogger(PetListener.class.getName());

    @PrePersist
    public void onPrePersist(Pet domainObject) {
        logIt("try to Persist: ",StandardLevel.DEBUG,log, domainObject);
    }

    @PreUpdate
    public void onPreUpdate(Pet domainObject) {
        logIt("try to Update: ",StandardLevel.DEBUG,log, domainObject);
    }

    @PreRemove
    public void onPreRemove(Pet domainObject) {
        logIt("try to Remove: ",StandardLevel.INFO,log, domainObject);
    }

    @PostPersist
    public void onPostPersist(Pet domainObject) {
        logIt("Persisted: ",StandardLevel.INFO,log, domainObject);
    }

    @PostUpdate
    public void onPostUpdate(Pet domainObject) {
        logIt("Updated: ",StandardLevel.INFO,log, domainObject);
    }

    @PostRemove
    public void onPostRemove(Pet domainObject) {
        logIt("Removed: ",StandardLevel.INFO,log, domainObject);
    }

    @PostLoad
    public void onPostLoad(Pet domainObject) {
        logIt("Loaded: ",StandardLevel.TRACE,log, domainObject);
    }

}
