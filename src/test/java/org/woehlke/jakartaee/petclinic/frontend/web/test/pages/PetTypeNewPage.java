package org.woehlke.jakartaee.petclinic.frontend.web.test.pages;

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

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
@Location("petTypeNew.jsf")
public class PetTypeNewPage extends AbstractPrimePage {

    private static Logger log = LogManager.getLogger(PetTypeNewPage.class.getName());

    @FindBy(id="addNewPetType")
    private WebElement addNewPetType;

    @FindBy(id="addNewPetTypeForm:name")
    private InputText name;

    @FindBy(id="addNewPetTypeForm:save")
    private CommandButton save;

    public PetTypeNewPage() {
    }

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(addNewPetType).is().visible();
        Assert.assertTrue(addNewPetType.isDisplayed());
    }

    public void addNewContent(String content) {
        name.clear();
        name.sendKeys(content);
        Graphene.guardHttp(save).click();
    }
}
