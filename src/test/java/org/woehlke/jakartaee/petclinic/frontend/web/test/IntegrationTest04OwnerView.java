package org.woehlke.jakartaee.petclinic.frontend.web.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.*;


import java.io.File;
import java.time.LocalDate;

import static org.jboss.arquillian.graphene.Graphene.goTo;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 22.01.14
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class IntegrationTest04OwnerView extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(IntegrationTest04OwnerView.class.getName());

    /*
    @BeforeDeployment
    public static void beforeDeployment(){
        log.info("beforeDeployment");
    }
    */

    @Deployment
    public static WebArchive createDeployment() {
        log.info("createDeployment");
        File warFile = new File("target/petclinic.war");
        return ShrinkWrap.createFromZipFile(WebArchive.class, warFile);
    }

    @Page
    private HomePage homePage;

    @Page
    private OwnerPage ownerPage;

    @Page
    private PetTypePage petTypePage;


    
    @Test
    @InSequence(1)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testOpeningHomePage() {
        log.info("testOpeningHomePage");
        goTo(HomePage.class);
        homePage.assertTitle();
    }

    
    @Test
    @InSequence(2)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testOpenFindOwnersPage() {
        log.info("testOpenFindOwnersPage");
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(3)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testOpenOwnersPage() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(4)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testOpenNewOwnerPage() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickNewOwner();
        ownerPage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(5)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testOpenNewOwnerPageFromOwnersList() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickNewOwner();
        ownerPage.assertPageIsLoaded();
    }

    @Test
    @InSequence(6)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testAddNewOwner() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickNewOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.addNewContent(
                "Thomas",
                "Woehlke",
                "Schoenhauser Allee",
                "42",
                "Hinterhof",
                "Berlin",
                "10551",
                "03012345678"
        );
        ownerPage.assertPageIsLoaded();
        ownerPage.assertNewContentFound(
                "Thomas",
                "Woehlke",
                "Schoenhauser Allee",
                "42",
                "Hinterhof",
                "Berlin",
                "10551",
                "03012345678"
        );
    }

    @Test
    @InSequence(7)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testEditOwner() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickShowOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickEditOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.editContent(
                "Willy",
                "Wombel",
                "Elbchaussee 242",
                "Hamburg",
                "04012345678"
        );
        ownerPage.assertPageIsLoaded();
        ownerPage.assertContent(
                "Willy",
                "Wombel",
                "Elbchaussee 242",
                "Hamburg",
                "04012345678"
        );
    }

    @Test
    @InSequence(8)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testAddNewPet() {
        goTo(PetTypePage.class);
        petTypePage.assertPageIsLoaded();
        petTypePage.clickAddNewPetType();
        petTypePage.addNewContent("cat");
        petTypePage.clickAddNewPetType();
        petTypePage.addNewContent("dog");
        petTypePage.clickAddNewPetType();
        petTypePage.addNewContent("mouse");
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickShowOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickAddNewPet();
        ownerPage.assertPageIsLoaded();
        LocalDate birthDate1_15_05_2013 = LocalDate.of(113, 04, 15);
        LocalDate birthDate2_03_08_2012 = LocalDate.of(112, 07, 03);
        ownerPage.setNewPetContent("Tomcat", birthDate1_15_05_2013, "cat");
        ownerPage.assertPageIsLoaded();
        ownerPage.clickAddNewPet();
        ownerPage.setNewPetContent("Bully", birthDate2_03_08_2012, "dog");
        ownerPage.assertPageIsLoaded();
        ownerPage.assertFirstPetContent("Bully", birthDate2_03_08_2012, "dog");
        ownerPage.assertSecondPetContent("Tomcat", birthDate1_15_05_2013, "cat");
    }

    @Test
    @InSequence(9)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testEditPet() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickShowOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickEditFirstPet();
        ownerPage.assertPageIsLoaded();
        LocalDate birthDate_01_06_2010 = LocalDate.of(110, 05, 01);
        ownerPage.setEditPetContent("Speedy", birthDate_01_06_2010, "mouse");
        ownerPage.assertPageIsLoaded();
        ownerPage.assertFirstPetContent("Speedy", birthDate_01_06_2010, "mouse");
    }

    @Test
    @InSequence(10)
    @RunAsClient
    @OverProtocol("Servlet 3.0")
    public void testAddVisitToFirstPet() {
        goTo(HomePage.class);
        homePage.assertPageIsLoaded();
        homePage.clickSearch();
        ownerPage.assertPageIsLoaded();
        ownerPage.clickShowOwner();
        ownerPage.assertPageIsLoaded();
        ownerPage.addVisitToFirstPet();
        ownerPage.assertPageIsLoaded();
        LocalDate birthDate_01_06_2010 = LocalDate.of(110, 05, 01);
        ownerPage.assertOwnerContent("Willy","Wombel");
        ownerPage.assertPetContent("Speedy", birthDate_01_06_2010, "mouse");
        LocalDate visitDate_16_01_2014 = LocalDate.of(114, 01, 16);
        ownerPage.setNewVisitContent(visitDate_16_01_2014,"get milk");
        ownerPage.assertFirstVisitToFirstPet(visitDate_16_01_2014,"get milk");
    }
}
