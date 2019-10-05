package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class OwnerListPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(OwnerListPage.class.getName());

    @FindBy(id="owners")
    private WebElement owners;

    @FindBy(id="ownersForm:getNewOwnerForm")
    private WebElement getNewOwnerForm;

    @FindBy(id="ownersForm:ownersTable:0:firstName")
    private WebElement firstName;

    @FindBy(id="ownersForm:ownersTable:0:lastName")
    private WebElement lastName;

    @FindBy(id="ownersForm:ownersTable:0:address")
    private WebElement address;

    @FindBy(id="ownersForm:ownersTable:0:houseNumber")
    private WebElement houseNumber;

    @FindBy(id="ownersForm:ownersTable:0:addressInfo")
    private WebElement addressInfo;

    @FindBy(id="ownersForm:ownersTable:0:city")
    private WebElement city;

    @FindBy(id="ownersForm:ownersTable:0:zipCode")
    private WebElement zipCode;

    @FindBy(id="ownersForm:ownersTable:0:telephone")
    private WebElement telephone;

    @FindBy(id="ownersForm:ownersTable:0:showOwner")
    private WebElement showOwner;

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(owners).is().visible();
        Assert.assertTrue(owners.isDisplayed());
    }

    public void clickNewOwner() {
        Graphene.guardHttp(getNewOwnerForm).click();
    }

    public void assertNewContentFound(String firstName,
                                      String lastName,
                                      String address,
                                      String houseNumber,
                                      String addressInfo,
                                      String city,
                                      String zipCode,
                                      String telephone) {
        Assert.assertEquals(firstName,this.firstName.getText());
        Assert.assertEquals(lastName,this.lastName.getText());
        Assert.assertEquals(address,this.address.getText());
        Assert.assertEquals(houseNumber,this.houseNumber.getText());
        Assert.assertEquals(addressInfo,this.addressInfo.getText());
        Assert.assertEquals(city,this.city.getText());
        Assert.assertEquals(zipCode,this.zipCode.getText());
        Assert.assertEquals(telephone,this.telephone.getText());
        Assert.assertTrue(showOwner.isDisplayed());
    }

    public void clickShowOwner() {
        Graphene.guardHttp(showOwner).click();
    }
}
