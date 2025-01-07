package com.google.android.systemui.dagger;

import com.android.systemui.unfold.FullscreenLightRevealAnimation;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl {
    public final Provider bindNaturalRotationUnfoldProgressProvider;
    public final Provider bindsFoldLightRevealOverlayAnimationProvider;
    public final Provider factoryProvider;
    public final Provider foldAodAnimationControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider foldLightRevealOverlayAnimationProvider;
    public final Provider keyguardUnfoldTransitionProvider;
    public final Provider notificationPanelUnfoldAnimationControllerProvider;
    public final InstanceFactory p1Provider;
    public final NaturalRotationUnfoldProgressProvider p2;
    public final InstanceFactory p2Provider;
    public final ScopedUnfoldTransitionProgressProvider p3;
    public final InstanceFactory p4Provider;
    public final Provider unfoldHapticsPlayerProvider;
    public final Provider unfoldLatencyTrackerProvider;
    public final Provider unfoldLightRevealOverlayAnimationProvider;
    public final Provider unfoldTransitionWallpaperControllerProvider;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider, NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider, ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2) {
        this.p3 = scopedUnfoldTransitionProgressProvider;
        this.p2 = naturalRotationUnfoldProgressProvider;
        this.bindNaturalRotationUnfoldProgressProvider = DoubleCheck.provider(new InstanceFactory(naturalRotationUnfoldProgressProvider));
        int i = 10;
        this.keyguardUnfoldTransitionProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, i));
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, i));
        this.notificationPanelUnfoldAnimationControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2, i));
        this.foldAodAnimationControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3, i));
        this.p4Provider = new InstanceFactory(unfoldTransitionProgressProvider2);
        this.p1Provider = new InstanceFactory(unfoldTransitionProgressProvider);
        int i2 = 10;
        this.factoryProvider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5, i2));
        this.unfoldLightRevealOverlayAnimationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4, i2));
        this.bindsFoldLightRevealOverlayAnimationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6, i2));
        this.unfoldTransitionWallpaperControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7, i2));
        this.unfoldHapticsPlayerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8, i2));
        this.unfoldLatencyTrackerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9, i2));
    }

    public final Set getFullScreenLightRevealAnimations() {
        ArrayList arrayList = new ArrayList(2);
        FullscreenLightRevealAnimation fullscreenLightRevealAnimation = (FullscreenLightRevealAnimation) this.unfoldLightRevealOverlayAnimationProvider.get();
        Preconditions.checkNotNull(fullscreenLightRevealAnimation, "Set contributions cannot be null");
        arrayList.add(fullscreenLightRevealAnimation);
        FullscreenLightRevealAnimation fullscreenLightRevealAnimation2 = (FullscreenLightRevealAnimation) this.bindsFoldLightRevealOverlayAnimationProvider.get();
        Preconditions.checkNotNull(fullscreenLightRevealAnimation2, "Set contributions cannot be null");
        arrayList.add(fullscreenLightRevealAnimation2);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }
}
