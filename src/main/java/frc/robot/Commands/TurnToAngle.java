package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Vision;
import frc.robot.Constants;
import frc.robot.SubSystems.Chassis;

public class TurnToAngle extends CommandBase{

    private Vision v;
    private double target,integral=0,lastError=0,error,derivative;
    
    public TurnToAngle(double target){
        addRequirements(Chassis.getInstance());
        this.target = target;
        v=new Vision(7112);
    }
 
    @Override
    public void execute() { 
        error=target-v.getAngleX();
        derivative=lastError-error;
        double speed=error*Constants.PID.ANGLE_KP+integral*Constants.PID.ANGLE_KI-derivative*Constants.PID.ANGLE_KD;
        Chassis.getInstance().turnRight(speed);
        integral+=error;
        lastError=error;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
