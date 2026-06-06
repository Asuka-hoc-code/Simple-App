package configuregame;
import java.awt.*;

public class SkinManager {

    private SkinManager() {
    }

    public static Color[] getColors(String skin) {

        return switch (skin) {

            case "Blue Snake" -> new Color[]{
                    new Color(0, 102, 255),
                    new Color(102, 178, 255)
            };

            case "White Snake" -> new Color[]{
                    Color.WHITE,
                    new Color(220, 220, 220)
            };

            case "Red Snake" -> new Color[]{
                    new Color(220, 20, 60),
                    new Color(255, 99, 71)
            };

            case "Purple Snake" -> new Color[]{
                    new Color(128, 0, 128),
                    new Color(186, 85, 211)
            };

            default -> new Color[]{
                    Color.GREEN,
                    new Color(45, 180, 0)
            };
        };
    }
}
