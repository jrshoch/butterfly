package team000;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;


public class ArchonCalculator {
    
    public static MapLocation getNearestArchonLocation(RobotController rc) {
        MapLocation robotLocation = rc.getLocation();
        MapLocation[] archonLocations = rc.senseAlliedArchons();
        MapLocation closestArchonLocation = null;
        int closestArchonDistanceSquared = Integer.MAX_VALUE;
        for (MapLocation archonLocation : archonLocations) {
            int distanceSquared = robotLocation.distanceSquaredTo(archonLocation);
            if (distanceSquared < closestArchonDistanceSquared) {
                closestArchonLocation = archonLocation;
                closestArchonDistanceSquared = distanceSquared;
            }
        }
        return closestArchonLocation;
    }
    
    public static MapLocation getAverageArchonLocation(RobotController rc) {
        int totalX = 0;
        int totalY = 0;
        MapLocation[] archonLocations = rc.senseAlliedArchons();
        for (MapLocation archonLocation : archonLocations) {
            totalX += archonLocation.x;
            totalY += archonLocation.y;
        }
        int nArchons = archonLocations.length;
        return new MapLocation(totalX / nArchons, totalY / nArchons);
    }

}
