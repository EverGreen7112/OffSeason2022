package frc.robot.Commands;
import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.SubSystems.DownPasser;
import frc.robot.SubSystems.Shooter;

public class Shoot extends CommandBase implements Constants {
    private Supplier<Boolean> m_startPassing;
    private double m_diffSec = 0;
    private double m_startTime = 0;


    public Shoot(Supplier<Boolean> startPassing) {
        addRequirements(Shooter.getInstance(), DownPasser.getInstance());
        m_startPassing = startPassing;
    }
    public Shoot() {
        addRequirements(Shooter.getInstance(), DownPasser.getInstance());
        m_startPassing = ()->m_diffSec > Times.secondsFromShottToPass;
    }

    @Override
    public void initialize() {
        m_startTime = System.currentTimeMillis();
        Shooter.getInstance().shoot();
    }

    @Override
    public void execute() {
        m_diffSec = (System.currentTimeMillis() - m_startTime);
        if (m_startPassing.get()) {
            DownPasser.getInstance().pass();
        }
    }

    @Override
    public void end(boolean interrupted) {
        DownPasser.getInstance().stop();
        Shooter.getInstance().stop();
    }
}
