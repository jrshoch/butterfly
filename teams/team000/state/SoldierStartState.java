package team000.state;

import team000.Knowledge;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotLevel;

public class SoldierStartState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
        if (!rc.isMovementActive() && rc.getFlux() > rc.getType().moveCost) {
            if (rc.canMove(rc.getDirection())) {
                rc.moveForward();
            } else {
                rc.setDirection(rc.getDirection().rotateRight());
            }
        }

        if (!rc.isAttackActive()) {
            Robot[] robots = rc.senseNearbyGameObjects(Robot.class);
            for (Robot r : robots) {
                if (r.getTeam() == rc.getTeam().opponent()) {
                    MapLocation l = rc.senseLocationOf(r);
                    if (rc.canAttackSquare(l)) {
                        rc.attackSquare(l, r.getRobotLevel());
                        break;
                    }
                }
            }
        }

        return new SoldierStartState();
    }

}
