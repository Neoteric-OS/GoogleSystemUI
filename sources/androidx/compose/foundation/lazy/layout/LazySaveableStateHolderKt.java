package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazySaveableStateHolderKt {
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$LazySaveableStateHolderProvider$1, kotlin.jvm.internal.Lambda] */
    public static final void LazySaveableStateHolderProvider(final Function3 function3, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(674185128);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = SaveableStateRegistryKt.LocalSaveableStateRegistry;
            final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) composerImpl.consume(staticProvidableCompositionLocal);
            Object[] objArr = {saveableStateRegistry};
            LazySaveableStateHolder$Companion$saver$1 lazySaveableStateHolder$Companion$saver$1 = new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$Companion$saver$1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Map performSave = ((LazySaveableStateHolder) obj2).performSave();
                    if (performSave.isEmpty()) {
                        return null;
                    }
                    return performSave;
                }
            };
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$Companion$saver$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return new LazySaveableStateHolder(SaveableStateRegistry.this, (Map) obj);
                }
            };
            SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
            SaverKt$Saver$1 saverKt$Saver$12 = new SaverKt$Saver$1(lazySaveableStateHolder$Companion$saver$1, function1);
            boolean changedInstance = composerImpl.changedInstance(saveableStateRegistry);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$LazySaveableStateHolderProvider$holder$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new LazySaveableStateHolder(SaveableStateRegistry.this, MapsKt.emptyMap());
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final LazySaveableStateHolder lazySaveableStateHolder = (LazySaveableStateHolder) RememberSaveableKt.rememberSaveable(objArr, saverKt$Saver$12, (Function0) rememberedValue, composerImpl, 0, 4);
            CompositionLocalKt.CompositionLocalProvider(staticProvidableCompositionLocal.defaultProvidedValue$runtime_release(lazySaveableStateHolder), ComposableLambdaKt.rememberComposableLambda(1863926504, new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$LazySaveableStateHolderProvider$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 3) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    LazySaveableStateHolder lazySaveableStateHolder2 = LazySaveableStateHolder.this;
                    ((SnapshotMutableStateImpl) lazySaveableStateHolder2.wrappedHolder$delegate).setValue(SaveableStateHolderKt.rememberSaveableStateHolder(composer2));
                    function3.invoke(LazySaveableStateHolder.this, composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolderKt$LazySaveableStateHolderProvider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    LazySaveableStateHolderKt.LazySaveableStateHolderProvider(Function3.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
