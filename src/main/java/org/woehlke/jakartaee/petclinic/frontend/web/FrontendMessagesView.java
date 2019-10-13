package org.woehlke.jakartaee.petclinic.frontend.web;

import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import java.io.Serializable;

public interface FrontendMessagesView extends Serializable {

  long serialVersionUID = 2936821773310905949L;

  void addInfoMessage(String summary, String detail);

  void addWarnMessage(String summary, String detail);

  void addErrorMessage(String summary, String detail);

  void addFatalMessage(String summary, String detail);

  void addInfoMessage(String summary, TwEntities entity);

  void addWarnMessage(String summary, TwEntities entity);

  void addErrorMessage(String summary, TwEntities entity);

  void addFatalMessage(String summary, TwEntities entity);

  void addInfoMessage(RuntimeException e, TwEntities entity);

  void addWarnMessage(RuntimeException e, TwEntities entity);

  void addErrorMessage(RuntimeException e, TwEntities entity);

  void addFatalMessage(RuntimeException e, TwEntities entity);

}
