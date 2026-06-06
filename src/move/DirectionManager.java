package move;

public class DirectionManager {

    public static boolean isValidChange(
            int currentDx,
            int currentDy,
            int newDx,
            int newDy
    ) {

        return !(currentDx == -newDx
                && currentDy == -newDy);
    }
}
