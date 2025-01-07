package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import java.util.Arrays;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EffectsKt {
    public static final DisposableEffectScope InternalDisposableEffectScope = new DisposableEffectScope();

    public static final void DisposableEffect(Object obj, Function1 function1, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        boolean changed = composerImpl.changed(obj);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new DisposableEffectImpl(function1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
    }

    public static final void LaunchedEffect(Composer composer, Object obj, Function2 function2) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        CoroutineContext effectCoroutineContext = composerImpl.parentContext.getEffectCoroutineContext();
        boolean changed = composerImpl.changed(obj);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new LaunchedEffectImpl(effectCoroutineContext, function2);
            composerImpl.updateRememberedValue(rememberedValue);
        }
    }

    public static final void SideEffect(Function0 function0, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ChangeList changeList = ((ComposerImpl) composer).changeListWriter.changeList;
        changeList.getClass();
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        Operations operations = changeList.operations;
        operations.pushOp(sideEffect);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, function0);
        int i = operations.pushedIntMask;
        int i2 = sideEffect.ints;
        int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
        int i3 = sideEffect.objects;
        if (i != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i3)) {
            StringBuilder sb = new StringBuilder();
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                if (((1 << i5) & operations.pushedIntMask) != 0) {
                    if (i4 > 0) {
                        sb.append(", ");
                    }
                    sb.append(sideEffect.mo260intParamNamew8GmfQM(i5));
                    i4++;
                }
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                if (((1 << i7) & operations.pushedObjectMask) != 0) {
                    if (i4 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(sideEffect.mo261objectParamName31yXWZQ(i7));
                    i6++;
                }
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(sideEffect);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i4);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
    }

    public static final ContextScope createCompositionCoroutineScope(EmptyCoroutineContext emptyCoroutineContext, Composer composer) {
        Job.Key key = Job.Key.$$INSTANCE;
        emptyCoroutineContext.getClass();
        CoroutineContext effectCoroutineContext = ((ComposerImpl) composer).parentContext.getEffectCoroutineContext();
        return CoroutineScopeKt.CoroutineScope(effectCoroutineContext.plus(new JobImpl((Job) effectCoroutineContext.get(key))).plus(emptyCoroutineContext));
    }

    public static final void DisposableEffect(Object obj, Object obj2, Function1 function1, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        boolean changed = composerImpl.changed(obj) | composerImpl.changed(obj2);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new DisposableEffectImpl(function1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
    }

    public static final void LaunchedEffect(Object obj, Object obj2, Function2 function2, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        CoroutineContext effectCoroutineContext = composerImpl.parentContext.getEffectCoroutineContext();
        boolean changed = composerImpl.changed(obj) | composerImpl.changed(obj2);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new LaunchedEffectImpl(effectCoroutineContext, function2);
            composerImpl.updateRememberedValue(rememberedValue);
        }
    }

    public static final void DisposableEffect(Object[] objArr, Function1 function1, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        boolean z = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            z |= ((ComposerImpl) composer).changed(obj);
        }
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (z || rememberedValue == Composer.Companion.Empty) {
            composerImpl.updateRememberedValue(new DisposableEffectImpl(function1));
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
    }

    public static final void LaunchedEffect(Object[] objArr, Function2 function2, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        CoroutineContext effectCoroutineContext = composerImpl.parentContext.getEffectCoroutineContext();
        boolean z = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            z |= composerImpl.changed(obj);
        }
        Object rememberedValue = composerImpl.rememberedValue();
        if (z || rememberedValue == Composer.Companion.Empty) {
            composerImpl.updateRememberedValue(new LaunchedEffectImpl(effectCoroutineContext, function2));
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
    }
}
