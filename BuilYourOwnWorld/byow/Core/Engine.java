package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private Game game;
    public File POSITION = new File("Position.txt");
    public File SEED = new File("Seed.txt");
    public File NAME = new File("Name.txt");
    private TETile[][] WORLD;
    private String playerName;
    private Position position;
    private Position position2;
    private ArrayList<Character> moveList;
    private boolean readyToQuit;
    private boolean quit;
    private boolean encounter;
    private int numEncounter;
    private int totalEncounter;
    private int randomNumber;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        moveList = new ArrayList<>();
        readyToQuit = false;
        quit = false;
        encounter = false;
        numEncounter = 0;

        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        displayMainMenu();

        InputSource input = new KeyboardInputSource();

        selectStartOptions(input);
        if (quit) {
            System.exit(0);
        }
        ter.initialize(Engine.WIDTH, Engine.HEIGHT);
        ter.renderFrame(WORLD);

        while (input.possibleNextInput()) {
            headsUpDisplay();
            if (StdDraw.hasNextKeyTyped()) {
                move(input);
            }
            StdDraw.pause(30);
            ter.renderFrame(WORLD);
            if (quit) {
                System.exit(0);
            }
            if (encounter) {
                generateMiniGame(input);
                encounter = false;
            }
            if (numEncounter == totalEncounter) {
                displayFinalScreen();
            }
        }
    }

    public void displayMainMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.WIDTH / 2, this.HEIGHT * 0.7, "CS61B: THE GAME");
        StdDraw.text(this.WIDTH / 2, this.HEIGHT / 2, "New Game (N)");
        StdDraw.text(this.WIDTH / 2, this.HEIGHT / 2 - 4, "Load Game (L)");
        StdDraw.text(this.WIDTH / 2, this.HEIGHT / 2 - 8, "Quit (Q)");
        StdDraw.show();
    }

    public void displaySeed(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH / 2, HEIGHT - 2, "ENTER SEED, THEN S TO START!");
        StdDraw.line(0, HEIGHT - 4, WIDTH, HEIGHT - 4);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "SEED: " + s);
        StdDraw.show();
    }

    public void displayName(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH / 2, HEIGHT - 2, "Enter Character Name, THEN @ TO START!");
        StdDraw.line(0, HEIGHT - 4, WIDTH, HEIGHT - 4);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Name: " + s);
        StdDraw.show();
    }

    private void displayFinalScreen() {
        headsUpDisplay();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);

        StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "All Encounters Passed!");
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Congrats! You won the game!");
        StdDraw.show();
    }

    private void displayStartMiniGame() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH / 2, HEIGHT - 2, "Collect all the COINS!");
        StdDraw.show();
        StdDraw.pause(700);
    }

    private void displayFinishMiniGame() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "You got all the COINS!");
        StdDraw.show();
        StdDraw.pause(1000);
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(WORLD);
    }

    public void generateMiniGame(InputSource input) {
        displayStartMiniGame();

        Game miniGame = new Game(8);
        randomNumber += 1;
        miniGame.newInterface();

        ter.initialize(WIDTH, HEIGHT);
        TETile[][] MINIWORLD = miniGame.getWORLD();
        ter.renderFrame(MINIWORLD);

        position2 = miniGame.getMARIO();

        int coins = 0;
        int numCoins = miniGame.getNumCoins();
        while (coins < numCoins) {
            headsUpDisplay();
            char key = input.getNextKey();
            int x = position2.x;
            int y = position2.y;
            if (key == 'W') {
                if (MINIWORLD[x][y + 1].equals(Tileset.FLOOR) || MINIWORLD[x][y + 1].equals(Tileset.COIN)) {
                    if (MINIWORLD[x][y + 1].equals(Tileset.COIN)) {
                        coins += 1;
                    }
                    MINIWORLD[x][y] = Tileset.FLOOR;
                    MINIWORLD[x][y + 1] = Tileset.MARIO;
                    position2.y = position2.y + 1;
                }
            } else if (key == 'A') { //west
                if (MINIWORLD[x - 1][y].equals(Tileset.FLOOR) || MINIWORLD[x - 1][y].equals(Tileset.COIN)) {
                    if (MINIWORLD[x - 1][y].equals(Tileset.COIN)) {
                        coins += 1;
                    }
                    MINIWORLD[x][y] = Tileset.FLOOR;
                    MINIWORLD[x - 1][y] = Tileset.MARIO;
                    position2.x = position2.x - 1;
                }
            } else if (key == 'S') { //south
                if (MINIWORLD[x][y - 1].equals(Tileset.FLOOR) || MINIWORLD[x][y - 1].equals(Tileset.COIN)) {
                    if (MINIWORLD[x][y - 1].equals(Tileset.COIN)) {
                        coins += 1;
                    }
                    MINIWORLD[x][y] = Tileset.FLOOR;
                    MINIWORLD[x][y - 1] = Tileset.MARIO;
                    position2.y = position2.y - 1;
                }
            } else if (key == 'D') { //east
                if (MINIWORLD[x + 1][y].equals(Tileset.FLOOR) || MINIWORLD[x + 1][y].equals(Tileset.COIN)) {
                    if (MINIWORLD[x + 1][y].equals(Tileset.COIN)) {
                        coins += 1;
                    }
                    MINIWORLD[x][y] = Tileset.FLOOR;
                    MINIWORLD[x + 1][y] = Tileset.MARIO;
                    position2.x = position2.x + 1;
                }
            } else if (key == ':') {
                readyToQuit = true;
            } else if (key == 'Q') {
                if (readyToQuit) {
                    save(this.game);
                    System.exit(0);
                }
            }
            ter.renderFrame(MINIWORLD);
        }
        numEncounter += 1;

        ter.renderFrame(MINIWORLD);

        displayFinishMiniGame();
    }

    private void headsUpDisplay() {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();
        Position mouse = new Position(x, y);
        if (mouse.x < 0 || mouse.x >= WIDTH
                || mouse.y < 0 || mouse.y >= HEIGHT) {
            return;
        }
        TETile[][] world = game.getWORLD();
        TETile current = world[mouse.x][mouse.y];
        String description = current.description();

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 14));
        StdDraw.textLeft(1, HEIGHT - 1, "Current Tile: " + description);
        StdDraw.textRight(WIDTH - 2, HEIGHT - 1, "Player Name: " + playerName);
        StdDraw.text(WIDTH / 2, HEIGHT - 1,
                "Encounters passed: " + numEncounter + "/" + totalEncounter);
        StdDraw.line(0, HEIGHT - 2, WIDTH, HEIGHT - 2);
        StdDraw.show();
    }

    private void selectStartOptions(InputSource input) {
        if (input.possibleNextInput()) {
            char key = input.getNextKey();
            if (key == 'N') {
                if (POSITION.exists() && SEED.exists()) {
                    POSITION.delete();
                    SEED.delete();
                    NAME.delete();
                }
                POSITION = new File("Position.txt");
                SEED = new File("Seed.txt");
                NAME = new File("Name.txt");
                startGame(input);
            } else if (key == 'L') {
                if (POSITION.exists() && SEED.exists()) {
                    StdDraw.clear();
                    load();
                }
            } else if (key == 'Q') {
                quit = true;
            }
        }
    }

    private void move(InputSource input) {
        char key = input.getNextKey();
        int x = position.x;
        int y = position.y;
        if (key == 'W') {
            if (WORLD[x][y + 1].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y + 1] = Tileset.MARIO;
                position.y = position.y + 1;
                moveList.add('W');
            } else if (WORLD[x][y + 1].equals(Tileset.DOOR)) {
                encounter = true;
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y + 1] = Tileset.MARIO;
                position.y = position.y + 1;
                moveList.add('W');
            }
        } else if (key == 'A') {
            if (WORLD[x - 1][y].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x - 1][y] = Tileset.MARIO;
                position.x = position.x - 1;
                moveList.add('A');
            } else if (WORLD[x - 1][y].equals(Tileset.DOOR)) {
                encounter = true;
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x - 1][y] = Tileset.MARIO;
                position.x = position.x - 1;
                moveList.add('A');
            }
        } else if (key == 'S') {
            if (WORLD[x][y - 1].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y - 1] = Tileset.MARIO;
                position.y = position.y - 1;
                moveList.add('S');
            } else if (WORLD[x][y - 1].equals(Tileset.DOOR)) {
                encounter = true;
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y - 1] = Tileset.MARIO;
                position.y = position.y - 1;
                moveList.add('S');
            }
        } else if (key == 'D') {
            if (WORLD[x + 1][y].equals(Tileset.FLOOR)) {
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x + 1][y] = Tileset.MARIO;
                position.x = position.x + 1;
                moveList.add('D');
            } else if (WORLD[x + 1][y].equals(Tileset.DOOR)) {
                encounter = true;
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x + 1][y] = Tileset.MARIO;
                position.x = position.x + 1;
                moveList.add('D');
            }
        } else if (key == ':') {
            key = input.getNextKey();
            if (key == 'Q') {
                save(this.game);
                quit = true;
            }
        }
    }

    public void save(Game g) {
        Out position = new Out(String.valueOf(POSITION));
        Out seedfile = new Out(String.valueOf(SEED));
        Out playername = new Out(String.valueOf(NAME));
        String record = "";
        for (int i = 0; i < moveList.size(); i += 1) {
            if (i < moveList.size() - 1) {
                record += moveList.get(i) + ",";
            } else {
                record += moveList.get(i);
            }
        }
        playername.println(playerName);
        position.println(record);
        seedfile.println(g.getSEED());
    }

    public void load() {
        In seedfile = new In(SEED);
        long seed = seedfile.readLong();
        this.game = new Game(seed);
        game.worldGenerate();
        this.WORLD = game.getWORLD();
        this.position = game.getMARIO();
        this.totalEncounter = game.getNumDoors();

        In namefile = new In(NAME);
        this.playerName = namefile.readString();

        In position = new In(POSITION);
        String s = position.readAll();
        char[] tokens = s.toCharArray();

        for (int i = 0; i < tokens.length; i += 1) {
            loadMove(tokens[i]);
        }
    }

    private void loadMove(char key) {
        int x = position.x;
        int y = position.y;
        if (key == 'W') {
            if (WORLD[x][y + 1].equals(Tileset.FLOOR) || WORLD[x][y + 1].equals(Tileset.DOOR)) {
                if (WORLD[x][y + 1].equals(Tileset.DOOR)) {
                    numEncounter += 1;
                }
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y + 1] = Tileset.MARIO;
                position.y = position.y + 1;
            }
        } else if (key == 'A') {
            if (WORLD[x - 1][y].equals(Tileset.FLOOR) || WORLD[x - 1][y].equals(Tileset.DOOR)) {
                if (WORLD[x - 1][y].equals(Tileset.DOOR)) {
                    numEncounter += 1;
                }
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x - 1][y] = Tileset.MARIO;
                position.x = position.x - 1;
            }
        } else if (key == 'S') {
            if (WORLD[x][y - 1].equals(Tileset.FLOOR) || WORLD[x][y - 1].equals(Tileset.DOOR)) {
                if (WORLD[x][y - 1].equals(Tileset.DOOR)) {
                    numEncounter += 1;
                }
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x][y - 1] = Tileset.MARIO;
                position.y = position.y - 1;
            }
        } else if (key == 'D') {
            if (WORLD[x + 1][y].equals(Tileset.FLOOR) || WORLD[x + 1][y].equals(Tileset.DOOR)) {
                if (WORLD[x + 1][y].equals(Tileset.DOOR)) {
                    numEncounter += 1;
                }
                WORLD[x][y] = Tileset.FLOOR;
                WORLD[x + 1][y] = Tileset.MARIO;
                position.x = position.x + 1;
            }
        }
    }

    private void startGame(InputSource input) {
        if (input.getClass() == KeyboardInputSource.class) {
            char letter;
            String inputSeed = "";
            displaySeed(inputSeed);

            while (input.possibleNextInput()) {
                letter = input.getNextKey();
                if (Character.isDigit(letter)) {
                    inputSeed += letter;
                    displaySeed(inputSeed);
                } else if (letter == 'S') {
                    break;
                }
            }

            long playerSeed = Long.parseLong(inputSeed);
            this.game = new Game(playerSeed);

            char letter2;
            String name = "";
            displayName(name);
            while (input.possibleNextInput()) {
                letter2 = input.getNextKey();
                if (Character.isLetter(letter2)) {
                    name += letter2;
                    displayName(name);
                } else if (letter2 == '@') {
                    break;
                }
            }
            playerName = name;

            game.worldGenerate();
            this.WORLD = game.getWORLD();
            this.position = game.getMARIO();

            this.numEncounter = 0;
            this.totalEncounter = game.getNumDoors();
            this.randomNumber = game.randomNum();
        } else if (input.getClass() == StringInputDevice.class) {
            String s = "";
            while (input.possibleNextInput()) {
                char key = input.getNextKey();
                if (Character.isDigit(key)) {
                    s += key;
                } else if (Character.toUpperCase(key) == 'S') {
                    break;
                }
            }
            long seed = Long.parseLong(s);
            this.game = new Game(seed);
            game.worldGenerate();
            this.WORLD = game.getWORLD();
            this.position = game.getMARIO();
            this.totalEncounter = game.getNumDoors();
        }
    }


    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, running both of these:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        moveList = new ArrayList<>();

        String S = input.toUpperCase();
        InputSource stringinput = new StringInputDevice(S);

        selectStartOptions(stringinput);
        if (quit) {
            return null;
        }

        while (stringinput.possibleNextInput()) {
            move(stringinput);
            if (quit) {
                selectStartOptions(stringinput);
            }
        }

        return WORLD;
    }
}
