package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
@Location("home.jsf")
public class HomePage extends AbstractPrimePage {

  private static Logger log = LogManager.getLogger(HomePage.class.getName());

  @FindBy(id = "findOwners")
  private WebElement findOwners;

  @FindBy(id = "findOwnersForm:search")
  private WebElement search;

  @FindBy(id = "findOwnersForm:getNewOwnerForm")
  private WebElement getNewOwnerForm;

  public void assertTitle() {
    Assert.assertEquals("Jakarta EE Petclinic", webDriver.getTitle());
  }

  public void assertPageIsLoaded() {
    Graphene.waitModel().until().element(findOwners).is().visible();
    Assert.assertTrue(findOwners.isDisplayed());
  }

  public void clickSearch() {
    Graphene.guardHttp(search).click();
  }

  public void clickNewOwner() {
    Graphene.guardHttp(getNewOwnerForm).click();
  }
}
