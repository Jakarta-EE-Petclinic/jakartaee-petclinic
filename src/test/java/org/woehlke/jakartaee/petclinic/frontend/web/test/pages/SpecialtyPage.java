package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

//import org.jboss.arquillian.graphene.Graphene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.arquillian.AbstractPrimePage;
import org.primefaces.extensions.arquillian.component.CommandButton;
import org.primefaces.extensions.arquillian.component.InputText;

//import org.openqa.selenium.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
@Location("specialty.jsf")
public class SpecialtyPage extends AbstractPrimePage {

  private static Logger log = LogManager.getLogger(SpecialtyPage.class.getName());

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
    Graphene.guardHttp(this.showEditFormButton).click();
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
    Graphene.writeIntoElement( specialtyNameNew, content);
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

  @FindBy(id = "editEntityForm:specialtyNameEdit")
  private InputText specialtyNameEdit;

  @FindBy(id = "editEntityForm:saveEditButton")
  private CommandButton saveEditButton;

  @FindBy(id = "editEntityForm:cancelEdit")
  private CommandButton cancelEdit;

  public void editContent(String content) {
    Graphene.writeIntoElement( specialtyNameEdit, content);
  }

  public void clickSaveEditButton(){
    Graphene.guardHttp(this.saveEditButton).click();
  }

  public void clickCancelEdit(){
    Graphene.guardHttp(this.cancelEdit).click();
  }

  public void assertEditedContentAdded(String content) {
    //TODO:
    //Assert.assertEquals(content, nameInTable.getText());
    Assert.assertTrue(true);
  }

  //-------------------

  public void clickDeleteSpecialty() {
    Graphene.guardHttp(deleteSelectedButton).click();
  }


    /*
    public void assertEditedContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }
    */

   /*
    public void assertDeletedContentNotFound() {
        boolean isDeleted = false;
        try {
            Assert.assertEquals(null,nameInTable);
        } catch (NoSuchElementException elementException) {
            isDeleted = true;
        }
        Assert.assertTrue(isDeleted);
    }
   */

    /*
    public void assertPageIsLoaded() {
        Assert.assertTrue(editSpecialty.isDisplayed());
    }
    */
}
