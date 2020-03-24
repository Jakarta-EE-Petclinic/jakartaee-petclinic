package org.woehlke.jakartaee.petclinic.oodm.view.converter;

import lombok.extern.log4j.Log4j2;
import org.woehlke.jakartaee.petclinic.oodm.entities.PetType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

@Log4j2
@FacesConverter(
    value = "petTypeConverter"
)
@Stateless
public class PetTypeConverter implements Converter<PetType>, Serializable {

  private static final long serialVersionUID = 4908876595996046904L;

  @Override
  public PetType getAsObject(FacesContext context, UIComponent component, String name) {
    PetType petType = new PetType(name);
    log.debug("PetTypeConverter.getAsObject: from = " + name + " to " + petType.toString());
    return petType;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, PetType petType) {
    String name = petType.getName();
    log.debug("PetTypeConverter.getAsObject: from = " + petType.toString() + " to " + name);
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
