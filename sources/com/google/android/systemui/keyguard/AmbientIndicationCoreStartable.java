package com.google.android.systemui.keyguard;

import android.app.AlarmManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.CoreStartable;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.google.android.systemui.ambientmusic.AmbientIndicationService;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AmbientIndicationCoreStartable implements CoreStartable {
    public final AlarmManager alarmManager;
    public final AmbientIndicationInteractor ambientIndicationInteractor;
    public final Context context;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final SelectedUserInteractor selectedUserInteractor;

    public AmbientIndicationCoreStartable(AlarmManager alarmManager, Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, SelectedUserInteractor selectedUserInteractor, AmbientIndicationInteractor ambientIndicationInteractor) {
        this.context = context;
        this.alarmManager = alarmManager;
        this.selectedUserInteractor = selectedUserInteractor;
        this.ambientIndicationInteractor = ambientIndicationInteractor;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Context context = this.context;
        AmbientIndicationService ambientIndicationService = new AmbientIndicationService(this.alarmManager, context, this.keyguardUpdateMonitor, this.selectedUserInteractor, this.ambientIndicationInteractor);
        if (ambientIndicationService.mStarted) {
            return;
        }
        ambientIndicationService.mStarted = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ambientindication.action.AMBIENT_INDICATION_SHOW");
        intentFilter.addAction("com.google.android.ambientindication.action.AMBIENT_INDICATION_HIDE");
        ambientIndicationService.mContext.registerReceiverAsUser(ambientIndicationService, UserHandle.ALL, intentFilter, "com.google.android.ambientindication.permission.AMBIENT_INDICATION", null, 2);
        ambientIndicationService.mKeyguardUpdateMonitor.registerCallback(ambientIndicationService.mCallback);
    }
}
