package org.woehlke.jakartaee.petclinic.oodm.entities.common;

public interface TwEntitiesListener<T extends TwEntities>  {

    void onPrePersist(T domainObject);

    void onPreUpdate(T domainObject);

    void onPreRemove(T domainObject);

    void onPostPersist(T domainObject);

    void onPostUpdate(T domainObject);

    void onPostRemove(T domainObject);

    void onPostLoad(T domainObject);
}
