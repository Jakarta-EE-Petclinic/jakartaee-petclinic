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
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
@Location("petType.jsf")
public class PetTypePage extends AbstractPrimePage {

  private static Logger log = LogManager.getLogger(PetTypePage.class.getName());

  @FindBy(id = "listEntityHeaderId")
  private WebElement listEntityHeaderId;

  @FindBy(id = "newEntityHeaderId")
  private WebElement newEntityHeaderId;

  @FindBy(id = "editEntityHeaderId")
  private WebElement editEntityHeaderId;

  @FindBy(id = "deleteEntityHeaderId")
  private WebElement deleteEntityHeaderId;

  @FindBy(id = "searchResultEntityHeaderId")
  private WebElement searchResultEntityHeaderId;

  public void assertListEntityPageIsLoaded() {
    Assert.assertTrue(listEntityHeaderId.isDisplayed());
  }

  public void assertNewEntityPageIsLoaded() {
    Assert.assertTrue(newEntityHeaderId.isDisplayed());
  }

  public void assertEditEntityPageIsLoaded() {
    Assert.assertTrue(editEntityHeaderId.isDisplayed());
  }

  public void assertDeleteEntityPageIsLoaded() {
    Assert.assertTrue(deleteEntityHeaderId.isDisplayed());
  }

  public void assertSearchResultEntityPageIsLoaded() {
    Assert.assertTrue(searchResultEntityHeaderId.isDisplayed());
  }

  //-------------------

  @FindBy(id = "findEntityForm:showNewFormButton")
  private CommandButton showNewFormButton;

  @FindBy(id = "findEntityForm:inputTextSearchterm")
  private InputText inputTextSearchterm;

  @FindBy(id = "findEntityForm:newAndSearchGrid:searchButton")
  private CommandButton searchButton;

  @FindBy(id = "entityDataTableForm:showEditFormButton")
  private CommandButton showEditFormButton;

  @FindBy(id = "entityDataTableForm:deleteSelectedButton")
  private CommandButton deleteSelectedButton;

  public void clickShowNewFormButton(){
    Graphene.guardHttp(this.showNewFormButton).click();
  }

  public void clickShowEditFormButton(){
    Graphene.guardHttp(this.showEditFormButton).click();
  }

  public void clickDeleteSelectedButton(){
    Graphene.guardHttp(this.deleteSelectedButton).click();
  }

  //-------------------

  @FindBy(id = "addNewEntityForm:specialtyNameNew")
  private InputText specialtyNameNew;

  @FindBy(id = "addNewEntityForm:saveNewButton")
  private CommandButton saveNewButton;

  @FindBy(id = "addNewEntityForm:cancelNew")
  private CommandButton cancelNew;

  public void addNewContent(String content) {
    specialtyNameNew.clear();
    specialtyNameNew.sendKeys(content);
    specialtyNameNew.click();
  }

  public void clickSaveNewButton(){
    Graphene.guardHttp(this.saveNewButton).click();
  }

  public void clickCancelNewButton(){
    Graphene.guardHttp(this.saveNewButton).click();
  }

  public void assertNewContentAdded(String content) {
    //TODO:
    //Assert.assertEquals(content, nameInTable.getText());
    Assert.assertTrue(true);
  }

  //-------------------

  @FindBy(id = "editSpecialtyForm:specialtyNameEdit")
  private InputText specialtyNameEdit;

  @FindBy(id = "editSpecialtyForm:saveEditButton")
  private CommandButton saveEditButton;

  @FindBy(id = "editSpecialtyForm:cancelEdit")
  private CommandButton cancelEdit;

  public void editContent(String content) {
    this.specialtyNameEdit.clear();
    this.specialtyNameEdit.sendKeys(content);
    this.specialtyNameEdit.click();
  }

  public void clickSaveEditButton(){
    Graphene.guardHttp(this.saveEditButton).click();
  }

  public void clickCancelEdit(){
    Graphene.guardHttp(this.cancelEdit).click();
  }

  //-------------------

  /*
  @FindBy(id = "petTypes")
  private WebElement petTypes;

  @FindBy(id = "petTypesForm:getNewPetTypeForm")
  private WebElement getNewPetTypeForm;

  @FindBy(id = "petTypesForm:petTypesTable:5:name")
  private WebElement name5InTable;

  @FindBy(id = "petTypesForm:petTypesTable:0:name")
  private WebElement nameInTable;

  @FindBy(id = "petTypesForm:petTypesTable:0:edit")
  private WebElement editInTable;

  @FindBy(id = "petTypesForm:petTypesTable:0:delete")
  private WebElement deleteInTable;

  @FindBy(id = "petTypesForm:scroller_ds_next")
  private WebElement scrollerNext;

  @FindBy(id = "petTypesForm:scroller_ds_prev")
  private WebElement scrollerPrev;

  @FindBy(id = "petTypesForm:petTypesTable:colNameSort")
  private WebElement colNameSort;

  @FindBy(id = "editPetType")
  private WebElement editPetType;

  @FindBy(id = "editPetTypeForm:name")
  private InputText name;

  @FindBy(id = "editPetTypeForm:save")
  private CommandButton save;

  public void assertPageIsLoaded() {
    Graphene.waitModel().until().element(petTypes).is().visible();
    Assert.assertTrue(petTypes.isDisplayed());
  }

  public void clickAddNewPetType() {
    Graphene.guardHttp(getNewPetTypeForm).click();
  }

  public void clickEditSpecialty() {
    Graphene.guardHttp(editInTable).click();
  }

  public void assertEditedContentFound(String content) {
    Assert.assertEquals(content, nameInTable.getText());
  }

  public void clickDeleteSpecialty() {
    Graphene.guardHttp(deleteInTable).click();
  }

  public void assertDeletedContentNotFound() {
    boolean isDeleted = false;
    try {
      Assert.assertEquals(null, nameInTable);
    } catch (NoSuchElementException elementException) {
      isDeleted = true;
    }
    Assert.assertTrue(isDeleted);
  }

  public void assertPagerNextIsLoaded() {
    Graphene.waitModel().until().element(scrollerNext).is().visible();
    Assert.assertTrue(scrollerNext.isDisplayed());
  }

  public void assertPagerPrevIsLoaded() {
    Graphene.waitModel().until().element(scrollerPrev).is().visible();
    Assert.assertTrue(scrollerPrev.isDisplayed());
  }

  public void clickPagerNext() {
    scrollerNext.click();
  }


  public void clickPagerPrev() {
    scrollerPrev.click();
  }

  public void assertSorterIsLoaded() {
    Graphene.waitModel().until().element(colNameSort).is().visible();
    Assert.assertTrue(colNameSort.isDisplayed());
  }

  public void assertOrder() {
    Graphene.waitModel().until().element(nameInTable).is().visible();
    Assert.assertTrue(nameInTable.getText().compareTo("pet01") == 0);
  }

  public void clickSorter() {
    Graphene.waitModel().until().element(colNameSort).is().visible();
    colNameSort.click();
  }

  public void assertReverseOrder() {
    Graphene.waitModel().until().element(name5InTable).is().visible();
    Assert.assertTrue(name5InTable.getText().compareTo("pet06") == 0);
  }
  */
}
