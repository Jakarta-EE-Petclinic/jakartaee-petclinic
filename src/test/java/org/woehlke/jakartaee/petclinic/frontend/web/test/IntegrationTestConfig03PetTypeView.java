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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.PetTypePage;

import java.io.File;

import static org.jboss.arquillian.graphene.Graphene.goTo;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.PROTOCOL;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.WAR_FILE;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 21.01.14
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
@RunAsClient
@NotThreadSafe
public class IntegrationTestConfig03PetTypeView extends AbstractPrimePageTest {

  private static Logger log = LogManager.getLogger(IntegrationTestConfig03PetTypeView.class.getName());

  @Page
  private HomePage homePage;

  @Page
  private PetTypePage petTypePage;

  @Deployment
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
  public void testOpeningPetTypesPage() {
    log.info("testOpeningPetTypesPage");
    goTo(PetTypePage.class);
    petTypePage.assertPageIsLoaded();
  }


  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testNewPetTypePage() {
    log.info("testNewPetTypePage");
    goTo(PetTypePage.class);
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("dog");
    petTypePage.assertPageIsLoaded();
    petTypePage.assertNewContentFound("dog");
  }


  @Test
  @InSequence(4)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testEditPetTypePage() {
    log.info("testEditPetTypePage");
    goTo(PetTypePage.class);
    petTypePage.assertPageIsLoaded();
    petTypePage.clickEditSpecialty();
    petTypePage.assertPageIsLoaded();
    petTypePage.editContent("mouse");
    petTypePage.assertPageIsLoaded();
    petTypePage.assertEditedContentFound("mouse");
  }

  @Test
  @InSequence(5)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testDeletePetTypePage() {
    log.info("testDeletePetTypePage");
    goTo(PetTypePage.class);
    petTypePage.assertPageIsLoaded();
    petTypePage.clickDeleteSpecialty();
    petTypePage.assertPageIsLoaded();
    petTypePage.assertDeletedContentNotFound();
  }

  @Test
  @InSequence(6)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testFillNewPetTypes() {
    log.info("testFillNewPetTypes");
    goTo(PetTypePage.class);
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet01");
    petTypePage.assertPageIsLoaded();
    petTypePage.assertNewContentFound("pet01");
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet02");
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet03");
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet04");
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet05");
    petTypePage.assertPageIsLoaded();
    petTypePage.clickAddNewPetType();
    petTypePage.assertPageIsLoaded();
    petTypePage.addNewContent("pet06");
    petTypePage.assertPageIsLoaded();
  }


  @Test
  @InSequence(7)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testPetTypesPager() {
    log.info("testPetTypesPager");
    petTypePage.assertPagerNextIsLoaded();
    petTypePage.clickPagerNext();
    petTypePage.assertPagerPrevIsLoaded();
    petTypePage.clickPagerPrev();
    petTypePage.assertPagerNextIsLoaded();
  }

  @Test
  @InSequence(8)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testPetTypesSorter() {
    log.info("testPetTypesSorter");
    petTypePage.assertSorterIsLoaded();
    petTypePage.assertOrder();
    petTypePage.clickSorter();
    petTypePage.assertReverseOrder();
    petTypePage.assertSorterIsLoaded();
    petTypePage.clickSorter();
    petTypePage.assertOrder();
  }
}
