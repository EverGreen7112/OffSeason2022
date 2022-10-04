package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Vision;

public class Shooter extends SubsystemBase implements Constants {
    private MotorController m_shootController;
    private Vision v;
    private static Shooter m_instance = null;

    private Shooter() {
        v = new Vision(7112);
        // initilize controllers by type
    }

    public static Shooter getInstance() {
        if (m_instance == null) {
            m_instance = new Shooter();
        }
        return m_instance;
    }

    public void shoot() {
        m_shootController.set(Speeds.SHOOT_SPEED);
    }

    public void shootbyPower(double power) {
        m_shootController.set(power);
    }

    public void shootByDistance() {
        shootbyPower(calculateShootingSpeed(v.getXYZ()[1]));
    }

    private double calculateShootingSpeed(double distance){
        //preform some function that takes distance and return double
        return 0.5;
    }

    public void stop() {
        m_shootController.set(0);
    }
}
