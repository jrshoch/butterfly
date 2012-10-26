package team000;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.RobotController;
import battlecode.common.RobotLevel;
import battlecode.common.RobotType;

public class BuildSomeTowersState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
        if (!rc.isMovementActive() && !rc.getLocation().isAdjacentTo(knowledge.pathGoal)) {
            knowledge.pathGoal = rc.senseCapturablePowerNodes()[rc.getRobot().getID()%rc.senseCapturablePowerNodes().length];
            MovementAction ma = GroundPathfinder.nextStep(rc, knowledge);
            try {
                if (ma.movement > 0) {
                    rc.moveForward();
                } else if (ma.direction != Direction.NONE && ma.direction != Direction.OMNI) {
                    rc.setDirection(ma.direction);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!rc.isMovementActive() && rc.getLocation().isAdjacentTo(knowledge.pathGoal)) {
            if (rc.getFlux() >= RobotType.TOWER.spawnCost
                    && rc.senseObjectAtLocation(rc.getLocation().add(rc.getDirection()),
                            RobotLevel.ON_GROUND) == null) {
                rc.spawn(RobotType.TOWER);
                knowledge.pathGoal = rc.senseCapturablePowerNodes()[rc.getRobot().getID()%rc.senseCapturablePowerNodes().length];
            }
        }
        return this;
    }

}
