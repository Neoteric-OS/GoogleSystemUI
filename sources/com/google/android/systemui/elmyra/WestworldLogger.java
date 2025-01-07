package com.google.android.systemui.elmyra;

import android.app.StatsManager;
import android.content.Context;
import android.util.Log;
import android.util.StatsEvent;
import com.android.internal.util.ConcurrentUtils;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.google.android.systemui.elmyra.SnapshotController;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis;
import com.google.android.systemui.elmyra.proto.nano.ElmyraAtoms$ElmyraSnapshot;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.MessageNano;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WestworldLogger implements GestureSensor.Listener {
    public ChassisProtos$Chassis mChassis;
    public CountDownLatch mCountDownLatch;
    public final GestureConfiguration mGestureConfiguration;
    public final Object mMutex;
    public SnapshotProtos$Snapshot mSnapshot;
    public final SnapshotController mSnapshotController;
    public final WestworldLogger$$ExternalSyntheticLambda0 mWestworldCallback;

    public WestworldLogger(Context context, GestureConfiguration gestureConfiguration, SnapshotController snapshotController) {
        StatsManager.StatsPullAtomCallback statsPullAtomCallback = new StatsManager.StatsPullAtomCallback() { // from class: com.google.android.systemui.elmyra.WestworldLogger$$ExternalSyntheticLambda0
            public final int onPullAtom(int i, List list) {
                WestworldLogger westworldLogger = WestworldLogger.this;
                westworldLogger.getClass();
                Log.d("Elmyra/Logger", "Receiving pull request from statsd.");
                int i2 = 1;
                if (westworldLogger.mSnapshotController == null) {
                    Log.d("Elmyra/Logger", "Snapshot Controller is null, returning.");
                } else {
                    synchronized (westworldLogger.mMutex) {
                        try {
                            if (westworldLogger.mCountDownLatch == null) {
                                westworldLogger.mCountDownLatch = new CountDownLatch(1);
                                SnapshotController snapshotController2 = westworldLogger.mSnapshotController;
                                snapshotController2.getClass();
                                SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = new SnapshotProtos$SnapshotHeader();
                                snapshotProtos$SnapshotHeader.gestureType = 4;
                                snapshotProtos$SnapshotHeader.identifier = 0L;
                                SnapshotController.AnonymousClass1 anonymousClass1 = snapshotController2.mHandler;
                                anonymousClass1.sendMessage(anonymousClass1.obtainMessage(1, snapshotProtos$SnapshotHeader));
                                try {
                                    long currentTimeMillis = System.currentTimeMillis();
                                    westworldLogger.mCountDownLatch.await(50L, TimeUnit.MILLISECONDS);
                                    Log.d("Elmyra/Logger", "Snapshot took " + Long.toString(System.currentTimeMillis() - currentTimeMillis) + " milliseconds.");
                                } catch (IllegalMonitorStateException e) {
                                    Log.d("Elmyra/Logger", e.getMessage());
                                } catch (InterruptedException e2) {
                                    Log.d("Elmyra/Logger", e2.getMessage());
                                }
                                synchronized (westworldLogger.mMutex) {
                                    try {
                                        if (westworldLogger.mSnapshot != null && westworldLogger.mChassis != null) {
                                            ElmyraAtoms$ElmyraSnapshot elmyraAtoms$ElmyraSnapshot = new ElmyraAtoms$ElmyraSnapshot();
                                            float sensitivity = westworldLogger.mGestureConfiguration.getSensitivity();
                                            SnapshotProtos$Snapshot snapshotProtos$Snapshot = westworldLogger.mSnapshot;
                                            snapshotProtos$Snapshot.sensitivitySetting = sensitivity;
                                            elmyraAtoms$ElmyraSnapshot.snapshot = snapshotProtos$Snapshot;
                                            elmyraAtoms$ElmyraSnapshot.chassis = westworldLogger.mChassis;
                                            list.add(StatsEvent.newBuilder().setAtomId(i).writeByteArray(MessageNano.toByteArray(elmyraAtoms$ElmyraSnapshot.snapshot)).writeByteArray(MessageNano.toByteArray(elmyraAtoms$ElmyraSnapshot.chassis)).build());
                                            westworldLogger.mSnapshot = null;
                                            synchronized (westworldLogger.mMutex) {
                                                westworldLogger.mCountDownLatch = null;
                                                westworldLogger.mSnapshot = null;
                                            }
                                            i2 = 0;
                                        }
                                        westworldLogger.mCountDownLatch = null;
                                    } finally {
                                    }
                                }
                            }
                        } finally {
                        }
                    }
                }
                return i2;
            }
        };
        this.mChassis = null;
        this.mGestureConfiguration = gestureConfiguration;
        this.mSnapshotController = snapshotController;
        this.mSnapshot = null;
        this.mMutex = new Object();
        StatsManager statsManager = (StatsManager) context.getSystemService("stats");
        if (statsManager == null) {
            Log.d("Elmyra/Logger", "Failed to get StatsManager");
        }
        try {
            statsManager.setPullAtomCallback(150000, (StatsManager.PullAtomMetadata) null, ConcurrentUtils.DIRECT_EXECUTOR, statsPullAtomCallback);
        } catch (RuntimeException e) {
            Log.d("Elmyra/Logger", "Failed to register callback with StatsManager");
            e.printStackTrace();
        }
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
    public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
        SysUiStatsLog.write(174, 3);
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor.Listener
    public final void onGestureProgress(int i, float f) {
        SysUiStatsLog.write(176, (int) (f * 100.0f));
        SysUiStatsLog.write(174, i);
    }
}
