// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  public static final CANSparkMax m_intakeMotor = new CANSparkMax(23, MotorType.kBrushless);
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public CommandBase grabThing() {
    // implicitly require `this`
    return this.runOnce(() -> m_intakeMotor.set(.3));
  }

  /** Releases the hatch. */
  public CommandBase releaseThing() {
    // implicitly require `this`
    return this.runOnce(() -> m_intakeMotor.set(-.3));
  }
  public CommandBase stopThing() {
    // implicitly require `this`
    return this.runOnce(() -> m_intakeMotor.set(0));
  }
  public void powerIntake(double y){
    m_intakeMotor.set(y);
  }
}
