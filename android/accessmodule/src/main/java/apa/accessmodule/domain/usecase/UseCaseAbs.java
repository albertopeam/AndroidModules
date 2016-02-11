package apa.accessmodule.domain.usecase;

import apa.executor.Executor;
import apa.executor.Interactor;
import apa.executor.MainThread;

/**
 * Created by alberto on 3/1/16.
 */
public abstract class UseCaseAbs implements Interactor {


    private Executor executor;
    private MainThread mainThread;


    protected UseCaseAbs(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }

    protected void runOnMainThread(Runnable runnable){
        mainThread.runInMainThread(runnable);
    }

    protected void runOnBackgroundThread(Interactor interactor){
        executor.runInBackgroundThread(interactor);
    }
}
