package com.android.systemui.controls;

import android.util.StatsEvent;
import android.util.StatsLog;
import com.android.internal.logging.InstanceIdSequence;
import com.android.systemui.controls.ui.ControlViewHolder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsMetricsLoggerImpl {
    public int instanceId;
    public final InstanceIdSequence instanceIdSequence = new InstanceIdSequence(8192);

    public void drag(ControlViewHolder controlViewHolder, boolean z) {
        log(ControlsMetricsLogger$ControlsEvents.CONTROL_DRAG.getId(), controlViewHolder.getDeviceType(), controlViewHolder.uid, z);
    }

    public final void log(int i, int i2, int i3, boolean z) {
        int i4 = this.instanceId;
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(349);
        newBuilder.writeInt(i);
        newBuilder.writeInt(i4);
        newBuilder.writeInt(i2);
        newBuilder.writeInt(i3);
        newBuilder.addBooleanAnnotation((byte) 1, true);
        newBuilder.writeBoolean(z);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }

    public void longPress(ControlViewHolder controlViewHolder, boolean z) {
        log(ControlsMetricsLogger$ControlsEvents.CONTROL_LONG_PRESS.getId(), controlViewHolder.getDeviceType(), controlViewHolder.uid, z);
    }

    public void refreshBegin(int i, boolean z) {
        this.instanceId = this.instanceIdSequence.newInstanceId().getId();
        log(ControlsMetricsLogger$ControlsEvents.CONTROL_REFRESH_BEGIN.getId(), 0, i, z);
    }

    public void refreshEnd(ControlViewHolder controlViewHolder, boolean z) {
        log(ControlsMetricsLogger$ControlsEvents.CONTROL_REFRESH_END.getId(), controlViewHolder.getDeviceType(), controlViewHolder.uid, z);
    }

    public void touch(ControlViewHolder controlViewHolder, boolean z) {
        log(ControlsMetricsLogger$ControlsEvents.CONTROL_TOUCH.getId(), controlViewHolder.getDeviceType(), controlViewHolder.uid, z);
    }
}
