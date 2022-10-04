package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.SubSystems.Constants;

public class Controls {
    
    public static Joystick m_leftJoystick=new Joystick(Constants.JoystickPorts.leftJoystick);
    public static Joystick m_rightJoystick=new Joystick(Constants.JoystickPorts.rightJoystick);
    public static JoystickButton m_b0  =new JoystickButton(m_leftJoystick, 0);//TODO: decide which button
    public static JoystickButton m_b1  =new JoystickButton(m_leftJoystick, 1);//TODO: decide which button

}
