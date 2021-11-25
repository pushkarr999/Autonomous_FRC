// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public static final int FR_port = 21;
  public static final int FL_port = 11;
  public static final int BR_port = 22; 
  public static final int BL_port = 12;

  public static Joystick joy1 = new Joystick(0);

  private double startTime;

  private CANSparkMax fl = new CANSparkMax(11, MotorType.kBrushless);
  private CANSparkMax fr = new CANSparkMax(21, MotorType.kBrushless);
  private CANSparkMax br = new CANSparkMax(22, MotorType.kBrushless);
  private CANSparkMax bl = new CANSparkMax(12, MotorType.kBrushless);

  private CANSparkMax shooter = new CANSparkMax(3, MotorType.kBrushed);

  private CANSparkMax intakeDragger = new CANSparkMax(1, MotorType.kBrushed);

  private WPI_TalonSRX intakeRotor = new WPI_TalonSRX(2);

  Servo blocker = new Servo(0);

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    System.out.println(time - startTime);
   if (time - startTime < 2) {
      fl.set(0.2);
      bl.set(0.2);
      fr.set(-0.2);
      br.set(-0.2);
    } else {
      fl.set(0);
      bl.set(0);
      fr.set(0);
      br.set(0);
    }

    
    if (time - startTime >= 3 && time - startTime <= 3.52) {//working fine
      intakeRotor.set(0.37515);
    } else {
      intakeRotor.set(0);
    }

    if (time - startTime > 5 && time - startTime <= 8) {
      intakeDragger.set(0.4);
    } else {
      intakeDragger.set(0);
    }

    if(time -startTime > 10){
      blocker.setAngle(80);
    }else{
      blocker.setAngle(150);
    }

    if (time - startTime >= 9  && time - startTime <= 12) {
      shooter.set(-0.4);
    } else {
      shooter.set(0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double up = joy1.getRawAxis(1) * -0.3;
    double speed = -joy1.getRawAxis(5) * 0.2;
    intakeRotor.set(up);
    fl.set(-speed);
    bl.set(-speed);
    fr.set(speed);
    br.set(speed);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
