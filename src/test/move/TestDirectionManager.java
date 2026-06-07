package test.move;

import move.DirectionManager;

public class TestDirectionManager {

    public static void main(String[] args) {

        DirectionManager dm =
                new DirectionManager();

        boolean test1 =
                dm.isValidChange(
                        1,
                        0,
                        -1,
                        0
                );

        boolean test2 =
                dm.isValidChange(
                        1,
                        0,
                        0,
                        -1
                );

        System.out.println(
                "Reverse Direction: "
                        + test1
        );

        System.out.println(
                "Valid Direction: "
                        + test2
        );
    }
}