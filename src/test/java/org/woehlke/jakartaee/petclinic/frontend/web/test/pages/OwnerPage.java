package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;
import org.primefaces.extensions.arquillian.component.Calendar;
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;
import org.primefaces.extensions.arquillian.component.SelectOneMenu;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 27.01.14
 * Time: 09:23
 * To change this template use File | Settings | File Templates.
 */
@Location("owner.jsf")
public class OwnerPage extends AbstractPrimePage {

  private static Logger log = LogManager.getLogger(OwnerPage.class.getName());

  @FindBy(id = "showOwnerForm")
  private WebElement showOwnerForm;

  @FindBy(id = "showOwnerForm:firstName")
  private WebElement firstName;

  @FindBy(id = "showOwnerForm:lastName")
  private WebElement lastName;

  @FindBy(id = "showOwnerForm:address")
  private WebElement address;

  @FindBy(id = "showOwnerForm:houseNumber")
  private WebElement houseNumber;

  @FindBy(id = "showOwnerForm:addressInfo")
  private WebElement addressInfo;

  @FindBy(id = "showOwnerForm:city")
  private WebElement city;

  @FindBy(id = "showOwnerForm:zipCode")
  private WebElement zipCode;

  @FindBy(id = "showOwnerForm:telephone")
  private WebElement telephone;

  @FindBy(id = "showOwnerForm:edit")
  private WebElement edit;

  @FindBy(id = "showOwnerForm:addPet")
  private WebElement addPet;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:visitsTable:editPet")
  private WebElement editFirstPet;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:visitsTable:addVisit")
  private WebElement newVisitForFirstPet;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:petsName")
  private WebElement firstPetsName;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:petsBirthDate")
  private WebElement firstPetsBirthDate;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:petsType")
  private WebElement firstPetsType;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:1:petsName")
  private WebElement secondPetsName;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:1:petsBirthDate")
  private WebElement secondPetsBirthDate;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:1:petsType")
  private WebElement secondPetsType;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:visitsTable:0:date")
  private WebElement firstPetsFirstVisitDate;

  @FindBy(id = "showOwnerForm:petsAndVisitsTable:0:visitsTable:0:description")
  private WebElement firstPetsFirstVisitDescription;

  @FindBy(id = "ownersForm:ownersTable:0:showOwner")
  private WebElement showOwner;


  @FindBy(id = "editPetForm")
  private WebElement editPetForm;

  @FindBy(id = "editPetForm:petName")
  private InputText petName;

  //TODO: Remove Dependency to Richfaces RichFacesCalendar
  @FindBy(id = "editPetForm:petBirthDate")
  private Calendar petBirthDate;

  @FindBy(id = "editPetForm:petType")
  private SelectOneMenu petType;

  @FindBy(id = "editPetForm:save")
  private CommandButton save;

  @FindBy(id = "addNewPetForm:add")
  private CommandButton add;

  @FindBy(id = "owners")
  private WebElement owners;

  @FindBy(id = "ownersForm:getNewOwnerForm")
  private WebElement getNewOwnerForm;


  @FindBy(id = "addVisitForm")
  private WebElement addVisitForm;

  @FindBy(id = "addVisitForm:petName")
  private WebElement addVisitPetName;

  @FindBy(id = "addVisitForm:petBirthDate")
  private Calendar addVisitPetBirthDate;

  @FindBy(id = "addVisitForm:petType")
  private WebElement addVisitPetType;

  @FindBy(id = "addVisitForm:ownerFirstName")
  private WebElement ownerFirstName;

  @FindBy(id = "addVisitForm:ownerLastName")
  private WebElement ownerLastName;

  //TODO: Remove Dependency to Richfaces RichFacesCalendar
  @FindBy(id = "addVisitForm:visitDate")
  private Calendar visitDate;

  @FindBy(id = "addVisitForm:visitDescription")
  private InputText visitDescription;

  @FindBy(id = "addVisitForm:save")
  private CommandButton saveNewVisit;

  public void assertPageIsLoaded() {
    Graphene.waitModel().until().element(showOwnerForm).is().visible();
    Assert.assertTrue(showOwnerForm.isDisplayed());
    Graphene.waitModel().until().element(owners).is().visible();
    Assert.assertTrue(owners.isDisplayed());
  }

  public void clickEditOwner() {
    Graphene.guardHttp(edit).click();
  }

  public void assertContent(String firstName,
                            String lastName,
                            String address,
                            String city,
                            String telephone) {
    Assert.assertEquals(firstName, this.firstName.getText());
    Assert.assertEquals(lastName, this.lastName.getText());
    Assert.assertEquals(address, this.address.getText());
    Assert.assertEquals(city, this.city.getText());
    Assert.assertEquals(telephone, this.telephone.getText());
  }

  public void clickAddNewPet() {
    Graphene.guardHttp(addPet).click();
  }

  public void clickEditFirstPet() {
    Graphene.guardHttp(editFirstPet).click();
  }

  public void assertFirstPetContent(String petsName, LocalDate birthDate, String petType) {
    Assert.assertEquals(petsName, firstPetsName.getText());
    Assert.assertEquals(petType, firstPetsType.getText());
    LocalDate birthDateTmp = birthDate.minusDays(1);
    Assert.assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(birthDateTmp), firstPetsBirthDate.getText());

  }

  public void assertSecondPetContent(String petsName, LocalDate birthDate, String petType) {
    Assert.assertEquals(petsName, secondPetsName.getText());
    Assert.assertEquals(petType, secondPetsType.getText());
    LocalDate birthDateTmp = birthDate.minusDays(1);
    Assert.assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(birthDateTmp), secondPetsBirthDate.getText());

  }

  public void addVisitToFirstPet() {
    Graphene.guardHttp(newVisitForFirstPet).click();
  }

  public void assertFirstVisitToFirstPet(LocalDate visitDate, String description) {
    LocalDate visitDateTmp = visitDate.minusDays(1);
    String visitDateString = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(visitDateTmp);
    Assert.assertEquals(visitDateString, firstPetsFirstVisitDate.getText());
    Assert.assertEquals(description, firstPetsFirstVisitDescription.getText());
  }


  public void setEditPetContent(String petName, LocalDate petBirthDate, String petType) {
    this.petName.clear();
    this.petName.sendKeys(petName);
    this.petBirthDate.setValue(petBirthDate);
    List<WebElement> options = this.petType.findElements(By.tagName("option"));
    for (WebElement option : options) {
      if (option.getText().contentEquals(petType)) {
        option.click();
        break;
      }
    }
    save.click();
  }


  public void setNewPetContent(String petName, LocalDate petBirthDate, String petType) {
    this.petName.sendKeys(petName);
    this.petBirthDate.setValue(petBirthDate);
    List<WebElement> options = this.petType.findElements(By.tagName("option"));
    for (WebElement option : options) {
      if (option.getText().contentEquals(petType)) {
        option.click();
        break;
      }
    }
    Graphene.guardHttp(add).click();
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
    Assert.assertEquals(firstName, this.firstName.getText());
    Assert.assertEquals(lastName, this.lastName.getText());
    Assert.assertEquals(address, this.address.getText());
    Assert.assertEquals(houseNumber, this.houseNumber.getText());
    Assert.assertEquals(addressInfo, this.addressInfo.getText());
    Assert.assertEquals(city, this.city.getText());
    Assert.assertEquals(zipCode, this.zipCode.getText());
    Assert.assertEquals(telephone, this.telephone.getText());
    Assert.assertTrue(showOwner.isDisplayed());
  }

  public void clickShowOwner() {
    Graphene.guardHttp(showOwner).click();
  }


  public void assertOwnerContent(String ownerFirstName, String ownerLastName) {
    Assert.assertEquals(ownerFirstName, firstName.getText());
    Assert.assertEquals(ownerLastName, lastName.getText());
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

  public void assertPetContent(String petName, LocalDate birthDate, String petType) {
    Assert.assertEquals(petName, this.petName.getText());
    Assert.assertEquals(petType, this.petType.getText());
    LocalDate birthDateTmp = birthDate.minusDays(1);
    Assert.assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(birthDateTmp), petBirthDate.getText());
  }


  public void setNewVisitContent(LocalDate visitDate, String description) {
    Graphene.waitModel().until().element(addVisitForm).is().visible();
    this.visitDescription.sendKeys(description);
    this.visitDate.setValue(visitDate);
    Graphene.guardHttp(saveNewVisit).click();
  }
}
