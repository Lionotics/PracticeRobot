package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DrivingTeleop")
    public class Driving  extends LinearOpMode {
    Servo claw;
    DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor lift1, lift2;
    int motorTop = 10000;

    enum Target {No_TARGET,UP,DOWN};
    Target currentTarget = Target.No_TARGET;





    @Override
    public void runOpMode() throws InterruptedException {
        claw = hardwareMap.servo.get("claw");
        lift1 = hardwareMap.dcMotor.get("lift1");
        lift2 = hardwareMap.dcMotor.get("lift2");
        frontLeft = hardwareMap.dcMotor.get("leftFront");
        frontRight = hardwareMap.dcMotor.get("rightFront");
        backLeft = hardwareMap.dcMotor.get("leftBack");
        backRight = hardwareMap.dcMotor.get("rightBack");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {
            //closes and opens the claw
            if (gamepad2.left_bumper) {
                claw.setPosition(1);
            }
            if (gamepad2.right_bumper) {
                claw.setPosition(0.3);
            }

            if (gamepad2.left_trigger > 0) {
                //      currentTarget = Target.UP;
            }
            if (gamepad2.right_trigger > 0) {
                //  currentTarget = Target.DOWN;
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                currentTarget = Target.No_TARGET;
            }

            if ((currentTarget == Target.UP || gamepad2.dpad_up)){ //&& lift1.getCurrentPosition() < motorTop) {
                lift1.setPower(1);
                lift2.setPower(1);
            } else if ((currentTarget == Target.DOWN || gamepad2.dpad_down)) { //&& lift1.getCurrentPosition()>0) {
                lift1.setPower(-1);
                lift2.setPower(-1);

            } else {
                lift1.setPower(0);
                lift2.setPower(0);
            }

            if (lift1.getTargetPosition() >= motorTop || lift1.getCurrentPosition() <= 0) {
                currentTarget = Target.No_TARGET;
            }

            telemetry.addLine(String.valueOf(lift1.getCurrentPosition()));
            telemetry.update();


            //controls for driving
            frontRight.setPower(gamepad1.right_stick_y);
            backRight.setPower(gamepad1.right_stick_y);
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        }
    }
    }


