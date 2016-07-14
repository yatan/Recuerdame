package com.example.fran.recuerdame;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by yatan on 7/14/16.
 */
public class OpcionesActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new OpcionesFragment()).commit();
    }
    public static class OpcionesFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle saBundle)
        {
            super.onCreate(saBundle);
            addPreferencesFromResource(R.xml.opciones);
        }
    }
}
