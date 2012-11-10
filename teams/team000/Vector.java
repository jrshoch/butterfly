package team000;

import battlecode.common.Direction;
import battlecode.common.MapLocation;


public class Vector {
    private int x;
    private int y;
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public Vector(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public static Vector fromAToB(MapLocation a, MapLocation b) {
        return new Vector(b.x - a.x, b.y - a.y);
    }
    
    public Vector times(double a) {
        return new Vector((int) (x * a), (int) (y * a));
    }
    
    public Vector plus(Vector b) {
        return new Vector(x+b.x, y+b.y);
    }
    
    public Vector inverse() {
        return new Vector(-x, -y);
    }
    
    public Direction direction() {
        if (x < 0) {
            if (y < 0) {
                return Direction.NORTH_WEST;
            } else if (y > 0) {
                return Direction.SOUTH_WEST;
            } else {
                return Direction.WEST;
            }
        } else if (x > 0) {
            if (y < 0) {
                return Direction.NORTH_EAST;
            } else if (y > 0) {
                return Direction.SOUTH_EAST;
            } else {
                return Direction.EAST;
            }
        } else {
            if (y < 0) {
                return Direction.NORTH;
            } else if (y > 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NONE;
            }
        }
    }
}
