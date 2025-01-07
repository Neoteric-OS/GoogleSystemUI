package androidx.activity.compose;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BackHandlerKt {
    public static final void BackHandler(final boolean z, final Function0 function0, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-361453782);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerImpl.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerImpl.changed(function0) ? 32 : 16;
        }
        if ((i3 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                z = true;
            }
            final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function0, composerImpl);
            composerImpl.startReplaceableGroup(-41177256);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new OnBackPressedCallback(z) { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$backCallback$1$1
                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackPressed() {
                        ((Function0) rememberUpdatedState.getValue()).invoke();
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final BackHandlerKt$BackHandler$backCallback$1$1 backHandlerKt$BackHandler$backCallback$1$1 = (BackHandlerKt$BackHandler$backCallback$1$1) rememberedValue;
            composerImpl.end(false);
            composerImpl.startReplaceableGroup(-41168687);
            boolean changed = composerImpl.changed(backHandlerKt$BackHandler$backCallback$1$1) | composerImpl.changed(z);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changed || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function0() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.FunctionReferenceImpl] */
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        BackHandlerKt$BackHandler$backCallback$1$1 backHandlerKt$BackHandler$backCallback$1$12 = BackHandlerKt$BackHandler$backCallback$1$1.this;
                        backHandlerKt$BackHandler$backCallback$1$12.isEnabled = z;
                        ?? r1 = backHandlerKt$BackHandler$backCallback$1$12.enabledChangedCallback;
                        if (r1 != 0) {
                            r1.invoke();
                        }
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            composerImpl.end(false);
            EffectsKt.SideEffect((Function0) rememberedValue2, composerImpl);
            OnBackPressedDispatcherOwner current = LocalOnBackPressedDispatcherOwner.getCurrent(composerImpl);
            if (current == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            final OnBackPressedDispatcher onBackPressedDispatcher = current.getOnBackPressedDispatcher();
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) composerImpl.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            composerImpl.startReplaceableGroup(-41153386);
            boolean changed2 = composerImpl.changed(onBackPressedDispatcher) | composerImpl.changed(lifecycleOwner) | composerImpl.changed(backHandlerKt$BackHandler$backCallback$1$1);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        OnBackPressedDispatcher.this.addCallback(lifecycleOwner, backHandlerKt$BackHandler$backCallback$1$1);
                        final BackHandlerKt$BackHandler$backCallback$1$1 backHandlerKt$BackHandler$backCallback$1$12 = backHandlerKt$BackHandler$backCallback$1$1;
                        return new DisposableEffectResult() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                remove();
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            EffectsKt.DisposableEffect(lifecycleOwner, onBackPressedDispatcher, (Function1) rememberedValue3, composerImpl);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.activity.compose.BackHandlerKt$BackHandler$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BackHandlerKt.BackHandler(z, function0, (Composer) obj, i | 1, i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
