package za.co.morristech.dentalmanager.app.common.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

/**
 * Created by wademorris on 2014/09/10.
 */
public class DataUtils {

    private static final String TAG = DataUtils.class.getName();

    private static byte[] mData;
    private static SimpleDateFormat mDateFormat;

    static {
        DataUtils.mDateFormat = new SimpleDateFormat("ddMMyy", Locale.getDefault());
        DataUtils.mData = new byte[2048];
    }

    public static String exportToFile() {

        String string = (Environment.getExternalStorageDirectory() + "/dentistmanager/");
        new File(string).mkdirs();
        String string2 = (string + "dentist_" + mDateFormat.format(new Date(System.currentTimeMillis())) + ".zip");

        GZIPOutputStream zipOutputStream = null;

        try {
            //TODO Complete the export implementation
            zipOutputStream = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(string2)));
            //Data.write(zipOutputStream, patientsDB.getPath());
            //Data.write(zipOutputStream, notesDB.getPath());
            //Data.write(zipOutputStream, photosDB.getPath());
            //Iterator iterator = photosDB.getPhotos().iterator();
            //while (iterator.hasNext()) {
            //Data.write(zipOutputStream, (Photo)(iterator.next()).getPath());
            //}
            zipOutputStream.finish();

            return string2;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
            return null;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (Exception ex) {
                    Log.d(TAG, ex.getMessage());
                }

            }
        }

    }
}
