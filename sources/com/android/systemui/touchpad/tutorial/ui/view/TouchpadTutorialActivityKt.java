package com.android.systemui.touchpad.tutorial.ui.view;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.touchpad.tutorial.ui.composable.BackGestureTutorialScreenKt;
import com.android.systemui.touchpad.tutorial.ui.composable.HomeGestureTutorialScreenKt;
import com.android.systemui.touchpad.tutorial.ui.composable.RecentAppsGestureTutorialScreenKt;
import com.android.systemui.touchpad.tutorial.ui.composable.TutorialSelectionScreenKt;
import com.android.systemui.touchpad.tutorial.ui.viewmodel.Screen;
import com.android.systemui.touchpad.tutorial.ui.viewmodel.TouchpadTutorialViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TouchpadTutorialActivityKt {
    public static final void TouchpadTutorialScreen(final TouchpadTutorialViewModel touchpadTutorialViewModel, final Function0 function0, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1136903685);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Enum r0 = (Enum) FlowExtKt.collectAsStateWithLifecycle(touchpadTutorialViewModel.screen, Lifecycle.State.STARTED, composerImpl, 56).getValue();
        if (r0 == Screen.TUTORIAL_SELECTION) {
            composerImpl.startReplaceGroup(391199137);
            TutorialSelectionScreenKt.TutorialSelectionScreen(new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.BACK_GESTURE);
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.HOME_GESTURE);
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.RECENT_APPS_GESTURE);
                    return Unit.INSTANCE;
                }
            }, function0, composerImpl, (i << 6) & 7168);
            composerImpl.end(false);
        } else if (r0 == Screen.BACK_GESTURE) {
            composerImpl.startReplaceGroup(391199478);
            BackGestureTutorialScreenKt.BackGestureTutorialScreen(new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$4
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$5
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, composerImpl, 0);
            composerImpl.end(false);
        } else if (r0 == Screen.HOME_GESTURE) {
            composerImpl.startReplaceGroup(391199684);
            HomeGestureTutorialScreenKt.HomeGestureTutorialScreen(new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$6
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$7
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, composerImpl, 0);
            composerImpl.end(false);
        } else if (r0 == Screen.RECENT_APPS_GESTURE) {
            composerImpl.startReplaceGroup(391199897);
            RecentAppsGestureTutorialScreenKt.RecentAppsGestureTutorialScreen(new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$8
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$9
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TouchpadTutorialViewModel.this.goTo(Screen.TUTORIAL_SELECTION);
                    return Unit.INSTANCE;
                }
            }, composerImpl, 0);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(391200078);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivityKt$TouchpadTutorialScreen$10
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TouchpadTutorialActivityKt.TouchpadTutorialScreen(TouchpadTutorialViewModel.this, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
