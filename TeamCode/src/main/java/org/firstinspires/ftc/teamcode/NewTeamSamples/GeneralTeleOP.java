//General Idea of TeleOP.
// PLEASE LET THE COMMENTS BE AS IT IS.
// USE THE CODE ON YOUR OWN GITHUB.
// DO NOT EDIT THIS.
package org.firstinspires.ftc.teamcode.NewTeamSamples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class GeneralTeleOP extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize drive motors
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive"); //0
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive"); //1
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive"); //2
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive"); //3

        // Initialize Expansion Hub motors
//        DcMotorEx linearSlideMotor = hardwareMap.get(DcMotorEx.class, "arm1");
//        DcMotorEx slideMovementMotor = hardwareMap.get(DcMotorEx.class, "movement1");

        // Power constants
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Linear slide motor configuration
//        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
//        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Motor power configuration
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double speed = 0.5;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Gamepad1 - Movement Controls
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower = -(y + x + rx) / deno * speed;
            double backLeftPower = -(y - x + rx) / deno * speed;
            double frontRightPower = (y - x - rx) / deno * speed;
            double backRightPower = -(y + x - rx) / deno * speed;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Gamepad2 - Linear Slide and Slide Movement Controls
//            if (gamepad2.y) {
//                linearSlideMotor.setPower(UP_POWER);
//            } else if (gamepad2.b) {
//                linearSlideMotor.setPower(DOWN_POWER);
//            } else {
//                linearSlideMotor.setPower(STOP_POWER);
//            }
//
//            if (gamepad2.x) {
//                slideMovementMotor.setPower(UP_POWER);
//            } else if (gamepad2.a) {
//                slideMovementMotor.setPower(DOWN_POWER);
//            } else {
//                slideMovementMotor.setPower(0);
//            }

            // Ensure interrupted state clean-up if stop requested
            if (isStopRequested()) {
//                linearSlideMotor.setPower(0);
//                slideMovementMotor.setPower(0);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                return;
            }
        }
    }
}