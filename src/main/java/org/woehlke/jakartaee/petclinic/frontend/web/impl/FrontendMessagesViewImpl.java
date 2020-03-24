package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Named("frontendMessagesView")
@SessionScoped
public class FrontendMessagesViewImpl implements FrontendMessagesView {

  private static final long serialVersionUID = -2267751568724878682L;

  public void addInfoMessage(String summary, String detail) {
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
    String clientId = null;
    this.addMessage(messageSeverity, summary, detail, clientId);
  }

  public void addWarnMessage(String summary, String detail) {
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_WARN;
    String clientId = null;
    this.addMessage(messageSeverity, summary, detail, clientId);
  }

  public void addErrorMessage(String summary, String detail) {
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
    String clientId = null;
    this.addMessage(messageSeverity, summary, detail, clientId);
  }

  public void addFatalMessage(String summary, String detail) {
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_FATAL;
    String clientId = null;
    this.addMessage(messageSeverity, summary, detail, clientId);
  }

  @Override
  public void addInfoMessage(String summary, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
    this.addMessageForEntity(summary, entity, clientId, messageSeverity);
  }

  @Override
  public void addWarnMessage(String summary, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_WARN;
    this.addMessageForEntity(summary, entity, clientId, messageSeverity);
  }

  @Override
  public void addErrorMessage(String summary, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
    this.addMessageForEntity(summary, entity, clientId, messageSeverity);
  }

  @Override
  public void addFatalMessage(String summary, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_FATAL;
    this.addMessageForEntity(summary, entity, clientId, messageSeverity);
  }

  @Override
  public void addInfoMessage(RuntimeException e, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
    this.addMessageForEntityAndRuntimeException(e, entity, clientId, messageSeverity);
  }

  @Override
  public void addWarnMessage(RuntimeException e, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_WARN;
    this.addMessageForEntityAndRuntimeException(e, entity, clientId, messageSeverity);
  }

  @Override
  public void addErrorMessage(RuntimeException e, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
    this.addMessageForEntityAndRuntimeException(e, entity, clientId, messageSeverity);
  }

  @Override
  public void addFatalMessage(RuntimeException e, TwEntities entity) {
    String clientId = null;
    FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_FATAL;
    this.addMessageForEntityAndRuntimeException(e, entity, clientId, messageSeverity);
  }

  private void doLogging(List<String> logInfos, FacesMessage.Severity messageSeverity) {
    if (!logInfos.isEmpty()) {
      int first = 0;
      String firstLine = logInfos.remove(first);
      if (messageSeverity.equals(FacesMessage.SEVERITY_INFO)) {
        log.info(firstLine);
      }
      if (messageSeverity.equals(FacesMessage.SEVERITY_WARN)) {
        log.warn(firstLine);
      }
      if (messageSeverity.equals(FacesMessage.SEVERITY_ERROR)) {
        log.error(firstLine);
      }
      if (messageSeverity.equals(FacesMessage.SEVERITY_FATAL)) {
        log.fatal(firstLine);
      }
      for (String logInfo : logInfos) {
        log.debug(logInfo);
      }
    }
  }

  private void addMessageForEntity(String summary, TwEntities entity, String clientId, FacesMessage.Severity messageSeverity) {
    List<String> logInfos = new ArrayList<>();
    logInfos.add("summary:           " + summary);
    String detail = "";
    if (entity != null) {
      logInfos.add("addFrontendMessageForEntity.PrimaryKey: " + entity.getPrimaryKey());
      logInfos.add("addFrontendMessageForEntity.id:         " + entity.getId());
      logInfos.add("addFrontendMessageForEntity.uud:        " + entity.getUuid());
      if (clientId != null) {
        logInfos.add("addFrontendMessageForEntity.clientId:   " + clientId);
      }
      detail = entity.getPrimaryKey();
    } else {
      String msg = "entity == null ";
      logInfos.add(msg);
      detail = msg;
    }
    this.doLogging(logInfos, messageSeverity);
    this.addMessage(messageSeverity, summary, detail, clientId);
  }

  private void addMessageForEntityAndRuntimeException(RuntimeException e, TwEntities entity, String clientId, FacesMessage.Severity messageSeverity) {
    String summary = e.getLocalizedMessage();
    StringBuilder sb1 = new StringBuilder("\n");
    sb1.append("-----------------------------------------------------\n");
    sb1.append("entity Table       " + entity.getTableName() + "\n");
    sb1.append("entity Class       " + entity.getClass().getName() + "\n");
    sb1.append("entity UUID        " + entity.getUuid() + "\n");
    sb1.append("entity ID          " + entity.getId() + "\n");
    sb1.append("entity PK          " + entity.getPrimaryKey() + "\n");
    sb1.append("-----------------------------------------------------\n");
    sb1.append("RuntimeException Class   " + e.getClass().getName() + "\n");
    sb1.append("RuntimeException Message " + e.getLocalizedMessage() + "\n");
    sb1.append("Exception Cause Class    " + e.getCause().getClass().getName() + "\n");
    sb1.append("Exception Cause Message   " + e.getCause().getLocalizedMessage() + "\n");
    sb1.append("-----------------------------------------------------\n");
    StringBuilder sb = new StringBuilder("\n");
    long i = 0L;
    for (StackTraceElement element : e.getStackTrace()) {
      i++;
      StringBuilder lfdnr = new StringBuilder();
      if (i < 10) {
        lfdnr.append(" ");
      }
      if (i < 100) {
        lfdnr.append(" ");
      }
      lfdnr.append(i);
      sb.append("StackTrace[" + lfdnr.toString() + "]: " + element.getClassName() + " . " + element.getMethodName() + " in: \n");
      sb.append("StackTrace[" + lfdnr.toString() + "]: " + element.getFileName() + " ( Line " + element.getLineNumber() + ")\n");
    }
    sb.append("-----------------------------------------------------\n");
    List<String> logInfos = new ArrayList<>();
    logInfos.add(sb.toString());
    logInfos.add(sb1.toString());
    this.doLogging(logInfos, messageSeverity);
    this.addMessageForEntity(summary, entity, clientId, messageSeverity);
  }

  private void addMessage(FacesMessage.Severity messageSeverity, String summary, String detail, String clientId) {
    List<String> logInfos = new ArrayList<>();
    logInfos.add("addFrontendMessage.summary:   " + summary);
    logInfos.add("addFrontendMessage.detail:    " + detail);
    if (clientId != null) {
      logInfos.add("addFrontendMessage.clientId:  " + clientId);
    }
    this.doLogging(logInfos, messageSeverity);
    FacesMessage message = new FacesMessage(messageSeverity, summary, detail);
    FacesContext.getCurrentInstance().addMessage(clientId, message);
  }


  @PostConstruct
  public void postConstruct() {
    log.trace("postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    log.trace("preDestroy");
  }

}
