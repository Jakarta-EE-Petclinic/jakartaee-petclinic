package org.woehlke.jakartaee.petclinic.oodm.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Pet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class PetDaoImpl implements PetDao {

	private static final long serialVersionUID = -9149391932558758266L;

	private static Logger log = LogManager.getLogger(PetDaoImpl.class.getName());

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;


	@Override
	public Pet addNew(Pet pet) {
		pet.setUuid(UUID.randomUUID());
		log.debug("transient New Pet: " + pet.toString());
		entityManager.persist(pet);
		log.debug("persistent New Pet: " + pet.toString());
		return pet;
	}

	@Override
	public List<Pet> getAll() {
		String qlString = "select p from Pet p order by p.name";
		TypedQuery<Pet> q = entityManager.createQuery(qlString, Pet.class);
		List<Pet> list = q.getResultList();
		return list;
	}

	@Override
	public Pet findById(long petId) {
		return entityManager.find(Pet.class, petId);
	}

	@Override
	public Pet update(Pet pet) {
		log.debug("updatePet: " + pet.toString());
		return entityManager.merge(pet);
	}

	@Override
	public void delete(long id) {
		Pet p = this.findById(id);
		entityManager.remove(p);
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
