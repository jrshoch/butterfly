package team000;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class FlyingPathfinder {

    public static class MovementAction {
        public int movement;
        public Direction direction;
        public MovementAction(int movement, Direction direction) {
            this.movement = movement;
            this.direction = direction;
        }
    }
    
    private static Direction directionFromDeltas(int dx, int dy) {
        if(dx < 0) {
            if(dy < 0) {
                return Direction.NORTH_WEST;
            } else if (dy > 0) {
                return Direction.SOUTH_WEST;
            } else {
                return Direction.WEST;
            }
        } else if (dx > 0) {
            if(dy < 0) {
                return Direction.NORTH_EAST;
            } else if (dy > 0) {
                return Direction.SOUTH_EAST;
            } else {
                return Direction.EAST;
            }
        } else {
            if(dy < 0) {
                return Direction.NORTH;
            } else if (dy > 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NONE;
            }
        }
    }
    
    public static MovementAction nextStep(RobotController rc, Knowledge knowledge) {
        
        MapLocation myLocation = rc.getLocation();
        if(myLocation.equals(knowledge.pathGoal)) {
            return new MovementAction(0, Direction.NONE);
        }
        
        int dx = knowledge.pathGoal.x - myLocation.x;
        int dy = knowledge.pathGoal.y - myLocation.y;
        
        Direction desiredDirection = directionFromDeltas(dx, dy);
        
        if(desiredDirection == rc.getDirection()) {
            return new MovementAction(1, Direction.NONE);
        } else {
            return new MovementAction(0, desiredDirection);
        }
    }
    
}
