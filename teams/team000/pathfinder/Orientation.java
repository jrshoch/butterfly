package team000.pathfinder;

import battlecode.common.Direction;

public enum Orientation {
	CLOCKWISE,
	COUNTERCLOCKWISE,
	;
	
	public Direction nextDirection(Direction direction) {
		switch(this) {
			case CLOCKWISE:
				return direction.rotateRight();
			case COUNTERCLOCKWISE:
				return direction.rotateLeft();
			default:
				throw new RuntimeException("Hocho: case not handled in switch statement.");
		}
	}

	public Direction previousDirection(Direction direction) {
		switch(this) {
			case CLOCKWISE:
				return direction.rotateLeft();
			case COUNTERCLOCKWISE:
				return direction.rotateRight();
			default:
				throw new RuntimeException("Hocho: case not handled in switch statement.");
		}
	}
	
	public Orientation opposite() {
		switch(this) {
			case CLOCKWISE:
				return COUNTERCLOCKWISE;
			case COUNTERCLOCKWISE:
				return CLOCKWISE;
			default:
				throw new RuntimeException("Hocho: case not handled in switch statement.");
		}
	}
}
