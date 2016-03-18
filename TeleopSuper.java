package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by pev on 10/31/2015.
 */
// Version 1.20
public class TeleOp extends OpMode {
    DcMotor MotorRightDrive;
    DcMotor MotorLeftDrive;
    DcMotor MotorRightDriveCenter;
    DcMotor MotorLeftDriveCenter;
    DcMotor BrazoDerecho;
    DcMotor BrazoDerechoUp;
    DcMotor BrazoIzquierdo;
    DcMotor BrazoIzquierdoUp;
    Servo LeftTrigger;
    Servo RightTrigger;
    Servo Defense;
    Servo Climbers;
    Servo DefenseDerecha;
    public void init() {

        MotorRightDrive = hardwareMap.dcMotor.get("right.drive");
        MotorLeftDrive = hardwareMap.dcMotor.get("left.drive");
        MotorRightDriveCenter = hardwareMap.dcMotor.get("right.center");
        MotorLeftDriveCenter = hardwareMap.dcMotor.get("left.center");
        MotorLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        MotorLeftDriveCenter.setDirection(DcMotor.Direction.REVERSE);

        BrazoDerecho = hardwareMap.dcMotor.get("brazo.derecho");
        BrazoDerechoUp = hardwareMap.dcMotor.get("brazo.derecho.up");


        BrazoIzquierdo = hardwareMap.dcMotor.get("brazo.izquierdo");
        BrazoIzquierdoUp = hardwareMap.dcMotor.get("brazo.izquierdo.up");
        BrazoIzquierdoUp.setDirection(DcMotor.Direction.REVERSE);
        BrazoIzquierdo.setDirection(DcMotor.Direction.REVERSE);

        LeftTrigger = hardwareMap.servo.get("left.trigger");
        RightTrigger = hardwareMap.servo.get("right.trigger");
        LeftTrigger.setDirection(Servo.Direction.REVERSE);
        Climbers = hardwareMap.servo.get("climbers");
        Defense = hardwareMap.servo.get("protector");
        DefenseDerecha = hardwareMap.servo.get("protector.derecha");
        DefenseDerecha.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void start(){
        Defense.setPosition(0);
        DefenseDerecha.setPosition(0);


    }
    public void loop() {
        double valor = gamepad1.right_trigger;
        //Driver1
        if (valor>0) {
            MotorLeftDrive.setPower(-gamepad1.right_stick_y);
            MotorRightDrive.setPower(-gamepad1.left_stick_y);
            MotorLeftDriveCenter.setPower(-gamepad1.right_stick_y);
            MotorRightDriveCenter.setPower(-gamepad1.left_stick_y);
            Defense.setPosition(1);
            DefenseDerecha.setPosition(1);

        }
        else if (gamepad1.x){
            Defense.setPosition(1);
            DefenseDerecha.setPosition(0.98);

        }
        else if(gamepad1.y){
            Defense.setPosition(0.98);
            DefenseDerecha.setPosition(1);
        }


        else {
            MotorRightDriveCenter.setPower(gamepad1.right_stick_y);
            MotorLeftDriveCenter.setPower(gamepad1.left_stick_y);
            MotorRightDrive.setPower(gamepad1.right_stick_y);
            MotorLeftDrive.setPower(gamepad1.left_stick_y);
            Defense.setPosition(0.7);
            DefenseDerecha.setPosition(0.7);
        }
        //LeftTrigger
        if (gamepad1.left_bumper){
            LeftTrigger.setPosition(0.5);

        }
        else{
            LeftTrigger.setPosition(0);

        }
        //RightTrigger
        if(gamepad1.right_bumper){
            RightTrigger.setPosition(0.5);
        }
        else{
            RightTrigger.setPosition(0);
        }
        //Driver2
        //Brazo Derecho
        BrazoDerecho.setPower(gamepad2.right_stick_y);
        BrazoDerechoUp.setPower(gamepad2.right_trigger);

        if (gamepad2.right_bumper) {
            BrazoDerechoUp.setPower(-1);
        }


       //BrazoIzquierdo
        BrazoIzquierdo.setPower(gamepad2.left_stick_y);
        BrazoIzquierdoUp.setPower(gamepad2.left_trigger);

        if (gamepad2.left_bumper){
            BrazoIzquierdoUp.setPower(-1);

        }
        if (gamepad2.y){

            Climbers.setPosition(1);
        }
        else{

            Climbers.setPosition(0.05);

        }



        telemetry.addData("Game pad value", gamepad2.right_trigger);


    }




}
