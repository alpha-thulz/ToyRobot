package za.co.wethinkcode.examples.toyrobot;

import za.co.wethinkcode.examples.toyrobot.position.Position;

import java.util.List;

public class Robot {
    private static final List<String> VALID_COMMANDS = List.of("off", "help", "forward");
    private String name, status;
    private Direction currentDirection;
    private final Position TOP_LEFT = new Position(-100, 200);
    private final Position BOTTOM_RIGHT = new Position(100, -200);
    public static final Position CENTRE = new Position(0,0);
    private Position position;


    public Robot(String name) {
        this.name = name;
        position = CENTRE;
        currentDirection = Direction.NORTH;
        status = "Ready";
    }

    public String getStatus() {
        return status;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isValidCommand(String input) {
        String[] args = input.strip().split(" ");
        return  VALID_COMMANDS.contains(args[0].trim().toLowerCase());
    }

    public boolean handleCommand(String input) {
        if (!isValidCommand(input)) {
            status = "I am not programmed to: " + input;
            return false;
        }

        String[] args = input.strip().split(" ");
        String command = args[0].trim().toLowerCase();

        switch (command) {
            case "off":
                status = "Shutting down...";
                break;
            case "help":
                status = doHelp();
                break;
            case "forward" :
                status = doForward(Integer.parseInt(args[1]));
                break;
            default:
                status = "I am not programmed to: " + command;
        }

        return true;
    }

    private String doHelp() {
        return "I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'";
    }

    private boolean updatePosition(int steps) {
        int newX = position.getX();
        int newY = position.getY();

        if(Direction.NORTH.equals(currentDirection)) {
            newY += steps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            position = new Position(newX, newY);
            return true;
        }
        return false;
    }

    private String doForward(int steps) {
        if (updatePosition(steps)) return "Moved forward by "+ steps +" steps.";
        return "Sorry, I cannot go outside my safe zone.";
    }

    @Override
    public String toString() {
        return String.format("[%d,%d] {%s} %s> %s", position.getX(), position.getY(), currentDirection, name, status);
    }
}
