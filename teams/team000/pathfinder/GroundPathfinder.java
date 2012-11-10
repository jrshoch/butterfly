package team000.pathfinder;

import team000.Knowledge;
import team000.MovementAction;
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
        	return MovementAction.STANDSTILL;
        }

        int dx = knowledge.pathGoal.x - myLocation.x;
        int dy = knowledge.pathGoal.y - myLocation.y;

        Direction desiredDirection = PathfinderUtils.directionFromDeltas(dx, dy);

        // move towards goal if we can
        while (!rc.canMove(desiredDirection)) {
            GameObject objectInDesiredDirection = rc.senseObjectAtLocation(myLocation.add(desiredDirection), RobotLevel.ON_GROUND);
            if(objectInDesiredDirection != null && objectInDesiredDirection.getTeam() == rc.getTeam()) {
            	return MovementAction.STANDSTILL;
            }
            desiredDirection = desiredDirection.rotateRight();
        }

        return MovementAction.createActionInDirection(desiredDirection, rc);
    }
}
