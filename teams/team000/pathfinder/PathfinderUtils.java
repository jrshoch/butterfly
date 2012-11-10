package team000.pathfinder;

import team000.Vector;
import battlecode.common.Direction;


public class PathfinderUtils {
    
    public enum PathfinderState {
        MOTION_TO_GOAL,
        FOLLOW_BOUNDARY
    }
    
    public static Direction directionFromDeltas(int dx, int dy) {
        return (new Vector(dx, dy)).direction();
    }
}
