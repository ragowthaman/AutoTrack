package com.data2sense.gramasamy.autotrack;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * Created by gramasamy on 9/6/15.
 */
public class TheBackupAgent extends BackupAgentHelper {
    // The name of the [SharedPreferences/internal] file
    static final String FILENAME = "myLogLocationFile";
    static final String PREFS_FILE_NAME = "MyPrefsFile";

    // A key to uniquely identify the set of backup data
    static final String FILES_BACKUP_KEY = "mylogfiles";
    static final String PREFS_BACKUP_KEY = "prefs";

    // Allocate a helper and add it to the backup agent
    @Override
    public void onCreate() {
        FileBackupHelper fileBackupHelper = new FileBackupHelper(this, FILENAME);
        addHelper(FILES_BACKUP_KEY, fileBackupHelper);

        SharedPreferencesBackupHelper sharedPreferencesBackupHelper = new SharedPreferencesBackupHelper(this, PREFS_FILE_NAME);
        addHelper(PREFS_BACKUP_KEY, sharedPreferencesBackupHelper);
    }
}
