package com.google.android.systemui.columbus.legacy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusStructuredDataManager {
    public final Set allowPackageList;
    public final ContentResolver contentResolver;
    public final Object lock = new Object();
    public JSONArray packageStats;
    public final UserTracker userTracker;
    public final ColumbusStructuredDataManager$userTrackerCallback$1 userTrackerCallback;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.systemui.settings.UserTracker$Callback, com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager$userTrackerCallback$1] */
    public ColumbusStructuredDataManager(Context context, UserTracker userTracker, Executor executor) {
        this.userTracker = userTracker;
        this.contentResolver = context.getContentResolver();
        String[] stringArray = context.getResources().getStringArray(R.array.columbus_sumatra_package_allow_list);
        this.allowPackageList = SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        ?? r0 = new UserTracker.Callback() { // from class: com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager$userTrackerCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                ColumbusStructuredDataManager columbusStructuredDataManager = ColumbusStructuredDataManager.this;
                synchronized (columbusStructuredDataManager.lock) {
                    columbusStructuredDataManager.packageStats = columbusStructuredDataManager.fetchPackageStats();
                }
            }
        };
        this.userTrackerCallback = r0;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String dataString;
                if (intent == null || (dataString = intent.getDataString()) == null) {
                    return;
                }
                List split$default = StringsKt.split$default(dataString, new String[]{":"}, 0, 6);
                if (split$default.size() != 2) {
                    Log.e("Columbus/StructuredData", "Unexpected package name tokens: ".concat(CollectionsKt.joinToString$default(split$default, ",", null, null, null, 62)));
                    return;
                }
                String str = (String) split$default.get(1);
                if (intent.getBooleanExtra("android.intent.extra.REPLACING", false) || !ColumbusStructuredDataManager.this.allowPackageList.contains(str)) {
                    return;
                }
                ColumbusStructuredDataManager columbusStructuredDataManager = ColumbusStructuredDataManager.this;
                synchronized (columbusStructuredDataManager.lock) {
                    int length = columbusStructuredDataManager.packageStats.length();
                    for (int i = 0; i < length; i++) {
                        if (Intrinsics.areEqual(str, columbusStructuredDataManager.packageStats.getJSONObject(i).getString("packageName"))) {
                            columbusStructuredDataManager.packageStats.remove(i);
                            columbusStructuredDataManager.storePackageStats();
                            return;
                        }
                    }
                }
            }
        };
        this.packageStats = fetchPackageStats();
        ((UserTrackerImpl) userTracker).addCallback(r0, executor);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static JSONObject makeJSONObject$default(ColumbusStructuredDataManager columbusStructuredDataManager, String str, long j, int i) {
        int i2 = (i & 2) != 0 ? 0 : 1;
        if ((i & 4) != 0) {
            j = 0;
        }
        columbusStructuredDataManager.getClass();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("packageName", str);
        jSONObject.put("shownCount", i2);
        jSONObject.put("lastDeny", j);
        return jSONObject;
    }

    public final JSONArray fetchPackageStats() {
        JSONArray jSONArray;
        synchronized (this.lock) {
            String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_package_stats", ((UserTrackerImpl) this.userTracker).getUserId());
            if (stringForUser == null) {
                stringForUser = "[]";
            }
            try {
                jSONArray = new JSONArray(stringForUser);
            } catch (JSONException e) {
                Log.e("Columbus/StructuredData", "Failed to parse package counts", e);
                jSONArray = new JSONArray();
            }
        }
        return jSONArray;
    }

    public final long getLastDenyTimestamp(String str) {
        synchronized (this.lock) {
            int length = this.packageStats.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = this.packageStats.getJSONObject(i);
                if (str.equals(jSONObject.getString("packageName"))) {
                    return jSONObject.getLong("lastDeny");
                }
            }
            return 0L;
        }
    }

    public final int getPackageShownCount(String str) {
        synchronized (this.lock) {
            int length = this.packageStats.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = this.packageStats.getJSONObject(i);
                if (str.equals(jSONObject.getString("packageName"))) {
                    return jSONObject.getInt("shownCount");
                }
            }
            return 0;
        }
    }

    public final void storePackageStats() {
        synchronized (this.lock) {
            Settings.Secure.putStringForUser(this.contentResolver, "columbus_package_stats", this.packageStats.toString(), ((UserTrackerImpl) this.userTracker).getUserId());
        }
    }
}
