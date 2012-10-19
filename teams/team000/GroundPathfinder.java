package team000;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotLevel;

public class GroundPathfinder {

    public static MovementAction nextStep(RobotController rc, Knowledge knowledge) throws GameActionException {
        MapLocation myLocation = rc.getLocation();
        if (myLocation.equals(knowledge.pathGoal)) {
            return new MovementAction(0, Direction.NONE);
        }

        int dx = knowledge.pathGoal.x - myLocation.x;
        int dy = knowledge.pathGoal.y - myLocation.y;

        Direction desiredDirection = PathfinderUtils.directionFromDeltas(dx, dy);

        // move towards goal if we can
        while (!rc.canMove(desiredDirection)) {
            GameObject objectInDesiredDirection = rc.senseObjectAtLocation(myLocation.add(desiredDirection), RobotLevel.ON_GROUND);
            if(objectInDesiredDirection != null && objectInDesiredDirection.getTeam() == rc.getTeam()) {
                return new MovementAction(0, Direction.NONE);
            }
            desiredDirection = desiredDirection.rotateRight();
        }

        if (desiredDirection == rc.getDirection()) {
            return new MovementAction(1, Direction.NONE);
        } else {
            return new MovementAction(0, desiredDirection);
        }
    }
}
