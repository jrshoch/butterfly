package team000;

import battlecode.common.Direction;


public class PathfinderUtils {
    
    public enum PathfinderState {
        MOTION_TO_GOAL,
        FOLLOW_BOUNDARY
    }
    
    public static Direction directionFromDeltas(int dx, int dy) {
        if (dx < 0) {
            if (dy < 0) {
                return Direction.NORTH_WEST;
            } else if (dy > 0) {
                return Direction.SOUTH_WEST;
            } else {
                return Direction.WEST;
            }
        } else if (dx > 0) {
            if (dy < 0) {
                return Direction.NORTH_EAST;
            } else if (dy > 0) {
                return Direction.SOUTH_EAST;
            } else {
                return Direction.EAST;
            }
        } else {
            if (dy < 0) {
                return Direction.NORTH;
            } else if (dy > 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NONE;
            }
        }
    }
}
