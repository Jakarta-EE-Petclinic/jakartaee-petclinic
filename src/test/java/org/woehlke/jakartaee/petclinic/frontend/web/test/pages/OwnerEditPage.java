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
 * Date: 27.01.14
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */
public class OwnerEditPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(OwnerEditPage.class.getName());

    @FindBy(id="editOwnerForm")
    private WebElement editOwnerForm;

    @FindBy(id="editOwnerForm:firstName")
    private InputText firstName;

    @FindBy(id="editOwnerForm:lastName")
    private InputText lastName;

    @FindBy(id="editOwnerForm:address")
    private InputText address;

    @FindBy(id="editOwnerForm:city")
    private InputText city;

    @FindBy(id="editOwnerForm:telephone")
    private InputText telephone;

    @FindBy(id="editOwnerForm:save")
    private CommandButton save;

    public OwnerEditPage() {
    }

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(editOwnerForm).is().visible();
        Assert.assertTrue(editOwnerForm.isDisplayed());
    }

    public void editContent(String firstName,
                            String lastName,
                            String address,
                            String city,
                            String telephone) {
        this.firstName.clear();
        this.lastName.clear();
        this.address.clear();
        this.city.clear();
        this.telephone.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.telephone.sendKeys(telephone);
        Graphene.guardHttp(this.save).click();
    }
}
