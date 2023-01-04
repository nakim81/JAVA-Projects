package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class Game {
    private TETile[][] WORLD;
    private Random RANDOM;
    private long SEED;
    private Position MARIO;
    private int numCoins;
    private int numDoors;
    private final ArrayList<Room> roomList = new ArrayList<>();

    public Game(long seed) {
        this.RANDOM = new Random(seed);
        this.WORLD = new TETile[WIDTH][HEIGHT];
        this.SEED = seed;
    }

    public void displayRoom(Position p, int width, int height) {
        int x = p.x;
        int y = p.y;
        Position leftTop = new Position(x, y - 1);
        Position rightTop = new Position(x + width - 1, y - 1);
        Position leftBottom = new Position(x, y - height + 1);
        if (checker(p, width, 0) && checker(leftTop, 0,height - 2) && checker(rightTop,  0,height - 1) && checker(leftBottom, width, 0)) {
            for (int i = 0; i < width; i += 1) {
                WORLD[p.x + i][p.y] = Tileset.WALL;
                WORLD[leftBottom.x + i][leftBottom.y] = Tileset.WALL;
            }
            for (int i = 0; i < height - 2; i += 1) {
                WORLD[leftTop.x][leftTop.y - i] = Tileset.WALL;
            }
            for (int i = 0; i < height - 1; i += 1) {
                WORLD[rightTop.x][rightTop.y - i] = Tileset.WALL;
            }
            for (int i = p.x + 1; i < p.x + width - 1; i += 1) {
                for (int j = p.y - 1; j > p.y - height + 1; j -= 1) {
                    WORLD[i][j] = Tileset.FLOOR;
                }
            }

            Room room = new Room(p, height, width);
            roomList.add(room);

            if (roomList.size() > 1) {
                Room prevRoom = roomList.get(roomList.size() - 2);
                horizontalHallway(prevRoom.center.x, room.center.x, prevRoom.center.y);
                verticalHallway(room.center.x, prevRoom.center.y, room.center.y);
            }
        }
    }

    public void horizontalHallway(int x1, int x2, int y) {
        int x;
        for (int i = 1; i < Math.abs(x1 - x2); i += 1) {
            if ((x1 - x2) < 0) {
                x = x1 + i;
            } else {
                x = x1 - i;
            }
            if (WORLD[x][y + 1].equals(Tileset.FLOOR) && WORLD[x][y - 1].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
            } else if (WORLD[x][y + 1].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y - 1] = Tileset.WALL;
            } else if (WORLD[x][y - 1].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y + 1] = Tileset.WALL;
            } else {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y + 1] = Tileset.WALL;
                WORLD[x][y - 1] = Tileset.WALL;
            }
        }
    }

    public void verticalHallway(int x, int y1, int y2) {
        Position pos;
        int y;
        String direction;
        for (int i = 0; i < Math.abs(y1 - y2); i += 1) {
            if ((y1 - y2) < 0) {
                y = y1 + i;
                direction = "up";
            } else {
                y = y1 - i;
                direction = "down";
            }
            pos = new Position(x, y);
            if (WORLD[x + 1][y].equals(Tileset.FLOOR) && WORLD[x - 1][y].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
            } else if (WORLD[pos.x + 1][pos.y].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x - 1][y] = Tileset.WALL;
                intersectionHallway(pos, direction);
            } else if (WORLD[x - 1][y].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x + 1][y] = Tileset.WALL;
                intersectionHallway(pos, direction);
            } else {
                WORLD[x + 1][y] = Tileset.WALL;
                WORLD[x - 1][y] = Tileset.WALL;
                WORLD[x][y] = Tileset.FLOOR;
            }
        }
    }

    public void intersectionHallway(Position p, String direction) {
        int x = p.x;
        int y = p.y;
        if (direction.equals("up")) {
            if (WORLD[x - 1][y - 1].equals(Tileset.FLOOR)) {
                WORLD[x + 1][y - 1] = Tileset.WALL;
            } else if (WORLD[x + 1][y - 1].equals(Tileset.FLOOR)) {
                WORLD[x - 1][y - 1] = Tileset.WALL;
            } else {
                WORLD[x][y - 1] = Tileset.WALL;
                WORLD[x - 1][y - 1] = Tileset.WALL;
                WORLD[x + 1][y - 1] = Tileset.WALL;
            }
        } else if (direction.equals("down")) {
            if (WORLD[x - 1][y + 1].equals(Tileset.FLOOR)) {
                WORLD[x + 1][y - 1] = Tileset.WALL;
            } else if (WORLD[x + 1][y + 1].equals(Tileset.FLOOR)) {
                WORLD[x - 1][y - 1] = Tileset.WALL;
            } else {
                WORLD[x][y + 1] = Tileset.WALL;
                WORLD[x - 1][y + 1] = Tileset.WALL;
                WORLD[x + 1][y + 1] = Tileset.WALL;
            }
        }
    }

    public boolean checker(Position p , int width, int height) {
        if (height == 0) {
            int x = p.x;
            int y = p.y;
            boolean canDraw = true;
            for (int i = 0; i < width; i += 1) {
                if (x + i + 3 > WIDTH) {
                    canDraw = false;
                    return canDraw;
                }
                if (WORLD[x + i][y].equals(Tileset.WALL) || WORLD[x + i][y].equals(Tileset.UNLOCKED_DOOR)) {
                    canDraw = false;
                }
            }
            return canDraw;
        }
        if (width == 0) {
            int x = p.x;
            int y = p.y;
            boolean canDraw = true;
            for (int i = 0; i < height; i += 1) {
                if (y - i < 3) {
                    canDraw = false;
                    return canDraw;
                }
                if (WORLD[x][y - i].equals(Tileset.WALL) || WORLD[x][y - i].equals(Tileset.UNLOCKED_DOOR)) {
                    canDraw = false;
                }
            }
            return canDraw;
        }
        return true;
    }

    public void worldGenerate() {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                WORLD[x][y] = Tileset.NOTHING;
            }
        }
        for (int i = 1000; i > 0; i -= 1) {
            int width = 5 + RANDOM.nextInt(10);
            int height = 5 + RANDOM.nextInt(10);
            int x = 3 + RANDOM.nextInt(WIDTH - 13);
            int y = 3 + RANDOM.nextInt(HEIGHT - 6);
            Position p = new Position(x, y);
            displayRoom(p, width, height);
        }
        boolean mario = false;
        while (!mario) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            if (WORLD[x][y].equals(Tileset.FLOOR)) {
                Position mario_pos = new Position(x, y);
                WORLD[x][y] = Tileset.MARIO;
                this.MARIO = mario_pos;
                mario = true;
            }
        }
        int count = 0;
        numDoors = 3 + RANDOM.nextInt(2);
        while (count < numDoors) {
            boolean door = false;
            while (!door) {
                int x = RANDOM.nextInt(WIDTH);
                int y = RANDOM.nextInt(HEIGHT);
                if (WORLD[x][y].equals(Tileset.FLOOR)) {
                    WORLD[x][y] = Tileset.DOOR;
                    door = true;
                }
            }
            count += 1;
        }
    }

    public void newInterface() {
        Random r = new Random();
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                WORLD[x][y] = Tileset.NOTHING;
            }
        }
        for (int i = 25; i < 56; i += 1) {
            for (int j = 0; j < 10; j += 1) {
                WORLD[i][j] = Tileset.WALL;
            }
        }
        for (int i = 26; i < 55; i += 1) {
            for (int j = 1; j < 9; j += 1) {
                WORLD[i][j] = Tileset.FLOOR;
            }
        }
        boolean mario = false;
        while (!mario) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            if (WORLD[x][y].equals(Tileset.FLOOR)) {
                Position mario_pos = new Position(x, y);
                WORLD[x][y] = Tileset.MARIO;
                this.MARIO = mario_pos;
                mario = true;
            }
        }
        int count = 0;
        numCoins = 5 + r.nextInt(5);
        while (count < numCoins) {
            boolean coin = false;
            while (!coin) {
                int x = r.nextInt(WIDTH);
                int y = r.nextInt(HEIGHT);
                if (WORLD[x][y].equals(Tileset.FLOOR)) {
                    WORLD[x][y] = Tileset.COIN;
                    coin = true;
                }
            }
            count += 1;
        }
    }

    public int randomNum() {
        return RANDOM.nextInt(100);
    }

    public long getSEED() {
        return SEED;
    }

    public TETile[][] getWORLD() {
        return this.WORLD;
    }

    public Position getMARIO() {
        return this.MARIO;
    }
    public int getNumCoins() {
        return numCoins;
    }

    public int getNumDoors() {
        return numDoors;
    }
}
