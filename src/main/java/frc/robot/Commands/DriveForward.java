package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Chassis;

public class DriveForward extends CommandBase{
    
    private static DriveForward m_instance=null;
    private static double m_speed;

    private DriveForward(double speed){
        m_speed=speed;
    }

    public static DriveForward getInstance(){
        if(m_instance==null){
            m_instance=new DriveForward(m_speed);
        }
        return m_instance;
    }

    @Override
    public void initialize() {
        Chassis.getInstance().driveStraight(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        Chassis.getInstance().stop();
    }

}
