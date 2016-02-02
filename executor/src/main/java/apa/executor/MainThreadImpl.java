package apa.executor;

import android.os.Handler;
import android.os.Looper;

public class MainThreadImpl implements MainThread {


    private Handler handler;


    public MainThreadImpl() {
    this.handler = new Handler(Looper.getMainLooper());
  }


    @Override
    public void runInMainThread(Runnable runnable) {
        handler.post(runnable);
    }
}
