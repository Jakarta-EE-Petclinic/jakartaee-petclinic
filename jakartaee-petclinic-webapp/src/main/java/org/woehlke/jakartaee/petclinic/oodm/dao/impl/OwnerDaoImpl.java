package org.woehlke.jakartaee.petclinic.oodm.dao.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.woehlke.jakartaee.petclinic.oodm.dao.OwnerDao;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

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
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class OwnerDaoImpl implements OwnerDao {

    private static Logger log = LogManager.getLogger(OwnerDaoImpl.class.getName());

    @PersistenceContext(unitName="javaee8petclinic")
    private EntityManager entityManager;

    @Override
    public List<Owner> getAll() {
        TypedQuery<Owner> q = entityManager.createQuery(
                "select o from org.woehlke.jakartaee.petclinic.oodm.entities.Owner o order by o.lastName,o.firstName",
                Owner.class
        );
        List<Owner> list =  q.getResultList();
        return list;
    }

    @Override
    public void delete(long id) {
        Owner owner = entityManager.find(Owner.class, id);
        entityManager.remove(owner);
    }

    @Override
    public Owner addNew(Owner owner) {
        owner.setUuid(UUID.randomUUID());
        log.debug("addNewOwner: "+owner.toString());
        entityManager.persist(owner);
        return owner;
    }

    @Override
    public Owner findById(long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public Owner update(Owner owner) {
        log.debug("updateOwner: "+owner.toString());
        return entityManager.merge(owner);
    }

    @Override
    public List<Owner> search(String searchterm) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity( Owner.class ).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("firstName", "lastName", "city", "pets.name")
                .matching(searchterm)
                .createQuery();
        // wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, Owner.class);
        // execute search
        @SuppressWarnings("unchecked")
		List<Owner> result = persistenceQuery.getResultList();
        for(Owner o: result){
            log.debug("found: "+o.getFullName());
        }
        return  result;
    }

    @Override
    public void resetSearchIndex() {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        String qlString = "select o from Owner o";
        TypedQuery<Owner> findAllActionItems = fullTextEntityManager.createQuery (
                qlString,
                Owner.class
        );
        for(Owner owner : findAllActionItems.getResultList()){
            fullTextEntityManager.index(owner);
        }
        fullTextEntityManager.flushToIndexes();
        //fullTextEntityManager.clear();
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
