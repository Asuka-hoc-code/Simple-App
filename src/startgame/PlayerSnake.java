package startgame;

import java.awt.*;
import java.util.ArrayList;

public class PlayerSnake {

    public ArrayList<Point> body =
            new ArrayList<>();

    public int dx = 0;
    public int dy = 0;



    public Color headColor;
    public Color bodyColor;

    public String playerName;

    public PlayerSnake(String playerName, Color headColor, Color bodyColor) {
        this.playerName = playerName;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
    }
}