public class Board {
    private Tile[][] tileBoard;
    private final int width = 0;
    private final int height = 0;

    Board(){

    }

    public void setTile(int x, int y, TileID tileID, int snowLevel, Object occupant){
        Tile tile = tileBoard[x][y];
        tile.setTileID(tileID);
        tile.setSnowLevel(snowLevel);
        tile.setOccupant(occupant);
    }

    public Tile getTile(int x, int y){
        return tileBoard[x][y];
    }

}
