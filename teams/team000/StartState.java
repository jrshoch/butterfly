package team000;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class StartState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) {
        knowledge.pathGoal = rc.senseCapturablePowerNodes()[0];
        return new BuildSomeTowersState();
    }

}
