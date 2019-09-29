package org.woehlke.jakartaee.petclinic.oodm.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.woehlke.jakartaee.petclinic.oodm.dao.PetTypeDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

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
 * User: Fert
 * Date: 06.01.14
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class PetTypeDaoImpl implements PetTypeDao {

    private static Logger log = LogManager.getLogger(PetTypeDaoImpl.class.getName());

    @PersistenceContext(unitName="javaee8petclinic")
    private EntityManager entityManager;

    @Override
    public List<PetType> getAll() {
        String qlString = "select pt from PetType pt order by pt.name";
        TypedQuery<PetType> q = entityManager.createQuery(qlString, PetType.class);
        List<PetType> list =  q.getResultList();
        return list;
    }

    @Override
    public void delete(long id) {
        PetType petType = entityManager.find(PetType.class, id);
        entityManager.remove(petType);
    }

    @Override
    public PetType addNew(PetType petType) {
        log.debug("addNewPetType: "+petType.toString());
        petType.setUuid(UUID.randomUUID());
        entityManager.persist(petType);
        return petType;
    }

    @Override
    public PetType findById(long id) {
        PetType petType = entityManager.find(PetType.class, id);
        return petType;
    }

    @Override
    public PetType update(PetType petType) {
        return entityManager.merge(petType);
    }

    @Override
    public List<PetType> search(String searchterm) {
        log.debug("search for: "+searchterm);
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity( PetType.class ).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("name")
                .matching(searchterm)
                .createQuery();
        // wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, PetType.class);
        // execute search
        @SuppressWarnings("unchecked")
        List<PetType> result = persistenceQuery.getResultList();
        log.debug("found: "+result.size());
        for(PetType o: result){
            log.debug("found: "+o.getName());
        }
        return  result;
    }

    @Override
    public void resetSearchIndex() {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        String qlString = "select o from PetType o";
        TypedQuery<PetType> findAllActionItems = fullTextEntityManager.createQuery(qlString,PetType.class);
        for(PetType petType :findAllActionItems.getResultList()){
            fullTextEntityManager.index(petType);
        }
        //fullTextEntityManager.flushToIndexes();
        //fullTextEntityManager.clear();
    }

    @Override
    public PetType findByName(String name) {
        String ql = "select s from PetType s where s.name=:name";
        TypedQuery<PetType> query = entityManager.createQuery(ql, PetType.class);
        query.setParameter("name",name);
        PetType myPetType = query.getSingleResult();
        return myPetType;
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
