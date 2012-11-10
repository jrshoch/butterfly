package team000.pathfinder;

import team000.Vector;
import team000.SenseBoolean;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.TerrainTile;


public class PathfinderUtils {
    
    public enum PathfinderState {
        MOTION_TO_GOAL,
        FOLLOW_BOUNDARY
    }
    
    public static Direction directionFromDeltas(int dx, int dy) {
        return (new Vector(dx, dy)).direction();
    }
    
    public static SenseBoolean isOffMap(MapLocation location, RobotController rc) {
    	TerrainTile terrainTile = rc.senseTerrainTile(location);
    	if (terrainTile == null) {
    		return SenseBoolean.CANNOT_SENSE;
    	}
    	return SenseBoolean.create(terrainTile == TerrainTile.OFF_MAP);
    }
}
