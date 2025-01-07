package com.google.android.systemui.elmyra;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader;
import com.google.android.systemui.elmyra.sensors.CHREGestureSensor;
import com.google.android.systemui.elmyra.sensors.CHREGestureSensor$$ExternalSyntheticLambda0;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.protobuf.nano.MessageNano;
import java.util.Random;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotController implements GestureSensor.Listener {
    public final int mSnapshotDelayAfterGesture;
    public CHREGestureSensor$$ExternalSyntheticLambda0 mSnapshotListener;
    public int mLastGestureStage = 0;
    public final AnonymousClass1 mHandler = new Handler(Looper.getMainLooper()) { // from class: com.google.android.systemui.elmyra.SnapshotController.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = (SnapshotProtos$SnapshotHeader) message.obj;
            CHREGestureSensor$$ExternalSyntheticLambda0 cHREGestureSensor$$ExternalSyntheticLambda0 = SnapshotController.this.mSnapshotListener;
            if (cHREGestureSensor$$ExternalSyntheticLambda0 != null) {
                CHREGestureSensor cHREGestureSensor = cHREGestureSensor$$ExternalSyntheticLambda0.f$0;
                cHREGestureSensor.getClass();
                cHREGestureSensor.sendMessageToNanoApp(203, MessageNano.toByteArray(snapshotProtos$SnapshotHeader));
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.elmyra.SnapshotController$1] */
    public SnapshotController(SnapshotConfiguration snapshotConfiguration) {
        this.mSnapshotDelayAfterGesture = snapshotConfiguration.mSnapshotDelayAfterGesture;
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
    public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
        SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = new SnapshotProtos$SnapshotHeader();
        snapshotProtos$SnapshotHeader.gestureType = 1;
        snapshotProtos$SnapshotHeader.identifier = detectionProperties != null ? detectionProperties.mActionId : 0L;
        this.mLastGestureStage = 0;
        AnonymousClass1 anonymousClass1 = this.mHandler;
        anonymousClass1.sendMessageDelayed(anonymousClass1.obtainMessage(1, snapshotProtos$SnapshotHeader), this.mSnapshotDelayAfterGesture);
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
    public final void onGestureProgress(int i, float f) {
        if (this.mLastGestureStage == 2 && i != 2) {
            SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = new SnapshotProtos$SnapshotHeader();
            snapshotProtos$SnapshotHeader.identifier = new Random().nextLong();
            snapshotProtos$SnapshotHeader.gestureType = 2;
            CHREGestureSensor$$ExternalSyntheticLambda0 cHREGestureSensor$$ExternalSyntheticLambda0 = this.mSnapshotListener;
            if (cHREGestureSensor$$ExternalSyntheticLambda0 != null) {
                CHREGestureSensor cHREGestureSensor = cHREGestureSensor$$ExternalSyntheticLambda0.f$0;
                cHREGestureSensor.getClass();
                cHREGestureSensor.sendMessageToNanoApp(203, MessageNano.toByteArray(snapshotProtos$SnapshotHeader));
            }
        }
        this.mLastGestureStage = i;
    }
}
