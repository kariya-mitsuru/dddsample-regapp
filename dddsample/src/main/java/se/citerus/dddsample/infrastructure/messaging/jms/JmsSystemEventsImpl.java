package se.citerus.dddsample.infrastructure.messaging.jms;

import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import se.citerus.dddsample.application.event.SystemEvents;
import se.citerus.dddsample.domain.model.cargo.Cargo;
import se.citerus.dddsample.domain.model.handling.HandlingEvent;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * JMS based implementation.
 */
public final class JmsSystemEventsImpl implements SystemEvents {

  private JmsOperations jmsOperations;
  private Destination cargoHandledDestination;
  private Destination cargoUpdateDestination;

  @Override
  public void notifyOfHandlingEvent(final HandlingEvent event) {
    final Cargo cargo = event.cargo();
    jmsOperations.send(cargoHandledDestination, new MessageCreator() {
      public Message createMessage(final Session session) throws JMSException {
        return session.createObjectMessage(cargo.trackingId());
      }
    });
  }

  @Override
  public void notifyOfCargoUpdate(final Cargo cargo) {
    jmsOperations.send(cargoUpdateDestination, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        return session.createObjectMessage(cargo.trackingId());
      }
    });
  }

  public void setJmsOperations(JmsOperations jmsOperations) {
    this.jmsOperations = jmsOperations;
  }

  public void setCargoHandledDestination(Destination cargoHandledDestination) {
    this.cargoHandledDestination = cargoHandledDestination;
  }

  public void setCargoUpdateDestination(Destination cargoUpdateDestination) {
    this.cargoUpdateDestination = cargoUpdateDestination;
  }

}