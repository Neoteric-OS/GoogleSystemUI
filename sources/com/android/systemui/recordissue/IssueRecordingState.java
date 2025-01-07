package com.android.systemui.recordissue;

import android.content.SharedPreferences;
import android.util.ArraySet;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.traceur.PresetTraceConfigs;
import com.android.traceur.TraceConfig;
import com.android.wm.shell.R;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IssueRecordingState {
    public static final LinkedHashMap ALL_ISSUE_TYPES;
    public final CustomTraceState customTraceState;
    public boolean isRecording;
    public final CopyOnWriteArrayList listeners;
    public final SharedPreferences prefs;

    static {
        Integer valueOf = Integer.valueOf(R.string.performance);
        if (PresetTraceConfigs.mPerformanceTagList == null) {
            ArraySet arraySet = new ArraySet(PresetTraceConfigs.PERFORMANCE_TRACE_TAGS);
            PresetTraceConfigs.mPerformanceTagList = arraySet;
            PresetTraceConfigs.updateTagsIfUserBuild(arraySet);
        }
        Pair pair = new Pair(valueOf, new TraceConfig(PresetTraceConfigs.PERFORMANCE_TRACE_OPTIONS, PresetTraceConfigs.mPerformanceTagList));
        Integer valueOf2 = Integer.valueOf(R.string.user_interface);
        if (PresetTraceConfigs.mUiTagList == null) {
            ArraySet arraySet2 = new ArraySet(PresetTraceConfigs.UI_TRACE_TAGS);
            PresetTraceConfigs.mUiTagList = arraySet2;
            PresetTraceConfigs.updateTagsIfUserBuild(arraySet2);
        }
        Pair pair2 = new Pair(valueOf2, new TraceConfig(PresetTraceConfigs.UI_TRACE_OPTIONS, PresetTraceConfigs.mUiTagList));
        Integer valueOf3 = Integer.valueOf(R.string.battery);
        if (PresetTraceConfigs.mBatteryTagList == null) {
            ArraySet arraySet3 = new ArraySet(PresetTraceConfigs.BATTERY_TRACE_TAGS);
            PresetTraceConfigs.mBatteryTagList = arraySet3;
            PresetTraceConfigs.updateTagsIfUserBuild(arraySet3);
        }
        Pair pair3 = new Pair(valueOf3, new TraceConfig(PresetTraceConfigs.BATTERY_TRACE_OPTIONS, PresetTraceConfigs.mBatteryTagList));
        Integer valueOf4 = Integer.valueOf(R.string.thermal);
        if (PresetTraceConfigs.mThermalTagList == null) {
            ArraySet arraySet4 = new ArraySet(PresetTraceConfigs.THERMAL_TRACE_TAGS);
            PresetTraceConfigs.mThermalTagList = arraySet4;
            PresetTraceConfigs.updateTagsIfUserBuild(arraySet4);
        }
        Pair[] pairArr = {pair, pair2, pair3, new Pair(valueOf4, new TraceConfig(PresetTraceConfigs.THERMAL_TRACE_OPTIONS, PresetTraceConfigs.mThermalTagList)), new Pair(Integer.valueOf(R.string.custom), null)};
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(5));
        MapsKt.putAll(linkedHashMap, pairArr);
        ALL_ISSUE_TYPES = linkedHashMap;
    }

    public IssueRecordingState(UserTracker userTracker, UserFileManager userFileManager) {
        SharedPreferences sharedPreferences$1 = ((UserFileManagerImpl) userFileManager).getSharedPreferences$1(((UserTrackerImpl) userTracker).getUserId(), "record_issue");
        this.prefs = sharedPreferences$1;
        this.customTraceState = new CustomTraceState(sharedPreferences$1);
        this.listeners = new CopyOnWriteArrayList();
    }

    public final int getIssueTypeRes() {
        if (this.prefs.getInt("key_issueTypeIndex", -1) == -1) {
            return -1;
        }
        return CollectionsKt.toIntArray(ALL_ISSUE_TYPES.keySet())[this.prefs.getInt("key_issueTypeIndex", -1)];
    }
}
