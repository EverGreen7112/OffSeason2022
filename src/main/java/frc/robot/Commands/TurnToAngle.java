package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Vision;
import frc.robot.Constants;
import frc.robot.SubSystems.Chassis;

public class TurnToAngle extends CommandBase{

    private Vision v;
    private double target,integral=0,lastError=0,error,d;
    
    public TurnToAngle(double target){
        addRequirements(Chassis.getInstance());
        this.target = target;
        v=new Vision(7112);
    }

    @Override
    public void execute() {
        error=target-v.getAngleX();
        d=lastError-error;
        double fs=error*Constants.PID.ANGLE_KP+integral*Constants.PID.ANGLE_KI-d*Constants.PID.ANGLE_KD;
        Chassis.getInstance().driveTank(fs, -fs);
        integral+=error;
        lastError=error;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
