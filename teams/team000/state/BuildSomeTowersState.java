package team000.state;

import java.util.Arrays;
import java.util.List;

import team000.CollectionUtils;
import team000.Knowledge;
import team000.MovementAction;
import team000.pathfinder.GroundPathfinder;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotLevel;
import battlecode.common.RobotType;

public class BuildSomeTowersState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
        List<MapLocation> potentialLocations = CollectionUtils.subtract(
                Arrays.asList(rc.senseCapturablePowerNodes()), knowledge.enemyTowers);
        if (potentialLocations.size() > 0) {
            knowledge.pathGoal = potentialLocations.get(0);
        }

        if (!rc.isMovementActive()) {

            if (rc.canSenseSquare(knowledge.pathGoal)) {
                GameObject objectAtLocation = rc.senseObjectAtLocation(knowledge.pathGoal,
                        RobotLevel.ON_GROUND);
                if (objectAtLocation != null
                        && objectAtLocation.getTeam().equals(rc.getTeam().opponent())) {
                    knowledge.enemyTowers.add(knowledge.pathGoal);
                }
            }

            potentialLocations = CollectionUtils.subtract(
                    Arrays.asList(rc.senseCapturablePowerNodes()), knowledge.enemyTowers);
            if (potentialLocations.size() > 0) {
                knowledge.pathGoal = potentialLocations.get(0);
            }

            // first case: need to keep moving
            if (!rc.getLocation().isAdjacentTo(knowledge.pathGoal)) {
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
            }
            // second case: need to build tower
            else {
                if (rc.getFlux() >= RobotType.TOWER.spawnCost
                        && rc.senseObjectAtLocation(rc.getLocation().add(rc.getDirection()),
                                RobotLevel.ON_GROUND) == null) {
                    rc.spawn(RobotType.TOWER);
                    knowledge.pathGoal = rc.senseCapturablePowerNodes()[rc.getRobot().getID()
                            % rc.senseCapturablePowerNodes().length];
                }
            }
        }
        return this;
    }

}
