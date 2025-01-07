package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SnapshotStateKt {
    public static final MutableVector derivedStateObservers() {
        SnapshotThreadLocal snapshotThreadLocal = SnapshotStateKt__DerivedStateKt.derivedStateObservers;
        MutableVector mutableVector = (MutableVector) snapshotThreadLocal.get();
        if (mutableVector != null) {
            return mutableVector;
        }
        MutableVector mutableVector2 = new MutableVector(new DerivedStateObserver[0]);
        snapshotThreadLocal.set(mutableVector2);
        return mutableVector2;
    }

    public static final State derivedStateOf(SnapshotMutationPolicy snapshotMutationPolicy, Function0 function0) {
        SnapshotThreadLocal snapshotThreadLocal = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
        return new DerivedSnapshotState(snapshotMutationPolicy, function0);
    }

    public static final MutableState mutableStateOf(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        return new ParcelableSnapshotMutableState(obj, snapshotMutationPolicy);
    }

    public static MutableState mutableStateOf$default(Object obj) {
        return new ParcelableSnapshotMutableState(obj, StructuralEqualityPolicy.INSTANCE);
    }

    public static final SnapshotMutationPolicy neverEqualPolicy() {
        return NeverEqualPolicy.INSTANCE;
    }

    public static final MutableState produceState(Composer composer, Object obj, Function2 function2) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = mutableStateOf$default(obj);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        Unit unit = Unit.INSTANCE;
        boolean changedInstance = composerImpl.changedInstance(function2);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$1$1(function2, mutableState, null);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.LaunchedEffect(composerImpl, unit, (Function2) rememberedValue2);
        return mutableState;
    }

    public static final SnapshotMutationPolicy referentialEqualityPolicy() {
        return ReferentialEqualityPolicy.INSTANCE;
    }

    public static final MutableState rememberUpdatedState(Object obj, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = mutableStateOf$default(obj);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        mutableState.setValue(obj);
        return mutableState;
    }

    public static final SafeFlow snapshotFlow(Function0 function0) {
        return new SafeFlow(new SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1(function0, null));
    }

    public static final SnapshotMutationPolicy structuralEqualityPolicy() {
        return StructuralEqualityPolicy.INSTANCE;
    }

    public static final State derivedStateOf(Function0 function0) {
        SnapshotThreadLocal snapshotThreadLocal = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
        return new DerivedSnapshotState(null, function0);
    }

    public static final MutableState produceState(Object obj, Object[] objArr, Function2 function2, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = mutableStateOf$default(obj);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        boolean changedInstance = composerImpl.changedInstance(function2);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new SnapshotStateKt__ProduceStateKt$produceState$5$1(function2, mutableState, null);
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        EffectsKt.LaunchedEffect(copyOf, (Function2) rememberedValue2, composerImpl);
        return mutableState;
    }
}
