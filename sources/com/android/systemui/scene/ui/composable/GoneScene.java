package com.android.systemui.scene.ui.composable;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.lifecycle.ExclusiveActivatable;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.ui.viewmodel.GoneUserActionsViewModel;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$36;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GoneScene extends ExclusiveActivatable implements Scene {
    public final Lazy actionsViewModel$delegate;
    public final SceneKey key;
    public final dagger.Lazy notificationStackScrolLView;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 notificationsPlaceholderViewModelFactory;
    public final StateFlow userActions;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$36 viewModelFactory;

    public GoneScene(dagger.Lazy lazy, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$34, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$36 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$36) {
        this.viewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$36;
        SceneKey sceneKey = Scenes.Gone;
        ReadonlyStateFlow readonlyStateFlow = ((GoneUserActionsViewModel) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.scene.ui.composable.GoneScene$actionsViewModel$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new GoneUserActionsViewModel((ShadeInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) GoneScene.this.viewModelFactory.this$0.wMComponentImpl).shadeInteractorImplProvider.get());
            }
        }).getValue()).actions;
    }
}
