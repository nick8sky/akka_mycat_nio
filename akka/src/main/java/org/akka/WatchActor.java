package org.akka;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WatchActor extends UntypedActor {

    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    /**
     * 监听一个actor
     *
     * @param actorRef
     */
    public WatchActor(ActorRef actorRef) {
        getContext().watch(actorRef);
    }

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        if (msg instanceof Terminated) {
            //这里简单打印一下，然后停止system
            logger.error(((Terminated) msg).getActor().path() + " has Terminated. now shutdown the system");
            getContext().system().shutdown();
        } else {
            unhandled(msg);
        }

    }
}

