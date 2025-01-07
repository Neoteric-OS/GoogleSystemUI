package com.android.systemui.bouncer.ui.composable;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.bouncer.domain.interactor.BouncerInteractor;
import com.android.systemui.bouncer.ui.BouncerViewModule$Companion$bouncerDialogFactory$1;
import com.android.systemui.bouncer.ui.viewmodel.BouncerUserActionsViewModel;
import com.android.systemui.lifecycle.ExclusiveActivatable;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.ui.composable.Scene;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerScene extends ExclusiveActivatable implements Scene {
    public final Lazy actionsViewModel$delegate;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3 actionsViewModelFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4 contentViewModelFactory;
    public final BouncerViewModule$Companion$bouncerDialogFactory$1 dialogFactory;
    public final SceneKey key;
    public final StateFlow userActions;

    public BouncerScene(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$4, BouncerViewModule$Companion$bouncerDialogFactory$1 bouncerViewModule$Companion$bouncerDialogFactory$1) {
        this.actionsViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$3;
        SceneKey sceneKey = Scenes.Bouncer;
        ReadonlyStateFlow readonlyStateFlow = ((BouncerUserActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.bouncer.ui.composable.BouncerScene$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new BouncerUserActionsViewModel((BouncerInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) BouncerScene.this.actionsViewModelFactory.this$0.wMComponentImpl).bouncerInteractorProvider.get());
            }
        }).getValue()).actions;
    }
}
