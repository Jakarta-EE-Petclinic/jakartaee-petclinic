package org.woehlke.jakartaee.petclinic.oodm.entities.listener;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.spi.StandardLevel;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import javax.persistence.*;
import java.io.Serializable;

import static org.woehlke.jakartaee.petclinic.oodm.entities.listener.ListenerLogger.logIt;

@Log4j2
public class PetTypeListener implements Serializable {

  private static final long serialVersionUID = 8419288369702922659L;

  @PrePersist
  public void onPrePersist(PetType domainObject) {
    logIt("try to Persist: ", StandardLevel.DEBUG, log, domainObject);
  }

  @PreUpdate
  public void onPreUpdate(PetType domainObject) {
    logIt("try to Update: ", StandardLevel.DEBUG, log, domainObject);
  }

  @PreRemove
  public void onPreRemove(PetType domainObject) {
    logIt("try to Remove: ", StandardLevel.INFO, log, domainObject);
  }

  @PostPersist
  public void onPostPersist(PetType domainObject) {
    logIt("Persisted: ", StandardLevel.INFO, log, domainObject);
  }

  @PostUpdate
  public void onPostUpdate(PetType domainObject) {
    logIt("Updated: ", StandardLevel.INFO, log, domainObject);
  }

  @PostRemove
  public void onPostRemove(PetType domainObject) {
    logIt("Removed: ", StandardLevel.INFO, log, domainObject);
  }

  @PostLoad
  public void onPostLoad(PetType domainObject) {
    logIt("Loaded: ", StandardLevel.TRACE, log, domainObject);
  }

}
