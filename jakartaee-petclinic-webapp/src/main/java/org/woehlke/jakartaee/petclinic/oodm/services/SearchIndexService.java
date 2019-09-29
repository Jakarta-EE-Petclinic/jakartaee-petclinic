package org.woehlke.jakartaee.petclinic.oodm.services;

import java.io.Serializable;

public interface SearchIndexService extends Serializable {
    void resetSearchIndex();
}
