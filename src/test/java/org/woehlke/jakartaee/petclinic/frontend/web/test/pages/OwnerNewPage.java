package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class OwnerNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(OwnerNewPage.class.getName());

    @FindBy(id="addNewOwner")
    private WebElement addNewOwner;

    @FindBy(id="addNewOwnerForm:firstName")
    private InputText firstName;

    @FindBy(id="addNewOwnerForm:lastName")
    private InputText lastName;

    @FindBy(id="addNewOwnerForm:address")
    private InputText address;

    @FindBy(id="addNewOwnerForm:houseNumber")
    private InputText houseNumber;

    @FindBy(id="addNewOwnerForm:addressInfo")
    private InputText addressInfo;

    @FindBy(id="addNewOwnerForm:city")
    private InputText city;

    @FindBy(id="addNewOwnerForm:zipCode")
    private InputText zipCode;

    @FindBy(id="addNewOwnerForm:telephone")
    private InputText telephone;

    @FindBy(id="addNewOwnerForm:save")
    private CommandButton save;

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(addNewOwner).is().visible();
        Assert.assertTrue(addNewOwner.isDisplayed());
    }

    public void addNewContent(String firstName,
                              String lastName,
                              String address,
                              String houseNumber,
                              String addressInfo,
                              String city,
                              String zipCode,
                              String telephone) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.address.sendKeys(address);
        this.houseNumber.sendKeys(houseNumber);
        this.addressInfo.sendKeys(addressInfo);
        this.city.sendKeys(city);
        this.zipCode.sendKeys(zipCode);
        this.telephone.sendKeys(telephone);
        Graphene.guardHttp(this.save).click();
    }
}
