package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ToggleButton {
    Joystick joystick;
    int button;
    boolean toggled = false;
    public ToggleButton(Joystick joystick, int button) {
        this.joystick = joystick;
        this.button = button;
    }

    public boolean isToggled() {
        boolean wasToggled = this.joystick.getRawButtonPressed(this.button);
        if (wasToggled) {
            toggled = !toggled;
        }
        return toggled;
    }
}
