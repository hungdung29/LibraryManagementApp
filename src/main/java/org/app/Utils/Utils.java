package org.app.Utils;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Utils {
    /**
     * Shaking label.
     * @param label label
     */
    public static void shakeLabel(Label label) {
        TranslateTransition shake = new TranslateTransition();
        shake.setNode(label);
        shake.setDuration(Duration.millis(100)); // Duration of one shake
        shake.setByX(10); // Move 10px to the right
        shake.setCycleCount(6); // Number of shakes (3 cycles left and right)
        shake.setAutoReverse(true); // Reverse the direction
        shake.play();
    }

    public static void setTextAndShakingLabel(Label label, String text) {
        label.setText(text);
        shakeLabel(label);
    }
}
