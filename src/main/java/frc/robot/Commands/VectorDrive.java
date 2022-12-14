package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.SubSystems.Chassis;

public class VectorDrive extends CommandBase{

    private Supplier<Double> m_rightSupllier;
    private Supplier<Double> m_leftSupllier;

    public VectorDrive(Supplier<Double> left, Supplier<Double> right){
        addRequirements(Chassis.getInstance());
        m_rightSupllier=right;
        m_leftSupllier=left;
    }
    
    @Override
    public void execute() {
        double rSpeed=-m_rightSupllier.get();
        double lSpeed=-m_leftSupllier.get();
        Vector2d v=new Vector2d(lSpeed, rSpeed);
        if(v.magnitude()>Constants.Speeds.driveMax.get()){
            //normalizing the vector.
            v.x/=(v.magnitude());
            v.y/=(v.magnitude());
            v.x*=Constants.Speeds.driveMax.get();
            v.y*=Constants.Speeds.driveMax.get();
        }
        lSpeed=v.x;
        rSpeed=v.y;
        Chassis.getInstance().driveTank(lSpeed, rSpeed);
        SmartDashboard.putNumber("right speed", rSpeed);
        SmartDashboard.putNumber("left speed", lSpeed);
        SmartDashboard.putNumber("right joystick", Controls.m_rightJoystick.getY());
        SmartDashboard.putNumber("left joystick", Controls.m_leftJoystick.getY());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Chassis.getInstance().stop();
    }

}