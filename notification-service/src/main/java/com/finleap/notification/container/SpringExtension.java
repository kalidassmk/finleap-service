package com.finleap.notification.container;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * com.finleap.notification.container
 *
 * @author Kalidass Mahalingam 16 Feb 2019
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

    /**
     * The constant SPRING_EXTENSION_PROVIDER.
     */
    public static final SpringExtension SPRING_EXTENSION_PROVIDER
            = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    /**
     * The type Spring ext.
     */
    public static class SpringExt implements Extension {
        private volatile ApplicationContext applicationContext;

        /**
         * Initialize.
         *
         * @param applicationContext the application context
         */
        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        /**
         * Props props.
         *
         * @param actorBeanName the actor bean name
         * @return the props
         */
        public Props props(String actorBeanName) {
            return Props.create(
                    SpringActorProducer.class, applicationContext, actorBeanName);
        }
    }
}