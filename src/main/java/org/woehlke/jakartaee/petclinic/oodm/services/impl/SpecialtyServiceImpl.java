package org.woehlke.jakartaee.petclinic.oodm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;
import org.woehlke.jakartaee.petclinic.oodm.services.SpecialtyService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SpecialtyServiceImpl implements SpecialtyService {

	private static final long serialVersionUID = 6145428275502469961L;

	private static Logger log = LogManager.getLogger(SpecialtyServiceImpl.class.getName());

	@EJB
	private SpecialtyDao specialtyDao;

	@Override
	public List<Specialty> getAll() {
		return this.specialtyDao.getAll();
	}

	@Override
	public void delete(long id) {
		this.specialtyDao.delete(id);
	}

	@Override
	public Specialty addNew(Specialty specialty) {
		log.debug("about to addNew: " + specialty.toString());
		return this.specialtyDao.addNew(specialty);
	}

	@Override
	public Specialty findById(long id) {
		return this.specialtyDao.findById(id);
	}

	@Override
	public Specialty update(Specialty specialty) {
		log.debug("about to update: " + specialty.toString());
		return this.specialtyDao.update(specialty);
	}

	@Override
	public List<Specialty> search(String searchterm) {
		return this.specialtyDao.search(searchterm);
	}

	@Override
	public Specialty findSpecialtyByName(String name) {
		return this.specialtyDao.findSpecialtyByName(name);
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
