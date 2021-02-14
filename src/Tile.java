public class Tile {

    private TileID tileID;
    private int snowLevel;
    private Object occupant;

    Tile(TileID id, int snowLevel, Object occupant){
        this.tileID = id;
        this.snowLevel = snowLevel;
        this.occupant = occupant;
    }

    public void setValues(TileID t, int snowLevel, GameObject occupant) {
        this.tileID = t;
        this.snowLevel = snowLevel;
        this.occupant = occupant;
    }
    public boolean hasOccupant()
    {
        if (this.occupant != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setTileID(TileID tileID) {
        this.tileID = tileID;
    }

    public void setSnowLevel(int snowLevel) {
        this.snowLevel = snowLevel;
        if (this.snowLevel > 3) {
            this.snowLevel = 3;
        }
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public TileID getTileID() {return tileID;}

    public int getSnowLevel() {return snowLevel;}

    public Object getOccupant() {return occupant;}
}
