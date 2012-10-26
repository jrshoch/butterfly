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
            return new MovementAction(0, Direction.NONE);
        }

        int dx = knowledge.pathGoal.x - myLocation.x;
        int dy = knowledge.pathGoal.y - myLocation.y;

        Direction desiredDirection = PathfinderUtils.directionFromDeltas(dx, dy);

        if (desiredDirection == rc.getDirection()) {
            return new MovementAction(1, Direction.NONE);
        } else {
            return new MovementAction(0, desiredDirection);
        }
    }

}
