package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class Map {

    private List<Room> roomlist = new ArrayList<>();

    public TETile[][] makemap() {
        Random rand = new Random();
        int Room_Numbers = 5 + rand.nextInt(10);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        int i = 0;
        while (i < Room_Numbers) {
            int width = 6 + rand.nextInt(4);
            int height = 6 + rand.nextInt(4);
            int starting_width = rand.nextInt(WIDTH - width - 1);
            int starting_height = rand.nextInt(HEIGHT - height - 1);
            Position position = new Position(starting_width, starting_height);
            Room room = new Room(position, width, height);
            if (candraw(room, finalWorldFrame)) {
                for (int x = starting_width; x < starting_width + width; x += 1) {
                    for (int y = starting_height; y < starting_height + height; y += 1) {
                        finalWorldFrame[x][y] = Tileset.WALL;
                    }
                }
                for (int x = starting_width + 1; x < starting_width + width - 1; x += 1) {
                    for (int y = starting_height + 1; y < starting_height + height - 1; y += 1) {
                        finalWorldFrame[x][y] = Tileset.FLOOR;
                    }
                }
                roomlist.add(room);
                // make hallway

            }
            i += 1;
        }
        return finalWorldFrame;
    }

//    public TETile[][] drawHallway(Room room, Room room2, TETile world) {
//
//    }

    private static boolean candraw(Room room, TETile[][] world) {
        int starting_width = room.getBottomLeftCorner(room).x;
        int starting_height = room.getBottomLeftCorner(room).y;
        int width = room.getBottomRightCorner(room).x - starting_width;
        int height = room.getTopLeftCorner(room).y - starting_height;

        for (int x = starting_width; x < starting_width + width; x += 1) {
            for (int y = starting_height; y < starting_height + height; y += 1) {
                TETile checker = world[x][y];
                if (checker.equals(Tileset.FLOOR) || checker.equals(Tileset.WALL) || checker.equals(Tileset.UNLOCKED_DOOR)) {
                    return false;
                }
            }
        }
        return true;
    }

//    public Room distance(Room room1, List<Room> roomList) {
//        if (roomList == null) {
//            return null;
//        }
//        Room minRoom = null;
//        double distance = 100.0;
//        for (Room list : roomList) {
//            int x = room1.getCenter(room1).x;
//            int y = room1.getCenter(room1).y;
//            int x2 = list.getCenter(list).x;
//            int y2 = list.getCenter(list).y;
//            double dist = Math.sqrt((x-x2) * (x-x2) + (y-y2) * (y-y2));
//            if (dist < distance) {
//                distance = dist;
//                minRoom = list;
//            }
//        }
//        return minRoom;
//    }
}
