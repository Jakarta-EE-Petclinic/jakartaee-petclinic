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
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.PetTypeEditPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.PetTypeNewPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.PetTypeListPage;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 21.01.14
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class Test03PetTypeView extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(Test03PetTypeView.class.getName());

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
    private PetTypeListPage petTypeListPage;

    @Page
    private PetTypeNewPage petTypeNewPage;

    @Page
    private PetTypeEditPage petTypeEditPage;

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
    public void testOpeningPetTypesPage() {
        log.info("testOpeningPetTypesPage");
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
    }


    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewPetTypePage() {
        log.info("testNewPetTypePage");
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("dog");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.assertNewContentFound("dog");
    }


    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditPetTypePage() {
        log.info("testEditPetTypePage");
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickEditSpecialty();
        petTypeEditPage.assertPageIsLoaded();
        petTypeEditPage.editContent("mouse");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.assertEditedContentFound("mouse");
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeletePetTypePage() {
        log.info("testDeletePetTypePage");
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickDeleteSpecialty();
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.assertDeletedContentNotFound();
    }

    @Test
    @InSequence(6)
    @RunAsClient
    public void testFillNewPetTypes() {
        log.info("testFillNewPetTypes");
        goTo(PetTypeListPage.class);
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet01");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.assertNewContentFound("pet01");
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet02");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet03");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet04");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet05");
        petTypeListPage.assertPageIsLoaded();
        petTypeListPage.clickAddNewPetType();
        petTypeNewPage.assertPageIsLoaded();
        petTypeNewPage.addNewContent("pet06");
        petTypeListPage.assertPageIsLoaded();
    }



    @Test
    @InSequence(7)
    @RunAsClient
    public void testPetTypesPager() {
        log.info("testPetTypesPager");
        petTypeListPage.assertPagerNextIsLoaded();
        petTypeListPage.clickPagerNext();
        petTypeListPage.assertPagerPrevIsLoaded();
        petTypeListPage.clickPagerPrev();
        petTypeListPage.assertPagerNextIsLoaded();
    }

    @Test
    @InSequence(8)
    @RunAsClient
    public void testPetTypesSorter() {
        log.info("testPetTypesSorter");
        petTypeListPage.assertSorterIsLoaded();
        petTypeListPage.assertOrder();
        petTypeListPage.clickSorter();
        petTypeListPage.assertReverseOrder();
        petTypeListPage.assertSorterIsLoaded();
        petTypeListPage.clickSorter();
        petTypeListPage.assertOrder();
    }
}
