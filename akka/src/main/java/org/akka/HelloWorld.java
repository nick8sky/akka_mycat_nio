package org.akka;

import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class HelloWorld extends UntypedActor {
    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);


    public static enum Msg{
        Starting, Working, CLOSE;
    }


    @Override
    public void preStart() {
        logger.info("HelloWorld starting.");
    }

    @Override
    public void postStop() throws Exception {
        logger.info("HelloWorld stoping..");
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        System.out.println("HelloWorld received msg :"+ msg.toString());
        if(msg == Msg.Starting){
            logger.info(" starting ");
        }else if(msg == Msg.Working){
            logger.info(" working");
        }else if(msg == Msg.CLOSE){
            logger.info(" stoping");
            getSender().tell(new Terminated(getSelf(),false,false), getSelf());
            //getSender().tell("STOP", getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(msg);
        }
    }

}
