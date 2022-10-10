package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.SubSystems.Collector;
import frc.robot.SubSystems.DownPasser;

public class Collect extends CommandBase implements Constants {
    private boolean m_finishedPhase1 = false;
    private boolean m_finishedPhase2 = false;
    private long m_finishTime;

    public Collect() {
        addRequirements(Collector.getInstance(), DownPasser.getInstance());
    }

    @Override
    public void initialize() {
        m_finishTime = System.currentTimeMillis();
        Collector.getInstance().lowerCollector();
        Collector.getInstance().collect();
        DownPasser.getInstance().pass();
        while (System.currentTimeMillis() - m_finishTime < Times.lowerCollectorSec * 1000)
            ;
        Collector.getInstance().stopLifting();

    }

    @Override
    public void end(boolean interrupted) {
        m_finishTime = System.currentTimeMillis();
        Collector.getInstance().stopCollecting();
        Collector.getInstance().liftCollector();
        while (System.currentTimeMillis() - m_finishTime < Times.liftCollectorSec * 1000)
            ;
        Collector.getInstance().stopLifting();
        DownPasser.getInstance().stop();

        ;

    }
}
