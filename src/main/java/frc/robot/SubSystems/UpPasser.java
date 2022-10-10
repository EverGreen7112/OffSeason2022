package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants;

public class UpPasser implements Constants {
    private MotorController m_upPassController;
    private static UpPasser m_instance = null;
    private UpPasser() {
            m_upPassController = new WPI_VictorSPX(MotorPorts.passUp);
    }
    public static UpPasser getInstance(){
        if(m_instance == null){
            m_instance = new UpPasser();
        }
        return m_instance;
    }
    public void upPass(){
        m_upPassController.set(Speeds.passerSpeed);
    }
    public void stop(){
        m_upPassController.set(0);
    }
}
