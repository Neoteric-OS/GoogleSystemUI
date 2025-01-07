package com.android.systemui.keyguard.ui.composable;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenUserActionsViewModel;
import com.android.systemui.lifecycle.ExclusiveActivatable;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.ui.composable.Scene;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$37;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenScene extends ExclusiveActivatable implements Scene {
    public final Lazy actionsViewModel$delegate;
    public final SceneKey key;
    public final dagger.Lazy lockscreenContent;
    public final StateFlow userActions;

    public LockscreenScene(final LockscreenUserActionsViewModel.Factory factory, dagger.Lazy lazy) {
        SceneKey sceneKey = Scenes.Lockscreen;
        ReadonlyStateFlow readonlyStateFlow = ((LockscreenUserActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.ui.composable.LockscreenScene$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$37) LockscreenUserActionsViewModel.Factory.this).this$0;
                DeviceEntryInteractor deviceEntryInteractor = (DeviceEntryInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).deviceEntryInteractorProvider.get();
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
                return new LockscreenUserActionsViewModel(deviceEntryInteractor, (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get());
            }
        }).getValue()).actions;
    }
}
