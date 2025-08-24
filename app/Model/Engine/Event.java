package Model.Engine;

@FunctionalInterface
public interface Event extends Runnable {

    @Override
    void run();
}
