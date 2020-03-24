package org.woehlke.jakartaee.petclinic.application;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Log4j2
@Startup
@Singleton
@ApplicationScoped
public class SchedulerBean implements Serializable {

  private static final long serialVersionUID = 8425286360447275162L;

  @EJB
  private SearchIndexService searchIndexService;

  @Schedules({
      @Schedule(
          minute = "15",
          persistent = false
      )
  })
  public void automaticallyScheduled() {
    LocalDateTime now = LocalDateTime.now();
    log.debug("automaticallyScheduled: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    searchIndexService.resetSearchIndex();
  }

  @PostConstruct
  public void postConstruct() {
    log.debug("postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    log.debug("preDestroy");
  }

  @PrePassivate
  public void prePassivate() {
    log.debug("prePassivate");
  }

  @PostActivate
  public void postActivate() {
    log.debug("postActivate");
  }
}
