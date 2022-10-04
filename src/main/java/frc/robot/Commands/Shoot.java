package frc.robot.Commands;

import java.util.function.Function;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Constants;
import frc.robot.SubSystems.Passer;
import frc.robot.SubSystems.Shooter;

public class Shoot extends CommandBase implements Constants {
    private Supplier<Boolean> m_startPassing;
    private double m_diffSec = 0;
    private double m_startTime = 0;


    public Shoot(Supplier<Boolean> startPassing,Function<Double,Double> distnceToPower) {
        addRequirements(Shooter.getInstance(), Passer.getInstance());
        m_startPassing = startPassing;
    }
    public Shoot() {
        addRequirements(Shooter.getInstance(), Passer.getInstance());
        m_startPassing = ()->m_diffSec > Times.secondsFromShottToPass;
    }

    @Override
    public void initialize() {
        m_startTime = System.currentTimeMillis();
        //TODO choose here which shooting type we use from shoot or shotByDistance
        Shooter.getInstance().shoot();
    }
    @Override
    public void execute() {
        m_diffSec = (System.currentTimeMillis() - m_startTime);
        if (m_startPassing.get()) {
            Passer.getInstance().pass();
        }
    }
    @Override
    public void end(boolean interrupted) {
        Passer.getInstance().stop();
        Shooter.getInstance().stop();
    }
}
