package com.android.traceur;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.traceur.PresetTraceConfigs;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TraceConfig implements Parcelable {
    public final boolean apps;
    public final boolean attachToBugreport;
    public final int bufferSizeKb;
    public final boolean longTrace;
    public final int maxLongTraceDurationMinutes;
    public final int maxLongTraceSizeMb;
    public final Set tags;
    public final boolean winscope;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public boolean apps;
        public boolean attachToBugreport;
        public int bufferSizeKb;
        public boolean longTrace;
        public int maxLongTraceDurationMinutes;
        public int maxLongTraceSizeMb;
        public Set tags;
        public boolean winscope;
    }

    public TraceConfig(PresetTraceConfigs.TraceOptions traceOptions, Set set) {
        int i = traceOptions.bufferSizeKb;
        int i2 = traceOptions.maxLongTraceSizeMb;
        int i3 = traceOptions.maxLongTraceDurationMinutes;
        boolean z = traceOptions.winscope;
        boolean z2 = traceOptions.apps;
        boolean z3 = traceOptions.longTrace;
        boolean z4 = traceOptions.attachToBugreport;
        this.bufferSizeKb = i;
        this.winscope = z;
        this.apps = z2;
        this.longTrace = z3;
        this.attachToBugreport = z4;
        this.maxLongTraceSizeMb = i2;
        this.maxLongTraceDurationMinutes = i3;
        this.tags = set;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bufferSizeKb);
        parcel.writeBoolean(this.winscope);
        parcel.writeBoolean(this.apps);
        parcel.writeBoolean(this.longTrace);
        parcel.writeBoolean(this.attachToBugreport);
        parcel.writeInt(this.maxLongTraceSizeMb);
        parcel.writeInt(this.maxLongTraceDurationMinutes);
        parcel.writeStringArray((String[]) this.tags.toArray(new TraceConfig$$ExternalSyntheticLambda0()));
    }
}
