package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//
//        StdDraw.text(this.WIDTH / 2, this.HEIGHT * 0.96, "CS61B: THE GAME");
//        StdDraw.text(this.WIDTH / 2, this.HEIGHT/2 + 5, "New Game (N)");
//        StdDraw.text(this.WIDTH / 2, this.HEIGHT/2, "Load Game (L)");
//        StdDraw.text(this.WIDTH / 2, this.HEIGHT/2 - 5, "Quit (Q)");
//        StdDraw.show();
//        String input = Character.toString(StdDraw.nextKeyTyped());
//        if (input.equals("N")) {
//            newGame(input);
//        } else if (input.equals("L")) {
//            loadGame(input);
//        } else if (input.equals("Q")) {
//            System.exit(0);
//        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
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
        Random rand = new Random();
        TERenderer ter = new TERenderer();
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }
        int numRooms = 5;
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        int i = 0;
        while (i < numRooms) {
            int width = 6 + rand.nextInt(4);
            int height = 6 + rand.nextInt(4);
            int starting_width = rand.nextInt(WIDTH - width - 1);
            int starting_height = rand.nextInt(HEIGHT - height - 1);
            for (int x = starting_width; x < starting_width + width; x += 1) {
                for (int y = starting_height; y < starting_height + height; y += 1) {
                    world[x][y] = Tileset.WALL;
                }
            }
            for (int x = starting_width + 1; x < starting_width + width - 1; x += 1) {
                for (int y = starting_height + 1; y < starting_height + height - 1; y += 1) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
            i += 1;
        }
        ter.renderFrame(world);


        return finalWorldFrame;
    }
}
