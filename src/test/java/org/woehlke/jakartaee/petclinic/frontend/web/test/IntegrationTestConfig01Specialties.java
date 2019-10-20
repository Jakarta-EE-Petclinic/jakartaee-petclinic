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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.SpecialtyPage;

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
public class IntegrationTestConfig01Specialties extends AbstractPrimePageTest implements ArquillianTestConfig {

  private static Logger log = LogManager.getLogger(IntegrationTestConfig01Specialties.class.getName());



  @Page
  private HomePage homePage;

  @Page
  private SpecialtyPage specialtyPage;

  @Deployment(testable = false)
  public static WebArchive createDeployment() {
    log.info("createDeployment");
    File warFile = new File(WAR_FILE);
    return ShrinkWrap.createFromZipFile(WebArchive.class, warFile);
  }

  @Test
  @InSequence(1)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpeningHomePage() {
    log.info("testOpeningHomePage");
    goTo(HomePage.class);
    homePage.assertTitle();
  }

  @Test
  @InSequence(2)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpeningSpecialtiesPage() {
    log.info("testOpeningSpecialtiesPage");
    goTo(SpecialtyPage.class);
    specialtyPage.assertPageIsLoaded();
  }

  @Ignore
  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testNewSpecialtyPage() {
    log.info("testNewSpecialtyPage");
    goTo(SpecialtyPage.class);
    specialtyPage.assertPageIsLoaded();
    specialtyPage.clickAddNewSpecialty();
    specialtyPage.assertPageIsLoaded();
    specialtyPage.addNewContent("dentist");
    specialtyPage.assertPageIsLoaded();
    //specialtyListPage.assertNewContentFound("dentist");
  }

  @Ignore
  @Test
  @InSequence(4)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testEditSpecialtyPage() {
    log.info("testEditSpecialtyPage");
    goTo(SpecialtyPage.class);
    specialtyPage.assertPageIsLoaded();
    specialtyPage.clickEditSpecialty();
    specialtyPage.assertPageIsLoaded();
    specialtyPage.editContent("specialist");
    specialtyPage.assertPageIsLoaded();
    //specialtyListPage.assertEditedContentFound("specialist");
  }

  @Ignore
  @Test
  @InSequence(5)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testDeleteSpecialtyPage() {
    log.info("testDeleteSpecialtyPage");
    goTo(SpecialtyPage.class);
    specialtyPage.assertPageIsLoaded();
    specialtyPage.clickDeleteSpecialty();
    specialtyPage.assertPageIsLoaded();
    //specialtyListPage.assertDeletedContentNotFound();
  }

  @Ignore
  @Test
  @InSequence(6)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testFillSpecialtyPager() {
    log.info("testFillSpecialtyPager");
    goTo(SpecialtyPage.class);
    String[] specialtyList = {
        "specialty01", "specialty02",
        "specialty03", "specialty04",
        "specialty05", "specialty06",
    };
    for (String specialty : specialtyList) {
      specialtyPage.assertPageIsLoaded();
      specialtyPage.clickAddNewSpecialty();
      specialtyPage.assertPageIsLoaded();
      specialtyPage.addNewContent(specialty);
    }
    specialtyPage.assertPageIsLoaded();
  }

  //TODO:
  @Ignore
  @Test
  @InSequence(7)
  @RunAsClient
  @OverProtocol(PROTOCOL)
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
  @OverProtocol(PROTOCOL)
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
