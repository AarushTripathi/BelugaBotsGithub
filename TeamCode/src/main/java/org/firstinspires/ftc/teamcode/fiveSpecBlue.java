package org.firstinspires.ftc.teamcode;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.pedropathing.pathgen.PathBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name = "5 Spec Blue")
public class fiveSpecBlue extends LinearOpMode {

        DcMotor back;

        public static double kP_o = 0.01;

        // PID control method
        public double outtakePid(double target, double current) {
                return (target - current) * kP_o;
        }

        // Move the slide to the target position using PID control
        private void moveSlideToPosition(int targetPosition, int currentPosition) {
                while (Math.abs(targetPosition - back.getCurrentPosition()) > 10 && opModeIsActive()) {
                        back.setPower(outtakePid(targetPosition, currentPosition));
                        telemetry.addData("Target", targetPosition);
                        telemetry.addData("Current", back.getCurrentPosition());
                        telemetry.addData("Power", outtakePid(targetPosition, currentPosition));
                        telemetry.update();
                }
                back.setPower(0); // Stop the motor once the target is reached
        }

        @Override
        public void runOpMode() throws InterruptedException {

                back = hardwareMap.get(DcMotor.class, "back");

                back.setDirection(DcMotor.Direction.REVERSE);
                back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                // Reset and configure the slide motor
                back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                Follower f = new Follower(hardwareMap);
                waitForStart(); // Wait for the game to start


                if (opModeIsActive()) {
                        PathBuilder builder = f.pathBuilder();

                        builder
                                .addPath(
                                        // Line 1 (forward to spec bar) add hang spec
                                        new BezierLine(
                                                new Point(8.000, 65.000, Point.CARTESIAN),
                                                new Point(38.000, 65.000, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(2600, back.getCurrentPosition()); // Move to hang height
                        sleep(500); // Allow time for the slide to reach the position
                        moveSlideToPosition(0,back.getCurrentPosition()); // Move back to home position

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                                .addPath(
                                        // Line 2 (curve to the 3 alliance sample side
                                        new BezierCurve(
                                                new Point(38.000, 65.000, Point.CARTESIAN),
                                                new Point(0.000, 29.023, Point.CARTESIAN),
                                                new Point(79.575, 37.794, Point.CARTESIAN),
                                                new Point(59.538, 24.692, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                                .addPath(
                                        // Line 3  push sample 1
                                        new BezierLine(
                                                new Point(59.538, 24.692, Point.CARTESIAN),
                                                new Point(12.692, 24.462, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 4  reset
                                        new BezierCurve(
                                                new Point(12.692, 24.462, Point.CARTESIAN),
                                                new Point(51.462, 31.385, Point.CARTESIAN),
                                                new Point(63.231, 13.615, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 5 push sample 2
                                        new BezierLine(
                                                new Point(63.231, 13.615, Point.CARTESIAN),
                                                new Point(12.462, 12.923, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 6 reset
                                        new BezierCurve(
                                                new Point(12.462, 12.923, Point.CARTESIAN),
                                                new Point(39.923, 23.769, Point.CARTESIAN),
                                                new Point(63.692, 8.538, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 7 push sample 3
                                        new BezierLine(
                                                new Point(63.692, 8.538, Point.CARTESIAN),
                                                new Point(21.462, 8.308, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 8 pick spec
                                        new BezierCurve(
                                                new Point(21.462, 8.308, Point.CARTESIAN),
                                                new Point(29.538, 19.846, Point.CARTESIAN),
                                                new Point(9.923, 26.538, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(2600, back.getCurrentPosition()); // Move to hang height


                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                                .addPath(
                                        // Line 9 hang
                                        new BezierCurve(
                                                new Point(9.923, 26.538, Point.CARTESIAN),
                                                new Point(16.154, 54.231, Point.CARTESIAN),
                                                new Point(37.154, 60.692, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(0, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                                .addPath(
                                        // Line 10 pick spec
                                        new BezierCurve(
                                                new Point(37.154, 60.692, Point.CARTESIAN),
                                                new Point(26.538, 35.769, Point.CARTESIAN),
                                                new Point(10.846, 26.538, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(2600, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                                .addPath(
                                        // Line 11 hang spec
                                        new BezierCurve(
                                                new Point(10.846, 26.538, Point.CARTESIAN),
                                                new Point(13.154, 49.615, Point.CARTESIAN),
                                                new Point(37.154, 69.462, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(0, back.getCurrentPosition()); // Move to hang height

                        builder

                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                                .addPath(
                                        // Line 12 pick spec
                                        new BezierCurve(
                                                new Point(37.154, 69.462, Point.CARTESIAN),
                                                new Point(26.950, 42.738, Point.CARTESIAN),
                                                new Point(11.077, 26.077, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(2600, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                                .addPath(
                                        // Line 13 hang
                                        new BezierCurve(
                                                new Point(11.077, 26.077, Point.CARTESIAN),
                                                new Point(20.769, 71.308, Point.CARTESIAN),
                                                new Point(37.846, 74.077, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(0, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                                .addPath(
                                        // Line 14 pick spec
                                        new BezierCurve(
                                                new Point(37.846, 74.077, Point.CARTESIAN),
                                                new Point(37.615, 44.077, Point.CARTESIAN),
                                                new Point(11.538, 25.615, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(2600, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                                .addPath(
                                        // Line 15 hang
                                        new BezierCurve(
                                                new Point(11.538, 25.615, Point.CARTESIAN),
                                                new Point(11.538, 59.077, Point.CARTESIAN),
                                                new Point(38.077, 77.769, Point.CARTESIAN)
                                        )
                                );
                        moveSlideToPosition(0, back.getCurrentPosition()); // Move to hang height

                        builder
                                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                                .addPath(
                                        // Line 16 park
                                        new BezierLine(
                                                new Point(38.077, 77.769, Point.CARTESIAN),
                                                new Point(10.154, 12.923, Point.CARTESIAN)
                                        )
                                )
                                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90));
                }
        }
}
