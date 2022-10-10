package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DownPasser extends SubsystemBase implements Constants{
    private MotorController m_passController;
    private static DownPasser m_instance = null;
    private DownPasser() {
        //initilize controller by type
    }
    public static DownPasser getInstance(){
        if(m_instance == null){
            m_instance = new DownPasser();
        }
        return m_instance;
    }
    public void pass(){
        m_passController.set(Speeds.passerSpeed);
    }
    public void stop(){
        m_passController.set(0);
    }
}
