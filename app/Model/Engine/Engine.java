package Model.Engine;


import Model.Entities.Enemies.Enemy;
import Model.Entities.Towers.Tower;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

final public class Engine {

    /**This is the queue of events that the entities want, to take.*/
    private static final Queue<Event> myEventQueue = new LinkedList<>();
    /**This is the currently active, enemies.*/
    private static final ArrayList<Enemy> myEnemies = new ArrayList<>();
    /**This is the currently active, towers.*/
    private static final ArrayList<Tower> myTowers = new ArrayList<>();
    /**This is the singleton instance of the engine*/
    private static final Engine myInstance = new Engine();


    /**
     * Private constructor to enforce singleton.
     * */
    private Engine() {
        super();
    }

    /**
     * This is how you get the instance of the Engine.
     * @return A reference to the class object.
     */
    public static Engine getMyInstance() {
        return myInstance;
    }

    /**
     * This adds events to the queue to be processed.
     *
     * @param theEvent This is the event that will be added to the back
     *                 of the queue.
     */
    public static void  addEvent(final Event theEvent) {
        myEventQueue.add(theEvent);
    }

    /**
     * This will return the event queue.
     * @return This is the event queue object.
     * */
    public static Queue<Event> getMyEventQueue() {
        return myEventQueue;
    }

    /**
     * This will execute all events in the event queue.
     */
    public static void processEvents() {

        for(Event e : myEventQueue) {
            e.run();
        }
        myEventQueue.clear();

    }


}
