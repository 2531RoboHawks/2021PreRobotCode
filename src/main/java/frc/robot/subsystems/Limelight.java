package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  //Whether the limelight has any valid targets (0 or 1)
  private NetworkTableEntry tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv");

  //Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees)
  private NetworkTableEntry tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx"); 

  //Vertical Offset From Crosshair To Target (LL2: -24.85 to 24.85 degrees)
  private NetworkTableEntry ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty"); 

  //ta Target Area (0% of image to 100% of image)
  private NetworkTableEntry ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta");

  //ts Skew or rotation (-90 degrees to 0 degrees)
  private NetworkTableEntry ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts");

  //tl The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency. 
  private NetworkTableEntry tl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl"); 

  //tshort Sidelength of shortest side of the fitted bounding box (pixels)
  private NetworkTableEntry tshort = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort");

  //tlong Sidelength of longest side of the fitted bounding box (pixels)
  private NetworkTableEntry tlong = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong"); 

  //thor Horizontal sidelength of the rough bounding box (0 - 320 pixels)
  private NetworkTableEntry thor = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor");

  //tvert Vertical sidelength of the rough bounding box (0 - 320 pixels)
  private NetworkTableEntry tvert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert");

  //getpipe True active pipeline index of the camera (0 .. 9)
  private NetworkTableEntry getpipe = NetworkTableInstance.getDefault().getTable("limelight").getEntry("getpipe"); 

  //camtran Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll)
  private NetworkTableEntry camtran = NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran"); 

  public boolean hasValidTargets() {
    return tv.getDouble(0) == 1;
  }

  public double getX() {
    return tx.getDouble(0);
  }

  public double getY() {
    return ty.getDouble(0);
  }

  public double getArea() {
    return ta.getDouble(0);
  }

  public double getSkew() {
    return ts.getDouble(0);
  }

  public double getLatency() {
    return tl.getDouble(0);
  }

  public double getShortSize() {
    return tshort.getDouble(0);
  }

  public double getLongSize() {
    return tlong.getDouble(0);
  }

  public double getHorizontalSize() {
    return thor.getDouble(0);
  }

  public double getVerticalSize() {
    return tvert.getDouble(0);
  }

  public double getPipeline() {
    return getpipe.getDouble(0);
  }

  //FIXME: prob not going to work
  public double[] getTranslationRotation() {
    return camtran.getDoubleArray(new double[5]);
  }

  public double getDistance() {
    // d = (h2 - h1) / tan(a1+a2)
    //TO DO: get height
    double h1 = 3.125;

    double h2 = 7.125;
    //TO DO: get cam angle
    double a1 = 0.0; //(RobotContainer.servoSubsystem.getTopServoAngle() - 0.74) * 360;

    double a2 = getY();
    return (h2 - h1) / Math.tan((a1 + a2) * Math.PI / 180.0);
  }
}
