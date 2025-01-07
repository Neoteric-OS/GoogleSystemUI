package com.google.android.systemui.dagger;

import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.dreams.DreamOverlayLifecycleOwner;
import com.android.systemui.touch.TouchInsetManager;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl {
    public final ComplicationHostViewController complicationHostViewController;
    public final Provider dreamOverlayAnimationsControllerProvider;
    public final Provider dreamOverlayContainerViewControllerProvider;
    public final DreamOverlayLifecycleOwner lifecycleOwner;
    public final Provider providesDreamInComplicationsTranslationYDurationProvider;
    public final Provider providesDreamInComplicationsTranslationYProvider;
    public final Provider providesDreamOverlayContainerViewProvider;
    public final Provider providesDreamOverlayContentViewProvider;
    public final Provider providesDreamOverlayStatusBarViewProvider;
    public final Provider providesLifecycleProvider;
    public final Provider providesMaxBurnInOffsetProvider;
    public final Provider providesStatusBarViewControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final TouchInsetManager touchInsetManager;

    public DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner, ComplicationHostViewController complicationHostViewController, TouchInsetManager touchInsetManager) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.complicationHostViewController = complicationHostViewController;
        this.touchInsetManager = touchInsetManager;
        this.lifecycleOwner = dreamOverlayLifecycleOwner;
        this.providesDreamOverlayContainerViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, 2));
        this.providesDreamOverlayContentViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2, 2));
        this.providesDreamOverlayStatusBarViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4, 2));
        this.providesStatusBarViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3, 2));
        this.providesMaxBurnInOffsetProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5, 2));
        this.providesDreamInComplicationsTranslationYProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7, 2));
        this.providesDreamInComplicationsTranslationYDurationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8, 2));
        this.dreamOverlayAnimationsControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6, 2));
        this.dreamOverlayContainerViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, 2));
        this.providesLifecycleProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9, 2));
    }
}
