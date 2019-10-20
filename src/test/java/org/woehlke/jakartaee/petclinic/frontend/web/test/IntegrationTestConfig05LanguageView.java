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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.LanguageViewImpl;
import org.woehlke.jakartaee.petclinic.frontend.web.test.common.ArquillianTestConfig;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;

import java.io.File;
import java.util.Locale;

import static org.jboss.arquillian.graphene.Graphene.goTo;

//import org.jboss.arquillian.container.test.api.BeforeDeployment;

@RunWith(Arquillian.class)
@RunAsClient
public class IntegrationTestConfig05LanguageView extends AbstractPrimePageTest implements ArquillianTestConfig {

  private static Logger log = LogManager.getLogger(IntegrationTestConfig05LanguageView.class.getName());

  private LanguageView languageView = new LanguageViewImpl();

  @Page
  private HomePage homePage;

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
  public void testGetLocale() {
    log.info("testGetLocale");
    Locale expected = Locale.ENGLISH;
    Assert.assertEquals(languageView.getLocale(), expected);
    log.info("testGetLocale DONE");
  }

  @Test
  @InSequence(2)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testJsfLocale() {
    log.info("testJsfLocale");
    Locale result = org.primefaces.util.LocaleUtils.toLocale(Locale.GERMAN.toString());
    log.info("Locale result = " + result.toString());
    result = org.primefaces.util.LocaleUtils.toLocale(Locale.ENGLISH.toString());
    log.info("Locale result = " + result.toString());
    result = org.primefaces.util.LocaleUtils.toLocale(Locale.US.toString());
    log.info("Locale result = " + result.toString());
    result = org.primefaces.util.LocaleUtils.toLocale(Locale.GERMANY.toString());
    log.info("Locale result = " + result.toString());
    log.info("testJsfLocale Done");
  }

  @Test
  @InSequence(3)
  @RunAsClient
  @OverProtocol(PROTOCOL)
  public void testOpeningHomePage() {
    log.info("testOpeningHomePage");
    goTo(HomePage.class);
    homePage.assertTitle();
  }
}
