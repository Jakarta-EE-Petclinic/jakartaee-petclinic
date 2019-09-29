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
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class PetTypeEditPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(PetTypeEditPage.class.getName());

    @FindBy(id="editPetType")
    private WebElement editPetType;

    @FindBy(id="editPetTypeForm:name")
    private InputText name;

    @FindBy(id="editPetTypeForm:save")
    private CommandButton save;


    public void assertPageIsLoaded() {
        Assert.assertTrue(editPetType.isDisplayed());
    }

    public void editContent(String content) {
        name.clear();
        name.sendKeys(content);
        save.click();
    }
}
