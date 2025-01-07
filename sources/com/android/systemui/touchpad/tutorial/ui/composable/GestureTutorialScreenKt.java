package com.android.systemui.touchpad.tutorial.ui.composable;

import android.content.Context;
import android.content.res.Resources;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.ActionTutorialContentKt;
import com.android.systemui.inputdevice.tutorial.ui.composable.TutorialActionState;
import com.android.systemui.inputdevice.tutorial.ui.composable.TutorialScreenConfig;
import com.android.systemui.touchpad.tutorial.ui.gesture.GestureState;
import com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureHandler;
import com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class GestureTutorialScreenKt {
    /* JADX WARN: Type inference failed for: r3v5, types: [com.android.systemui.touchpad.tutorial.ui.composable.GestureTutorialScreenKt$GestureTutorialScreen$1, kotlin.jvm.internal.Lambda] */
    public static final void GestureTutorialScreen(final TutorialScreenConfig tutorialScreenConfig, final GestureMonitorProvider gestureMonitorProvider, final Function0 function0, final Function0 function02, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-406690958);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(tutorialScreenConfig) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(gestureMonitorProvider) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerImpl.changedInstance(function02) ? 2048 : 1024;
        }
        if ((i2 & 5851) == 1170 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            BackHandlerKt.BackHandler(false, function02, composerImpl, (i2 >> 6) & 112, 1);
            composerImpl.startReplaceGroup(830795111);
            Object rememberedValue = composerImpl.rememberedValue();
            Object obj = Composer.Companion.Empty;
            if (rememberedValue == obj) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default(GestureState.NOT_STARTED);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final MutableState mutableState = (MutableState) rememberedValue;
            composerImpl.end(false);
            Resources resources = ((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)).getResources();
            composerImpl.startReplaceGroup(830795330);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (rememberedValue2 == obj) {
                rememberedValue2 = new Function1() { // from class: com.android.systemui.touchpad.tutorial.ui.composable.GestureTutorialScreenKt$GestureTutorialScreen$gestureMonitor$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        MutableState.this.setValue((GestureState) obj2);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            TouchpadGestureMonitor rememberGestureMonitor = gestureMonitorProvider.rememberGestureMonitor(resources, (Function1) rememberedValue2, composerImpl);
            composerImpl.startReplaceGroup(830795387);
            boolean changed = composerImpl.changed(rememberGestureMonitor);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed || rememberedValue3 == obj) {
                rememberedValue3 = new TouchpadGestureHandler(rememberGestureMonitor);
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            TouchpadGesturesHandlingBox((TouchpadGestureHandler) rememberedValue3, (GestureState) mutableState.getValue(), null, ComposableLambdaKt.rememberComposableLambda(-1716292011, new Function3() { // from class: com.android.systemui.touchpad.tutorial.ui.composable.GestureTutorialScreenKt$GestureTutorialScreen$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    TutorialActionState tutorialActionState;
                    Composer composer2 = (Composer) obj3;
                    if ((((Number) obj4).intValue() & 81) == 16) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    int ordinal = ((GestureState) mutableState.getValue()).ordinal();
                    if (ordinal == 0) {
                        tutorialActionState = TutorialActionState.NOT_STARTED;
                    } else if (ordinal == 1) {
                        tutorialActionState = TutorialActionState.IN_PROGRESS;
                    } else {
                        if (ordinal != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        tutorialActionState = TutorialActionState.FINISHED;
                    }
                    ActionTutorialContentKt.ActionTutorialContent(tutorialActionState, Function0.this, tutorialScreenConfig, composer2, 512);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 3072, 4);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.touchpad.tutorial.ui.composable.GestureTutorialScreenKt$GestureTutorialScreen$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    GestureTutorialScreenKt.GestureTutorialScreen(TutorialScreenConfig.this, gestureMonitorProvider, function0, function02, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TouchpadGesturesHandlingBox(final com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureHandler r15, final com.android.systemui.touchpad.tutorial.ui.gesture.GestureState r16, androidx.compose.ui.Modifier r17, final kotlin.jvm.functions.Function3 r18, androidx.compose.runtime.Composer r19, final int r20, final int r21) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.touchpad.tutorial.ui.composable.GestureTutorialScreenKt.TouchpadGesturesHandlingBox(com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureHandler, com.android.systemui.touchpad.tutorial.ui.gesture.GestureState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }
}
