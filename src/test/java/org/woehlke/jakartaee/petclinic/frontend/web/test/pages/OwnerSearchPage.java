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
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
@Location("ownerSearch.jsf")
public class OwnerSearchPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(OwnerSearchPage.class.getName());

    @FindBy(id="findOwners")
    private WebElement findOwners;

    @FindBy(id="findOwnersForm:search")
    private WebElement search;

    @FindBy(id="findOwnersForm:getNewOwnerForm")
    private WebElement getNewOwnerForm;

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
