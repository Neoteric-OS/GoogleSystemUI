package com.android.systemui.statusbar.connectivity;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.settingslib.SignalIcon$IconGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ConnectivityState {
    public boolean activityIn;
    public boolean activityOut;
    public boolean connected;
    public boolean enabled;
    public SignalIcon$IconGroup iconGroup;
    public int inetCondition;
    public int level;
    public int rssi;
    public long time;

    public void copyFrom(ConnectivityState connectivityState) {
        this.connected = connectivityState.connected;
        this.enabled = connectivityState.enabled;
        this.activityIn = connectivityState.activityIn;
        this.activityOut = connectivityState.activityOut;
        this.level = connectivityState.level;
        this.iconGroup = connectivityState.iconGroup;
        this.inetCondition = connectivityState.inetCondition;
        this.rssi = connectivityState.rssi;
        this.time = connectivityState.time;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        ConnectivityState connectivityState = (ConnectivityState) obj;
        return connectivityState.connected == this.connected && connectivityState.enabled == this.enabled && connectivityState.level == this.level && connectivityState.inetCondition == this.inetCondition && connectivityState.iconGroup == this.iconGroup && connectivityState.activityIn == this.activityIn && connectivityState.activityOut == this.activityOut && connectivityState.rssi == this.rssi;
    }

    public int hashCode() {
        int m = (TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.connected) * 31, 31, this.enabled), 31, this.activityIn), 31, this.activityOut) + this.level) * 31;
        SignalIcon$IconGroup signalIcon$IconGroup = this.iconGroup;
        return Long.hashCode(this.time) + ((((((m + (signalIcon$IconGroup != null ? signalIcon$IconGroup.hashCode() : 0)) * 31) + this.inetCondition) * 31) + this.rssi) * 31);
    }

    public List tableColumns() {
        return CollectionsKt__CollectionsKt.listOf("connected", "enabled", "activityIn", "activityOut", "level", "iconGroup", "inetCondition", "rssi", "time");
    }

    public List tableData() {
        List listOf = CollectionsKt__CollectionsKt.listOf(Boolean.valueOf(this.connected), Boolean.valueOf(this.enabled), Boolean.valueOf(this.activityIn), Boolean.valueOf(this.activityOut), Integer.valueOf(this.level), this.iconGroup, Integer.valueOf(this.inetCondition), Integer.valueOf(this.rssi), ConnectivityStateKt.sSDF.format(Long.valueOf(this.time)));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        return arrayList;
    }

    public final String toString() {
        if (this.time == 0) {
            return "Empty ".concat(getClass().getSimpleName());
        }
        StringBuilder sb = new StringBuilder();
        toString(sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNull(sb2);
        return sb2;
    }

    public void toString(StringBuilder sb) {
        sb.append("connected=" + this.connected + ",");
        sb.append("enabled=" + this.enabled + ",");
        sb.append("level=" + this.level + ",");
        sb.append("inetCondition=" + this.inetCondition + ",");
        sb.append("iconGroup=" + this.iconGroup + ",");
        sb.append("activityIn=" + this.activityIn + ",");
        sb.append("activityOut=" + this.activityOut + ",");
        sb.append("rssi=" + this.rssi + ",");
        sb.append("lastModified=" + ConnectivityStateKt.sSDF.format(Long.valueOf(this.time)));
    }
}
