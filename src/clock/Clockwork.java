/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 *
 * @author Yitty
 */
public class Clockwork {

    public SimpleIntegerProperty hour = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty minute = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty second = new SimpleIntegerProperty(0);

    public Clockwork() {
        startTicking();
    }

    private void startTicking() {
        TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(
                        new KeyFrame(Duration.seconds(1), updateTime())
                )
                .build()
                .play();
    }

    private EventHandler updateTime() {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                Calendar calendar = Calendar.getInstance();
                hour.set(calendar.get(Calendar.HOUR));
                minute.set(calendar.get(Calendar.MINUTE));
                second.set(calendar.get(Calendar.SECOND));
            }
        };
    }
}
