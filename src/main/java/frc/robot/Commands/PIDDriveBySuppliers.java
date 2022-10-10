package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.SubSystems.Chassis;

public class PIDDriveBySuppliers extends CommandBase {

    private double error,lastError=0,integral,derivative,target,stopRange;
    private Supplier<Float> value;

    public PIDDriveBySuppliers(Supplier<Float> s, double t, double range){
        addRequirements(Chassis.getInstance());
        value=s;
        this.target = t;
        stopRange=range;
    }

    @Override
    public void execute() {
        error = target - value.get();
        derivative=lastError-error;
        double speed=error*Constants.PID.VELOCITY_KP+integral*Constants.PID.VELOCITY_KI-derivative*Constants.PID.VELOCITY_KD;
        Chassis.getInstance().driveStraight(speed); 
        integral+=error;
        lastError=error;
        SmartDashboard.putNumber("speed", speed);
        SmartDashboard.putNumber("distance", value.get());
        SmartDashboard.putNumber("error", error);
        SmartDashboard.putNumber("integral", integral);
        Constants.Vectors.distanceVector.x=speed;
        Constants.Vectors.distanceVector.y=speed;
    }

    @Override
    public boolean isFinished() {
        if(error<stopRange)
            return true;        
        return false;
    }
}
