package org.woehlke.jakartaee.petclinic.frontend.web.test;

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
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.*;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 21.01.14
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class Test02Vet extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(Test02Vet.class.getName());


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
    private VetListPage vetListPage;

    @Page
    private VetNewPage vetNewPage;

    @Page
    private VetEditPage vetEditPage;

    @Page
    private SpecialtyListPage specialtyListPage;

    @Page
    private SpecialtyNewPage specialtyNewPage;

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
    public void testOpeningVetPage() {
        log.info("testOpeningVetPage");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewVetPage() {
        log.info("testNewVetPage");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContent(
                "Thomas",
                "Woehlke"
        );
        vetListPage.assertPageIsLoaded();
        vetListPage.assertNewContentFound(
                "Thomas",
                "Woehlke"
        );
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditVetPage() {
        log.info("testEditVetPage");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickEditVet();
        vetEditPage.assertPageIsLoaded();
        vetEditPage.editContent(
                "Willy",
                "Wacker"
        );
        vetListPage.assertPageIsLoaded();
        vetListPage.assertEditedContentFound(
                "Willy",
                "Wacker"
        );
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeleteVetPage() {
        log.info("testDeleteVetPage");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickDeleteVet();
        vetListPage.assertPageIsLoaded();
        vetListPage.assertDeletedContentNotFound();
    }

    @Test
    @InSequence(6)
    @RunAsClient
    public void testNewVetPageWithSpecialties() {
        log.info("testNewVetPageWithSpecialties");
        goTo(SpecialtyListPage.class);
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("dentist");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("anesthetist");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("radiology");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithAllSpecialties(
                "Thomas",
                "Woehlke"
        );
        vetListPage.assertPageIsLoaded();
        vetListPage.assertContentFoundWithSpecialties(
                "Thomas",
                "Woehlke",
                "anesthetist dentist radiology"
        );
    }

    @Test
    @InSequence(7)
    @RunAsClient
    public void testEditVetPageWithSpecialties() {
        log.info("testEditVetPageWithSpecialties");
        goTo(VetListPage.class);
        vetListPage.clickEditVet();
        vetEditPage.assertPageIsLoaded();
        vetEditPage.editContentWithNoneSpecialties(
                "Henry",
                "Jones"
        );
        vetListPage.assertPageIsLoaded();
        vetListPage.assertContentFoundWithSpecialties(
                "Henry",
                "Jones",
                "none"
        );
    }

    /**
     * Test for #24 new specialties not visible in veterinarians editmode
     */
    @Test
    @InSequence(8)
    @RunAsClient
    public void testEditVetPageWithNewSpecialties(){
        log.info("testEditVetPageWithNewSpecialties");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        goTo(SpecialtyListPage.class);
        specialtyListPage.assertPageIsLoaded();
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.assertPageIsLoaded();
        specialtyNewPage.addNewContent("hero");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickEditVet();
        vetEditPage.assertPageIsLoaded();
        vetEditPage.editContentWithAllSpecialties(
                "Thomas",
                "Woehlke"
        );
        vetListPage.assertPageIsLoaded();
        vetListPage.assertContentFoundWithSpecialties(
                "Thomas",
                "Woehlke",
                "anesthetist dentist hero radiology"
        );
    }

    @Test
    @InSequence(9)
    @RunAsClient
    public void testFillNewVetPageWithSpecialties() {
        log.info("testFillNewVetPageWithSpecialties");
        goTo(SpecialtyListPage.class);
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s01");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s02");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s03");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s04");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s05");
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.addNewContent("s06");
    }

    @Test
    @InSequence(10)
    @RunAsClient
    public void testFillNewVetsPage() {
        log.info("testFillNewVetsPage");
        goTo(VetListPage.class);
        vetListPage.assertPageIsLoaded();
        vetListPage.clickDeleteVet();
        vetListPage.assertPageIsLoaded();
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname01",
                "Nachname06",
                "s03"
        );
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname02",
                "Nachname05",
                "s02"
        );
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname03",
                "Nachname04",
                "s01"
        );
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname04",
                "Nachname03",
                "s06"
        );
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname05",
                "Nachname02",
                "s05"
        );
        vetListPage.clickAddNewVet();
        vetNewPage.assertPageIsLoaded();
        vetNewPage.addNewContentWithOneSpecialty(
                "Vorname06",
                "Nachname01",
                "s04"
        );
        vetListPage.assertPageIsLoaded();
    }

    @Test
    @InSequence(11)
    @RunAsClient
    public void testVetsPager() {
        log.info("testVetsPager");
        vetListPage.assertPagerNextIsLoaded();
        vetListPage.clickPagerNext();
        vetListPage.assertPagerPrevIsLoaded();
        vetListPage.clickPagerPrev();
        vetListPage.assertPagerNextIsLoaded();
    }

    @Test
    @InSequence(12)
    @RunAsClient
    public void testVetsSorterFirstname() {
        log.info("testVetsSorterFirstname");
        vetListPage.assertSorterIsLoaded();
        vetListPage.assertOrder();
        vetListPage.clickSorterFirstname();
        vetListPage.assertFirstnameOrder();
        vetListPage.clickSorterFirstname();
        vetListPage.assertFirstnameReverseOrder();
    }

    @Test
    @InSequence(13)
    @RunAsClient
    public void testVetsSorterLastname() {
        log.info("testVetsSorterLastname");
        vetListPage.clickSorterLastname();
        vetListPage.assertLastnameOrder();
        vetListPage.clickSorterLastname();
        vetListPage.assertLastnameReverseOrder();
    }

    @Test
    @InSequence(14)
    @RunAsClient
    public void testVetsSorterSpecialty() {
        log.info("testVetsSorterSpecialty");
        vetListPage.clickSorterSpecialty();
        vetListPage.assertSpecialtyOrder();
        vetListPage.clickSorterSpecialty();
        vetListPage.assertSpecialtyReverseOrder();
    }

}
