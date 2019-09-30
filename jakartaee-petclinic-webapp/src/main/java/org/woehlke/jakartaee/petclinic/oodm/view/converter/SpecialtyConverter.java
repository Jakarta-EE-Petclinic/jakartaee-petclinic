package org.woehlke.jakartaee.petclinic.oodm.view.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.01.14
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
@FacesConverter(
    value = "specialtyConverter",
    managed = true
)
public class SpecialtyConverter implements Converter<Specialty>, Serializable {

    private static Logger log = LogManager.getLogger(SpecialtyConverter.class.getName());

    @Override
    public Specialty getAsObject(FacesContext context, UIComponent component, String value) {
        log.trace("getAsObject: value = "+value);
        Specialty specialty = new Specialty(value);
        return specialty;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Specialty value) {
        if (value == null){
            return "Bingo Bongo";
        } else {
            log.trace("getAsString: " + value.toString());
            return value.getName();
        }
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
