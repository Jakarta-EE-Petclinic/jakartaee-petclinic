package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
@Location("vetList.jsf")
public class VetListPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(VetListPage.class.getName());

    @FindBy(id="veterinarians")
    private WebElement veterinarians;

    @FindBy(id="veterinariansForm:getNewVetForm")
    private WebElement getNewVetForm;

    @FindBy(id="veterinariansForm:veterinariansTable:5:firstName")
    private WebElement firstName5InTable;

    @FindBy(id="veterinariansForm:veterinariansTable:5:lastName")
    private WebElement lastName5InTable;

    @FindBy(id="veterinariansForm:veterinariansTable:0:firstName")
    private WebElement firstNameInTable;

    @FindBy(id="veterinariansForm:veterinariansTable:0:lastName")
    private WebElement lastNameInTable;

    @FindBy(id="veterinariansForm:veterinariansTable:0:specialtiesAsString")
    private WebElement specialtiesAsString0InTable;

    @FindBy(id="veterinariansForm:veterinariansTable:2:specialtiesAsString")
    private WebElement specialtiesAsString2InTable;

    @FindBy(id="veterinariansForm:veterinariansTable:3:specialtiesAsString")
    private WebElement specialtiesAsString3InTable;

    @FindBy(id="veterinariansForm:veterinariansTable:0:edit")
    private WebElement editInTable;

    @FindBy(id="veterinariansForm:veterinariansTable:0:delete")
    private WebElement deleteInTable;

    @FindBy(id="veterinariansForm:scroller_ds_next")
    private WebElement scrollerNext;

    @FindBy(id="veterinariansForm:scroller_ds_prev")
    private WebElement scrollerPrev;

    @FindBy(id="veterinariansForm:veterinariansTable:colSurnameSort")
    private WebElement colSurnameSort;

    @FindBy(id="veterinariansForm:veterinariansTable:colLastnameSort")
    private WebElement colLastnameSort;

    @FindBy(id="veterinariansForm:veterinariansTable:colSpecialtySort")
    private WebElement colSpecialtySort;

    public void assertPageIsLoaded() {
        Assert.assertTrue(veterinarians.isDisplayed());
    }

    public void clickAddNewVet() {
        getNewVetForm.click();
    }

    public void assertNewContentFound(String firstName, String lastName) {
        Assert.assertEquals(firstName,firstNameInTable.getText());
        Assert.assertEquals(lastName, lastNameInTable.getText());
    }

    public void clickEditVet() {
        editInTable.click();
    }

    public void assertEditedContentFound(String firstName, String lastName) {
        Assert.assertEquals(firstName,firstNameInTable.getText());
        Assert.assertEquals(lastName, lastNameInTable.getText());
    }

    public void clickDeleteVet() {
        deleteInTable.click();
    }

    public void assertDeletedContentNotFound() {
        boolean isDeletedFirstName = false;
        try {
            Assert.assertEquals(null,firstNameInTable);
        } catch (NoSuchElementException elementException) {
            isDeletedFirstName = true;
        }
        Assert.assertTrue(isDeletedFirstName);
        boolean isDeletedLastName = false;
        try {
            Assert.assertEquals(null,lastNameInTable);
        } catch (NoSuchElementException elementException) {
            isDeletedLastName = true;
        }
        Assert.assertTrue(isDeletedLastName);
    }

    public void assertContentFoundWithSpecialties(String firstName, String lastName, String specialties) {
        Assert.assertEquals(firstName, firstNameInTable.getText());
        Assert.assertEquals(lastName, lastNameInTable.getText());
        Assert.assertEquals(specialties, specialtiesAsString0InTable.getText());
    }

    public void assertPagerNextIsLoaded(){
        Graphene.waitModel().until().element(scrollerNext).is().visible();
        Assert.assertTrue(scrollerNext.isDisplayed());
    }

    public void assertPagerPrevIsLoaded(){
        Graphene.waitModel().until().element(scrollerPrev).is().visible();
        Assert.assertTrue(scrollerPrev.isDisplayed());
    }

    public void clickPagerNext() {
        scrollerNext.click();
    }


    public void clickPagerPrev() {
        scrollerPrev.click();
    }

    public void assertSorterIsLoaded(){
        Graphene.waitModel().until().element(colSurnameSort).is().visible();
        Graphene.waitModel().until().element(colLastnameSort).is().visible();
        Assert.assertTrue(colSurnameSort.isDisplayed());
        Assert.assertTrue(colLastnameSort.isDisplayed());
    }

    public void assertOrder() {
        Graphene.waitModel().until().element(firstNameInTable).is().visible();
        Graphene.waitModel().until().element(lastNameInTable).is().visible();
        Assert.assertTrue(firstNameInTable.getText().compareTo("Vorname06") == 0);
        Assert.assertTrue(lastNameInTable.getText().compareTo("Nachname01") == 0);
    }

    public void clickSorterFirstname() {
        Graphene.waitModel().until().element(colSurnameSort).is().visible();
        Graphene.guardAjax(colSurnameSort).click();
    }

    public void assertFirstnameOrder() {
        Graphene.waitModel().until().element(firstName5InTable).is().visible();
        Graphene.waitModel().until().element(lastName5InTable).is().visible();
        Assert.assertTrue(firstName5InTable.getText().compareTo("Vorname01") == 0);
        Assert.assertTrue(lastName5InTable.getText().compareTo("Nachname06") == 0);
    }

    public void assertFirstnameReverseOrder() {
        Graphene.waitModel().until().element(firstNameInTable).is().visible();
        Graphene.waitModel().until().element(lastNameInTable).is().visible();
        Assert.assertTrue(firstNameInTable.getText().compareTo("Vorname06") == 0);
        Assert.assertTrue(lastNameInTable.getText().compareTo("Nachname01") == 0);
    }

    public void clickSorterLastname() {
        Graphene.waitModel().until().element(colLastnameSort).is().visible();
        Graphene.guardAjax(colLastnameSort).click();
    }

    public void assertLastnameOrder() {
        Graphene.waitModel().until().element(firstNameInTable).is().visible();
        Graphene.waitModel().until().element(lastNameInTable).is().visible();
        Assert.assertTrue(firstNameInTable.getText().compareTo("Vorname06") == 0);
        Assert.assertTrue(lastNameInTable.getText().compareTo("Nachname01") == 0);
    }

    public void assertLastnameReverseOrder() {
        Graphene.waitModel().until().element(firstName5InTable).is().visible();
        Graphene.waitModel().until().element(lastName5InTable).is().visible();
        Assert.assertTrue(firstName5InTable.getText().compareTo("Vorname01") == 0);
        Assert.assertTrue(lastName5InTable.getText().compareTo("Nachname06") == 0);
    }

    public void assertSpecialtyOrder() {
        Graphene.waitModel().until().element(specialtiesAsString3InTable).is().visible();
        Assert.assertTrue(specialtiesAsString3InTable.getText().compareTo("s01") == 0);
    }

    public void assertSpecialtyReverseOrder() {
        Graphene.waitModel().until().element(specialtiesAsString2InTable).is().visible();
        Assert.assertTrue(specialtiesAsString2InTable.getText().compareTo("s06") == 0);
    }

    public void clickSorterSpecialty() {
        Graphene.waitModel().until().element(colSpecialtySort).is().visible();
        Graphene.guardAjax(colSpecialtySort).click();
    }
}
