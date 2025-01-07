package com.android.systemui.statusbar.phone;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenOffAnimationController implements WakefulnessLifecycle.Observer {
    public final List animations;
    public final WakefulnessLifecycle wakefulnessLifecycle;

    public ScreenOffAnimationController(Optional optional, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, WakefulnessLifecycle wakefulnessLifecycle) {
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) optional.orElse(null);
        this.animations = ArraysKt.filterNotNull(new ScreenOffAnimation[]{daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl != null ? (FoldAodAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.foldAodAnimationControllerProvider.get() : null, unlockedScreenOffAnimationController});
    }

    public final boolean isKeyguardShowDelayed() {
        List list = this.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).isKeyguardShowDelayed()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onStartedGoingToSleep() {
        Iterator it = this.animations.iterator();
        while (it.hasNext() && !((ScreenOffAnimation) it.next()).startAnimation()) {
        }
    }

    public final boolean overrideNotificationsFullyDozingOnKeyguard() {
        List list = this.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).overrideNotificationsDozeAmount()) {
                return true;
            }
        }
        return false;
    }

    public final boolean shouldDelayKeyguardShow() {
        List list = this.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).shouldDelayKeyguardShow()) {
                return true;
            }
        }
        return false;
    }

    public final boolean shouldExpandNotifications() {
        List list = this.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).isAnimationPlaying()) {
                return true;
            }
        }
        return false;
    }
}
