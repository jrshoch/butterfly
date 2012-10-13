package team000;

import java.util.Map;

import battlecode.common.RobotController;

/**
 * Implements the functionality of a single state of a robot
 * @author sashko
 */
public interface State {
    
    State run(RobotController rc, Knowledge knowledge);

}
