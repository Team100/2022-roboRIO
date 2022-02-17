// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.CANSparkMax.IdleMode;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DrivetrainConstants {
        public static final class DrivetrainMotors {
            public static final class LeftMaster {
                public static final int CAN_ID = 18;
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.None;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class LeftFollower {
                public static final int CAN_ID = 19;
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.FollowMaster;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class RightMaster {
                public static final int CAN_ID = 10;
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.InvertMotorOutput;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class RightFollower {
                public static final int CAN_ID = 11;
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.FollowMaster;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }
        }
        public static final class DrivetrainControls {
            public static final double RAMP_LIMIT = 0.01;
        }
    }
    public static final class IntakeConstants {
        public static final class PivotConstants {
            public static final int DOWN_POSITION = 30;
        }
        public static final class IntakeMotionParameters {
            public static final double INTAKE_SPINNER_PERCENT_OUTPUT = 0.7;
            public static final double INTAKE_PIVOT_PERCENT_OUTPUT = 0.1;
            public static final double INTAKE_ZERO = 0;

            public static final int ZEROED_ENCODER_TICKS = 65;

            // public static final double INTAKE_DOWN_DEGREES = EncoderConversionFactors
            //         .CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(-40);  //Ultimate down = 22ticks @ ~-15deg
            // public static final double INTAKE_UP_DEGREES = EncoderConversionFactors
            //         .CONVERT_ANGLE_TO_MA3_ENCODER_TICKS(90);

            public static final int ACCEPTABLE_ERROR_TICKS = 10;

            public static final double KP = 6;
            public static final double KI = 0;
            public static final double KD = 3;
            public static final double KF = 0;
        }

        public static final class IntakeMotors {
            public static final class IntakeSpin {
                public static final int CAN_ID = 1;

                public static final boolean INVERT = true;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

                public static final IdleMode NEUTRAL_MODE = IdleMode.kCoast;
            }

            public static final class IntakePivot {
                public static final int CAN_ID = 5;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = .5;
                public static final double PEAK_OUTPUT_REVERSE = -.5;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

                public static final SparkMaxAnalogSensor.Mode ANALOG_MODE = SparkMaxAnalogSensor.Mode.kAbsolute;

                public static final IdleMode NEUTRAL_MODE = IdleMode.kBrake;
            }
        }
    }
}
