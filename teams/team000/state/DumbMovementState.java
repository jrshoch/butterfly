package team000.state;

import team000.Knowledge;
import team000.MovementAction;
import team000.pathfinder.DumbBugPathfinder;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class DumbMovementState implements State {
	
	private MapLocation goalLocation;
	
	private DumbMovementState (MapLocation goalLocation) {
		this.goalLocation = goalLocation;
	}

	public static DumbMovementState create(MapLocation goalLocation) {
		return new DumbMovementState(goalLocation);
	}

	@Override
	public State run(RobotController rc, Knowledge knowledge) throws GameActionException {
		if (rc.getLocation().isAdjacentTo(goalLocation)) {
			rc.setIndicatorString(0, "Happy :)");
			return this;
		}
		knowledge.pathGoal = goalLocation;
		MovementAction action = DumbBugPathfinder.nextStep(rc, knowledge);
		action.execute(rc);
		return this;
	}
}
