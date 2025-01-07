package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnpinNotifications extends Action {
    public final Context mContext;
    public boolean mHasPinnedHeadsUp;
    public final AnonymousClass1 mHeadsUpChangedListener;
    public final Optional mHeadsUpManagerOptional;
    public boolean mSilenceSettingEnabled;

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.elmyra.actions.UnpinNotifications$1] */
    public UnpinNotifications(Context context, Executor executor, Optional optional) {
        super(executor, null);
        this.mHeadsUpChangedListener = new OnHeadsUpChangedListener() { // from class: com.google.android.systemui.elmyra.actions.UnpinNotifications.1
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpPinnedModeChanged(boolean z) {
                UnpinNotifications unpinNotifications = UnpinNotifications.this;
                if (unpinNotifications.mHasPinnedHeadsUp != z) {
                    unpinNotifications.mHasPinnedHeadsUp = z;
                    unpinNotifications.notifyListener();
                }
            }
        };
        this.mContext = context;
        this.mHeadsUpManagerOptional = optional;
        if (!optional.isPresent()) {
            Log.w("Elmyra/UnpinNotifications", "No HeadsUpManager");
        } else {
            updateHeadsUpListener();
            new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_silence_alerts_enabled"), new Consumer() { // from class: com.google.android.systemui.elmyra.actions.UnpinNotifications$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnpinNotifications.this.updateHeadsUpListener();
                }
            }, true);
        }
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final boolean isAvailable() {
        if (this.mSilenceSettingEnabled) {
            return this.mHasPinnedHeadsUp;
        }
        return false;
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mHeadsUpManagerOptional.ifPresent(new UnpinNotifications$$ExternalSyntheticLambda1());
    }

    public final void updateHeadsUpListener() {
        if (this.mHeadsUpManagerOptional.isPresent()) {
            boolean z = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "assist_gesture_silence_alerts_enabled", 1, -2) != 0;
            if (this.mSilenceSettingEnabled != z) {
                this.mSilenceSettingEnabled = z;
                AnonymousClass1 anonymousClass1 = this.mHeadsUpChangedListener;
                if (z) {
                    this.mHasPinnedHeadsUp = ((BaseHeadsUpManager) ((HeadsUpManager) this.mHeadsUpManagerOptional.get())).mHasPinnedNotification;
                    ((BaseHeadsUpManager) ((HeadsUpManager) this.mHeadsUpManagerOptional.get())).addListener(anonymousClass1);
                } else {
                    this.mHasPinnedHeadsUp = false;
                    ((BaseHeadsUpManager) ((HeadsUpManager) this.mHeadsUpManagerOptional.get())).mListeners.remove(anonymousClass1);
                }
                notifyListener();
            }
        }
    }
}
