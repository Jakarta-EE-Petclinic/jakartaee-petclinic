package org.woehlke.jakartaee.petclinic.application;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


public class MessageProvider implements Serializable {

	public ResourceBundle getBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getResourceBundle(context, "msg");
	}

}
