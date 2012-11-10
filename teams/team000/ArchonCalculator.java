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

    /**
     * Get the average location of all archons on the map
     */
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

    /**
     * Get the average location of all archons within minRadiusSquared and
     * maxRadiusSquared
     */
    public static MapLocation getAverageArchonLocationWithin(RobotController rc,
            int minRadiusSquared, int maxRadiusSquared) {
        int totalX = 0;
        int totalY = 0;
        MapLocation[] archonLocations = rc.senseAlliedArchons();
        int numArchonsWithinDistance = 0;
        for (MapLocation archonLocation : archonLocations) {
            int d = rc.getLocation().distanceSquaredTo(archonLocation);
            if (d >= minRadiusSquared && d <= maxRadiusSquared) {
                totalX += archonLocation.x;
                totalY += archonLocation.y;
                numArchonsWithinDistance++;

            }
        }
        if (numArchonsWithinDistance > 0) {
            return new MapLocation(totalX / numArchonsWithinDistance, totalY
                    / numArchonsWithinDistance);
        } else {
            return rc.getLocation();
        }
    }

}
