package app.model;

public class Pair {
    public Integer x, y;

    public Pair(){
        //default constructor
    }

    public Pair(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer getX(){
        return x;
    }

    public Integer getY(){
        return y;
    }

    public String toString() {
        return x + "," + y + " ";
    }
}
