package org.akka.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class FutureMain {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("FutureMain" );
        ActorRef futureActor = system.actorOf(Props.create(FutureActor.class), "FutureActor");
        ActorRef workerActor = system.actorOf(Props.create(WorkerActor.class), "WorkerActor");

        //等等future返回
        Future<Object> future = Patterns.ask(workerActor, "1", 1000);
        int result = (int) Await.result(future, Duration.create(3, TimeUnit.SECONDS));
        System.out.println("result:" + result);

        //不等待返回值，直接重定向到其他actor，有返回值来的时候将会重定向到printActor
        Future<Object> future1 = Patterns.ask(workerActor, 3, 1000);
        Patterns.pipe(future1, system.dispatcher()).to(futureActor);


        workerActor.tell(PoisonPill.getInstance(), ActorRef.noSender()); //stop
    }
}
