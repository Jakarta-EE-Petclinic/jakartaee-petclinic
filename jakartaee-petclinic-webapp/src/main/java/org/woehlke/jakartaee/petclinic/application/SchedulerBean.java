package org.woehlke.jakartaee.petclinic.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Startup
@ManagedBean
public class SchedulerBean implements Serializable {

    private static final long serialVersionUID = 8425286360447275162L;

    private static Logger log = LogManager.getLogger(SchedulerBean.class.getName());

    @EJB
    private SearchIndexService searchIndexService;

    @Schedules ({
            @Schedule(minute="15",persistent = false)
    })
    public void automaticallyScheduled() {
        LocalDateTime now = LocalDateTime.now();
        log.debug("automaticallyScheduled: "+now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        searchIndexService.resetSearchIndex();
    }

    @PostConstruct
    public void postConstruct(){
        log.debug("postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        log.debug("preDestroy");
    }

    @PrePassivate
    public void prePassivate(){
        log.debug("prePassivate");
    }

    @PostActivate
    public void postActivate(){
        log.debug("postActivate");
    }
}
