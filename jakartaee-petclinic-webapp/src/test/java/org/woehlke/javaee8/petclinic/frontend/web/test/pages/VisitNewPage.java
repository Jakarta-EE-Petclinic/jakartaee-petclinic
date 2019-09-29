package org.woehlke.javaee8.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;
import org.primefaces.extensions.arquillian.component.Calendar;
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 28.01.14
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class VisitNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(VisitNewPage.class.getName());

    @FindBy(id="addVisitForm")
    private WebElement addVisitForm;

    @FindBy(id="addVisitForm:petName")
    private WebElement petName;

    @FindBy(id="addVisitForm:petBirthDate")
    private Calendar petBirthDate;

    @FindBy(id="addVisitForm:petType")
    private WebElement petType;

    @FindBy(id="addVisitForm:ownerFirstName")
    private WebElement ownerFirstName;

    @FindBy(id="addVisitForm:ownerLastName")
    private WebElement ownerLastName;

    //TODO: Remove Dependency to Richfaces RichFacesCalendar
    @FindBy(id="addVisitForm:visitDate")
    private Calendar visitDate;

    @FindBy(id="addVisitForm:visitDescription")
    private InputText visitDescription;

    @FindBy(id="addVisitForm:save")
    private CommandButton save;

    public void assertPageIsLoaded() {
        Assert.assertTrue(addVisitForm.isDisplayed());
    }

    public void assertOwnerContent(String firstName, String lastName) {
        Assert.assertEquals(firstName,ownerFirstName.getText());
        Assert.assertEquals(lastName,ownerLastName.getText());
    }

    public void assertPetContent(String petName, LocalDate birthDate, String petType) {
        Assert.assertEquals(petName,this.petName.getText());
        Assert.assertEquals(petType,this.petType.getText());
        LocalDate birthDateTmp = birthDate.minusDays(1);
        Assert.assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(birthDateTmp),petBirthDate.getText());
    }

    public void setNewContent(LocalDate visitDate, String description) {
        Graphene.waitModel().until().element(addVisitForm).is().visible();
        this.visitDescription.sendKeys(description);
        this.visitDate.setValue(visitDate);
        Graphene.guardHttp(save).click();
    }
}
