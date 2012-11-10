package team000;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class MovementAction {

	public static enum Action {
		FORWARD, NONE, BACKWARD, TURN
	}

	public final Action action;
	public final Direction direction;

	public static final MovementAction STANDSTILL = new MovementAction(Action.NONE,
			Direction.NONE);

	public MovementAction(Action action, Direction direction) {
		this.action = action;
		this.direction = direction;
	}

	/**
	 * Returns a turn if not facing in the correct direction, or
	 * FORWARD\BACKWARD if appropriate
	 * 
	 * @param direction
	 *            desired direction of movement
	 * @param rc
	 * @return corresponding action
	 */
	public static MovementAction createActionInDirection(Direction direction, RobotController rc) {
		Direction robotDirection = rc.getDirection();
		if (direction == robotDirection) {
			return new MovementAction(Action.FORWARD, direction);
		} else if (direction == robotDirection.opposite()) {
			return new MovementAction(Action.BACKWARD, direction);
		} else {
			return new MovementAction(Action.TURN, direction);
		}
	}

	public void execute(RobotController rc) throws GameActionException {
		if (rc.isMovementActive()) {
			return;
		}
		switch (action) {
			case FORWARD:
				rc.moveForward();
				break;
			case BACKWARD:
				rc.moveBackward();
				break;
			case TURN:
				rc.setDirection(direction);
				break;
			default:
				// Stand still.
				break;
		}
	}
}