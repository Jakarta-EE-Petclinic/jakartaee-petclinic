package org.woehlke.javaee8.petclinic.frontend.web.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.container.test.api.BeforeDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.javaee8.petclinic.frontend.web.test.pages.*;


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
public class Test04Owner extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(Test04Owner.class.getName());

    @BeforeDeployment
    public static void beforeDeployment(){
        log.info("beforeDeployment");
    }

    @Deployment
    public static WebArchive createDeployment() {
        log.info("createDeployment");
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/petclinic.war"));
    }

    @Page
    private HomePage homePage;

    @Page
    private OwnerSearchPage ownerSearchPage;

    @Page
    private OwnerListPage ownerListPage;

    @Page
    private OwnerNewPage ownerNewPage;

    @Page
    private OwnerShowPage ownerShowPage;

    @Page
    private OwnerEditPage ownerEditPage;

    @Page
    private PetNewPage petNewPage;

    @Page
    private VisitNewPage visitNewPage;

    @Page
    private PetEditPage petEditPage;

    @Page
    private PetTypeListPage petTypeListPage;

    @Page
    private PetTypeNewPage petTypeNewPage;

    
    @Test
    @InSequence(1)
    @RunAsClient
    public void testOpeningHomePage() {
        log.info("testOpeningHomePage");
        goTo(HomePage.class);
        homePage.assertTitle();
    }

    
    @Test
    @InSequence(2)
    @RunAsClient
    public void testOpenFindOwnersPage() {
        log.info("testOpenFindOwnersPage");
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(3)
    @RunAsClient
    public void testOpenOwnersPage() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(4)
    @RunAsClient
    public void testOpenNewOwnerPage() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickNewOwner();
        ownerNewPage.assertPageIsLoaded();
    }

    
    @Test
    @InSequence(5)
    @RunAsClient
    public void testOpenNewOwnerPageFromOwnersList() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickNewOwner();
        ownerNewPage.assertPageIsLoaded();
    }

    @Test
    @InSequence(6)
    @RunAsClient
    public void testAddNewOwner() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickNewOwner();
        ownerNewPage.assertPageIsLoaded();
        ownerNewPage.addNewContent(
                "Thomas",
                "Woehlke",
                "Schoenhauser Allee",
                "42",
                "Hinterhof",
                "Berlin",
                "10551",
                "03012345678"
        );
        ownerListPage.assertPageIsLoaded();
        ownerListPage.assertNewContentFound(
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
    public void testEditOwner() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickShowOwner();
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.clickEditOwner();
        ownerEditPage.assertPageIsLoaded();
        ownerEditPage.editContent(
                "Willy",
                "Wombel",
                "Elbchaussee 242",
                "Hamburg",
                "04012345678"
        );
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.assertContent(
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
    public void testAddNewPet() {
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.addNewContent("cat");
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.addNewContent("dog");
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.addNewContent("mouse");
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickShowOwner();
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.clickAddNewPet();
        petNewPage.assertPageIsLoaded();
        LocalDate birthDate1_15_05_2013 = LocalDate.of(113, 04, 15);
        LocalDate birthDate2_03_08_2012 = LocalDate.of(112, 07, 03);
        petNewPage.setContent("Tomcat", birthDate1_15_05_2013, "cat");
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.clickAddNewPet();
        petNewPage.setContent("Bully", birthDate2_03_08_2012, "dog");
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.assertFirstPetContent("Bully", birthDate2_03_08_2012, "dog");
        ownerShowPage.assertSecondPetContent("Tomcat", birthDate1_15_05_2013, "cat");
    }

    @Test
    @InSequence(9)
    @RunAsClient
    public void testEditPet() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickShowOwner();
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.clickEditFirstPet();
        petEditPage.assertPageIsLoaded();
        LocalDate birthDate_01_06_2010 = LocalDate.of(110, 05, 01);
        petEditPage.setContent("Speedy", birthDate_01_06_2010, "mouse");
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.assertFirstPetContent("Speedy", birthDate_01_06_2010, "mouse");
    }

    @Test
    @InSequence(10)
    @RunAsClient
    public void testAddVisitToFirstPet() {
        goTo(OwnerSearchPage.class);
        ownerSearchPage.assertPageIsLoaded();
        ownerSearchPage.clickSearch();
        ownerListPage.assertPageIsLoaded();
        ownerListPage.clickShowOwner();
        ownerShowPage.assertPageIsLoaded();
        ownerShowPage.addVisitToFirstPet();
        visitNewPage.assertPageIsLoaded();
        LocalDate birthDate_01_06_2010 = LocalDate.of(110, 05, 01);
        visitNewPage.assertOwnerContent("Willy","Wombel");
        visitNewPage.assertPetContent("Speedy", birthDate_01_06_2010, "mouse");
        LocalDate visitDate_16_01_2014 = LocalDate.of(114, 01, 16);
        visitNewPage.setNewContent(visitDate_16_01_2014,"get milk");
        ownerShowPage.assertFirstVisitToFirstPet(visitDate_16_01_2014,"get milk");
    }
}
