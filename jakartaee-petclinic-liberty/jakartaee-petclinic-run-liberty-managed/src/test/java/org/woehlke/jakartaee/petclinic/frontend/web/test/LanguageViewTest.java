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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.arquillian.AbstractPrimePageTest;
import org.woehlke.jakartaee.petclinic.frontend.web.LanguageView;
import org.woehlke.jakartaee.petclinic.frontend.web.impl.LanguageViewImpl;
import org.woehlke.jakartaee.petclinic.frontend.web.test.pages.HomePage;

import java.io.File;
import java.util.Locale;

import static org.jboss.arquillian.graphene.Graphene.goTo;

@RunWith(Arquillian.class)
@RunAsClient
public class LanguageViewTest extends AbstractPrimePageTest {

    private static Logger log = LogManager.getLogger(LanguageViewTest.class.getName());

    private LanguageView languageView = new LanguageViewImpl();

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

    @Test
    @InSequence(1)
    public void testGetLocale(){
        log.info("testGetLocale");
        Locale expected = Locale.ENGLISH;
        Assert.assertEquals(languageView.getLocale(),expected);
        log.info("testGetLocale DONE");
    }

    @Test
    @InSequence(2)
    public void testJsfLocale(){
        log.info("testJsfLocale");
        Locale result = org.primefaces.util.LocaleUtils.toLocale(Locale.GERMAN.toString());
        log.info("Locale result = "+result.toString());
        result = org.primefaces.util.LocaleUtils.toLocale(Locale.ENGLISH.toString());
        log.info("Locale result = "+result.toString());
        result = org.primefaces.util.LocaleUtils.toLocale(Locale.US.toString());
        log.info("Locale result = "+result.toString());
        result = org.primefaces.util.LocaleUtils.toLocale(Locale.GERMANY.toString());
        log.info("Locale result = "+result.toString());
        log.info("testJsfLocale Done");
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testOpeningHomePage() {
        log.info("testOpeningHomePage");
        goTo(HomePage.class);
        homePage.assertTitle();
    }
}
