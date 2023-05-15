package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DrivingTeleop")
    public class Driving  extends LinearOpMode {
    Servo claw;
    Servo tilt;
    DcMotor frontLeft, frontRight, backLeft, backRight;



    @Override
    public void runOpMode() throws InterruptedException {
        claw = hardwareMap.servo.get("claw");
        tilt = hardwareMap.servo.get("tilt");
        frontLeft = hardwareMap.dcMotor.get("leftFront");
        frontRight = hardwareMap.dcMotor.get("rightFront");
        backLeft = hardwareMap.dcMotor.get("leftBack");
        backRight = hardwareMap.dcMotor.get("rightBack");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.x) {
                claw.setPosition(0.35);
            }
            if (gamepad1.b) {
                claw.setPosition(0);

            }
            if (gamepad1.y){
                tilt.setPosition(0.22);
            }
            if (gamepad1.a){
                tilt.setPosition(0.35);
            }

            double y = -gamepad1.left_stick_y;
            double x  = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            frontRight.setPower(y-x - rx);
            frontLeft.setPower(y+x + rx);
            backRight.setPower(y+x-rx);
            backLeft.setPower(y-x + rx);



        }

    }
}
