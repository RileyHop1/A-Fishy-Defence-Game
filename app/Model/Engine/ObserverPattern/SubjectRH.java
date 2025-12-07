package Model.Engine.ObserverPattern;

import java.util.ArrayList;

public interface SubjectRH {

    ArrayList<Object> myObserversRH = new ArrayList<>();


    /**
     * @param theUpdate This is the string of what is being updated
     */
    default void notifyRH(final String theUpdate) {
        System.out.println(myObserversRH.size());
        for(Object obj: myObserversRH) {
            System.out.println("butt hole");
            if (obj instanceof ObserverRH) {
                ((ObserverRH) obj).updateRH(theUpdate);
            } else {
                throw new IllegalArgumentException("The observer" +
                        " doesn't implement ObserverRH");
            }
        }

    }

    /**
     * @param theObserver
     */
    default void attachObserverRH(final Object theObserver) {
        System.out.println("gay sex");
        myObserversRH.add(theObserver);
    }

    /**
     * @param theObserver
     */
    default void dettachObserverRH(final Object theObserver) {

    }





}
