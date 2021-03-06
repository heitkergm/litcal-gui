package com.dappermoose.litcalgui.main;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.log4j.Log4j2;

/**
 * The Class SpringConfig.
 */
@ComponentScan ("com.dappermoose.litcalgui")
@Configuration
@Log4j2
public class SpringConfig
{
    @Inject
    ApplicationContext context;
    
    /**
     * Message source.
     *
     * @return the message source
     */
    @Bean
    MessageSource messageSource ()
    {
        final ReloadableResourceBundleMessageSource source =
                new ReloadableResourceBundleMessageSource ();
        source.setCacheSeconds (60);
        source.setBasenames ("classpath:messages");
        return source;
    }
    
    /**
     * frame.
     *
     * @return the swing Frame
     */
    @Bean
    JFrame frame ()
    {
        return new JFrame ();
    }
    
    @Bean
    Locale locale ()
    {
        // get the locale and save it off as a bean
        LOG.debug ("context is " + context);
        String localeName = context.getEnvironment ().getProperty ("locale");
        LOG.debug ("locale is " + localeName);
        if (localeName == null)
        {
            localeName = Locale.getDefault ().getDisplayName ();
        }
        
        Locale myLocale = new Locale (localeName);
        LOG.debug ("locale bean is " + myLocale);
        
        return myLocale;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer ()
    {
        PropertySourcesPlaceholderConfigurer propsConfig = 
            new PropertySourcesPlaceholderConfigurer ();
        propsConfig.setLocation (new ClassPathResource ("git.properties"));
        propsConfig.setIgnoreResourceNotFound (true);
        propsConfig.setIgnoreUnresolvablePlaceholders (true);
        return propsConfig;
    }
}
