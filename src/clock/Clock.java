/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.stage.Stage;

/**
 *
 * @author Yitty
 */
public class Clock extends Application {
    private final int unit = 200;
    private Clockwork clockwork = new Clockwork();
    public String getTimeAsString(){
    DateFormat df = new SimpleDateFormat("hh:mm:ss");
Date dateobj = new Date();
return df.format(dateobj);
    }
    @Override
    public void start(Stage primaryStage) {
        HBox hbox = new HBox();
        
        final Parent clock = GroupBuilder.create()
                .children(
                        outerRim(),
                        minuteHand(),
                        hourHand(),
                        secondsHand(),
                        tickMarks(),
                        centerPoint()
                )
                .build();
        hbox.setPadding(new Insets(25,0,0,0));
        VBox vbox = new VBox();
        vbox.getChildren().add(clock);
        Label label = new Label(getTimeAsString());
        //set a time to update the digital clock
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        timer.schedule(, 500, 500);
        vbox.getChildren().add(label);
        hbox.getChildren().add(vbox);
        Scene scene = new Scene(hbox, 800, 480);
        
        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private Node hourHand() {
    Rotate rotate = rotationAroundCenter();
    rotate.angleProperty().bind(clockwork.hour.multiply(360 / 12));
    return hand(unit * 0.4, Color.BLACK, rotate);
}

private Node minuteHand() {
    Rotate rotate = rotationAroundCenter();
    rotate.angleProperty().bind(clockwork.minute.multiply(360 / 60));
    return hand(unit * 0.2, Color.BLACK, rotate);
}

private Node secondsHand() {
    Rotate rotate = rotationAroundCenter();
    rotate.angleProperty().bind(clockwork.second.multiply(360 / 60));
    return LineBuilder.create()
            .startX(unit)
            .endX(unit)
            .startY(unit * 1.1)
            .endY(unit * 0.2)
            .transforms(rotate)
            .build();
}

private Rotate rotationAroundCenter() {
    return RotateBuilder.create()
            .pivotX(unit)
            .pivotY(unit)
            .build();
}


private Node hand(double stretchRelativeToRim, Color color, Rotate rotate) {
    return PathBuilder.create()
            .fill(color)
            .stroke(Color.TRANSPARENT)
            .elements(
                    new MoveTo(unit, unit),
                    new LineTo(unit * 0.9, unit * 0.9),
                    new LineTo(unit, stretchRelativeToRim),
                    new LineTo(unit * 1.1, unit * 0.9),
                    new LineTo(unit, unit)
            )
            .transforms(rotate)
            .build();
}
    private Node outerRim() {
    return CircleBuilder.create()
            .fill(
                    RadialGradientBuilder.create()
                            .stops(
                                    new Stop(0.8, Color.WHITE),
                                    new Stop(0.9, Color.BLACK),
                                    new Stop(0.95, Color.WHITE),
                                    new Stop(1.0, Color.BLACK)
                            )
                            .cycleMethod(CycleMethod.NO_CYCLE)
                            .centerX(0.5)
                            .centerY(0.5)
                            .radius(0.5)
                            .proportional(true)
                            .build()
            )
            .radius(unit)
            .centerX(unit)
            .centerY(unit)
            .build();
}



private Node hand(double stretchRelativeToRim, Color color, int startAngle) {
    return PathBuilder.create()
            .fill(color)
            .stroke(Color.TRANSPARENT)
            .elements(
                    new MoveTo(unit, unit),
                    new LineTo(unit * 0.9, unit * 0.9),
                    new LineTo(unit, stretchRelativeToRim),
                    new LineTo(unit * 1.1, unit * 0.9),
                    new LineTo(unit, unit)
            )
            .transforms(
                    RotateBuilder.create()
                            .pivotX(unit)
                            .pivotY(unit)
                            .angle(startAngle)
                            .build()
            )
            .build();
}
private Node tickMarks() {
     Group tickMarkGroup = new Group();
     for (int n = 0; n < 12; n++) {
         tickMarkGroup.getChildren().add(tickMark(n));
     }
     return tickMarkGroup;
 }

 private Node tickMark(int n) {
     return LineBuilder.create()
             .startX(unit)
             .endX(unit)
             .startY(unit * 0.12)
             .endY(unit * (n % 3 == 0 ? 0.3 : 0.2))
             .transforms(
                     RotateBuilder.create()
                             .pivotX(unit)
                             .pivotY(unit)
                             .angle(360 / 12 * n)
                             .build()
             )
             .strokeWidth(2)
             .build();
 }
 private Node centerPoint() {
    return CircleBuilder.create()
            .fill(Color.BLACK)
            .radius(0.05 * unit)
            .centerX(unit)
            .centerY(unit)
            .build();
}
}
