package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Passer extends SubsystemBase implements Constants{
    private MotorController m_passController;
    private static Passer m_instance = null;
    private Passer() {
        //initilize controller by type
    }
    public static Passer getInstance(){
        if(m_instance == null){
            m_instance = new Passer();
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
