package com.android.systemui.recordissue;

import android.content.SharedPreferences;
import com.android.traceur.PresetTraceConfigs;
import com.android.traceur.TraceConfig;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTraceState {
    public final SharedPreferences prefs;

    public CustomTraceState(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public final TraceConfig getTraceConfig() {
        PresetTraceConfigs.TraceOptions traceOptions = new PresetTraceConfigs.TraceOptions(this.prefs.getInt("key_bufferSizeKb", PresetTraceConfigs.getDefaultConfig().bufferSizeKb), this.prefs.getBoolean("key_winscope", PresetTraceConfigs.getDefaultConfig().winscope), this.prefs.getBoolean("key_apps", PresetTraceConfigs.getDefaultConfig().apps), this.prefs.getBoolean("key_longTrace", PresetTraceConfigs.getDefaultConfig().longTrace), this.prefs.getBoolean("key_attachToBugReport", PresetTraceConfigs.getDefaultConfig().attachToBugreport), this.prefs.getInt("key_maxLongTraceSizeMb", PresetTraceConfigs.getDefaultConfig().maxLongTraceSizeMb), this.prefs.getInt("key_maxLongTraceDurationInMinutes", PresetTraceConfigs.getDefaultConfig().maxLongTraceDurationMinutes));
        Set<String> stringSet = this.prefs.getStringSet("key_tags", PresetTraceConfigs.getDefaultConfig().tags);
        if (stringSet == null) {
            stringSet = PresetTraceConfigs.getDefaultConfig().tags;
        }
        return new TraceConfig(traceOptions, stringSet);
    }
}
