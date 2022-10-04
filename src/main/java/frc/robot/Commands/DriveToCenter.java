package frc.robot.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Vision;
import frc.robot.SubSystems.Chassis;
import frc.robot.SubSystems.Constants;

public class DriveToCenter extends CommandBase {

    private Vision v;
    private static DriveToCenter m_instance;

    private double error,lastError=0,integral,d,target;

    public DriveToCenter(){
        addRequirements(Chassis.getInstance());
        v=new Vision(7112); 
        this.target = 4;
    }

    public static DriveToCenter getInstance(){
        if(m_instance==null){
            m_instance=new DriveToCenter();
        }
        return m_instance;
    }
    

    @Override
    public void execute() {
        error = target - v.getXYZ()[2];
        if(Math.abs(v.getXYZ()[2]) > 10){
            return;
        }
        d=lastError-error;
        double fs=error*Constants.PID.VELOCITY_KP+integral*Constants.PID.VELOCITY_KI-d*Constants.PID.VELOCITY_KD;
        if(Math.abs(fs) >= 0.6){
            return;
        }
        Chassis.getInstance().driveTank(fs, fs);
        SmartDashboard.putNumber("speed", fs);
        SmartDashboard.putNumber("distance", v.getXYZ()[2]);
        SmartDashboard.putNumber("error", error);
        integral+=error;
        SmartDashboard.putNumber("integral", integral);
        lastError=error;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
