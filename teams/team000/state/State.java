package team000.state;

import team000.Knowledge;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

/**
 * Implements the functionality of a single state of a robot
 * 
 * @author sashko
 */
public interface State {
    
    public State run(RobotController rc, Knowledge knowledge) throws GameActionException;

}
