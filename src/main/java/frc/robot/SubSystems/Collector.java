package frc.robot.SubSystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase implements Constants{
    private MotorController m_collectController;
    private static Collector m_instance = null;
    private Collector() {
        //initilize controllers by type
    }
    public static Collector getInstance(){
        if(m_instance == null){
            m_instance = new Collector();
        }
        return m_instance;
    }
    public void collect(){
        m_collectController.set(Speeds.collectorCollect);
    }
    public void stop(){
        m_collectController.set(0);
    }
}
