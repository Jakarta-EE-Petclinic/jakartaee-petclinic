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

  @FindBy(id = "contentTitleHeadline")
  private WebElement contentTitleHeadline;

  @FindBy(id = "findEntityForm:newAndSearchGrid:showNewFormButton")
  private CommandButton showNewFormButton;

  @FindBy(id = "findEntityForm:newAndSearchGrid:inputTextSearchterm")
  private InputText inputTextSearchterm;

  @FindBy(id = "findEntityForm:newAndSearchGrid:searchButton")
  private CommandButton searchButton;

  @FindBy(id = "entityDataTableForm:footerGrid:showEditFormButton")
  private CommandButton showEditFormButton;

  @FindBy(id = "entityDataTableForm:footerGrid:deleteSelectedButton")
  private CommandButton deleteSelectedButton;

  @FindBy(id = "editSpecialty")
  private WebElement editSpecialty;

  @FindBy(id = "editSpecialtyForm:name")
  private InputText name;

  @FindBy(id = "editSpecialtyForm:save")
  private CommandButton save;

  public void assertPageIsLoaded() {
    Assert.assertTrue(contentTitleHeadline.isDisplayed());
  }

  public void clickAddNewSpecialty() {
    showNewFormButton.click();
  }

    /*
    public void assertNewContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }
    */

  public void clickEditSpecialty() {
    showEditFormButton.click();
  }

    /*
    public void assertEditedContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }
    */

  public void clickDeleteSpecialty() {
    deleteSelectedButton.click();
  }

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

  public void editContent(String content) {
    name.clear();
    name.sendKeys(content);
    save.click();
  }

  public void addNewContent(String content) {
    name.clear();
    name.sendKeys(content);
    save.click();
  }
}
