package team000;

import battlecode.common.RobotController;

/**
 * Implements the functionality of a single state of a robot
 * 
 * @author sashko
 */
public interface State {

    public State run(RobotController rc, Knowledge knowledge);

}
