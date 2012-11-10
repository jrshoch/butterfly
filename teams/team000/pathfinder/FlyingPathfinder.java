package team000.pathfinder;

import team000.Knowledge;
import team000.MovementAction;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class FlyingPathfinder {

    public static MovementAction nextStep(RobotController rc, Knowledge knowledge) {

        MapLocation myLocation = rc.getLocation();
        if (myLocation.equals(knowledge.pathGoal)) {
            return MovementAction.STANDSTILL;
        }

        int dx = knowledge.pathGoal.x - myLocation.x;
        int dy = knowledge.pathGoal.y - myLocation.y;

        Direction desiredDirection = PathfinderUtils.directionFromDeltas(dx, dy);

        return MovementAction.createActionInDirection(desiredDirection, rc);
    }

}
