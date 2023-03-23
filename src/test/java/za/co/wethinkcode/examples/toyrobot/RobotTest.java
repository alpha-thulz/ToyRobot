package za.co.wethinkcode.examples.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.toyrobot.position.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RobotTest {
    @Test
    void isValidCommand() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.isValidCommand("forward"));
        assertTrue(robot.isValidCommand("FORWARD"));
        assertTrue(robot.isValidCommand("forward 10"));
        assertTrue(robot.isValidCommand("off"));
        assertTrue(robot.isValidCommand("off "));
        assertTrue(robot.isValidCommand("help"));
        assertTrue(robot.isValidCommand(" HELP  "));
        assertFalse(robot.isValidCommand("random"));
    }

    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] {NORTH} CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand("off"));
    }

    @Test
    void forward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand("forward 10"));

        Position expected = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);

        assertEquals(expected, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void forwardforward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand("forward 10"));
        assertTrue(robot.handleCommand("forward 5"));
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand("forward 1000"));
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void help() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand("help"));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'", robot.getStatus());
    }

    @Test
    public void shouldKnowDirection() {
        Robot robot = new Robot("HAL");
        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }
}
