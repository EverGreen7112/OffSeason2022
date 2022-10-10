package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Times;
import frc.robot.SubSystems.Collector;
import frc.robot.SubSystems.DownPasser;

public class AutoCollect extends CommandBase {
    private boolean m_finishedPhase1 = false;
    private boolean m_finishedPhase2 = false;
    private long m_startTime;

    public AutoCollect() {
        addRequirements(Collector.getInstance(), DownPasser.getInstance());
    }

    @Override
    public void initialize() {
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        if(!m_finishedPhase1){
            Collector.getInstance().collect();
            if(System.currentTimeMillis() - m_startTime >Times.autoCollectSeconds * 1000){
                Collector.getInstance().stopCollecting();
                m_finishedPhase1 = true;
            }
        }
        if(m_finishedPhase1){
            DownPasser.getInstance().pass();
            if(System.currentTimeMillis()- m_startTime >(Times.autoCollectSeconds +Times.passSeconds)*60 * 1000){
                DownPasser.getInstance().stop();
                m_finishedPhase2 = true;
            }
        }      
    }

    @Override
    public boolean isFinished() {
        return m_finishedPhase2;
    }
}
