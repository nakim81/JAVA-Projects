package byow.Core;

public class Room {
    private Position bottomLeftCorner;
    private Position bottomRightCorner;
    private Position topLeftCorner;
    private Position topRightCorner;
    private Position center;

    public Room(Position pos, int width, int height) {
        bottomLeftCorner = new Position(pos.x, pos.y);
        bottomRightCorner = new Position(pos.x + width, pos.y);
        topLeftCorner = new Position(pos.x, pos.y + height);
        topRightCorner = new Position(pos.x + width, pos.y + height);
        center = new Position(pos.x + (width / 2), pos.y + (height / 2));
    }

    Position getBottomLeftCorner(Room room) {
        return bottomLeftCorner;
    }

    Position getBottomRightCorner(Room room) {
        return bottomRightCorner;
    }

    Position getTopLeftCorner(Room room) {
        return topLeftCorner;
    }

    Position getTopRightCorner(Room room) {
        return topRightCorner;
    }

    Position getCenter(Room room) {
        return center;
    }
}
