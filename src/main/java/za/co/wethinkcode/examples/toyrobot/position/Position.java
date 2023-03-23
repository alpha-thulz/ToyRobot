package za.co.wethinkcode.examples.toyrobot.position;

public class Position {
    private final int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        if (x != position.x) return false;
        return y == position.y;
    }

    public boolean isIn (Position topLeft, Position bottomRight) {
        boolean withinTop = y <= topLeft.getY();
        boolean withinBottom = y >= bottomRight.getY();
        boolean withinLeft = x >= topLeft.getX();
        boolean withinRight = x <= bottomRight.getX();
        return withinBottom && withinRight && withinLeft && withinTop;
    }
}
