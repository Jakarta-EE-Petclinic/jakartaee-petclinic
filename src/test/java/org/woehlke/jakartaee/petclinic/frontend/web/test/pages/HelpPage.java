package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

@Location("help.jsf")
public class HelpPage extends AbstractPrimePage {

  private static Logger log = LogManager.getLogger(HelpPage.class.getName());

  public void assertTitle() {
    Assert.assertEquals("Jakarta EE Petclinic", webDriver.getTitle());
  }
}
