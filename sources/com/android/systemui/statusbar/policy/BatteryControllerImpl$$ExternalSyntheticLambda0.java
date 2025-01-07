package com.android.systemui.statusbar.policy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryControllerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatteryControllerImpl f$0;

    public /* synthetic */ BatteryControllerImpl$$ExternalSyntheticLambda0(BatteryControllerImpl batteryControllerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = batteryControllerImpl;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x004d, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0059, code lost:
    
        throw r7;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r7 = this;
            int r0 = r7.$r8$classId
            com.android.systemui.statusbar.policy.BatteryControllerImpl r7 = r7.f$0
            switch(r0) {
                case 0: goto L5a;
                default: goto L7;
            }
        L7:
            java.util.ArrayList r0 = r7.mFetchCallbacks
            monitor-enter(r0)
            java.lang.Object r1 = r7.mEstimateLock     // Catch: java.lang.Throwable -> L4d
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L4d
            com.android.settingslib.fuelgauge.Estimate r2 = r7.mEstimate     // Catch: java.lang.Throwable -> L14
            if (r2 != 0) goto L16
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L14
            r1 = 0
            goto L20
        L14:
            r7 = move-exception
            goto L56
        L16:
            android.content.Context r3 = r7.mContext     // Catch: java.lang.Throwable -> L14
            long r4 = r2.estimateMillis     // Catch: java.lang.Throwable -> L14
            java.lang.String r2 = com.android.settingslib.utils.PowerUtil.getBatteryRemainingShortStringFormatted(r3, r4)     // Catch: java.lang.Throwable -> L14
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L14
            r1 = r2
        L20:
            java.util.ArrayList r2 = r7.mFetchCallbacks     // Catch: java.lang.Throwable -> L4d
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L4d
        L26:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L4d
            if (r3 == 0) goto L4f
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L4d
            com.android.systemui.battery.BatteryMeterView$$ExternalSyntheticLambda0 r3 = (com.android.systemui.battery.BatteryMeterView$$ExternalSyntheticLambda0) r3     // Catch: java.lang.Throwable -> L4d
            com.android.systemui.battery.BatteryMeterView r3 = r3.f$0     // Catch: java.lang.Throwable -> L4d
            android.widget.TextView r4 = r3.mBatteryPercentView     // Catch: java.lang.Throwable -> L4d
            if (r4 != 0) goto L39
            goto L26
        L39:
            if (r1 == 0) goto L49
            int r5 = r3.mShowPercentMode     // Catch: java.lang.Throwable -> L4d
            r6 = 3
            if (r5 != r6) goto L49
            r3.mEstimateText = r1     // Catch: java.lang.Throwable -> L4d
            r4.setText(r1)     // Catch: java.lang.Throwable -> L4d
            r3.updateContentDescription()     // Catch: java.lang.Throwable -> L4d
            goto L26
        L49:
            r3.setPercentTextAtCurrentLevel()     // Catch: java.lang.Throwable -> L4d
            goto L26
        L4d:
            r7 = move-exception
            goto L58
        L4f:
            java.util.ArrayList r7 = r7.mFetchCallbacks     // Catch: java.lang.Throwable -> L4d
            r7.clear()     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
            return
        L56:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L14
            throw r7     // Catch: java.lang.Throwable -> L4d
        L58:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
            throw r7
        L5a:
            java.lang.Object r0 = r7.mEstimateLock
            monitor-enter(r0)
            r1 = 0
            r7.mEstimate = r1     // Catch: java.lang.Throwable -> L6e
            com.android.systemui.power.EnhancedEstimates r1 = r7.mEstimates     // Catch: java.lang.Throwable -> L6e
            com.google.android.systemui.power.EnhancedEstimatesGoogleImpl r1 = (com.google.android.systemui.power.EnhancedEstimatesGoogleImpl) r1     // Catch: java.lang.Throwable -> L6e
            boolean r1 = r1.isHybridNotificationEnabled()     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L70
            r7.updateEstimate()     // Catch: java.lang.Throwable -> L6e
            goto L70
        L6e:
            r7 = move-exception
            goto L80
        L70:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            r0 = 0
            r7.mFetchingEstimate = r0
            android.os.Handler r0 = r7.mMainHandler
            com.android.systemui.statusbar.policy.BatteryControllerImpl$$ExternalSyntheticLambda0 r1 = new com.android.systemui.statusbar.policy.BatteryControllerImpl$$ExternalSyntheticLambda0
            r2 = 1
            r1.<init>(r7, r2)
            r0.post(r1)
            return
        L80:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6e
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.BatteryControllerImpl$$ExternalSyntheticLambda0.run():void");
    }
}
