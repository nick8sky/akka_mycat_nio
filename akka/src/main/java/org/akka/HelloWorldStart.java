package org.akka;

import akka.actor.*;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HelloWorldStart {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("helloWorld");
        ActorRef helloWorld = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        //ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, helloWorld), "WatchActor");
        //System.out.println(helloWorld.path());


        //helloWorld.tell(HelloWorld.Msg.Starting, ActorRef.noSender());
        //helloWorld.tell(HelloWorld.Msg.Working, ActorRef.noSender());

        //中断myWork
        //helloWorld.tell(PoisonPill.getInstance(), ActorRef.noSender());
        //helloWorld.tell(HelloWorld.Msg.CLOSE, ActorRef.noSender());


        Inbox inbox = Inbox.create(system);
        inbox.watch(helloWorld);//监听一个actor
        inbox.send(helloWorld, HelloWorld.Msg.Starting);

        inbox.send(helloWorld, HelloWorld.Msg.CLOSE);
        Object receive = null;
        for (; ; ) {
            try {
                receive = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
                System.out.println(receive);
                if(receive instanceof  Terminated) {//中断 ，和线程一个概念
                    System.out.println("================");
                    system.shutdown();
                    break;
                }
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }
}


