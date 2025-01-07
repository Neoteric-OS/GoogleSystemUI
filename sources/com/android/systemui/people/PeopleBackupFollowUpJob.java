package com.android.systemui.people;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.people.IPeopleManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.ServiceManager;
import android.util.Log;
import com.android.systemui.people.widget.PeopleBackupHelper;
import com.android.systemui.people.widget.PeopleTileKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PeopleBackupFollowUpJob extends JobService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Context mContext;
    public IPeopleManager mIPeopleManager;
    public JobScheduler mJobScheduler;
    public final Object mLock = new Object();
    public PackageManager mPackageManager;
    public static final long JOB_PERIODIC_DURATION = Duration.ofHours(6).toMillis();
    public static final long CLEAN_UP_STORAGE_AFTER_DURATION = Duration.ofHours(48).toMillis();

    public final void cancelJobAndClearRemainingWidgets(Map map, SharedPreferences.Editor editor, SharedPreferences sharedPreferences) {
        for (Map.Entry entry : ((HashMap) map).entrySet()) {
            PeopleTileKey fromString = PeopleTileKey.fromString((String) entry.getKey());
            if (PeopleTileKey.isValid(fromString)) {
                try {
                    Iterator it = ((Set) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        try {
                            int parseInt = Integer.parseInt((String) it.next());
                            PeopleSpaceUtils.removeSharedPreferencesStorageForTile(this.mContext, fromString, parseInt, sharedPreferences.getString(String.valueOf(parseInt), ""));
                        } catch (NumberFormatException e) {
                            Log.e("PeopleBackupFollowUpJob", "Malformed widget id in follow-up file: " + e);
                        }
                    }
                } catch (Exception e2) {
                    Log.e("PeopleBackupFollowUpJob", "Malformed widget ids in follow-up file: " + e2);
                }
            } else {
                Log.e("PeopleBackupFollowUpJob", "Malformed peopleTileKey in follow-up file: " + ((String) entry.getKey()));
            }
        }
        editor.clear();
        this.mJobScheduler.cancel(74823873);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        this.mPackageManager = getApplicationContext().getPackageManager();
        this.mIPeopleManager = IPeopleManager.Stub.asInterface(ServiceManager.getService("people"));
        this.mJobScheduler = (JobScheduler) this.mContext.getSystemService(JobScheduler.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if ((r8 - r6) > com.android.systemui.people.PeopleBackupFollowUpJob.CLEAN_UP_STORAGE_AFTER_DURATION) goto L9;
     */
    @Override // android.app.job.JobService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onStartJob(android.app.job.JobParameters r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.mLock
            monitor-enter(r0)
            android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r10)     // Catch: java.lang.Throwable -> L3a
            android.content.SharedPreferences$Editor r2 = r1.edit()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r3 = "shared_follow_up"
            r4 = 0
            android.content.SharedPreferences r3 = r10.getSharedPreferences(r3, r4)     // Catch: java.lang.Throwable -> L3a
            android.content.SharedPreferences$Editor r5 = r3.edit()     // Catch: java.lang.Throwable -> L3a
            java.util.Map r3 = r10.processFollowUpFile(r3, r5)     // Catch: java.lang.Throwable -> L3a
            android.os.PersistableBundle r11 = r11.getExtras()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r6 = "start_date"
            long r6 = r11.getLong(r6)     // Catch: java.lang.Throwable -> L3a
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L3a
            boolean r11 = r3.isEmpty()     // Catch: java.lang.Throwable -> L3a
            if (r11 == 0) goto L2f
            goto L36
        L2f:
            long r8 = r8 - r6
            long r6 = com.android.systemui.people.PeopleBackupFollowUpJob.CLEAN_UP_STORAGE_AFTER_DURATION     // Catch: java.lang.Throwable -> L3a
            int r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r11 <= 0) goto L3c
        L36:
            r10.cancelJobAndClearRemainingWidgets(r3, r5, r1)     // Catch: java.lang.Throwable -> L3a
            goto L3c
        L3a:
            r10 = move-exception
            goto L49
        L3c:
            r2.apply()     // Catch: java.lang.Throwable -> L3a
            r5.apply()     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3a
            android.content.Context r10 = r10.mContext
            com.android.systemui.people.widget.PeopleBackupHelper.updateWidgets(r10)
            return r4
        L49:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3a
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.people.PeopleBackupFollowUpJob.onStartJob(android.app.job.JobParameters):boolean");
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final Map processFollowUpFile(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            if (PeopleBackupHelper.isReadyForRestore(this.mIPeopleManager, this.mPackageManager, PeopleTileKey.fromString(key))) {
                editor.remove(key);
            } else {
                try {
                    hashMap.put(entry.getKey(), (Set) entry.getValue());
                } catch (Exception unused) {
                    Log.e("PeopleBackupFollowUpJob", "Malformed entry value: " + entry.getValue());
                }
            }
        }
        return hashMap;
    }

    public void setManagers(Context context, PackageManager packageManager, IPeopleManager iPeopleManager, JobScheduler jobScheduler) {
        this.mContext = context;
        this.mPackageManager = packageManager;
        this.mIPeopleManager = iPeopleManager;
        this.mJobScheduler = jobScheduler;
    }
}
