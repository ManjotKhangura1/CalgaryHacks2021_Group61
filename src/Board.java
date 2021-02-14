import java.util.ArrayList;

public class Board {
    private ArrayList<Tile> tileBoard = new ArrayList<>();
    public final int width = 28;
    public final int height = 20;

    Display d;

    Board() {
        for (int i = 0; i < width * height; i++) {
            tileBoard.add(new Tile(null, 0, null));
        }
    }

    public void display(Display d) {
        this.d = d;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                drawTile(i, j);
            }
        }
    }

    public void drawTile(int x, int y) {
        d.displayTile(getTile(x,y), x, y);
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
                    getTile(i,j).setValues(TileID.Driveway, 3, null);
                } else {
                    getTile(i,j).setValues(TileID.Driveway, 3, null);
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tileBoard.get(width * y + x);
    }

}
