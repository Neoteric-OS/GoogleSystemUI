package com.google.android.systemui.keyguard.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.android.wm.shell.R;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda2;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class KeyguardAmbientIndicationAreaViewBinder {
    public static KeyguardAmbientIndicationAreaViewBinder$bind$1 bind(ViewGroup viewGroup, KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel, AodAlphaViewModel aodAlphaViewModel, ShadeViewController shadeViewController, PowerInteractor powerInteractor, KeyguardUpdateMonitor keyguardUpdateMonitor, ActivityStarter activityStarter, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) {
        final AmbientIndicationContainer ambientIndicationContainer = (AmbientIndicationContainer) viewGroup.findViewById(R.id.ambient_indication_container);
        if (ambientIndicationContainer != null) {
            ambientIndicationContainer.mShadeViewController = shadeViewController;
            ambientIndicationContainer.mPowerInteractor = powerInteractor;
            ambientIndicationContainer.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            ambientIndicationContainer.mActivityStarter = activityStarter;
            ambientIndicationContainer.mDelayedWakeLockFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
            ambientIndicationContainer.mWakeLock = ambientIndicationContainer.createWakeLock();
            ambientIndicationContainer.mInflateListeners.add(new AmbientIndicationContainer$$ExternalSyntheticLambda2(ambientIndicationContainer));
            ambientIndicationContainer.getChildAt(0);
            AmbientIndicationContainer.$r8$lambda$DFan0h9JQgIimo3ogLWaY_C9MMU(ambientIndicationContainer);
            ambientIndicationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda3
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    AmbientIndicationContainer ambientIndicationContainer2 = AmbientIndicationContainer.this;
                    int i9 = AmbientIndicationContainer.$r8$clinit;
                    ambientIndicationContainer2.updateBottomSpacing$1();
                }
            });
        }
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = null;
        if (ambientIndicationContainer != null) {
            KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1 keyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1 = new KeyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1(aodAlphaViewModel, ambientIndicationContainer, keyguardAmbientIndicationViewModel, null);
            CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
            repeatWhenAttachedKt$repeatWhenAttached$1 = RepeatWhenAttachedKt.repeatWhenAttached(ambientIndicationContainer, EmptyCoroutineContext.INSTANCE, keyguardAmbientIndicationAreaViewBinder$bind$disposableHandle$1);
        }
        return new KeyguardAmbientIndicationAreaViewBinder$bind$1(repeatWhenAttachedKt$repeatWhenAttached$1);
    }
}
