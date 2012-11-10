package team000.pathfinder;

import team000.Knowledge;
import team000.MovementAction;
import team000.MovementAction.Action;
import team000.OurConstants;
import team000.SenseBoolean;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotLevel;
import battlecode.common.TerrainTile;

public class DumbBugPathfinder {

	private static MovementAction nextTracingStep(RobotController rc, Knowledge knowledge) {
		MapLocation robotLocation = rc.getLocation();
		Orientation tracingOrientation = knowledge.tracingOrientation;
		Direction potentialDirection = tracingOrientation
				.previousDirection(knowledge.tracingDirection);
		if (PathfinderUtils.isOffMap(robotLocation.add(potentialDirection), rc) ==
				SenseBoolean.TRUE) {
			return knowledge.switchTracingOrientation(rc);
		}
		// TODO: pre-cache arrays of 8 directions starting at each
		// direction?
		for (int rotations = -1; rotations < OurConstants.NUMBER_OF_DIRECTIONS - 1; rotations++) {
			if (rc.canMove(potentialDirection)) {
				knowledge.updateTracingInformation(potentialDirection, rotations, rc);
				return MovementAction.createActionInDirection(potentialDirection, rc);
			}
			potentialDirection = tracingOrientation.nextDirection(potentialDirection);
		}
		// Can't move in any direction?
		return MovementAction.STANDSTILL;
	}

	public static MovementAction nextStep(RobotController rc, Knowledge knowledge) {
		// Proceed towards target until a terrain obstacle is hit
		// Bug clockwise around it until you've cleared it
		// TODO: Change direction if you hit map edge
		// TODO: randomly pick between clockwise/counterclockwise
		// TODO: handle robot collision
		rc.setIndicatorString(0, "path goal: " + knowledge.pathGoal);
		if (knowledge.tracing) {
			return nextTracingStep(rc, knowledge);
		}
		MapLocation robotLocation = rc.getLocation();
		Direction desiredDirection = robotLocation.directionTo(knowledge.pathGoal);
		// Not tracing, just try to move towards target
		if (rc.canMove(desiredDirection)) {
			return MovementAction.createActionInDirection(desiredDirection, rc);
		}
		MapLocation nextLocation = robotLocation.add(desiredDirection);
		if (!rc.canSenseSquare(nextLocation)) {
			// Can't sense the square, so turn towards it
			return new MovementAction(Action.TURN, desiredDirection);
		}
		RobotLevel robotLevel = rc.getRobot().getRobotLevel();
		if (robotLevel == RobotLevel.ON_GROUND) {
			TerrainTile nextTile = rc.senseTerrainTile(nextLocation);
			if (!nextTile.isTraversableAtHeight(RobotLevel.ON_GROUND)) {
				// Terrain in the way
				// start tracing
				return startTracing(rc, knowledge, desiredDirection);
			}
		}
		// Robot in the way
		GameObject blockingObject;
		try {
			blockingObject = rc.senseObjectAtLocation(nextLocation, robotLevel);
		} catch (GameActionException e) {
			System.out.println("Hocho: should be able to sense location " + nextLocation + " from "
					+ robotLocation);
			e.printStackTrace();
			return new MovementAction(Action.TURN, desiredDirection);
		}
		if (blockingObject instanceof Robot) {
			Robot blockingRobot = (Robot) blockingObject;
			if (blockingRobot.getRobotLevel() != robotLevel) {
				throw new RuntimeException(
						"Hocho: wasn't expecting robot of a different level to block.");
			}
		} else {
			throw new RuntimeException("Hocho: wasn't expecting non-Robot GameObject to block");
		}
		// for now we'll just trace, in the future we should handle this
		// better
		// start tracing
		return startTracing(rc, knowledge, desiredDirection);
	}

	private static MovementAction startTracing(RobotController rc, Knowledge knowledge,
			Direction desiredDirection) {
		Orientation tracingOrientation = OurConstants.TRACING_START_DIRECTION;
		knowledge.startTracing(tracingOrientation, desiredDirection);
		return nextTracingStep(rc, knowledge);
	}
}
