package org.woehlke.javaee8.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;
import org.primefaces.extensions.arquillian.component.Calendar;
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;
import org.primefaces.extensions.arquillian.component.SelectOneMenu;


import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 27.01.14
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class PetEditPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(PetEditPage.class.getName());

    @FindBy(id="editPetForm")
    private WebElement editPetForm;

    @FindBy(id="editPetForm:petName")
    private InputText petName;

    //TODO: Remove Dependency to Richfaces RichFacesCalendar
    @FindBy(id="editPetForm:petBirthDate")
    private Calendar petBirthDate;

    @FindBy(id="editPetForm:petType")
    private SelectOneMenu petType;

    @FindBy(id="editPetForm:save")
    private CommandButton save;

    public void assertPageIsLoaded() {
        Assert.assertTrue(editPetForm.isDisplayed());
    }

    public void setContent(String petName, LocalDate petBirthDate, String petType) {
        this.petName.clear();
        this.petName.sendKeys(petName);
        this.petBirthDate.setValue(petBirthDate);
        List<WebElement> options = this.petType.findElements(By.tagName("option"));
        for(WebElement option: options){
            if(option.getText().contentEquals(petType)){
                option.click();
                break;
            }
        }
        save.click();
    }
}
