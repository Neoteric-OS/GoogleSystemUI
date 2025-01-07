package com.android.systemui.shade.data.repository;

import android.app.AlarmManager;
import android.app.PendingIntent;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.NextAlarmControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderClockRepository {
    public final ShadeHeaderClockRepository$nextAlarmCallback$1 nextAlarmCallback;
    public PendingIntent nextAlarmIntent;

    public ShadeHeaderClockRepository(NextAlarmController nextAlarmController) {
        ((NextAlarmControllerImpl) nextAlarmController).addCallback(new NextAlarmController.NextAlarmChangeCallback() { // from class: com.android.systemui.shade.data.repository.ShadeHeaderClockRepository$nextAlarmCallback$1
            @Override // com.android.systemui.statusbar.policy.NextAlarmController.NextAlarmChangeCallback
            public final void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
                ShadeHeaderClockRepository.this.nextAlarmIntent = alarmClockInfo != null ? alarmClockInfo.getShowIntent() : null;
            }
        });
    }
}
