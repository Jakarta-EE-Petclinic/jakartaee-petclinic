package org.woehlke.javaee8.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class VetEditPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(VetEditPage.class.getName());

    @FindBy(id="editVeterinarian")
    private WebElement editVeterinarian;

    @FindBy(id="editVeterinarianForm:firstName")
    private InputText firstName;

    @FindBy(id="editVeterinarianForm:lastName")
    private InputText lastName;

    //TODO: Remove Dependency to Richfaces RichFacesPickList
    @FindBy(id="editVeterinarianForm:selectedSpecialtiesPickList")
    //private RichFacesPickList pickList;
    private WebElement pickList;

    @FindBy(id="editVeterinarianForm:save")
    private CommandButton save;

    public void assertPageIsLoaded() {
        Assert.assertTrue(editVeterinarian.isDisplayed());
    }

    public void editContent(String firstName,String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        save.click();
    }

    public void editContentWithNoneSpecialties(String firstName,String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        //this.pickList.removeAll();
        save.click();
    }

    public void editContentWithAllSpecialties(String firstName,String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        //this.pickList.addAll();
        save.click();
    }
}
