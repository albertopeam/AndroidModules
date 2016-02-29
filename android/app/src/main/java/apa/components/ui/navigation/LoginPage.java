package apa.components.ui.navigation;

import android.app.Activity;
import android.util.Log;

import apa.accessmodule.ui.navigation.Page;

/**
 * Created by alberto on 11/2/16.
 */
public class LoginPage implements Page<Void> {

    private static final String TAG = LoginPage.class.getSimpleName();
    private Activity activity;


    public LoginPage(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void backPage() {
        activity.finish();
    }

    @Override
    public void nextPage() {
        Log.d(TAG, "nextPage");
    }

    @Override
    public void gotoPage(Void aVoid) {

    }
}
