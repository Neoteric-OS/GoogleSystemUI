package com.android.systemui.sensorprivacy;

import com.android.internal.util.FrameworkStatsLog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorUseStartedActivity$onStop$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SensorUseStartedActivity this$0;

    public /* synthetic */ SensorUseStartedActivity$onStop$1(SensorUseStartedActivity sensorUseStartedActivity, int i) {
        this.$r8$classId = i;
        this.this$0 = sensorUseStartedActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                SensorUseStartedActivity sensorUseStartedActivity = this.this$0;
                int i = SensorUseStartedActivity.$r8$clinit;
                sensorUseStartedActivity.setSuppressed(false);
                break;
            default:
                SensorUseStartedActivity sensorUseStartedActivity2 = this.this$0;
                int i2 = SensorUseStartedActivity.$r8$clinit;
                sensorUseStartedActivity2.disableSensorPrivacy();
                String str = this.this$0.sensorUsePackageName;
                if (str == null) {
                    str = null;
                }
                FrameworkStatsLog.write(382, 1, str);
                break;
        }
    }
}
