package team000;

import battlecode.common.Direction;

public class Trajectory {
    
    public final int distanceSquared;
    public final Direction direction;
    
    public Trajectory(int distanceSquared, Direction direction) {
        this.distanceSquared = distanceSquared;
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + distanceSquared;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trajectory other = (Trajectory) obj;
        if (direction != other.direction)
            return false;
        if (distanceSquared != other.distanceSquared)
            return false;
        return true;
    }

}
