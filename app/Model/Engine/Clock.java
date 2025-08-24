package Model.Engine;

import javafx.animation.AnimationTimer;

public class Clock {
    private final long myIntervalNanos = 1_000_000_000L / 30; // target 30 ticks/sec
    private long myLastTime = 0;
    private AnimationTimer myTimer;

    public Clock() {

    }

    public void start() {
        myTimer = new AnimationTimer() {
            @Override
            public void handle(final long theNow) {

                //First frame: just init myLastTime.
                if (myLastTime == 0) {
                    myLastTime = theNow;
                    return;
                }

                long elapsed = theNow - myLastTime;
                if (elapsed >= myIntervalNanos) {
                    double deltaSeconds = elapsed / 1_000_000_000.0;

                    Engine.processEvents();

                    //reset myLastTime, and ovoid time drift.
                    myLastTime = theNow - (elapsed - myIntervalNanos);
                }


            }
        };
        myTimer.start();
    }

    public void stop() {
        if (myTimer != null) {
            myTimer.stop();
        }
    }
}
