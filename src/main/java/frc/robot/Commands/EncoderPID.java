package frc.robot.Commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class EncoderPID extends CommandBase{

    private Encoder m_encoder;
    private double target,integral=0,lastError=0,error,d,currentSpeed=0, prevDistance, currDistance;
    private WPI_TalonSRX motor=new WPI_TalonSRX(15);
    private int prevMillis,currMillis;
    
    public EncoderPID(){
        m_encoder=new Encoder(1, 0);
        m_encoder.setDistancePerPulse(1.55);
        m_encoder.reset();
        prevMillis= (int)(System.currentTimeMillis()%100000);
    }

    @Override
    public void execute() {
        target=(int)(SmartDashboard.getNumber("target", 10));
        error=target-currentSpeed;
        d=lastError-error;
        currMillis= (int)(System.currentTimeMillis()%1000000);
        int time=(int) (currMillis-prevMillis);
        currDistance=m_encoder.getDistance();
        currentSpeed=(currDistance-prevDistance)/time;
        double speed=error*Constants.PID.VELOCITY_KP+integral*Constants.PID.VELOCITY_KI-d*Constants.PID.VELOCITY_KD;
        motor.set(speed);
        integral+=error;
        lastError=error;
        prevDistance=currDistance;
        prevMillis=currMillis;
        SmartDashboard.putNumber("currentSpeed", currentSpeed*10);
        SmartDashboard.putNumber("speed", speed);
    }

    @Override
    public boolean isFinished() {
        // if(currentSpeed==target)
        //     return true;
        return false;
    }
}
