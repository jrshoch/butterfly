package team000.state;

import team000.ArchonCalculator;
import team000.Knowledge;
import team000.TeamConstants;
import team000.Vector;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class ArchonFlockTestState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {

        if (!rc.isMovementActive()) {
            System.out.println(TeamConstants.FLOCKING_DETECTION_RANGE);

            // head towards this location
            MapLocation goodAvg = ArchonCalculator.getAverageArchonLocationWithin(rc,
                    TeamConstants.FLOCKING_MIN, TeamConstants.FLOCKING_DETECTION_RANGE);

            // head away from this location
            MapLocation badAvg = ArchonCalculator.getAverageArchonLocationWithin(rc, 0,
                    TeamConstants.FLOCKING_MIN);

            Vector toGoodAvg = Vector.fromAToB(rc.getLocation(), goodAvg);
            Vector toBadAvg = Vector.fromAToB(rc.getLocation(), badAvg);

            Direction flockDirection = toGoodAvg.plus(toBadAvg.inverse()).direction();

            if(flockDirection == Direction.NONE || flockDirection == Direction.OMNI) {
                flockDirection = rc.getDirection();
            }
            
            if (rc.canMove(flockDirection)) {
                if (rc.getDirection() == flockDirection) {
                    rc.moveForward();
                } else {
                    rc.setDirection(flockDirection);
                }
            } else {
                rc.setDirection(rc.getDirection().rotateRight());
            }
        }

        return this;
    }

}
