package team000;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class PowerNodeSenser {

    public static MapLocation getClosestCapturablePowerNode(RobotController rc) {
        MapLocation robotLocation = rc.getLocation();
        MapLocation[] capturablePowerNodeLocations = rc.senseCapturablePowerNodes();
        MapLocation closestCapturablePowerNodeLocation = null;
        int closestPowerNodeDistanceSquared = Integer.MAX_VALUE;
        for (MapLocation capturablePowerNodeLocation : capturablePowerNodeLocations) {
            int distanceSquared = robotLocation.distanceSquaredTo(capturablePowerNodeLocation);
            if (distanceSquared < closestPowerNodeDistanceSquared) {
                closestCapturablePowerNodeLocation = capturablePowerNodeLocation;
                closestPowerNodeDistanceSquared = distanceSquared;
            }
        }
        return closestCapturablePowerNodeLocation;
    }
}
