package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import lombok.extern.log4j.Log4j2;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

@Log4j2
@Location("help.jsf")
public class HelpPage extends AbstractPrimePage {

  public void assertTitle() {
    Assert.assertEquals("Jakarta EE Petclinic", webDriver.getTitle());
  }
}
