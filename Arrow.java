/**
 * Models a simple arrow.
 * This class represents a Arrow object. When combined with the GameArena class,
 * instances of the Arrow class can be displayed on the screen.
 */
public class Arrow
{
    // The following instance variables define the information needed to represent a Arrow
    // Feel free to more instance variables if you think it will support your work...

    private Line[] line = new Line[3];

    private double xStart;            // The X coordinate of the start of this Arrow
    private double xEnd;              // The X coordinate of the end of this Arrow
    private double yStart;            // The X coordinate of the start of this Arrow
    private double yEnd;              // The X coordinate of the end of this Arrow
    private double width;             // The colour of this Arrow
    private double arrowHeadLength;   // The length of the arrowheads, as a proportion of the line length (percentage)
    private double arrowHeadPosition; // The position of the arrowheads, as a proportion of the line length (percentage)
    private String colour;            // The colour of this Arrow

    /**
     * Obtains the X coordinte of the start of this arrow.
     * @return the X coordinte of the start of this arrow within the GameArena.
     */
    public double getStartX()
    {
        return xStart;
    }

    /**
     * Obtains the X coordinte of the end of this arrow.
     * @return the X coordinte of the end of this arrow within the GameArena.
     */
    public double getEndX()
    {
        return xEnd;
    }

    /**
     * Obtains the Y coordinte of the start of this arrow.
     * @return the Y coordinte of the start of this arrow within the GameArena.
     */
    public double getStartY()
    {
        return yStart;
    }

    /**
     * Obtains the Y coordinte of the end of this arrow.
     * @return the Y coordinte of the end of this arrow within the GameArena.
     */
    public double getEndY()
    {
        return yEnd;
    }


    /**
     * Moves the current start position of this Arrow to the given co-ordinates
     * @param x the new x co-ordinate of the start of this Arrow
     * @param y the new y co-ordinate of the start of this Arrow
     */
    public void setStart(double x, double y)
    {
        this.xStart = x;
        this.yStart = y;

        setArrowHeadPosition(arrowHeadPosition);
    }

    /**
     * Moves the current end position of this Arrow to the given co-ordinates
     * @param x the new x co-ordinate of the end of this Arrow
     * @param y the new y co-ordinate of the end of this Arrow
     */
    public void setEnd(double x, double y)
    {
        this.xEnd = x;
        this.yEnd = y;

        setArrowHeadPosition(arrowHeadPosition);
    }

    /**
     * Obtains the width of this Arrow.
     * @return the width of this Arrow,in pixels.
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * Changes the width of this Arrow to the given value
     * @param w the new width of this arrow, in pixels.
     */
    public void setWidth(double w)
    {
        width = w;

        for (int i=0; i<line.length; i++)
            line[i].setWidth(width);
    }

    /**
     * Obtains the colour of this Arrow.
     * @return a textual description of the colour of this Arrow.
     */
    public String getColour()
    {
        return colour;
    }

    /**
     * Changes the colour of this Arrow to the given value.
     *
     * @param c The new colour of this Arrow.
     * <BR><BR>
     * Permissable values are 8 bit hexadecimal RGB values in the format #RRGGBB. e.g.
     * Pure red is "#FF0000"   <BR>
     * Pure green is "#00FF00" <BR>
     * Pure blue is "#0000FF"  <BR>
     * <BR>
     * Alternativley, named colours are also allowed for the following list. e.g. "AQUA" :
     * <BR><BR>
     * ALICEBLUE ANTIQUEWHITE AQUA AQUAMARINE AZURE BEIGE BISQUE BLACK BLANCHEDALMOND BLUE BLUEVIOLET BROWN BURLYWOOD CADETBLUE CHARTREUSE CHOCOLATE CORAL
     * CORNFLOWERBLUE CORNSILK CRIMSON CYAN DARKBLUE DARKCYAN DARKGOLDENROD DARKGRAY DARKGREEN DARKGREY DARKKHAKI DARKMAGENTA DARKOLIVEGREEN DARKORANGE DARKORCHID
     * DARKRED DARKSALMON DARKSEAGREEN DARKSLATEBLUE DARKSLATEGRAY DARKSLATEGREY DARKTURQUOISE DARKVIOLET DEEPPINK DEEPSKYBLUE DIMGRAY DIMGREY DODGERBLUE FIREBRICK
     * FLORALWHITE FORESTGREEN FUCHSIA GAINSBORO GHOSTWHITE GOLD GOLDENROD GRAY GREEN GREENYELLOW GREY HONEYDEW HOTPINK INDIANRED INDIGO IVORY KHAKI LAVENDER LAVENDERBLUSH
     * LAWNGREEN LEMONCHIFFON LIGHTBLUE LIGHTCORAL LIGHTCYAN LIGHTGOLDENRODYELLOW LIGHTGRAY LIGHTGREEN LIGHTGREY LIGHTPINK LIGHTSALMON LIGHTSEAGREEN LIGHTSKYBLUE LIGHTSLATEGRAY
     * LIGHTSLATEGREY LIGHTSTEELBLUE LIGHTYELLOW LIME LIMEGREEN LINEN MAGENTA MAROON MEDIUMAQUAMARINE MEDIUMBLUE MEDIUMORCHID MEDIUMPURPLE MEDIUMSEAGREEN MEDIUMSLATEBLUE
     * MEDIUMSPRINGGREEN MEDIUMTURQUOISE MEDIUMVIOLETRED MIDNIGHTBLUE MINTCREAM MISTYROSE MOCCASIN NAVAJOWHITE NAVY OLDLACE OLIVE OLIVEDRAB ORANGE ORANGERED ORCHID
     * PALEGOLDENROD PALEGREEN PALETURQUOISE PALEVIOLETRED PAPAYAWHIP PEACHPUFF PERU PINK PLUM POWDERBLUE PURPLE RED ROSYBROWN ROYALBLUE SADDLEBROWN SALMON SANDYBROWN
     * SEAGREEN SEASHELL SIENNA SILVER SKYBLUE SLATEBLUE SLATEGRAY SLATEGREY SNOW SPRINGGREEN STEELBLUE TAN TEAL THISTLE TOMATO TURQUOISE VIOLET WHEAT WHITE
     * WHITESMOKE YELLOW YELLOWGREEN.
     */
    public void setColour(String c)
    {
        colour = c;

        for (int i=0; i<line.length; i++)
            line[i].setColour(colour);
    }

    /**
     *
     * Constructor. Create a new instance of a Arrow.
     *
     * @param startx The X co-ordinate in the GameArena where this Arrow will start.
     * @param starty The Y co-ordinate in the GameArena where this Arrow will start.
     * @param endx The X co-ordinate in the GameArena where this Arrow will end.
     * @param endy The Y co-ordinate in the GameArena where this Arrow will end.
     * @param w The thickness of this arrow, in pixels
     * @param col. The colour of the Arrow. @see setColour for a description of permissable colours.
     *
     */
    public Arrow(double startX, double startY, double endX, double endY, double w, String col, GameArena arena)
    {
        xStart = startX;
        yStart = startY;
        xEnd = endX;
        yEnd = endY;
        width = w;
        colour = col;
        arrowHeadLength = 20.0;

        // Create three line instances - initially equal, but then update two to form the head of the arrow.
        for (int i=0; i<line.length; i++)
        {
            line[i] = new Line(xStart, yStart, xEnd, yEnd, width, colour);
            arena.addLine(line[i]);
        }

        setArrowHeadPosition(100.0);
    }

    /**
     * Moves the arrow head at the end of this Arrow to the specified position
     *
     * @param position The proportional distance along the line to draw the arrow head, expressed as a percentage
     */
    void setArrowHeadPosition(double position)
    {
        arrowHeadPosition = position;

        // First, calculate the position along the line where the tip of the arrowhead will be drawn.
        double xe = xStart + ((xEnd - xStart) * position) / 100.0;
        double ye = yStart + ((yEnd - yStart) * position) / 100.0;

        // Now, calculate the position of the "tails" of the arrowhead
        double dx = xStart - xe;
        double dy = yStart - ye;

        double theta = Math.PI / 6.0;

        double ax1 = xe + dx * Math.cos(theta) - dy * Math.sin(theta);
        double ay1 = ye + dy * Math.cos(theta) + dx * Math.sin(theta);

        double ax2 = xe + dx * Math.cos(-theta) - dy * Math.sin(-theta);
        double ay2 = ye + dy * Math.cos(-theta) + dx * Math.sin(-theta);

        ax1 = xe - (xe - ax1) * (arrowHeadLength / 100.0);
        ax2 = xe - (xe - ax2) * (arrowHeadLength / 100.0);

        ay1 = ye - (ye - ay1) * (arrowHeadLength / 100.0);
        ay2 = ye - (ye - ay2) * (arrowHeadLength / 100.0);

        line[1].setStart(ax1, ay1);
        line[1].setEnd(xe, ye);

        line[2].setStart(ax2, ay2);
        line[2].setEnd(xe, ye);

    }
}
