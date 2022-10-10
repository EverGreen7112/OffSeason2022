package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Vision;
import frc.calcTrajectory;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase implements Constants {
    private MotorController m_shootController;
    private static Shooter m_instance = null;

    private Shooter() {
        
        // initilize controllers by type
        m_shootController = new WPI_TalonSRX(MotorPorts.shoot);
    } 

    public static Shooter getInstance() {
        if (m_instance == null) {
            m_instance = new Shooter();
        }
        return m_instance;
    }

    public void shoot() {
        //distance from the target 
        double distance = Math.sqrt(Math.pow(Robot.v.getX()-Robot.target.getX(),2) + 
        Math.pow(Robot.target.getZ()-Robot.v.getZ(),2));
        
        m_shootController.set(calcTrajectory.calcSpeed(distance, Robot.v.getXYZ()[1], Robot.v.getAngleX()));
    }

    // public void shootbyPower(double power) {
    //     m_shootController.set(power);
    // }

    // public void shootByDistance() {
    //     shootbyPower(calculateShootingSpeed(v.getXYZ()[1]));
    // }

    // private double calculateShootingSpeed(double distance){
    //     //preform some function that takes distance and return double   
    //     return 0.5;
    // }

    public void stop() {
        m_shootController.set(0);
    }
}
