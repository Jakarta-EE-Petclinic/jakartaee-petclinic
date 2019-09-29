package org.woehlke.javaee8.petclinic.frontend.web.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.graphene.page.Location;
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
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
@Location("specialtyNew.jsf")
public class SpecialtyNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(SpecialtyNewPage.class.getName());

    @FindBy(id="addNewSpecialty")
    private WebElement addNewSpecialty;

    @FindBy(id="addNewSpecialtyForm:name")
    private InputText name;

    @FindBy(id="addNewSpecialtyForm:save")
    private CommandButton save;

    public void assertPageIsLoaded(){
        Assert.assertTrue(addNewSpecialty.isDisplayed());
    }

    public void addNewContent(String content) {
        name.clear();
        name.sendKeys(content);
        save.click();
    }
}
