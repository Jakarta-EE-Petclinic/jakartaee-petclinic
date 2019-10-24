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
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.*;
import org.woehlke.jakartaee.petclinic.oodm.entities.Owner;

import java.io.File;

import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.PROTOCOL;
import static org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig.WAR_FILE;

@RunWith(Arquillian.class)
@RunAsClient
@NotThreadSafe
public class IntegrationTest01AddData extends AbstractPrimePageTest {

  private static Logger log = LogManager.getLogger(IntegrationTest01AddData.class.getName());

  @Page
  private HelpPage helpPage;

  @Page
  private HomePage homePage;

  @Page
  private SpecialtyPage specialtyPage;

  @Page
  private PetTypePage petTypePage;

  @Page
  private VetPage vetPage;

  @Page
  private OwnerPage ownerPage;

  private String specialties[] = new String[]{
      "Zahnarzt","Internist","Chierurg","Orthopäde"
  };
  private String petTypes[] = new String[]{
      "Hund","Katze","Maus","Elefant","Wellensittich"
  };
  private String vets[][] = new String[][]{
      {"Albert","Schweizer"},
      {"Robert","Koch"},
      {"Rudolf","Virchow"},
      {"Viktor","Frankl"},
      {"Alexander","Fleming"},
      {"Hippokrates","von Kos"}
  };
  private String owners[][] = new String[][]{
      {"Helmut","Rahn","Dalbergstraße","38","Rathaus","63739","Aschaffenburg","+4960213300"},
      {"Fritz","Walter","Glatzer Str.","5a","Sigiriya","10247","Berlin","+493029044208"},
      {"Toni","Turek","Am Flutgraben","2","Club der Visionaere","12435","Berlin","+493069518942"},
      {"Bernhard","Klodt","Klingelhöferstraße","14","Bauhaus-Archiv","10785","Berlin","+49302540020"},
      {"Rio","Reiser","Tempelhofer Ufer","37-32","Gedenktafel","10963","Berlin","+493026995000"}
  };

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
  public void testAddNewSpecialties() {
    log.info("testAddNewSpecialties");
    specialtyPage.goTo();
    specialtyPage.assertListEntityPageIsLoaded();
    for(String specialty : specialties){
      specialtyPage.clickShowNewFormButton();
      specialtyPage.assertNewEntityPageIsLoaded();
      specialtyPage.addNewContent(specialty);
      specialtyPage.clickSaveNewButton();
      specialtyPage.assertListEntityPageIsLoaded();
      specialtyPage.assertNewContentAdded(specialty);
    }
  }

  @Test
  @InSequence(2)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testAddNewPetTypes() {
    log.info("testAddNewPetTypes");
    petTypePage.goTo();
    petTypePage.assertListEntityPageIsLoaded();
    for(String petType : petTypes){
      petTypePage.clickShowNewFormButton();
      petTypePage.assertNewEntityPageIsLoaded();
      petTypePage.addNewContent(petType);
      petTypePage.clickSaveNewButton();
      petTypePage.assertListEntityPageIsLoaded();
      petTypePage.assertNewContentAdded(petType);
    }
  }

  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testAddNewVets() {
    log.info("testAddNewVets");
    vetPage.goTo();
    vetPage.assertListEntityPageIsLoaded();
    for(String vet[] : vets){
      String firstNameNew = vet[0];
      String lastNameNew = vet[1];
      vetPage.clickShowNewFormButton();
      vetPage.assertNewEntityPageIsLoaded();
      vetPage.addNewContent(firstNameNew,lastNameNew);
      vetPage.clickSaveNewButton();
      vetPage.assertListEntityPageIsLoaded();
      vetPage.assertNewContentAdded(firstNameNew,lastNameNew);
    }
  }

  @Test
  @InSequence(4)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testAddNewOwners() {
    log.info("testAddNewOwners");
    ownerPage.goTo();
    ownerPage.assertListEntityPageIsLoaded();
    for(String owner[] : owners){
      Owner ownerNew = new Owner();
      ownerNew.setFirstName(owner[0]);
      ownerNew.setLastName(owner[1]);
      ownerNew.setAddress(owner[2]);
      ownerNew.setHouseNumber(owner[3]);
      ownerNew.setAddressInfo(owner[4]);
      ownerNew.setZipCode(owner[5]);
      ownerNew.setCity(owner[6]);
      ownerNew.setPhoneNumber(owner[7]);
      log.info("ownerNew: "+ownerNew.toString());
      ownerPage.clickShowNewFormButton();
      ownerPage.assertNewEntityPageIsLoaded();
      ownerPage.addNewContent(ownerNew);
      ownerPage.clickSaveNewButton();
      //ownerPage.assertListEntityPageIsLoaded();
      //ownerPage.assertNewContentAdded(ownerNew);
    }
  }
}
