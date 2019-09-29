package org.woehlke.jakartaee.petclinic.frontend.web.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.frontend.web.FrontendMessagesView;
import org.woehlke.jakartaee.petclinic.oodm.entities.common.TwEntities;

import javax.faces.bean.ManagedBean;;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

//TODO work with keys and fetch text from Resource-Bunble message.properties
@SuppressWarnings("deprecation")
@ManagedBean(name="frontendMessagesView")
@SessionScoped
public class FrontendMessagesViewImpl implements FrontendMessagesView {

    private static Logger log = LogManager.getLogger(FrontendMessagesViewImpl.class.getName());

    public void addInfoMessage(String summary, String detail) {
        FacesMessage.Severity messageSeverity=FacesMessage.SEVERITY_INFO;
        String clientId = null;
        this.addMessage(messageSeverity,summary,detail, clientId);
    }

    public void addWarnMessage(String summary, String detail) {
        FacesMessage.Severity messageSeverity=FacesMessage.SEVERITY_WARN;
        String clientId = null;
        this.addMessage(messageSeverity,summary,detail, clientId);
    }

    public void addErrorMessage(String summary, String detail) {
        FacesMessage.Severity messageSeverity=FacesMessage.SEVERITY_ERROR;
        String clientId = null;
        this.addMessage(messageSeverity,summary,detail, clientId);
    }

    public void addFatalMessage(String summary, String detail) {
        FacesMessage.Severity messageSeverity=FacesMessage.SEVERITY_FATAL;
        String clientId = null;
        this.addMessage(messageSeverity,summary,detail, clientId);
    }

    @Override
    public void addInfoMessage(String summary, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
        this.addMessageForEntity(summary,entity, clientId, messageSeverity);
    }

    @Override
    public void addWarnMessage(String summary, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_WARN;
        this.addMessageForEntity(summary,entity,clientId, messageSeverity);
    }

    @Override
    public void addErrorMessage(String summary, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
        this.addMessageForEntity(summary,entity,clientId, messageSeverity);
    }

    @Override
    public void addFatalMessage(String summary, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_FATAL;
        this.addMessageForEntity(summary,entity,clientId, messageSeverity);
    }

    @Override
    public void addInfoMessage(RuntimeException e, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_INFO;
        this.addMessageForEntityAndRuntimeException(e,entity,clientId, messageSeverity);
    }

    @Override
    public void addWarnMessage(RuntimeException e, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_WARN;
        this.addMessageForEntityAndRuntimeException(e,entity,clientId, messageSeverity);
    }

    @Override
    public void addErrorMessage(RuntimeException e, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_ERROR;
        this.addMessageForEntityAndRuntimeException(e,entity, clientId, messageSeverity);
    }

    @Override
    public void addFatalMessage(RuntimeException e, TwEntities entity) {
        String clientId = null;
        FacesMessage.Severity messageSeverity = FacesMessage.SEVERITY_FATAL;
        this.addMessageForEntityAndRuntimeException(e,entity,clientId, messageSeverity);
    }

    private void doLogging(List<String> logInfos,FacesMessage.Severity messageSeverity){
        if(messageSeverity.equals(FacesMessage.SEVERITY_INFO)) {
            for(String logInfo:logInfos){
                log.info(logInfo);
            }
        }
        if(messageSeverity.equals(FacesMessage.SEVERITY_WARN)) {
            for(String logInfo:logInfos){
                log.warn(logInfo);
            }
        }
        if(messageSeverity.equals(FacesMessage.SEVERITY_ERROR)) {
            for(String logInfo:logInfos){
                log.error(logInfo);
            }
        }
        if(messageSeverity.equals(FacesMessage.SEVERITY_FATAL)) {
            for(String logInfo:logInfos){
                log.error(logInfo);
            }
        }
    }

    private void addMessageForEntity(String summary, TwEntities entity, String clientId, FacesMessage.Severity messageSeverity){
        List<String> logInfos = new ArrayList<>();
        logInfos.add("summary:           " + summary);
        String detail = "";
        if(entity != null) {
            logInfos.add("addFrontendMessageForEntity.PrimaryKey: " + entity.getPrimaryKey());
            logInfos.add("addFrontendMessageForEntity.id:         " + entity.getId());
            logInfos.add("addFrontendMessageForEntity.uud:        " + entity.getUuid());
            logInfos.add("addFrontendMessageForEntity.clientId:   " + clientId);
            detail = "entity: " + entity.getPrimaryKey();
        } else {
            String msg = "entity == null ";
            logInfos.add(msg);
            detail = msg;
        }
        this.doLogging(logInfos,messageSeverity);
        this.addMessage(messageSeverity,summary, detail, clientId);
    }

    private void addMessageForEntityAndRuntimeException(RuntimeException e, TwEntities entity, String clientId, FacesMessage.Severity messageSeverity){
        String summary = e.getLocalizedMessage();
        List<String> logInfos = new ArrayList<>();
        logInfos.add("summary:           " + summary);
        logInfos.add("RuntimeException:  " + e.getMessage());
        logInfos.add("RuntimeException:  " + e.getLocalizedMessage());
        long i = 0;
        for(StackTraceElement element:e.getStackTrace()){
            i++;
            logInfos.add("StackTrace["+i+"]: "+element.getClass().getPackage());
            logInfos.add("StackTrace["+i+"]: "+element.getClassName());
            logInfos.add("StackTrace["+i+"]: "+element.getMethodName());
            logInfos.add("StackTrace["+i+"]: "+element.getFileName());
            logInfos.add("StackTrace["+i+"]: "+element.getLineNumber());
        }
        this.doLogging(logInfos,messageSeverity);
        this.addMessageForEntity(summary,entity,clientId,messageSeverity);
    }

    private void addMessage(FacesMessage.Severity messageSeverity, String summary, String detail, String clientId){
        List<String> logInfos = new ArrayList<>();
        logInfos.add("addFrontendMessage.summary:   " + summary);
        logInfos.add("addFrontendMessage.detail:    " + detail);
        logInfos.add("addFrontendMessage.clientId:  " + clientId);
        this.doLogging(logInfos,messageSeverity);
        FacesMessage message = new FacesMessage(messageSeverity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }


    @PostConstruct
    public void postConstruct(){
        log.trace("postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        log.trace("preDestroy");
    }

}