package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.SubSystems.Chassis;

public class PIDDriveBySuppliers extends CommandBase {

    private double error,lastError=0,integral,derivative,target;
    private Supplier<Float> value;

    public PIDDriveBySuppliers(Supplier<Float> s, double t){
        addRequirements(Chassis.getInstance());
        this.target = t;
        value=s;
    }

    @Override
    public void execute() {
        error = target - value.get();
        derivative=lastError-error;
        double fs=error*Constants.PID.VELOCITY_KP+integral*Constants.PID.VELOCITY_KI-derivative*Constants.PID.VELOCITY_KD;
        Chassis.getInstance().driveTank(fs, fs);
        SmartDashboard.putNumber("speed", fs);
        SmartDashboard.putNumber("distance", value.get());
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
