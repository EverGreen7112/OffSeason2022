package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Collector extends SubsystemBase implements Constants{
    private MotorController m_collectController;
    private MotorController m_upDownCollectController;
    private static Collector m_instance = null;
    
    private Collector() {
        m_collectController = new WPI_VictorSPX(MotorPorts.collect);
        m_upDownCollectController = new WPI_VictorSPX(MotorPorts.collectUpAndDown);
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
    public void liftCollector(){
        m_upDownCollectController.set(Speeds.liftCollector);
    }
    public void lowerCollector(){
        m_upDownCollectController.set(Speeds.lowerCollector);
    }
    public void stopCollecting(){
        m_collectController.set(0);
    }
    public void stopLifting(){
        m_upDownCollectController.set(0);
    }
}
