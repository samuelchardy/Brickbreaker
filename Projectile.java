public class Projectile
{
    private Ball b;
    private double xDirection;
    private double yDirection;

    Ball getBall()
    {
        return b;
    }

    double getxDirection()
    {
        return xDirection;
    }

    double getyDirection()
    {
        return yDirection;
    }

    void setxDirection(double xDir)
    {
        xDirection = xDir;
    }

    void setyDirection(double yDir)
    {
        yDirection = yDir;
    }

    public Projectile(double x, double y, double diameter, String col, GameArena g)
    {
        b = new Ball(x,y,diameter,col);
        g.addBall(b);
    }



}
