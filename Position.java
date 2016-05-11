public class Position {

    private int y;
    private int x;

    /**Sets a position
     * @param y the y coordinate of the position (line)
     * @param x the x coordinate of the position (column)
     */
    public Position(int y, int x){
        this.y=y;
        this.x=x;
    }

    /**Gets the x coordinate of the current position
     * @return the x coordinate
     */
    public int getX(){
        return this.x;
    }

    /**Gets the y coordinate of the current position
     * @return the y coordinate
     */
    public int getY(){
        return this.y;
    }

    /**Gets the position south of the current position
     * @return the position
     */
    public Position getPosToSouth(){
        return new Position(y+1,x);
    }

    /**Gets the position north of the current position
     * @return the position
     */
    public Position getPosToNorth(){
        return new Position(y-1,x);
    }

    /**Gets the position west of the current position
     * @return the position
     */
    public Position getPosToWest(){
        return new Position(y,x-1);
    }

    /**Gets the position east of the current position
     * @return the position
     */
    public Position getPosToEast(){
        return new Position(y,x+1);
    }

    /**Check if a given position is the current position
     * @param o the given position
     * @return true if the given position is the current position, false if not
     */
    public boolean equals(Position o){
        if(this.x==o.getX()&&this.y==o.getY())
            return true;
        else
            return false;
    }

    public Position getPosToNorthEast() {return new Position(y-1,x+1);}

    public Position getPosToNorthWest() {return new Position(y-1, x-1);}

    public Position getPosToSouthEast() {return new Position(y+1,x+1);}

    public Position getPosToSouthWest() {return new Position(y+1,x-1);}
}