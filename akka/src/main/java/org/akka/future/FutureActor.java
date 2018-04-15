package org.akka.future;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FutureActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object o) throws Throwable {
        log.info("akka.future.FutureActor.onReceive:" + o);
        if (o instanceof Integer) {
            log.info("print:" + o);
        } else if (o instanceof String) {
            log.info("print:" + o);
        } else {
            unhandled(o);
        }
    }
}
