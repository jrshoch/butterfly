package team000;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class PathfinderTestState implements State {

    @Override
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
        if (!rc.isMovementActive()) {
            System.out.println("hello");
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
        return this;
    }

}
