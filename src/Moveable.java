// moveable is important because it is the interface for all objects that can move, it makes it simpiler so other methods that
// impliment this interface can be used with all these diffrent methods, instead of having to create a new method for each

public interface Moveable {
    public void setPos(int x, int y);

    public void setX(int x);

    public void setY(int y);

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public void setWidth(int w);

    public void setHeight(int h);
}