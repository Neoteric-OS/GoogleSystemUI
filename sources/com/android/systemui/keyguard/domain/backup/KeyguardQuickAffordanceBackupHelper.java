package com.android.systemui.keyguard.domain.backup;

import android.app.backup.BackupDataInputStream;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.backup.BackupHelper;
import java.io.File;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceBackupHelper extends SharedPreferencesBackupHelper {
    public final BackupHelper context;

    public KeyguardQuickAffordanceBackupHelper(int i, BackupHelper backupHelper) {
        super(backupHelper, (new UserHandle(i).isSystem() ? new File("quick_affordance_selections") : new File(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(GenericDocument$$ExternalSyntheticOutline0.m("__USER_", "_", i), "quick_affordance_selections"))).getPath());
        this.context = backupHelper;
    }

    @Override // android.app.backup.SharedPreferencesBackupHelper, android.app.backup.BackupHelper
    public final void restoreEntity(BackupDataInputStream backupDataInputStream) {
        String key = backupDataInputStream != null ? backupDataInputStream.getKey() : null;
        Log.d("KeyguardQuickAffordanceBackupHelper", "Starting restore for " + key + " for user " + this.context.getUserId());
        String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("KeyguardQuickAffordanceBackupHelper File restore: ", backupDataInputStream != null ? backupDataInputStream.getKey() : null);
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice(m);
        }
        try {
            super.restoreEntity(backupDataInputStream);
            String key2 = backupDataInputStream != null ? backupDataInputStream.getKey() : null;
            Log.d("KeyguardQuickAffordanceBackupHelper", "Finished restore for " + key2 + " for user " + this.context.getUserId());
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
