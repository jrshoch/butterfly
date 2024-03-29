package team000.state;

import team000.Knowledge;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class StartState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
        knowledge.pathGoal = rc.senseCapturablePowerNodes()[0];
        if (rc.getType() == RobotType.ARCHON) {
            return new BuildSomeTowersState();
        } else {
            return new SoldierStartState();
        }
    }
}
