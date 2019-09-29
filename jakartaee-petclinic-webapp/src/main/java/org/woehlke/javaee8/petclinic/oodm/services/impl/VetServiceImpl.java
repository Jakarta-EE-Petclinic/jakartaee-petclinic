package org.woehlke.javaee8.petclinic.oodm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.javaee8.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.javaee8.petclinic.oodm.dao.VetDao;
import org.woehlke.javaee8.petclinic.oodm.entities.Specialty;
import org.woehlke.javaee8.petclinic.oodm.entities.Vet;
import org.woehlke.javaee8.petclinic.oodm.services.VetService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class VetServiceImpl implements VetService {

    private static Logger log = LogManager.getLogger(VetServiceImpl.class.getName());

    @EJB
    private VetDao vetDao;

    @EJB
    private SpecialtyDao specialtyDao;

    @Override
    public List<Vet> getAll() {
        return vetDao.getAll();
    }

    @Override
    public void delete(long id) {
        this.vetDao.delete(id);
    }

    @Override
    public Vet addNew(Vet vet) {
        log.debug("about to addNew: "+vet.toString());
        for(Specialty specialty:vet.getSpecialties()){
            //TODO: ???
            specialtyDao.addNew(specialty);
        }
        return this.vetDao.addNew(vet);
    }

    @Override
    public Vet findById(long id) {
        return this.vetDao.findById(id);
    }

    @Override
    public Vet update(Vet vet) {
        for(Specialty specialty:vet.getSpecialties()){
            specialtyDao.update(specialty);
        }
        return this.vetDao.update(vet);
    }

    @Override
    public List<Vet> search(String searchterm) {
        return this.vetDao.search(searchterm);
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
