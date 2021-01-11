package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="EncoderTest", group="Linear Opmode")

public class EncoderTest extends LinearOpMode {

    //creating objects for all of the different parts
//    private SciLift lift;

    private Drive d;


    @Override
    public void runOpMode() {

        //initializing every motor, servo, and sensor
        //these names all need to match the names in the config
  //      lift = new SciLift(
  //              hardwareMap.get(DcMotor.class, "liftmotor")
  //      );

        d = new Drive(
                hardwareMap.get(DcMotor.class, "rbmotor"),
 //               hardwareMap.get(DcMotor.class, "rfmotor"),
 //               hardwareMap.get(DcMotor.class, "lfmotor"),
 //               hardwareMap.get(DcMotor.class, "lbmotor")
        );

        waitForStart();
        while (opModeIsActive()) {

//            if (-gamepad2.right_stick_y > 0) {
//                lift.up(Math.abs(gamepad2.right_stick_y));
//            } else if (-gamepad2.right_stick_y < 0){
//                lift.down(Math.abs(gamepad2.right_stick_y));
//            } else {
//                lift.rest();
//            }

            if (gamepad1.right_trigger > 0) {
                lift.up(Math.abs(gamepad1.right_trigger));
            } else if (gamepad1.left_trigger > 0){
                lift.down(Math.abs(gamepad1.left_trigger));
            } else {
                lift.rest();
            }

            d.setPower(
                 gamepad1.left_stick_y,
                 gamepad1.left_stick_x,
                 gamepad1.right_stick_x,
                 gamepad1.right_trigger
              );

            if (gamepad1.y && !clawButtonIsDown) {
                clawButtonIsDown = true;
                claw.nextPos();
            } else if (!gamepad1.y) {
                clawButtonIsDown = false;
            }

            if (gamepad1.x && !gearboxButtonIsDown) {
                gearboxButtonIsDown = true;
                gear.nextPos();
            } else if (!gamepad1.x) {
                gearboxButtonIsDown = false;
            }


            telemetry.addData("Status", "Run Time: ");
            telemetry.addData("Motor Power", gamepad1.left_stick_y);
            telemetry.addData("Right Stick Pos", gamepad1.right_stick_y);
            telemetry.addData("Ly", gamepad1.left_stick_y);
            telemetry.addData("Lx", gamepad1.left_stick_x);
            telemetry.addData("Rx", gamepad1.right_stick_x);
            telemetry.addData("Clicks: ", d.getClickslf());
            telemetry.addData("lf", d.getPowerlf());
            telemetry.addData("lb", d.getPowerlb());
            telemetry.addData("rf", d.getPowerrf());
            telemetry.addData("rb", d.getPowerrb());
            telemetry.addData("Lift", lift.getClicks());
            telemetry.addData("Dist sensor!", sensors.getDistanceFront());
            telemetry.update();

        }
    }
}