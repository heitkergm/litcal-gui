package com.dappermoose.litcalgui.main;

import javax.swing.JFrame;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.extern.log4j.Log4j2;

/**
 * The Class SpringConfig.
 */
@ComponentScan ("com.dappermoose.litcalgui")
@Configuration
@Log4j2
public class SpringConfig
{
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
     * @return the swing frame
     */
    @Bean
    JFrame frame ()
    {
        return new JFrame ();
    }
}
