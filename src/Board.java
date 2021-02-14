import java.util.ArrayList;

public class Board {
    private ArrayList<Tile> tileBoard = new ArrayList<>();
    private final int width = 28;
    private final int height = 20;

    Board() {
        for (int i = 0; i < width * height; i++) {
            tileBoard.add(new Tile(null, 0, null));
        }
    }

    public void display(Display d) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                d.displayTile(getTile(i,j), i, j);
            }
        }
    }

    public void setup() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j < 2) {
                    getTile(i,j).setValues(TileID.Road, 0, null);
                } else if (i < 2 || i > width - 3) {
                    getTile(i, j).setValues(TileID.Environment, 0, null);
                } else if (j > height - 3) {
                    getTile(i,j).setValues(TileID.House, 0, null);
                } else if (i > 10) {
                    getTile(i,j).setValues(TileID.Driveway, 0, null);
                } else {
                    getTile(i,j).setValues(TileID.Driveway, 1, null);
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tileBoard.get(width * y + x);
    }

}
