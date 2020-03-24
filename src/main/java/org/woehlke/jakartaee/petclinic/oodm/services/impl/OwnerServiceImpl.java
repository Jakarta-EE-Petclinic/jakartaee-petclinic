package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.dao.OwnerDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetDao;
import org.woehlke.jakartaee.petclinic.oodm.dao.VisitDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;
import org.woehlke.jakartaee.petclinic.oodm.entities.Visit;
import org.woehlke.jakartaee.petclinic.oodm.services.OwnerService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by tw on 10.03.14.
 */
@Log4j2
@Stateless
public class OwnerServiceImpl implements OwnerService {

  private static final long serialVersionUID = -553095693269912269L;

  @EJB
  private OwnerDao ownerDao;

  @EJB
  private PetDao petDao;

  @EJB
  private VisitDao visitDao;

  @Override
  public Visit addNewVisit(Visit visit) {
    log.debug("about to addNewVisit: " + visit.toString());
    Pet pet = visit.getPet();
    Owner owner = pet.getOwner();
    visit.setPet(null);
    visit = visitDao.addNew(visit);
    owner = ownerDao.update(owner);
    pet.setOwner(owner);
    pet = petDao.update(pet);
    visit.setPet(pet);
    visit = visitDao.update(visit);
    return visit;
  }

  @Override
  public List<Owner> getAll() {
    return this.ownerDao.getAll();
  }

  @Override
  public void delete(long id) {
    this.ownerDao.delete(id);
  }

  @Override
  public Owner addNew(Owner owner) {
    log.debug("about to addNew: " + owner.toString());
    return this.ownerDao.addNew(owner);
  }

  @Override
  public Owner findById(long id) {
    return this.ownerDao.findById(id);
  }

  @Override
  public Owner update(Owner owner) {
    log.debug("about to update: " + owner.toString());
    return this.ownerDao.update(owner);
  }

  @Override
  public List<Owner> search(String searchterm) {
    return this.ownerDao.search(searchterm);
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
