package frc.robot.SubSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {

    private MotorControllerGroup rightMotors;
    private MotorControllerGroup leftMotors;

    private MotorController m_rightEngineTalonFront, m_rightEngineTalonMiddle, m_rightEngineTalonBack;
    private MotorController m_leftEngineTalonFront, m_leftEngineTalonMiddle, m_leftEngineTalonBack;

    // private WPI_VictorSPX m_rightEngineVictorFront, m_rightEngineVictorMiddle, m_rightEngineVictorBack, m_leftEngineVictorFront, m_leftEngineVictorMiddle, m_leftEngineVictorBack;
    private static Chassis m_instance=null;

    private Chassis(){
        m_leftEngineTalonFront=new WPI_VictorSPX(Constants.MotorPorts.chassisLeftFront);
        m_leftEngineTalonMiddle=new WPI_VictorSPX(Constants.MotorPorts.chassisLeftMiddle);
        m_leftEngineTalonBack = new WPI_VictorSPX(Constants.MotorPorts.chassisLeftBack);
        
        // m_leftEngineVictorBack=new WPI_VictorSPX(Constants.MotorPorts.chassisLeftBack);
        m_rightEngineTalonFront=new WPI_VictorSPX(Constants.MotorPorts.chassisRightFront);
        m_rightEngineTalonMiddle=new WPI_VictorSPX(Constants.MotorPorts.chassisRightMiddle);
        m_rightEngineTalonBack = new WPI_VictorSPX(Constants.MotorPorts.chassisRightBack);


        leftMotors=new MotorControllerGroup(m_leftEngineTalonFront, m_leftEngineTalonMiddle,m_leftEngineTalonBack);
        rightMotors=new MotorControllerGroup(m_rightEngineTalonFront, m_rightEngineTalonMiddle,m_rightEngineTalonBack);
        // rightMotors.setInverted(true);
    }

    public static Chassis getInstance(){
        if(m_instance==null){
            m_instance=new Chassis();
        }
        return m_instance;
    }

    public void driveTank(double lSpeed, double rSpeed){
        rightMotors.set(rSpeed);
        leftMotors.set(lSpeed);
    }

    public void turnRight(double speed){
       driveTank(speed, -speed);
    }

    public void turnLeft(double speed){
        driveTank(-speed, speed);
    }

    public void driveStraight(double speed){
        driveTank(speed, speed);
    }

    public void stop(){
        driveTank(0.0, 0.0);
    }
}
