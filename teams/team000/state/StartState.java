package team000.state;

import team000.Knowledge;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class StartState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) {
        knowledge.pathGoal = rc.senseCapturablePowerNodes()[0];
        return new BuildSomeTowersState();
    }

}
