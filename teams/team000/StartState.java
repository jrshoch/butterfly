package team000;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class StartState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) {
        knowledge.pathGoal = new MapLocation(rc.getLocation().x - 40, rc.getLocation().y + 40);
        return new PathfinderTestState();
    }

}
