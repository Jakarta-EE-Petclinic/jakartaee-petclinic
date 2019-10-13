package org.woehlke.jakartaee.petclinic.oodm.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.woehlke.jakartaee.petclinic.oodm.dao.SpecialtyDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

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
 * Date: 04.01.14
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class SpecialtyDaoImpl implements SpecialtyDao {

  private static final long serialVersionUID = 1355422039564914705L;

  private static Logger log = LogManager.getLogger(SpecialtyDaoImpl.class.getName());

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  private EntityManager entityManager;

  @Override
  public List<Specialty> getAll() {
    String qlString = "select s from Specialty s order by s.name";
    TypedQuery<Specialty> q = entityManager.createQuery(qlString, Specialty.class);
    List<Specialty> list = q.getResultList();
    return list;
  }

  @Override
  public void delete(long id) {
    Specialty specialty = entityManager.find(Specialty.class, id);
    entityManager.remove(specialty);
  }

  @Override
  public Specialty addNew(Specialty specialty) {
    specialty.setUuid(UUID.randomUUID());
    log.debug("addNewSpecialty: " + specialty.toString());
    entityManager.persist(specialty);
    log.debug("persisted:       " + specialty.toString());
    return specialty;
  }

  @Override
  public Specialty findById(long id) {
    Specialty specialty = entityManager.find(Specialty.class, id);
    return specialty;
  }

  @Override
  public Specialty findSpecialtyByName(String name) {
    String ql = "select  s from Specialty s where s.name=:name";
    TypedQuery<Specialty> query = entityManager.createQuery(ql, Specialty.class);
    query.setParameter("name", name);
    Specialty specialty = query.getSingleResult();
    return specialty;
  }

  @Override
  public Specialty update(Specialty specialty) {
    log.debug("update: " + specialty.toString());
    specialty = entityManager.merge(specialty);
    log.debug("merged: " + specialty.toString());
    return specialty;
  }

  @Override
  public List<Specialty> search(String searchterm) {
    log.debug("search for: " + searchterm);
    FullTextEntityManager fullTextEntityManager =
        org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
    QueryBuilder qb = fullTextEntityManager.getSearchFactory()
        .buildQueryBuilder().forEntity(Specialty.class).get();
    org.apache.lucene.search.Query query = qb
        .keyword()
        .onFields("name")
        .matching(searchterm)
        .createQuery();
    // wrap Lucene query in a javax.persistence.Query
    javax.persistence.Query persistenceQuery =
        fullTextEntityManager.createFullTextQuery(query, Specialty.class);
    // execute search
    @SuppressWarnings("unchecked")
    List<Specialty> result = persistenceQuery.getResultList();
    log.debug("found: " + result.size());
    for (Specialty o : result) {
      log.debug("found: " + o.getName());
    }
    return result;
  }

  @Override
  public void resetSearchIndex() {
    FullTextEntityManager fullTextEntityManager =
        org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
    String qlString = "select o from Specialty o";
    TypedQuery<Specialty> findAllActionItems = fullTextEntityManager.createQuery(qlString, Specialty.class);
    for (Specialty petType : findAllActionItems.getResultList()) {
      fullTextEntityManager.index(petType);
    }
    fullTextEntityManager.flushToIndexes();
    //fullTextEntityManager.clear();
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
