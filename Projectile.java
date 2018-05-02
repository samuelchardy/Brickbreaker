public class Projectile
{
    private Ball b;
    private double xDirection;
    private double yDirection;

    public Ball getBall()
    {
        return b;
    }

    public double getxDirection()
    {
        return xDirection;
    }

    public double getyDirection()
    {
        return yDirection;
    }

    public void setxDirection(double xDir)
    {
        xDirection = xDir;
    }

    public void setyDirection(double yDir)
    {
        yDirection = yDir;
    }

    public Projectile(double x, double y, double diameter, String col, GameArena g)
    {
        b = new Ball(x,y,diameter,col);
        g.addBall(b);
    }



}
