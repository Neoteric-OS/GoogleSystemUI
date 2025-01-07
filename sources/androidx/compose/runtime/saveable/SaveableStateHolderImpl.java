package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.saveable.SaveableStateHolderImpl;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SaveableStateHolderImpl implements SaveableStateHolder {
    public static final SaverKt$Saver$1 Saver;
    public SaveableStateRegistry parentSaveableStateRegistry;
    public final MutableScatterMap registryHolders;
    public final Map savedStates;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RegistryHolder {
        public final Object key;
        public final SaveableStateRegistry registry;
        public boolean shouldSave = true;

        public RegistryHolder(final SaveableStateHolderImpl saveableStateHolderImpl, Object obj) {
            this.key = obj;
            Map map = (Map) saveableStateHolderImpl.savedStates.get(obj);
            Function1 function1 = new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$RegistryHolder$registry$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    SaveableStateRegistry saveableStateRegistry = SaveableStateHolderImpl.this.parentSaveableStateRegistry;
                    return Boolean.valueOf(saveableStateRegistry != null ? saveableStateRegistry.canBeSaved(obj2) : true);
                }
            };
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = SaveableStateRegistryKt.LocalSaveableStateRegistry;
            this.registry = new SaveableStateRegistryImpl(map, function1);
        }
    }

    static {
        SaveableStateHolderImpl$Companion$Saver$1 saveableStateHolderImpl$Companion$Saver$1 = new Function2() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$Companion$Saver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaveableStateHolderImpl saveableStateHolderImpl = (SaveableStateHolderImpl) obj2;
                LinkedHashMap linkedHashMap = new LinkedHashMap(saveableStateHolderImpl.savedStates);
                MutableScatterMap mutableScatterMap = saveableStateHolderImpl.registryHolders;
                Object[] objArr = mutableScatterMap.values;
                long[] jArr = mutableScatterMap.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i = 0;
                    while (true) {
                        long j = jArr[i];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i2 = 8 - ((~(i - length)) >>> 31);
                            for (int i3 = 0; i3 < i2; i3++) {
                                if ((255 & j) < 128) {
                                    SaveableStateHolderImpl.RegistryHolder registryHolder = (SaveableStateHolderImpl.RegistryHolder) objArr[(i << 3) + i3];
                                    if (registryHolder.shouldSave) {
                                        Map performSave = ((SaveableStateRegistryImpl) registryHolder.registry).performSave();
                                        boolean isEmpty = performSave.isEmpty();
                                        Object obj3 = registryHolder.key;
                                        if (isEmpty) {
                                            linkedHashMap.remove(obj3);
                                        } else {
                                            linkedHashMap.put(obj3, performSave);
                                        }
                                    }
                                }
                                j >>= 8;
                            }
                            if (i2 != 8) {
                                break;
                            }
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                    }
                }
                if (linkedHashMap.isEmpty()) {
                    return null;
                }
                return linkedHashMap;
            }
        };
        SaveableStateHolderImpl$Companion$Saver$2 saveableStateHolderImpl$Companion$Saver$2 = new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$Companion$Saver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new SaveableStateHolderImpl((Map) obj);
            }
        };
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        Saver = new SaverKt$Saver$1(saveableStateHolderImpl$Companion$Saver$1, saveableStateHolderImpl$Companion$Saver$2);
    }

    public SaveableStateHolderImpl(Map map) {
        this.savedStates = map;
        long[] jArr = ScatterMapKt.EmptyGroup;
        this.registryHolders = new MutableScatterMap();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public final void SaveableStateProvider(final Object obj, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1198538093);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(this) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            composerImpl.startReusableGroup(obj);
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                SaveableStateRegistry saveableStateRegistry = this.parentSaveableStateRegistry;
                if (!(saveableStateRegistry != null ? saveableStateRegistry.canBeSaved(obj) : true)) {
                    throw new IllegalArgumentException(("Type of the key " + obj + " is not supported. On Android you can only use types which can be stored inside the Bundle.").toString());
                }
                rememberedValue = new RegistryHolder(this, obj);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final RegistryHolder registryHolder = (RegistryHolder) rememberedValue;
            CompositionLocalKt.CompositionLocalProvider(SaveableStateRegistryKt.LocalSaveableStateRegistry.defaultProvidedValue$runtime_release(registryHolder.registry), function2, composerImpl, (i2 & 112) | 8);
            Unit unit = Unit.INSTANCE;
            boolean changedInstance = composerImpl.changedInstance(this) | composerImpl.changedInstance(obj) | composerImpl.changedInstance(registryHolder);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$SaveableStateProvider$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        boolean contains = this.registryHolders.contains(obj);
                        Object obj3 = obj;
                        if (contains) {
                            throw new IllegalArgumentException(("Key " + obj3 + " was used multiple times ").toString());
                        }
                        this.savedStates.remove(obj3);
                        this.registryHolders.set(obj, registryHolder);
                        final SaveableStateHolderImpl.RegistryHolder registryHolder2 = registryHolder;
                        final SaveableStateHolderImpl saveableStateHolderImpl = this;
                        final Object obj4 = obj;
                        return new DisposableEffectResult() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$SaveableStateProvider$1$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                SaveableStateHolderImpl saveableStateHolderImpl2 = saveableStateHolderImpl;
                                Map map = saveableStateHolderImpl2.savedStates;
                                SaveableStateHolderImpl.RegistryHolder registryHolder3 = SaveableStateHolderImpl.RegistryHolder.this;
                                if (registryHolder3.shouldSave) {
                                    Map performSave = ((SaveableStateRegistryImpl) registryHolder3.registry).performSave();
                                    boolean isEmpty = performSave.isEmpty();
                                    Object obj5 = registryHolder3.key;
                                    if (isEmpty) {
                                        map.remove(obj5);
                                    } else {
                                        map.put(obj5, performSave);
                                    }
                                }
                                saveableStateHolderImpl2.registryHolders.remove(obj4);
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            EffectsKt.DisposableEffect(unit, (Function1) rememberedValue2, composerImpl);
            composerImpl.endReusableGroup();
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$SaveableStateProvider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    SaveableStateHolderImpl.this.SaveableStateProvider(obj, function2, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public final void removeState(Object obj) {
        RegistryHolder registryHolder = (RegistryHolder) this.registryHolders.get(obj);
        if (registryHolder != null) {
            registryHolder.shouldSave = false;
        } else {
            this.savedStates.remove(obj);
        }
    }
}
