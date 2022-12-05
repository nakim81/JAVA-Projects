package byow.Core;

public class Room {
    Position TopLeft;
    int height;
    int width;
    Position center;
    public Room(Position TopLeft, int height, int width) {
        this.TopLeft = TopLeft;
        this.height = height;
        this.width = width;
        this.center = new Position(TopLeft.x + Math.floorDiv(this.width - 1, 2), TopLeft.y - Math.floorDiv(this.height - 1, 2));
    }
}
