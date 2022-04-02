// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class AutonomousConstants {
        public static final class DipSwitches{
            public static final int FIRST_BALL_OPTION_ID = 3;
            public static final int SECOND_BALL_OPTION_ID = 4;
            public static final int YEET_OR_LEAVE = 5;
        }
    }
    public static final class DrivetrainConstants {
        public static final class Autonomous {
            public static final class Turning {
                public static final double TURN_SPEED = 0.25;
                public static final double TURN_ADJUSMENT =237;

                public static final int HHH_TURN = 74;
                // public static final int SECOND_HHH_TURN = -70;
                //public static final int SECOND_HHH_OFFSET = 46;
                public static final double TURNING_PP = 0.0006;
                public static final int SECOND_HHH_TURN = 34;
            }
            public static final class Distance {
                public static final int HIGH_GOAL_SHOT = -40000;
                public static final double FIRST_BALL_EXPECTED_STOP_FROM_TARMACK_EDGE = -50000;
                public static final double THIRD_BALL_OFFSET_FROM_SHOOT_POSITION = -85000;
            }
            public static final class Offsets {
                public static final int AUTO_H_H_OFFSET = 0;//-56000;
                public static final int AUTO_L_H_OFFSET = -41000;
                public static final int AUTO_LF_H_OFFSET = -40000;
                public static final double STEP_TWO_RETURN_OFFSET = -10000;
            }
            public static final class Speeds {
                public static final double DRIVE_REVERSE_SPEED = -0.15;
                public static final double DRIVE_FORWARD_SPEED = 0.15;
            }
        }
        public static final class DrivetrainMotion{
            public static final double ALIGN_SPEED = -0.1;
            public static final double SLOW_SPEED = 0.25;
        }
        public static final class DrivetrainSensors{
        }
        public static final class DrivetrainMotors {
            public static final class LeftMaster {
                public static final int CAN_ID = 18; //18
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.None;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                // public static final double NOMINAL_OUTPUT_FORWARD = 0.05;
                // public static final double NOMINAL_OUTPUT_REVERSE = -0.05;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class LeftFollower {
                public static final int CAN_ID = 19; //19
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.FollowMaster;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                // public static final double NOMINAL_OUTPUT_FORWARD = 0.05;
                // public static final double NOMINAL_OUTPUT_REVERSE = -0.05;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class RightMaster {
                public static final int CAN_ID = 10; //10
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.InvertMotorOutput;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                // public static final double NOMINAL_OUTPUT_FORWARD = 0.05;
                // public static final double NOMINAL_OUTPUT_REVERSE = -0.05;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }

            public static final class RightFollower {
                public static final int CAN_ID = 11; //11
                public static final double KP = 0;
                public static final double KI = 0;
                public static final double KD = 0;
                public static final double KF = 0;
                public static final InvertType INVERTED = InvertType.FollowMaster;
                public static final boolean SENSOR_PHASE = false;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                // public static final double NOMINAL_OUTPUT_FORWARD = 0.05;
                // public static final double NOMINAL_OUTPUT_REVERSE = -0.05;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }
        }
        public static final class DrivetrainControls {
            public static final double RAMP_LIMIT = 0.2;
            public static final double ERROR_ADJUSTMENT_DRIVE = 0.0000025;
        }
    }
    public static final class IntakeConstants {
        public static final class IntakeSensors{
            public static final class IntakePot{
                public static final int ID = 3;
                public static final int POT_OFFSET = 0;
                public static final int POT_ADJUSTMENT_FACTOR = 100;
            }
        }
        public static final class PivotConstants {
            public static final double UP_POSITION = 26.5;
            public static final double DOWN_POSITION = 2.5;
            public static final double UP_SETPOINT = 25;

            public static final double CYCLE_COUNT = 30;
        }
        public static final class IntakeMotionParameters {
            public static final double TRAPAZOID_PROFILE_MAX_VEL = 192;
            public static final double TRAPAZOID_PROFILE_MAX_ACL = 105;
            public static final double PID_TOLERANCE = 0.1;
            public static final double INTAKE_UP_TIMEOUT = 2;
            public static final double INTAKE_UP_TIME_TO_RESTART = 2;
            public static final double INTAKE_SPINNER_PERCENT_OUTPUT = -0.375; //-0.30
            public static final double INTAKE_SPINNER_SLOW_PERCENT_OUTPUT = -0.15; //-0.30
            public static final double INTAKE_PIVOT_PERCENT_OUTPUT_DOWN = -0.22;
            public static final double INTAKE_PIVOT_PERCENT_OUTPUT_DOWN_DECEL = 0.06;
            // public static final double INTAKE_ZERO = 0;
            // public static final double INITIAL_POSITION = 0;

            public static final double KP = 0.28;//0.2
            public static final double KI = 0.03;
            public static final double KD = 0.017;
            public static final double KF = 0;
            public static final double PP_ADJUSTMENT_CONSTANT = 16;
        }

        public static final class IntakeMotors {
            public static final class IntakeSpin {
                public static final int CAN_ID = 1; //1

                public static final boolean INVERT = true;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = false;
                public static final int CURRENT_LIMIT = 35;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;

                public static final double NOMINAL_OUTPUT_FORWARD = 0;
                public static final double NOMINAL_OUTPUT_REVERSE = 0;

                public static final IdleMode NEUTRAL_MODE = IdleMode.kCoast;
            }

            public static final class IntakePivot {
                public static final int CAN_ID = 3; //2

                public static final InvertType INVERT = InvertType.InvertMotorOutput;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 25;
                public static final double OPEN_LOOP_RAMP = 0.1;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;

                public static final SparkMaxAnalogSensor.Mode ANALOG_MODE = SparkMaxAnalogSensor.Mode.kAbsolute;

                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Brake;
            }
        }
    }
    public static final class ShooterConstants {
        public static final class ShooterMotionParameters {
            public static final double SHOOTER_PERCENT_OUTPUT = 0.2; //0.17 //0.175

            public static final double SHOOTER_VELOCITY_LOW = -2350; //0.17 //0.175 //-2500
			public static final double NOMINAL_LOW_VELOCITY = 2330;

            public static final double SHOOTER_VELOCITY_LOW_FAR = -2500;
			public static final double NOMINAL_LOW_FAR_VELOCITY = 2480;

            public static final double SHOOTER_VELOCITY_HIGH = -3950; //0.17 //0.175
            public static final double NOMINAL_HIGH_VELOCITY = 3930;

            public static final double SHOOTER_VELOCITY_HIGH_FAR = -5320; //0.17 //0.175
            public static final double NOMINAL_HIGH_VELOCITY_FAR = 5300;

            
            public static final double KP = 0.00012;
            public static final double KI = 0;
            public static final double KD = 0;
            public static final double KF = 0.00008947; // 0.17/1900
        }

        public static final class ShooterMotors {
            public static final class Shooter {
                public static final int CAN_ID = 2;
    
                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;
    
                public static final int TIMEOUT = 10;
    
                public static final boolean ENABLE_CURRENT_LIMIT = false;
                public static final int CURRENT_LIMIT = 55;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                
                public static final IdleMode NEUTRAL_MODE = IdleMode.kCoast;
            }
            public static final class ShooterFollower {
                public static final int CAN_ID = 27;
    
                public static final boolean INVERT = true;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;
    
                public static final int TIMEOUT = 10;
    
                public static final boolean ENABLE_CURRENT_LIMIT = false;
                public static final int CURRENT_LIMIT = 55;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                
                public static final IdleMode NEUTRAL_MODE = IdleMode.kCoast;
            }
        }
    }
    public static final class IndexerConstants {
        public static final class IndexerSensors {
            public static final class FrontSensor {
                public static final int ID = 7;
            }

            public static final class RearSensor {
                public static final int ID = 8;
            }
        }

        public static final class IndexerMotionParameters {
            public static final double STAGE_ONE_PERCENT_OUTPUT_FORWARD = 0.9; //
            public static final double STAGE_TWO_PERCENT_OUTPUT_FORWARD = 0.7;

            public static final double STAGE_ONE_PERCENT_OUTPUT_BACKWARD = -0.9;
            public static final double STAGE_TWO_PERCENT_OUTPUT_BACKWARD = -0.7;
        }

        public static final class IndexerMotors {
            public static final class IndexerStageOne {
                public static final int CAN_ID = 5;

                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = false;
                public static final int CURRENT_LIMIT = 80;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;

                public static final IdleMode NEUTRAL_MODE = IdleMode.kBrake;
            }

            public static final class IndexerStageTwo {
                public static final int CAN_ID = 4;
              
                public static final boolean INVERT = false;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;

                public static final int TIMEOUT = 10;

                public static final boolean ENABLE_CURRENT_LIMIT = false;
                public static final int CURRENT_LIMIT = 80;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;

                public static final IdleMode NEUTRAL_MODE = IdleMode.kBrake;
            }
        }
    }

    public static final class ClimberConstants {
        public static final class ClimberSensors{
            public static final class MainHooks{
                public static final class LeftSensor{
                    public static final int ID = 0;
                }
                public static final class RightSensor{
                    public static final int ID = 2;
                }
            }
            public static final class StationaryHooks{
                public static final class LeftSensor{
                    public static final int ID = 6;
                }
                public static final class RightSensor{
                    public static final int ID = 1;
                }
            }
            public static final class Homing{
                public static final int ID = 6;
                public static final double HomingSpeed = -0.5;
            }
            public static final class Tilt{
                public static final int ID = 2;
                public static final double POT_ADJUSTMENT_FACTOR = -348.837209;//105;
                public static final double POT_OFFSET = 106.5349;//274;
            }
        }
        public static final class ClimberMotionParameters {
            public static final double CLIMBER_PERCENT_OUTPUT = 0.85;
            public static final double CLIMBER_DESCEND_PERCENT_OUTPUT = 0.3;
            // public static final double CLIMBER_ZERO = 0;

            public static final double TILT_PERCENT_OUTPUT = 0.85;

            public static final int CLIMBER_MID_BAR = -250000; //-243000;
            public static final int CLIMBER_TOP = -234000; //-243000;
            public static final int CLIMBER_LOW_BAR_TOP = -110000;
            public static final int CLIMBER_BOTTOM = 5000;
            public static final double TILT_START = 7000;
            public static final double STATIONARY_LOCK_ANGLE = 21; //23.5; //22;

            public static final double EXTEND_START_ANGLE = 0;
            public static final double NEXT_BAR_ANGLE = -5;
            public static final double NEXT_BAR_DISTANCE = -293000;

            public static final double NEXT_BAR_GRAB_ANGLE = 5;

            public static final double BAR_HEIGHT_OFFSET = 10000;//I'd like to set off to a bar if you caatchmydrift
            public static final double STATIONARY_REMOVAL_OFFSET = 10000;
            public static final double CLIMBER_DESCEND_SAFTEY_SHUTOFF = -20000;
            public static final double CLIMBER_MAX_EXTEND = -320000;
            public static final double CLIMBER_TILT_BACK_ANGLE_DISTANCE_THING = -150000;
            public static final double CLIMBER_CLUTCH_SAFTEY_SPEED = 0.35;
            public static final double TILT_NEXT_BAR_HEIGHT_OFFSET = 500;
            public static final double PIVOT_AROUND_NEXT_BAR_MAIN_POSITION = -125000;
            public static final double SLOW_TILT_PERCENT_OUTPUT = 0.45;
            public static final double SLOW_CLIMBER_PERCENT_OUTPUT = 0.45;
            public static final double STATIONARIES_MISS_BAR_ANGLE = -5;
            public static final double MAX_NEGATIVE_TILT = -28;
            public static final double HOOK_ZERO_SPEED = -0.2;
            public static final double CLIMBER_TILT_ZERO_SPEED = 0.3;
        }

        public static final class ClimberControls {
            public static final double TILT_CONTROL_DEADZONE = 0.05;
            public static final double WINCH_CONTROL_DEADZONE = 0.05;
        };

        public static final class ClimberMotors {
            public static final class Winch {
                public static final int CAN_ID = 17;
    
                public static final InvertType INVERT = InvertType.None;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;
    
                public static final int TIMEOUT = 10;
    
                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 40;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Brake;
            }
    
            public static final class Tilt {
                public static final int CAN_ID = 12;
    
                public static final InvertType INVERT = InvertType.None;
                public static final int FEEDBACK_PORT = 0;
                public static final boolean SENSOR_PHASE = false;
    
                public static final int TIMEOUT = 10;
    
                public static final boolean ENABLE_CURRENT_LIMIT = true;
                public static final int CURRENT_LIMIT = 40;
                public static final double OPEN_LOOP_RAMP = 0;
                public static final double PEAK_OUTPUT_FORWARD = 1;
                public static final double PEAK_OUTPUT_REVERSE = -1;
                public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
            }
        }
    }
}
