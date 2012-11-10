package team000;

import java.util.HashSet;
import java.util.Set;

import team000.pathfinder.Orientation;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Knowledge {

	public MapLocation pathGoal;
	public Set<MapLocation> enemyTowers = new HashSet<MapLocation>();

	// Whether or not dumb bugging path-finding algorithm is currently tracing.
	public boolean tracing;
	public Direction tracingDirection;
	public Orientation tracingOrientation;
	public int tracingRotations;
	public Direction tracingDesiredDirection;
	public int tracingRotationThreshold;

	public void updateTracingInformation(Direction newDirection, int rotations, RobotController rc) {
		tracingDirection = newDirection;
		tracingRotations += rotations;
		Direction desiredDirection = rc.getLocation().directionTo(pathGoal);
		if (desiredDirection != tracingDesiredDirection) {
			if (desiredDirection == tracingOrientation.nextDirection(tracingDesiredDirection)) {
				tracingRotationThreshold -= 1;
			} else if (desiredDirection == tracingOrientation
					.previousDirection(tracingDesiredDirection)) {
				tracingRotationThreshold += 1;
			} else {
				throw new RuntimeException("Hocho: tracing desired direction jumped?");
			}
			tracingDesiredDirection = desiredDirection;
		}
		if (rc.canMove(desiredDirection) && tracingRotations <= tracingRotationThreshold) {
			tracing = false;
		}
		rc.setIndicatorString(1, "tracing: " + tracing + ", tracingDirection: " + tracingDirection
				+ ", tracingOrientation: " + tracingOrientation + ", tracingRotations: "
				+ tracingRotations + ", tracingDesiredDirection: " + tracingDesiredDirection
				+ ", tracingRotationsThreshold: " + tracingRotationThreshold);
	}

	public void startTracing(Orientation orientation, Direction desiredDirection) {
		tracing = true;
		tracingOrientation = orientation;
		tracingDirection = tracingOrientation.nextDirection(desiredDirection);
		tracingRotations = 1;
		tracingDesiredDirection = desiredDirection;
		tracingRotationThreshold = 0;
	}
}
