package sample.ui.javafx.simpleui;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class HoldingWithoutRepeatEventHandler implements EventHandler<KeyEvent> {

    private final Set<KeyCode> pressedKeyCodes = new HashSet<>();

    @Override
    public final void handle(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (isKeyPressed(event)) {
            if (!pressedKeyCodes.contains(keyCode)) {
                pressedKeyCodes.add(keyCode);
                handlePressed(keyCode);
            }

        } else if (isKeyReleased(event)) {
            pressedKeyCodes.remove(event.getCode());
            handleReleased(keyCode);
        }

    }

    private boolean isKeyPressed(KeyEvent event) {
        return KeyEvent.KEY_PRESSED.equals(event.getEventType());
    }

    private boolean isKeyReleased(KeyEvent event) {
        return KeyEvent.KEY_RELEASED.equals(event.getEventType());
    }


    public abstract void handlePressed(KeyCode keyCode);

    public abstract void handleReleased(KeyCode keyCode);

}
