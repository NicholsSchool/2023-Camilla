package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {

    private WPI_TalonFX rFDrive; 
    private WPI_TalonFX lFDrive; 
    private WPI_TalonFX rBDrive; 
    private WPI_TalonFX lBDrive; 

    private DifferentialDrive differentialDrive;

    public DriveTrain()
    {
        rFDrive = new WPI_TalonFX( RobotMap.RIGHT_FRONT_DRIVE_ID );
        rBDrive = new WPI_TalonFX( RobotMap.RIGHT_BACK_DRIVE_ID );
        lFDrive = new WPI_TalonFX( RobotMap.LEFT_FRONT_DRIVE_ID );
        lBDrive = new WPI_TalonFX( RobotMap.LEFT_BACK_DRIVE_ID );

        rFDrive.configFactoryDefault();
        rBDrive.configFactoryDefault();
        lFDrive.configFactoryDefault();
        lBDrive.configFactoryDefault();

        differentialDrive = new DifferentialDrive( lFDrive, rFDrive );

        rBDrive.follow( rFDrive );
        lBDrive.follow( lFDrive );

        rFDrive.setInverted( true );
        rBDrive.setInverted( true );

        brake(); 

    }

    public void tankDrive( double leftSpeed, double rightSpeed )
    {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);

        System.out.println( "Right Speed: " + rFDrive.get());
        System.out.println( "Left Speed: " + lFDrive.get());
        // rFDrive.set(rightSpeed);
        // lFDrive.set(leftSpeed);

        // rBDrive.set(rightSpeed);
        // lBDrive.set(leftSpeed);

    }

    public void arcadeDrive( double forwardSpeed, double turningSpeed )
    {
        differentialDrive.arcadeDrive(forwardSpeed, turningSpeed);
    }

    public void stop() 
    {
        rFDrive.stopMotor();
        rBDrive.stopMotor();
        lFDrive.stopMotor();
        lBDrive.stopMotor();
    }

    public void brake() 
    {
        rFDrive.setNeutralMode( NeutralMode.Brake );
        rBDrive.setNeutralMode( NeutralMode.Brake );
        lFDrive.setNeutralMode( NeutralMode.Brake );
        lBDrive.setNeutralMode( NeutralMode.Brake );
    }

    public void coast() 
    {
        rFDrive.setNeutralMode( NeutralMode.Coast );
        rBDrive.setNeutralMode( NeutralMode.Coast );
        lFDrive.setNeutralMode( NeutralMode.Coast );
        lBDrive.setNeutralMode( NeutralMode.Coast );
    }
}
