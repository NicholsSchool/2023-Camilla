package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
    private CANSparkMax intake; 
    
    public Intake(){
    
        intake = new CANSparkMax( RobotMap.SPARK_ID, MotorType.kBrushless );
    
        intake.restoreFactoryDefaults();

        intake.setInverted( false );
    }

    public void move( double speed )
    {
        intake.set( speed );
    }

    public void stop()
    {
        intake.stopMotor();
    }
}
