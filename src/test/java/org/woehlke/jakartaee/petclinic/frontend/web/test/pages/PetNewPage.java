package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
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
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class PetNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(PetNewPage.class.getName());

    @FindBy(id="addNewPetForm")
    private WebElement addNewPetForm;

    @FindBy(id="addNewPetForm:petName")
    private InputText petName;

    //TODO: Remove Dependency to Richfaces RichFacesCalendar
    @FindBy(id="addNewPetForm:petBirthDate")
    private Calendar petBirthDate;

    @FindBy(id="addNewPetForm:petType")
    private SelectOneMenu petType;

    @FindBy(id="addNewPetForm:add")
    private CommandButton add;

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(addNewPetForm).is().visible();
        Assert.assertTrue(addNewPetForm.isDisplayed());
    }

    public void setContent(String petName, LocalDate petBirthDate, String petType){
        this.petName.sendKeys(petName);
        this.petBirthDate.setValue(petBirthDate);
        List<WebElement> options = this.petType.findElements(By.tagName("option"));
        for(WebElement option: options){
            if(option.getText().contentEquals(petType)){
                option.click();
                break;
            }
        }
        Graphene.guardHttp(add).click();
    }
}
