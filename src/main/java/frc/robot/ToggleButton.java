package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;

public class ToggleButton {
  private static List<ToggleButton> allButtons = new ArrayList<>();

  Joystick joystick;
  int button;
  boolean toggled = false;
  boolean oldValue = false;

  public ToggleButton(Joystick joystick, int button) {
    this.joystick = joystick;
    this.button = button;
    allButtons.add(this);
  }

  public boolean isToggled() {
    boolean newValue = this.joystick.getRawButton(this.button);
    if (oldValue != newValue) {
      oldValue = newValue;
      if (newValue) {
        toggled = !toggled;
        System.out.println("Toggled " + this.button + " " + toggled);
      }
    }
    return toggled;
  }

  public void reset() {
    toggled = false;
    System.out.println("Reset button: " + this.button);
  }

  public static void resetAllbuttons() {
    System.out.println("Reset all buttons: " + allButtons.size());
    for (ToggleButton button : allButtons) {
      button.reset();
    }
  }
}
