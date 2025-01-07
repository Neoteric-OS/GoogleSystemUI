package com.google.android.systemui.ambientmusic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import com.google.android.systemui.keyguard.shared.AmbientIndicationMusic;
import java.util.Objects;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AmbientIndicationService extends BroadcastReceiver {
    public final AlarmManager mAlarmManager;
    public final AmbientIndicationInteractor mAmbientIndicationInteractor;
    public final KeyguardUpdateMonitorCallback mCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationService.1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitchComplete(int i) {
            AmbientIndicationService.this.onUserSwitched();
        }
    };
    public final Context mContext;
    public final AlarmManager.OnAlarmListener mHideIndicationListener;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public boolean mStarted;

    public AmbientIndicationService(AlarmManager alarmManager, Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, SelectedUserInteractor selectedUserInteractor, final AmbientIndicationInteractor ambientIndicationInteractor) {
        this.mContext = context;
        this.mAmbientIndicationInteractor = ambientIndicationInteractor;
        this.mAlarmManager = alarmManager;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        Objects.requireNonNull(ambientIndicationInteractor);
        this.mHideIndicationListener = new AlarmManager.OnAlarmListener() { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationService$$ExternalSyntheticLambda0
            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                AmbientIndicationInteractor.this.hideAmbientMusic();
            }
        };
    }

    public int getCurrentUser() {
        return this.mSelectedUserInteractor.getSelectedUserId();
    }

    public boolean isForCurrentUser() {
        return getSendingUserId() == getCurrentUser() || getSendingUserId() == -1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        char c;
        if (!isForCurrentUser()) {
            Log.i("AmbientIndication", "Suppressing ambient, not for this user.");
            return;
        }
        int intExtra = intent.getIntExtra("com.google.android.ambientindication.extra.VERSION", 0);
        if (intExtra != 1) {
            Log.e("AmbientIndication", "AmbientIndicationApi.EXTRA_VERSION is 1, but received an intent with version " + intExtra + ", dropping intent.");
            return;
        }
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != -1032272569) {
            if (hashCode == -1031945470 && action.equals("com.google.android.ambientindication.action.AMBIENT_INDICATION_SHOW")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (action.equals("com.google.android.ambientindication.action.AMBIENT_INDICATION_HIDE")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return;
            }
            this.mAlarmManager.cancel(this.mHideIndicationListener);
            this.mAmbientIndicationInteractor.hideAmbientMusic();
            Log.i("AmbientIndication", "Hiding ambient indication.");
            return;
        }
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("com.google.android.ambientindication.extra.TEXT");
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("com.google.android.ambientindication.extra.OPEN_INTENT");
        PendingIntent pendingIntent2 = (PendingIntent) intent.getParcelableExtra("com.google.android.ambientindication.extra.FAVORITING_INTENT");
        long min = Math.min(Math.max(intent.getLongExtra("com.google.android.ambientindication.extra.TTL_MILLIS", 180000L), 0L), 180000L);
        boolean booleanExtra = intent.getBooleanExtra("com.google.android.ambientindication.extra.SKIP_UNLOCK", false);
        int intExtra2 = intent.getIntExtra("com.google.android.ambientindication.extra.ICON_OVERRIDE", 0);
        String stringExtra = intent.getStringExtra("com.google.android.ambientindication.extra.ICON_DESCRIPTION");
        AmbientIndicationInteractor ambientIndicationInteractor = this.mAmbientIndicationInteractor;
        Integer valueOf = Integer.valueOf(intExtra2);
        Boolean valueOf2 = Boolean.valueOf(booleanExtra);
        StateFlowImpl stateFlowImpl = ambientIndicationInteractor.ambientIndicationRepository.ambientMusic;
        AmbientIndicationMusic ambientIndicationMusic = new AmbientIndicationMusic(charSequenceExtra, pendingIntent, pendingIntent2, valueOf, valueOf2, stringExtra);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, ambientIndicationMusic);
        StateFlowImpl stateFlowImpl2 = ambientIndicationInteractor.keyguardInteractor.repository.ambientIndicationVisible;
        Boolean bool = Boolean.TRUE;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + min, "AmbientIndication", this.mHideIndicationListener, null);
        Log.i("AmbientIndication", "Showing ambient indication.");
    }

    public void onUserSwitched() {
        this.mAmbientIndicationInteractor.hideAmbientMusic();
    }
}
