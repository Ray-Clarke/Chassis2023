// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  
  public static final CANSparkMax m_elevatorMotor = new CANSparkMax(ElevatorConstants.MotorCANID, MotorType.kBrushless);
  /** Creates a new Elevator. */
  public Elevator() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public CommandBase raiseElevator() {
    System.out.println("in raiseElevator()");
    return this.runOnce(() -> m_elevatorMotor.set(0.3));
  }

  /** Releases the hatch. */
  public CommandBase lowerElevator() {
    System.out.println("in lowerElevator()");
    return this.runOnce(() -> m_elevatorMotor.set(-.3));
  
  }
  public CommandBase stopElevator(){
    return this.runOnce(() -> m_elevatorMotor.set(0));
  }
  public void powerMotor(double x){
    m_elevatorMotor.set(x);
  }
  public void horizontalElevator(){
    
  }
}
