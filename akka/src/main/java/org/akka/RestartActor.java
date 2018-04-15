package org.akka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class RestartActor extends UntypedActor {
    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);


    public static enum Msg {
        Starting, Working, CLOSE, RESTART;
    }


    @Override
    public void preStart() {
        logger.info("RestartActor starting.");
    }

    @Override
    public void postStop() throws Exception {
        logger.info("RestartActor stoping..");
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        System.out.println("RestartActor received msg :" + msg.toString());
        if (msg == RestartActor.Msg.Starting) {
            logger.info(" starting ");
        } else if (msg == RestartActor.Msg.Working) {
            logger.info(" working");
        } else if (msg == RestartActor.Msg.CLOSE) {
            logger.info(" stoping");
            getSender().tell(RestartActor.Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else if (msg == RestartActor.Msg.RESTART) {
            int x = 1 / 0;
            //抛出异常，默认会被restart，但这里会resume
            //double a = 1/0;

        } else {
            unhandled(msg);
        }
    }


    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart 0   hashCode=" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart 1  hashCode=" + this.hashCode());
    }
}