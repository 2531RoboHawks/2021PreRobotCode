package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ToggleButton {
    Joystick joystick;
    int button;
    boolean oldValue = false;
    boolean toggled = false;
    public ToggleButton(Joystick joystick, int button) {
        this.joystick = joystick;
        this.button = button;
    }

    public boolean isToggled() {
        // TODO: need to use getRawButtonPressed
        boolean newValue = this.joystick.getRawButton(this.button);
        if (oldValue != newValue) {
            oldValue = newValue;
            if (newValue) {
                toggled = !toggled;
            }
        }
        return toggled;
    }
}
