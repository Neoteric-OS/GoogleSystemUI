package com.android.systemui.inputdevice.tutorial.ui.view;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.ActionKeyTutorialScreenKt;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen;
import com.android.systemui.touchpad.tutorial.ScreensProvider;
import java.util.Optional;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyboardTouchpadTutorialActivityKt {
    public static final void KeyboardTouchpadTutorialContainer(final KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel, final Optional optional, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1977628515);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Enum r0 = (Enum) FlowExtKt.collectAsStateWithLifecycle(keyboardTouchpadTutorialViewModel.screen, Lifecycle.State.STARTED, composerImpl, 56).getValue();
        if (r0 == Screen.BACK_GESTURE) {
            composerImpl.startReplaceGroup(-1447870603);
            ((ScreensProvider) optional.get()).BackGesture(new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$1(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onDoneButtonClicked", "onDoneButtonClicked()V", 0), new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$2(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onBack", "onBack()V", 0), composerImpl, 0);
            composerImpl.end(false);
        } else if (r0 == Screen.HOME_GESTURE) {
            composerImpl.startReplaceGroup(-1447870431);
            ((ScreensProvider) optional.get()).HomeGesture(new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$3(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onDoneButtonClicked", "onDoneButtonClicked()V", 0), new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$4(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onBack", "onBack()V", 0), composerImpl, 0);
            composerImpl.end(false);
        } else if (r0 == Screen.ACTION_KEY) {
            composerImpl.startReplaceGroup(-1447870317);
            ActionKeyTutorialScreenKt.ActionKeyTutorialScreen(new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$5(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onDoneButtonClicked", "onDoneButtonClicked()V", 0), new KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$6(0, keyboardTouchpadTutorialViewModel, KeyboardTouchpadTutorialViewModel.class, "onBack", "onBack()V", 0), composerImpl, 0);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(-1447870174);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivityKt$KeyboardTouchpadTutorialContainer$7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    KeyboardTouchpadTutorialActivityKt.KeyboardTouchpadTutorialContainer(KeyboardTouchpadTutorialViewModel.this, optional, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
