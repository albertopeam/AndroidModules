package apa.accessmodule.ui.presenter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by alberto on 29/1/16.
 */
@SuppressLint("ValidFragment")
public abstract class PresenterAbs extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

}
