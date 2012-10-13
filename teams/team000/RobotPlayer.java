package team000;

import battlecode.common.RobotController;

public class RobotPlayer {

    public static void run(RobotController myRC) {
        State state = new StartState();
        Knowledge knowledge = new Knowledge();
        while (true) {
            try {
                state = state.run(myRC, knowledge);
                myRC.yield();
            } catch (Exception e) {
                e.printStackTrace();
                myRC.yield();
            }
        }
    }
}
