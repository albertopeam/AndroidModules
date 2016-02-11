package apa.executor;

import java.util.concurrent.*;


public class ThreadExecutor implements Executor, java.util.concurrent.Executor {


    private java.util.concurrent.Executor e;
    private int NUM_THREADS = 0;
    private ExecutorService executor;


    public ThreadExecutor() {
        NUM_THREADS = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(NUM_THREADS);
    }


    @Override
    public void runInBackgroundThread(Interactor interactor) {
        if (interactor == null){
            throw new IllegalArgumentException("Interactor can't be null");
        }
        execute(interactor);
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null){
            throw new IllegalArgumentException("Runnable can't be null");
        }
        executor.execute(runnable);
    }
}
