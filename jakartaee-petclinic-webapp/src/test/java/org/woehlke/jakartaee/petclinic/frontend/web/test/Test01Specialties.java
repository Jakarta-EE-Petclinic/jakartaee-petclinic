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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.SpecialtyEditPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.SpecialtyNewPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.SpecialtyListPage;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 19.01.14
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class Test01Specialties extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(Test01Specialties.class.getName());
    
    @BeforeDeployment
    public static void beforeDeployment(){
        log.info("beforeDeployment");
    }

    @Deployment
    public static WebArchive createDeployment() {
        log.info("createDeployment");
        return ShrinkWrap.createFromZipFile(
                WebArchive.class,
                new File("target/petclinic.war")
        );
    }

    @Page
    private HomePage homePage;

    @Page
    private SpecialtyListPage specialtyListPage;

    @Page
    private SpecialtyNewPage specialtyNewPage;

    @Page
    private SpecialtyEditPage specialtyEditPage;

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
    public void testOpeningSpecialtiesPage() {
        log.info("testOpeningSpecialtiesPage");
        goTo(SpecialtyListPage.class);
        specialtyListPage.assertPageIsLoaded();
    }

    @Ignore
    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewSpecialtyPage() {
        log.info("testNewSpecialtyPage");
        goTo(SpecialtyListPage.class);
        specialtyListPage.assertPageIsLoaded();
        specialtyListPage.clickAddNewSpecialty();
        specialtyNewPage.assertPageIsLoaded();
        specialtyNewPage.addNewContent("dentist");
        specialtyListPage.assertPageIsLoaded();
        //specialtyListPage.assertNewContentFound("dentist");
    }

    @Ignore
    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditSpecialtyPage() {
        log.info("testEditSpecialtyPage");
        goTo(SpecialtyListPage.class);
        specialtyListPage.assertPageIsLoaded();
        specialtyListPage.clickEditSpecialty();
        specialtyEditPage.assertPageIsLoaded();
        specialtyEditPage.editContent("specialist");
        specialtyListPage.assertPageIsLoaded();
        //specialtyListPage.assertEditedContentFound("specialist");
    }

    @Ignore
    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeleteSpecialtyPage() {
        log.info("testDeleteSpecialtyPage");
        goTo(SpecialtyListPage.class);
        specialtyListPage.assertPageIsLoaded();
        specialtyListPage.clickDeleteSpecialty();
        specialtyListPage.assertPageIsLoaded();
        //specialtyListPage.assertDeletedContentNotFound();
    }

    @Ignore
    @Test
    @InSequence(6)
    @RunAsClient
    public void testFillSpecialtyPager() {
        log.info("testFillSpecialtyPager");
        goTo(SpecialtyListPage.class);
        String[] specialtyList = {
                "specialty01","specialty02",
                "specialty03","specialty04",
                "specialty05","specialty06",
        };
        for(String specialty:specialtyList){
            specialtyListPage.assertPageIsLoaded();
            specialtyListPage.clickAddNewSpecialty();
            specialtyNewPage.assertPageIsLoaded();
            specialtyNewPage.addNewContent(specialty);
        }
        specialtyListPage.assertPageIsLoaded();
    }

    //TODO:
    @Ignore
    @Test
    @InSequence(7)
    @RunAsClient
    public void testSpecialtyPager() {
        log.info("testSpecialtyPager");
        /*
        specialtyListPage.assertPagerNextIsLoaded();
        specialtyListPage.clickPagerNext();
        specialtyListPage.assertPagerPrevIsLoaded();
        specialtyListPage.clickPagerPrev();
        specialtyListPage.assertPagerNextIsLoaded();
        */
    }

    //TODO:
    @Ignore
    @Test
    @InSequence(8)
    @RunAsClient
    public void testSpecialtySorter() {
        log.info("testSpecialtySorter");
          /*
        specialtyListPage.assertSorterIsLoaded();
        specialtyListPage.assertOrder();
        specialtyListPage.clickSorter();
        specialtyListPage.assertReverseOrder();
        specialtyListPage.assertSorterIsLoaded();
        specialtyListPage.clickSorter();
        specialtyListPage.assertOrder();
           */
    }

}
