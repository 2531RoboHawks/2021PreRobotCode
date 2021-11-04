package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;

public class ToggleButton {
  private static List<ToggleButton> allButtons = new ArrayList<>();

  Joystick joystick;
  int button;
  boolean toggled = false;

  public ToggleButton(Joystick joystick, int button) {
    this.joystick = joystick;
    this.button = button;
    allButtons.add(this);
  }

  public boolean isToggled() {
    boolean wasToggled = this.joystick.getRawButtonPressed(this.button);
    if (wasToggled) {
      toggled = !toggled;
    }
    return toggled;
  }

  public void reset() {
    toggled = false;
  }

  public static void resetAllbuttons() {
    for (ToggleButton button : allButtons) {
      button.reset();
    }
  }
}
