package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

//import org.jboss.arquillian.graphene.Graphene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  public void clickShowEditFormButton(){
    this.showEditFormButton.click();
  }

  public void clickDeleteSelectedButton(){
    this.deleteSelectedButton.click();
  }

  public void addNewContent(String content) {
    specialtyNameNew.clear();
    specialtyNameNew.sendKeys(content);
    specialtyNameNew.click();
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
    this.saveEditButton.click();
  }

  public void clickCancelEdit(){
    this.cancelEdit.click();
  }

  //-------------------

  @FindBy(id = "addNewSpecialtyForm:specialtyNameNew")
  private InputText specialtyNameNew;

  @FindBy(id = "addNewSpecialtyForm:saveNewButton")
  private CommandButton saveNewButton;

  @FindBy(id = "addNewSpecialtyForm:cancelNew")
  private CommandButton cancelNew;

  //-------------------

  public void assertPageIsLoaded() {
    Assert.assertTrue(listEntityHeaderId.isDisplayed());
  }

  public void clickAddNewSpecialty() {
    showNewFormButton.click();
  }

  public void clickEditSpecialty() {
    showEditFormButton.click();
  }

  public void clickDeleteSpecialty() {
    deleteSelectedButton.click();
  }

    /*
    public void assertNewContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }
    */

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
