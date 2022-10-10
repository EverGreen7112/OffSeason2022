package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DownPasser extends SubsystemBase implements Constants{
    private MotorController m_passDownController;
    private static DownPasser m_instance = null;
    private DownPasser() {
        m_passDownController = new WPI_VictorSPX(MotorPorts.downPasser);
    }
    public static DownPasser getInstance(){
        if(m_instance == null){
            m_instance = new DownPasser();
        }
        return m_instance;
    }
    public void pass(){
        m_passDownController.set(Speeds.passerSpeed);
    }
    public void stop(){
        m_passDownController.set(0);
    }
}
