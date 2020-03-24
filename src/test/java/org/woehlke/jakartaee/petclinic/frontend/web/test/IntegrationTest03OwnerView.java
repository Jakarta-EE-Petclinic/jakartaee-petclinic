package org.woehlke.jakartaee.petclinic.frontend.web.test;

import lombok.extern.log4j.Log4j2;
import net.jcip.annotations.NotThreadSafe;
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
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.OwnerPage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.PetTypePage;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.PROTOCOL;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.WAR_FILE;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 22.01.14
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@RunWith(Arquillian.class)
@RunAsClient
@NotThreadSafe
public class IntegrationTest03OwnerView extends AbstractPrimePageTest {

  @Page
  private HomePage homePage;

  @Page
  private OwnerPage ownerPage;

  @Page
  private PetTypePage petTypePage;

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
  public void testOpenFindOwnersPage() {
    log.info("testOpenFindOwnersPage");
    goTo(HomePage.class);
    homePage.assertPageIsLoaded();
  }


  @Ignore
  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpenOwnersPage() {
    goTo(HomePage.class);
    homePage.assertPageIsLoaded();
    homePage.clickSearch();
    ownerPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(4)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpenNewOwnerPage() {
    goTo(HomePage.class);
    homePage.assertPageIsLoaded();
    homePage.clickNewOwner();
    ownerPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(5)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpenNewOwnerPageFromOwnersList() {
    goTo(HomePage.class);
    homePage.assertPageIsLoaded();
    homePage.clickSearch();
    ownerPage.assertPageIsLoaded();
    ownerPage.clickNewOwner();
    ownerPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(6)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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

  @Ignore
  @Test
  @InSequence(7)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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

  @Ignore
  @Test
  @InSequence(8)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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

  @Ignore
  @Test
  @InSequence(9)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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

  @Ignore
  @Test
  @InSequence(10)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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
    ownerPage.assertOwnerContent("Willy", "Wombel");
    ownerPage.assertPetContent("Speedy", birthDate_01_06_2010, "mouse");
    LocalDate visitDate_16_01_2014 = LocalDate.of(114, 01, 16);
    ownerPage.setNewVisitContent(visitDate_16_01_2014, "get milk");
    ownerPage.assertFirstVisitToFirstPet(visitDate_16_01_2014, "get milk");
  }

  */
}
