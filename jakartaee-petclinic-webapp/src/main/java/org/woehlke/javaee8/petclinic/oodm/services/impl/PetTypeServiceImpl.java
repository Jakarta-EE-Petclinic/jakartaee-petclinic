package org.woehlke.javaee8.petclinic.oodm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.oodm.dao.PetTypeDao;
import org.woehlke.javaee8.petclinic.oodm.entities.PetType;
import org.woehlke.javaee8.petclinic.oodm.services.PetTypeService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PetTypeServiceImpl implements PetTypeService {

    private static Logger log = LogManager.getLogger(PetTypeServiceImpl.class.getName());

    @EJB
    private PetTypeDao petTypeDao;

    @Override
    public List<PetType> getAll() {
        return petTypeDao.getAll();
    }

    @Override
    public void delete(long id) {
        this.petTypeDao.delete(id);
    }

    @Override
    public PetType addNew(PetType petType) {
        log.debug("about to addNew: "+ petType.toString());
        return this.petTypeDao.addNew(petType);
    }

    @Override
    public PetType findById(long id) {
        return this.petTypeDao.findById(id);
    }

    @Override
    public PetType update(PetType petType) {
        log.debug("about to update: "+ petType.toString());
        return this.petTypeDao.update(petType);
    }

    @Override
    public List<PetType> search(String searchterm) {
        return this.petTypeDao.search(searchterm);
    }

    @Override
    public PetType findByName(String name) {
        return this.petTypeDao.findByName(name);
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
