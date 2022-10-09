// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public interface Constants {

    public static interface MotorPorts {
        public static final int
        // chassisRightFront = 7,
        // chassisRightMiddle = 9,
        // chassisRightBack = 11,

        // chassisLeftFront = 10,
        // chassisLeftMiddle = 6,
        // chassisLeftBack = 2,
        chassisRightFront = 0,
                chassisRightMiddle = 1,
                chassisLeftFront = 2,
                chassisLeftMiddle = 3;

        // FLY_WHEEL = 1,

        // collectorCollect = 5,
        // collectorOpen = 3,

        // storage = 4,

        // climber = 0;
    }

    public static interface DigitalPorts {
        public static final int
        // missing ports
        switchUp = 3,
                switchDown = 0,

                switchClimber = 2;
    }

    public static interface JoystickPorts {
        public static final int rightJoystick = 0,
                leftJoystick = 1,
                operator = 2;
    }

    // Detail the Buttons of each Joystick
    public static interface ButtonPorts {
        public static final int
        // missing ports
        collectorOpen = 4,
                collectorClose = 2,
                collectorCollect = 1,
                collectorUncollect = 3,

                climberDown = 5,
                climberUp = 7,

                storageUp = 8,
                storageDown = 6;

        // Shooter;
    }

    public static interface CameraPorts {
        public static int
        // missing ports
        camera = 0;
    }

    public static interface Speeds {
        public static double
        // motorSpeed = 0.6,
        collectorClose = -0.05,
                collectorOpen = 0.1,
                collectorCollect = 0.55,

                climberMotor = 1,

                passerSpeed = 1,

                SHOOT_SPEED = 0.5,

                extraSpeed = 0.1;
        public static Supplier<Double> motorSpeed = () -> {
            return SmartDashboard.getNumber("motorSpeed", 0.1);
        };

    }

    public static interface Times {
        public static double autoCollectSeconds = 2;
        public static double passSeconds =0.3;
        public static double secondsFromShottToPass =0.3;
    }

    public static interface DriveConstants {
        public static double kS = 1.1056,
                kV = 2.6472,
                kA = 0.86768,
                kP = 4.2727,
                kTrackWidth = 0.45,
                kRamseteB = 2, // supposedly fine with any robot
                kRamseteZeta = 0.7; // supposedly fine with any robot

        public static DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidth);
    }

    public static interface PID {
        public static final double

        // can probably remove these later

        // Angle PID Variables \\
        ANGLE_KP = 0.04,
                ANGLE_KI = 0.001,
                ANGLE_KD = 0.006,
                ANGLE_TOLERANCE = 0.25,

                // Velocity PID Variables \\
                VELOCITY_KP = 0.0468 * 1.7,
                VELOCITY_KI = 0.0011,
                VELOCITY_KD = 0.01,

                // Distance PID Varibles \\
                DISTANCE_KP = 0,
                DISTANCE_KI = 0,
                DISTANCE_KD = 0,
                DISTANCE_TOLERANCE = 1;
    }

    public static interface EncoderPorts {
        public static final int ENCODER_RIGHT_ONE = 8,
                ENCODER_RIGHT_TWO = 9,

                ENCODER_LEFT_ONE = 0,
                ENCODER_LEFT_TWO = 1;
    }

}