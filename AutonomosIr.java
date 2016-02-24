package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by pev on 1/22/2016.
 */
public class AutonomousExperimental extends OpMode{
    final static double MOTOR_POWER = 0.45; // Higher values will cause the robot to move faster

    DcMotor MotorRightDrive;
    DcMotor MotorLeftDrive;
    IrSeekerSensor IrSeeker;
    Servo climbers;
    DcMotor Grabber;
    @Override
    public void init() {
        MotorRightDrive = hardwareMap.dcMotor.get("right.drive");
        MotorLeftDrive = hardwareMap.dcMotor.get("left.drive");
        MotorLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        IrSeeker = hardwareMap.irSeekerSensor.get("ir.seeker");
        Grabber = hardwareMap.dcMotor.get("grabber");
        climbers = hardwareMap.servo.get("climbers");

    }
    @Override
    public void start() {


        MotorLeftDrive.setPower(MOTOR_POWER);
        MotorRightDrive.setPower(MOTOR_POWER);
        try {
            Thread.sleep(2200); //1000 milliseconds is one second.

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
    public void loop() {

        if (IrSeeker.signalDetected()) {
            double angle = 0;
            double strength = 0;

            angle = IrSeeker.getAngle();
            strength = IrSeeker.getStrength();

            if (angle < -20 && strength < 0.44) {
                //Debemos ir para la izquierda
                MotorLeftDrive.setPower(MOTOR_POWER);
                MotorRightDrive.setPower(-MOTOR_POWER);
            } else if (angle > 20 && strength < 0.44) {
                // Debemos irnos para la derecha

                MotorRightDrive.setPower(MOTOR_POWER);
                MotorLeftDrive.setPower(-MOTOR_POWER);


            } else if (strength < 0.44) {
                MotorRightDrive.setPower(MOTOR_POWER);
                MotorLeftDrive.setPower(MOTOR_POWER);
            } else {
                MotorRightDrive.setPower(0);
                MotorLeftDrive.setPower(0);
                climbers.setPosition(0.7);
            }

            telemetry.addData("strength", strength);
            telemetry.addData("angle", angle);
            DbgLog.msg(IrSeeker.toString());

        }

        else{
            MotorRightDrive.setPower(.30);
            MotorLeftDrive.setPower(-.30);
        }

    }
}
