//General Idea of Auto.
// PLEASE LET THE COMMENTS BE AS IT IS.
// USE THE CODE ON YOUR OWN GITHUB.
// DO NOT EDIT THIS.
package org.firstinspires.ftc.teamcode.NewTeamSamples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Actual Red Auton")
public class SampleAuto extends LinearOpMode {

    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, linearSlideMotor, intakeMotor, backMotor;
    private CRServo armServo, linearSlideServoL, linearSlideServoR;

    // Constants for servo positions
    private static final double ARM_OPEN_POSITION = 0.0;
    private static final double ARM_CLOSE_POSITION = 1.0;

    @Override
    public void runOpMode() {
        // Initialize motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
        backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
        backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlide");
        backMotor = hardwareMap.get(DcMotor.class, "back");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        armServo = hardwareMap.get(CRServo.class, "frontIntakeServo");
        linearSlideServoL = hardwareMap.get(CRServo.class, "backIntakeServoL");
        linearSlideServoR = hardwareMap.get(CRServo.class, "backIntakeServoL");

        // Set motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to brake when zero power is applied, setup encoder modes
        setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeMotor.setTargetPosition(0);
        linearSlideMotor.setTargetPosition(50);
        backMotor.setTargetPosition(0);

        intakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intakeMotor.setPower(1);
        linearSlideMotor.setPower(1);
        backMotor.setPower(1);




        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Select a position and execute its routine
            String position = getStartingPosition();
            switch (position) {
                case "Position 1":
                    runPosition1Autonomous();
                    break;
                case "Position 2":
                    runPosition2Autonomous();
                    break;
                case "Position 3":
                    runPosition3Autonomous();
                    break;
                case "Position 4":
                    runPosition4Autonomous();
                    break;
                default:
                    telemetry.addData("Error", "Unknown Position");
                    telemetry.update();
                    break;
            }
        }
    }

    // Simulate is position selection for Skibidi demo purposes
    private String getStartingPosition() {
        return "Position 1"; // Change as needed to test each position
    }

    private void runPosition1Autonomous() {
        telemetry.addData("Position", "Position 1");
        telemetry.update();

        //moveForward(500, 0.5); // Move forward for 500 ms
        moveBackward(728, 0.5); // Move forward for 1000 ms
        setHeight(2800);
        sleep(2000);
        moveBackward(189,0.5);
        sleep(2000);
        moveBackward(185,0.5);
        setHeight(1000);
        sleep(1000);
        moveForward(1000,0.5);
        strafeRight(2000,0.5);
//        while(true) {
//            telemetry.addData("height", linearSlideMotor.getCurrentPosition());
//            telemetry.update();
//        }

//        dropGameElement();
//        moveBackward(500, 0.5); // Move backward for 500 ms
    }

    private void runPosition2Autonomous() {
        telemetry.addData("Position", "Position 2");
        telemetry.update();

        strafeRight(1000, 0.5); // Strafe right for 1000 ms
        moveForward(1000, 0.5);
//        dropGameElement();
    }

    private void runPosition3Autonomous() {
        telemetry.addData("Position", "Position 3");
        telemetry.update();

        moveForward(500, 0.5);
        turnRight(500, 0.5); // Turn right for 500 ms
        moveForward(1000, 0.5);
//        dropGameElement();
    }

    private void runPosition4Autonomous() {
        telemetry.addData("Position", "Position 4");
        telemetry.update();

        moveForward(500, 0.5);
        strafeLeft(1000, 0.5);
//        dropGameElement();
    }

    // Basic movement functions
    private void moveForward(int durationMs, double power) {
        setMotorPower(power, power, power, power);
        sleep(durationMs);
        stopMotors();
    }

    private void moveBackward(int durationMs, double power) {
        setMotorPower(-power, -power, -power, -power);
        sleep(durationMs);
        stopMotors();
    }

    private void strafeRight(int durationMs, double power) {
        setMotorPower(power, -power, -power, power);
        sleep(durationMs);
        stopMotors();
    }

    private void strafeLeft(int durationMs, double power) {
        setMotorPower(-power, power, power, -power);
        sleep(durationMs);
        stopMotors();
    }

    private void turnRight(int durationMs, double power) {
        setMotorPower(power, -power, power, -power);
        sleep(durationMs);
        stopMotors();
    }

    private void dropGameElement() {
        armServo.setDirection(DcMotorSimple.Direction.FORWARD);
        armServo.setPower(1);
        sleep(200);
        armServo.setPower(0);
        sleep(1000);


    }

    private void setHeight(int height)
    {
        linearSlideMotor.setTargetPosition(-height);
    }

    private void setMotorPower(double fl, double fr, double bl, double br) {
        frontLeftMotor.setPower(fl);
        frontRightMotor.setPower(fr);
        backLeftMotor.setPower(bl);
        backRightMotor.setPower(br);
    }

    private void stopMotors() {
        setMotorPower(0, 0, 0, 0);
    }

    private void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        frontLeftMotor.setZeroPowerBehavior(behavior);
        frontRightMotor.setZeroPowerBehavior(behavior);
        backLeftMotor.setZeroPowerBehavior(behavior);
        backRightMotor.setZeroPowerBehavior(behavior);
    }
}
