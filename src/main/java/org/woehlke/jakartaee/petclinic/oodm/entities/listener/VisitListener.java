package org.woehlke.jakartaee.petclinic.oodm.entities.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;

import javax.persistence.*;
import java.io.Serializable;

import static org.woehlke.jakartaee.petclinic.oodm.entities.listener.ListenerLogger.logIt;

public class VisitListener implements Serializable {

    private static final long serialVersionUID = -8346649868905401057L;

    private static Logger log = LogManager.getLogger(VisitListener.class.getName());

    @PrePersist
    public void onPrePersist(Visit domainObject) {
        logIt("try to Persist: ",StandardLevel.DEBUG,log, domainObject);
    }

    @PreUpdate
    public void onPreUpdate(Visit domainObject) {
        logIt("try to Update: ",StandardLevel.DEBUG,log,domainObject);
    }

    @PreRemove
    public void onPreRemove(Visit domainObject) {
        logIt("try to Remove: ",StandardLevel.INFO,log,domainObject);
    }

    @PostPersist
    public void onPostPersist(Visit domainObject) {
        logIt("Persisted: ",StandardLevel.INFO,log,domainObject);
    }

    @PostUpdate
    public void onPostUpdate(Visit domainObject) {
        logIt("Updated: ",StandardLevel.INFO,log,domainObject);
    }

    @PostRemove
    public void onPostRemove(Visit domainObject) {
        logIt("Removed: ",StandardLevel.INFO,log, domainObject);
    }

    @PostLoad
    public void onPostLoad(Visit domainObject) {
        logIt("Loaded: ", StandardLevel.TRACE,log, domainObject);
    }

}
