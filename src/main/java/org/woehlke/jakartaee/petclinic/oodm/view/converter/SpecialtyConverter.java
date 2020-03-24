package org.woehlke.jakartaee.petclinic.oodm.view.converter;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.entities.Specialty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
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
@Log4j2
@FacesConverter(
    value = "specialtyConverter"
)
@Stateless
public class SpecialtyConverter implements Converter<Specialty>, Serializable {

  private static final long serialVersionUID = 3816519727799645701L;

  @Override
  public Specialty getAsObject(FacesContext context, UIComponent component, String name) {
    Specialty specialty = new Specialty(name);
    log.debug("SpecialtyConverter.getAsObject: from = " + name + " to " + specialty.toString());
    return specialty;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Specialty specialty) {
    String name = specialty.getName();
    log.debug("SpecialtyConverter.getAsString: from " + specialty.toString() + " to " + name);
    return name;
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
