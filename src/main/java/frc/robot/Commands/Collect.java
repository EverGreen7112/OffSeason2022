package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SubSystems.Collector;
import frc.robot.SubSystems.Constants;
import frc.robot.SubSystems.Passer;

public class Collect extends CommandBase implements Constants {
    private boolean m_finishedPhase1 = false;
    private boolean m_finishedPhase2 = false;
    private long m_finishTime;

    public Collect() {
        addRequirements(Collector.getInstance(), Passer.getInstance());
    }

    @Override
    public void initialize() {
        Collector.getInstance().collect();
    }

    @Override
    public void end(boolean interrupted) {
        m_finishTime = System.currentTimeMillis();
        Collector.getInstance().stop();
        Passer.getInstance().pass();
        while (System.currentTimeMillis() - m_finishTime < Times.passSeconds * 1000)
            ;
        Passer.getInstance().stop();

    }
}
