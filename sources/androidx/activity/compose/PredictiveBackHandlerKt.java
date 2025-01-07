package androidx.activity.compose;

import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PredictiveBackHandlerKt {
    public static final void PredictiveBackHandler(final boolean z, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-642000585);
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
            i3 |= composerImpl.changed(function2) ? 32 : 16;
        }
        if ((i3 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                z = true;
            }
            final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerImpl);
            composerImpl.startReplaceableGroup(-723524056);
            composerImpl.startReplaceableGroup(-3687241);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
            }
            composerImpl.end(false);
            final ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).coroutineScope;
            composerImpl.end(false);
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            composerImpl.startReplaceableGroup(1140799243);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new OnBackPressedCallback(z) { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1
                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackCancelled() {
                        Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                        OnBackInstance onBackInstance = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance != null) {
                            onBackInstance.cancel();
                        }
                        OnBackInstance onBackInstance2 = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance2 == null) {
                            return;
                        }
                        onBackInstance2.isPredictiveBack = false;
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackPressed() {
                        Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                        OnBackInstance onBackInstance = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance != null && !onBackInstance.isPredictiveBack) {
                            onBackInstance.cancel();
                            ref$ObjectRef2.element = null;
                        }
                        if (ref$ObjectRef2.element == null) {
                            ref$ObjectRef2.element = new OnBackInstance(contextScope, false, (Function2) rememberUpdatedState.getValue());
                        }
                        OnBackInstance onBackInstance2 = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance2 != null) {
                            onBackInstance2.channel.close(null);
                        }
                        OnBackInstance onBackInstance3 = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance3 == null) {
                            return;
                        }
                        onBackInstance3.isPredictiveBack = false;
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackProgressed(BackEventCompat backEventCompat) {
                        OnBackInstance onBackInstance = (OnBackInstance) ref$ObjectRef.element;
                        if (onBackInstance != null) {
                            onBackInstance.channel.mo1790trySendJP2dKIU(backEventCompat);
                        }
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackStarted() {
                        Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                        OnBackInstance onBackInstance = (OnBackInstance) ref$ObjectRef2.element;
                        if (onBackInstance != null) {
                            onBackInstance.cancel();
                        }
                        ref$ObjectRef2.element = new OnBackInstance(contextScope, true, (Function2) rememberUpdatedState.getValue());
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            final PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 = (PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1) rememberedValue2;
            composerImpl.end(false);
            EffectsKt.LaunchedEffect(composerImpl, Boolean.valueOf(z), new PredictiveBackHandlerKt$PredictiveBackHandler$1(predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1, z, ref$ObjectRef, null));
            OnBackPressedDispatcherOwner current = LocalOnBackPressedDispatcherOwner.getCurrent(composerImpl);
            if (current == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            final OnBackPressedDispatcher onBackPressedDispatcher = current.getOnBackPressedDispatcher();
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) composerImpl.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            composerImpl.startReplaceableGroup(1140881635);
            boolean changed = composerImpl.changed(onBackPressedDispatcher) | composerImpl.changed(lifecycleOwner) | composerImpl.changed(predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        OnBackPressedDispatcher.this.addCallback(lifecycleOwner, predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1);
                        final PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$12 = predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1;
                        return new DisposableEffectResult() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$2$1$invoke$$inlined$onDispose$1
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
            endRestartGroup.block = new Function2() { // from class: androidx.activity.compose.PredictiveBackHandlerKt$PredictiveBackHandler$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PredictiveBackHandlerKt.PredictiveBackHandler(z, function2, (Composer) obj, i | 1, i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
