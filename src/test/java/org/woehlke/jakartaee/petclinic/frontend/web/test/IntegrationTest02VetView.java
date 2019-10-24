package org.woehlke.jakartaee.petclinic.frontend.web.test;

import net.jcip.annotations.NotThreadSafe;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.SpecialtyPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.VetPage;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.PROTOCOL;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.WAR_FILE;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 21.01.14
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
@NotThreadSafe
public class IntegrationTest02VetView extends AbstractPrimePageTest {

  private static Logger log = LogManager.getLogger(IntegrationTest02VetView.class.getName());

  @Page
  private HomePage homePage;

  @Page
  private VetPage vetPage;

  @Page
  private SpecialtyPage specialtyPage;

  @Deployment
  public static WebArchive createDeployment() {
    log.info("createDeployment");
    File warFile = new File(WAR_FILE);
    return ShrinkWrap.createFromZipFile(WebArchive.class, warFile);
  }

  @Ignore
  @Test
  @InSequence(1)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpeningHomePage() {
    log.info("testOpeningHomePage");
    goTo(HomePage.class);
    homePage.assertTitle();
  }

/*
  @Ignore
  @Test
  @InSequence(2)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpeningVetPage() {
    log.info("testOpeningVetPage");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testNewVetPage() {
    log.info("testNewVetPage");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContent(
        "Thomas",
        "Woehlke"
    );
    vetPage.assertPageIsLoaded();
    vetPage.assertNewContentFound(
        "Thomas",
        "Woehlke"
    );
  }

  @Ignore
  @Test
  @InSequence(4)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testEditVetPage() {
    log.info("testEditVetPage");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickEditVet();
    vetPage.assertPageIsLoaded();
    vetPage.editContent(
        "Willy",
        "Wacker"
    );
    vetPage.assertPageIsLoaded();
    vetPage.assertEditedContentFound(
        "Willy",
        "Wacker"
    );
  }

  @Ignore
  @Test
  @InSequence(5)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testDeleteVetPage() {
    log.info("testDeleteVetPage");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickDeleteVet();
    vetPage.assertPageIsLoaded();
    vetPage.assertDeletedContentNotFound();
  }

  @Ignore
  @Test
  @InSequence(6)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testNewVetPageWithSpecialties() {
    log.info("testNewVetPageWithSpecialties");
    goTo(SpecialtyPage.class);
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("dentist");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("anesthetist");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("radiology");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithAllSpecialties(
        "Thomas",
        "Woehlke"
    );
    vetPage.assertPageIsLoaded();
    vetPage.assertContentFoundWithSpecialties(
        "Thomas",
        "Woehlke",
        "anesthetist dentist radiology"
    );
  }

  @Ignore
  @Test
  @InSequence(7)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testEditVetPageWithSpecialties() {
    log.info("testEditVetPageWithSpecialties");
    goTo(VetPage.class);
    vetPage.clickEditVet();
    vetPage.assertPageIsLoaded();
    vetPage.editContentWithNoneSpecialties(
        "Henry",
        "Jones"
    );
    vetPage.assertPageIsLoaded();
    vetPage.assertContentFoundWithSpecialties(
        "Henry",
        "Jones",
        "none"
    );
  }
*/
/*
  @Ignore
  @Test
  @InSequence(8)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testEditVetPageWithNewSpecialties() {
    log.info("testEditVetPageWithNewSpecialties");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    goTo(SpecialtyPage.class);
    specialtyPage.assertPageIsLoaded();
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.assertPageIsLoaded();
    specialtyPage.addNewContent("hero");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickEditVet();
    vetPage.assertPageIsLoaded();
    vetPage.editContentWithAllSpecialties(
        "Thomas",
        "Woehlke"
    );
    vetPage.assertPageIsLoaded();
    vetPage.assertContentFoundWithSpecialties(
        "Thomas",
        "Woehlke",
        "anesthetist dentist hero radiology"
    );
  }

  @Ignore
  @Test
  @InSequence(9)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testFillNewVetPageWithSpecialties() {
    log.info("testFillNewVetPageWithSpecialties");
    goTo(SpecialtyPage.class);
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s01");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s02");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s03");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s04");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s05");
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.addNewContent("s06");
  }

  @Ignore
  @Test
  @InSequence(10)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testFillNewVetsPage() {
    log.info("testFillNewVetsPage");
    goTo(VetPage.class);
    vetPage.assertPageIsLoaded();
    vetPage.clickDeleteVet();
    vetPage.assertPageIsLoaded();
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname01",
        "Nachname06",
        "s03"
    );
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname02",
        "Nachname05",
        "s02"
    );
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname03",
        "Nachname04",
        "s01"
    );
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname04",
        "Nachname03",
        "s06"
    );
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname05",
        "Nachname02",
        "s05"
    );
    vetPage.clickAddNewVet();
    vetPage.assertPageIsLoaded();
    vetPage.addNewContentWithOneSpecialty(
        "Vorname06",
        "Nachname01",
        "s04"
    );
    vetPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(11)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testVetsPager() {
    log.info("testVetsPager");
    vetPage.assertPagerNextIsLoaded();
    vetPage.clickPagerNext();
    vetPage.assertPagerPrevIsLoaded();
    vetPage.clickPagerPrev();
    vetPage.assertPagerNextIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(12)
  @RunAsClient
  @OverProtocol("Servlet 3.0")
  public void testVetsSorterFirstname() {
    log.info("testVetsSorterFirstname");
    vetPage.assertSorterIsLoaded();
    vetPage.assertOrder();
    vetPage.clickSorterFirstname();
    vetPage.assertFirstnameOrder();
    vetPage.clickSorterFirstname();
    vetPage.assertFirstnameReverseOrder();
  }

  @Ignore
  @Test
  @InSequence(13)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testVetsSorterLastname() {
    log.info("testVetsSorterLastname");
    vetPage.clickSorterLastname();
    vetPage.assertLastnameOrder();
    vetPage.clickSorterLastname();
    vetPage.assertLastnameReverseOrder();
  }

  @Ignore
  @Test
  @InSequence(14)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testVetsSorterSpecialty() {
    log.info("testVetsSorterSpecialty");
    vetPage.clickSorterSpecialty();
    vetPage.assertSpecialtyOrder();
    vetPage.clickSorterSpecialty();
    vetPage.assertSpecialtyReverseOrder();
  }
  */
}
