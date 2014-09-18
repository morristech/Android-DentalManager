package za.co.morristech.dentalmanager.app;

import android.app.Application;
import android.util.Log;

public class DentalManagerApp extends Application {

    private static final String TAG = DentalManagerApp.class.getName();

    private static DentalManagerApp INSTANCE;

    private DentalManagerApp() {
        Log.d(TAG, "New application instance created.");
    }

    public static DentalManagerApp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DentalManagerApp();
        }
        return INSTANCE;
    }

    public void onCreate() {
        super.onCreate();
        getInstance();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        INSTANCE = null;
    }
}
