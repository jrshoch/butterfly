package team000;

import battlecode.common.RobotController;

public class RobotPlayer {

    public static void run(RobotController myRC) {
        State state = new StartState();
        Knowledge knowledge = new Knowledge();
        while (true) {
            state = state.run(myRC, knowledge);
            myRC.yield();
        }
    }
}
