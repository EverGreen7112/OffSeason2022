package frc.robot.Commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.SubSystems.Chassis;

public class TurnToAngle extends CommandBase{

    private double target,integral=0,lastError=0,error,derivative;
    
    public TurnToAngle(double target){
        addRequirements(Chassis.getInstance());
        this.target = target;
    }

    public void updateTarget(double t){
        target=t;
    }
 
    @Override
    public void execute() { 
        error=target-Robot.ball_Vision.getAngleX();
        derivative=lastError-error;
        double speed=error*Constants.PID.ANGLE_KP+integral*Constants.PID.ANGLE_KI-derivative*Constants.PID.ANGLE_KD;
        // Chassis.getInstance().turnRight(speed);
        SmartDashboard.putNumber("x", Robot.ball_Vision.getAngleX());
        integral+=error;
        lastError=error;
        Constants.Vectors.angleVector.x=speed;
        Constants.Vectors.angleVector.y=-speed;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
