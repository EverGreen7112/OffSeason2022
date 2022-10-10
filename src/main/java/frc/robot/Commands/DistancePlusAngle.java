package frc.robot.Commands;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.SubSystems.Chassis;

public class DistancePlusAngle extends CommandBase{
    
    private Vector2d m_vector;
    private TurnToAngle angle;
    private PIDDriveBySuppliers distance;

    public DistancePlusAngle(double d){
        m_vector=new Vector2d(0,0);
        angle=new TurnToAngle(0);
        distance=new PIDDriveBySuppliers(Robot.ball_Vision::getZ, d, 0.1);
    }

    @Override
    public void execute() {
        angle.execute();
        distance.execute();
        m_vector.x=Constants.Vectors.distanceVector.x + Constants.Vectors.angleVector.x;
        m_vector.y=Constants.Vectors.angleVector.y + Constants.Vectors.distanceVector.y;
        m_vector.x/=(m_vector.magnitude());
        m_vector.y/=(m_vector.magnitude());
        m_vector.x*=Constants.Vectors.distanceVector.x;
        m_vector.y*=Constants.Vectors.distanceVector.y;
        Chassis.getInstance().driveTank(m_vector.x, m_vector.y);
    }
}
