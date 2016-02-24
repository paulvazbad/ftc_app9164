package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by pev on 2/5/2016.
 */
public class AutonomousNew extends OpMode {
    DcMotor MotorRightDrive;
    DcMotor MotorLeftDrive;
    DcMotor MotorRightDriveCenter;
    DcMotor MotorLeftDriveCenter;
    Servo Defense;
    Servo LeftTrigger;

    @Override
    public void init() {
        MotorRightDrive = hardwareMap.dcMotor.get("right.drive");
        MotorLeftDrive = hardwareMap.dcMotor.get("left.drive");
        MotorRightDriveCenter = hardwareMap.dcMotor.get("right.center");
        MotorLeftDriveCenter = hardwareMap.dcMotor.get("left.center");
        MotorLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        MotorLeftDriveCenter.setDirection(DcMotor.Direction.REVERSE);
        Defense = hardwareMap.servo.get("protector");
        LeftTrigger = hardwareMap.servo.get("left.trigger");

    }
    @Override
    public void start(){
        Defense.setPosition(1);
        LeftTrigger.setPosition(0);
        if (this.time <= 5) {
            // from 0 to 1 seconds, run the motors for five seconds.
            MotorRightDriveCenter.setPower(0.15);
            MotorLeftDriveCenter.setPower(0.18);
            MotorRightDrive.setPower(0.15);
            MotorLeftDrive.setPower(0.18);


        } else if (this.time > 5 && this.time <= 8.5) {
            // between 5 and 8.5 seconds, point turn right.
            MotorRightDriveCenter.setPower(0);
            MotorLeftDriveCenter.setPower(0);
            MotorRightDrive.setPower(0);
            MotorLeftDrive.setPower(0);

        }
        }



    public void loop(){

    }
    public void stop(){

    }
}

