package za.co.morristech.dentalmanager.app.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import za.co.morristech.dentalmanager.app.DentalManagerApp;

/**
 * Created by wademorris on 2014/08/25.
 */
public class BaseActivity extends FragmentActivity {

    private static final String TAG = BaseActivity.class.getName();

    private DentalManagerApp mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mApplication =
    }
}
