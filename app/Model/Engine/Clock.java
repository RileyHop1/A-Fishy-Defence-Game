package Model.Engine;

import javafx.animation.AnimationTimer;

public class Clock {
    /**This is a intervale of  60 ticks/sec, in nanoseconds*/
    private final long INTERVAL_NANO = 1_000_000_000L / 60;
    /**This keeps track of the total time that has passed.*/
    private long myLastTime = 0;
    /**This is a javaFx Timer*/
    private AnimationTimer myTimer;


    /**
     * This starts the game clock timer.
     * */
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
                //This stops the actions from taking place till the right
                //Amount of time has passed.
                if (elapsed >= INTERVAL_NANO) {
                    double deltaSeconds = elapsed / 1_000_000_000.0;

                    Engine.processEvents();

                    //reset myLastTime, and ovoid time drift.
                    myLastTime = theNow - (elapsed - INTERVAL_NANO);
                }


            }
        };
        myTimer.start();
    }

    /**
     * This stops the clock.
     * */
    public void stop() {
        if (myTimer != null) {
            myTimer.stop();
        }
    }
}
