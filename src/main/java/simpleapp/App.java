package simpleapp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import simpleapp.beans.Client;
import simpleapp.beans.Event;
import simpleapp.loggers.EventLogger;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event,"some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event,"some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(event,"some event for 3");

        event = ctx.getBean(Event.class);
        app.logEvent(event,"some event for 4");

        ctx.close();
    }

    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event,String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }


}
