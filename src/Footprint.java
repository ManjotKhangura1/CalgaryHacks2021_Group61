import java.util.logging.XMLFormatter;

public class Footprint implements GameObject{
    private int x;
    private int y;
    private double opacity;
    private double age;
    private Handler handler;
    private final double MAXAGE =500.0;

    Footprint(int X, int Y, Handler handler){
        this.x = X;
        this.y = Y;
        this.age = 0;
        this.handler = handler;
    }

    public void tick(){
        opacity = 1 - (age / MAXAGE);
        age++;
    }

    public void display(Display d){
        d.footprint(x, y, opacity);
    }

    public boolean isDead(){
        return age >= MAXAGE;
    }
}
