package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controls;
import frc.robot.SubSystems.Chassis;
import frc.robot.SubSystems.Constants;

public class DriveBySuplliers extends CommandBase{

    private static DriveBySuplliers m_instance=null;

    private Supplier<Double> m_rightSupllier;
    private Supplier<Double> m_leftSupllier;

    private DriveBySuplliers(Supplier<Double> left, Supplier<Double> right){
        addRequirements(Chassis.getInstance());
        m_rightSupllier=right;
        m_leftSupllier=left;
    }

    public static DriveBySuplliers getInstance(){
        if(m_instance==null){
            m_instance=new DriveBySuplliers(new Supplier<Double>(){
                   public Double get(){
                          return Controls.m_leftJoystick.getY() ; }    
            }
            , new Supplier<Double>(){
                   public Double get(){
                          return Controls.m_rightJoystick.getY();
                        }    
            });
        }
        return m_instance;
    }
    
    @Override
    public void execute() {
        Chassis.getInstance().driveTank(-m_rightSupllier.get(), -m_leftSupllier.get());
        System.out.println(Controls.m_rightJoystick.getY() + "right");
        System.out.println(Controls.m_leftJoystick.getY() + "left");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        Chassis.getInstance().stop();
    }

}