package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.dao.OwnerDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetTypeDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.jakartaee.petclinic.oodm.services.SearchIndexService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

@Log4j2
@Stateless
public class SearchIndexServiceImpl implements SearchIndexService {

  private static final long serialVersionUID = 2941468681052495358L;

  @EJB
  private OwnerDao ownerDao;

  @EJB
  private PetTypeDao petTypeDao;

  @EJB
  private SpecialtyDao specialtyDao;

  @Override
  @Asynchronous
  public void resetSearchIndex() {
    log.debug("resetSearchIndex Start");
    ownerDao.resetSearchIndex();
    petTypeDao.resetSearchIndex();
    specialtyDao.resetSearchIndex();
    log.debug("resetSearchIndex Done");
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
