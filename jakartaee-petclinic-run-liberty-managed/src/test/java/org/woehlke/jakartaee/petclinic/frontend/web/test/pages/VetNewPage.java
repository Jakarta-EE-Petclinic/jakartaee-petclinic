package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.page.Location;
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
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
@Location("vetNew.jsf")
public class VetNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(VetNewPage.class.getName());

    @FindBy(id="addNewVeterinarianForm")
    private WebElement addNewVeterinarian;

    @FindBy(id="addNewVeterinarianForm:firstName")
    private InputText firstName;

    @FindBy(id="addNewVeterinarianForm:lastName")
    private InputText lastName;

    @FindBy(id="addNewVeterinarianForm:save")
    private CommandButton save;

    //TODO: Remove Dependency to Richfaces RichFacesPickList
    //@FindBy(id="addNewVeterinarianForm:selectedSpecialtiesPickList")
    //private WebElement pickList;

    public void assertPageIsLoaded() {
        Assert.assertTrue(addNewVeterinarian.isDisplayed());
    }

    public void addNewContent(String firstName,String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        save.click();
    }

    public void addNewContentWithAllSpecialties(String firstName, String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        //this.pickList.addAll();
        save.click();
    }

    public void addNewContentWithOneSpecialty(String firstName, String lastName, String specialty) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        //this.pickList.add(specialty);
        save.click();
    }
}
