package org.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RestartStart {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("restartActor");

        ActorRef superVisor = system.actorOf(Props.create(SuperVisor.class), "SuperVisor");
        superVisor.tell(Props.create(RestartActor.class), ActorRef.noSender());

        ActorSelection actorSelection = system.actorSelection("akka://org/akka/SuperVisor/restartActor");
        //这是akka的路径。restartActor是在SuperVisor中创建的。

        for(int i = 0 ; i < 100 ; i ++){
            actorSelection.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
        }



    }
}
