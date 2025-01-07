package com.android.app.motiontool;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.FrameData;
import com.android.app.viewcapture.data.MotionWindowData;
import com.google.protobuf.Internal;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TraceMetadata {
    public long lastPolledTime = 0;
    public final Function0 stopTrace;
    public final String windowId;

    public TraceMetadata(String str, Function0 function0) {
        this.windowId = str;
        this.stopTrace = function0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TraceMetadata)) {
            return false;
        }
        TraceMetadata traceMetadata = (TraceMetadata) obj;
        return Intrinsics.areEqual(this.windowId, traceMetadata.windowId) && this.lastPolledTime == traceMetadata.lastPolledTime && Intrinsics.areEqual(this.stopTrace, traceMetadata.stopTrace);
    }

    public final int hashCode() {
        return this.stopTrace.hashCode() + Scale$$ExternalSyntheticOutline0.m(this.windowId.hashCode() * 31, 31, this.lastPolledTime);
    }

    public final String toString() {
        return "TraceMetadata(windowId=" + this.windowId + ", lastPolledTime=" + this.lastPolledTime + ", stopTrace=" + this.stopTrace + ")";
    }

    public final void updateLastPolledTime(MotionWindowData motionWindowData) {
        Long l;
        Internal.ProtobufList frameDataList = motionWindowData.getFrameDataList();
        if (frameDataList != null) {
            Iterator<E> it = frameDataList.iterator();
            if (it.hasNext()) {
                Long valueOf = Long.valueOf(((FrameData) it.next()).getTimestamp());
                while (it.hasNext()) {
                    Long valueOf2 = Long.valueOf(((FrameData) it.next()).getTimestamp());
                    if (valueOf.compareTo(valueOf2) < 0) {
                        valueOf = valueOf2;
                    }
                }
                l = valueOf;
            } else {
                l = null;
            }
            if (l != null) {
                this.lastPolledTime = l.longValue();
            }
        }
    }
}
