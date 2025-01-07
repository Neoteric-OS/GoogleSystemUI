package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.CharsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    public final Lambda canBeSaved;
    public final MutableScatterMap restored;
    public final MutableScatterMap valueProviders;

    /* JADX WARN: Multi-variable type inference failed */
    public SaveableStateRegistryImpl(Map map, Function1 function1) {
        MutableScatterMap mutableScatterMap;
        this.canBeSaved = (Lambda) function1;
        if (map != null) {
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = SaveableStateRegistryKt.LocalSaveableStateRegistry;
            mutableScatterMap = new MutableScatterMap(map.size());
            for (Map.Entry entry : map.entrySet()) {
                mutableScatterMap.set(entry.getKey(), entry.getValue());
            }
        } else {
            long[] jArr = ScatterMapKt.EmptyGroup;
            mutableScatterMap = new MutableScatterMap();
        }
        this.restored = mutableScatterMap;
        long[] jArr2 = ScatterMapKt.EmptyGroup;
        this.valueProviders = new MutableScatterMap();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final boolean canBeSaved(Object obj) {
        return ((Boolean) this.canBeSaved.invoke(obj)).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final Object consumeRestored(String str) {
        MutableScatterMap mutableScatterMap = this.restored;
        List list = (List) mutableScatterMap.remove(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            mutableScatterMap.set(str, list.subList(1, list.size()));
        }
        return list.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final Map performSave() {
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        MutableScatterMap mutableScatterMap = this.restored;
        HashMap hashMap = new HashMap(mutableScatterMap._size);
        Object[] objArr = mutableScatterMap.keys;
        Object[] objArr2 = mutableScatterMap.values;
        long[] jArr3 = mutableScatterMap.metadata;
        int length = jArr3.length - 2;
        char c = 7;
        long j = -9187201950435737472L;
        int i3 = 8;
        if (length >= 0) {
            int i4 = 0;
            while (true) {
                long j2 = jArr3[i4];
                if ((((~j2) << 7) & j2 & j) != j) {
                    int i5 = 8 - ((~(i4 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((j2 & 255) < 128) {
                            int i7 = (i4 << 3) + i6;
                            hashMap.put((String) objArr[i7], (List) objArr2[i7]);
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        break;
                    }
                }
                if (i4 == length) {
                    break;
                }
                i4++;
                j = -9187201950435737472L;
            }
        }
        MutableScatterMap mutableScatterMap2 = this.valueProviders;
        Object[] objArr3 = mutableScatterMap2.keys;
        Object[] objArr4 = mutableScatterMap2.values;
        long[] jArr4 = mutableScatterMap2.metadata;
        int length2 = jArr4.length - 2;
        if (length2 >= 0) {
            int i8 = 0;
            while (true) {
                long j3 = jArr4[i8];
                if ((((~j3) << c) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i9 = 8 - ((~(i8 - length2)) >>> 31);
                    int i10 = 0;
                    while (i10 < i9) {
                        if ((j3 & 255) < 128) {
                            int i11 = (i8 << 3) + i10;
                            Object obj = objArr3[i11];
                            List list = (List) objArr4[i11];
                            String str = (String) obj;
                            if (list.size() == 1) {
                                Object invoke = ((Function0) list.get(0)).invoke();
                                if (invoke != null) {
                                    if (!canBeSaved(invoke)) {
                                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(invoke).toString());
                                    }
                                    hashMap.put(str, CollectionsKt__CollectionsKt.arrayListOf(invoke));
                                }
                                jArr2 = jArr4;
                            } else {
                                int size = list.size();
                                ArrayList arrayList = new ArrayList(size);
                                int i12 = 0;
                                while (i12 < size) {
                                    long[] jArr5 = jArr4;
                                    Object invoke2 = ((Function0) list.get(i12)).invoke();
                                    if (invoke2 != null && !canBeSaved(invoke2)) {
                                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(invoke2).toString());
                                    }
                                    arrayList.add(invoke2);
                                    i12++;
                                    jArr4 = jArr5;
                                }
                                jArr2 = jArr4;
                                hashMap.put(str, arrayList);
                            }
                            i2 = 8;
                        } else {
                            jArr2 = jArr4;
                            i2 = i3;
                        }
                        j3 >>= i2;
                        i10++;
                        i3 = i2;
                        jArr4 = jArr2;
                    }
                    jArr = jArr4;
                    i = i3;
                    if (i9 != i) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                    i = i3;
                }
                if (i8 == length2) {
                    break;
                }
                i8++;
                i3 = i;
                jArr4 = jArr;
                c = 7;
            }
        }
        return hashMap;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final SaveableStateRegistry.Entry registerProvider(String str, Function0 function0) {
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = SaveableStateRegistryKt.LocalSaveableStateRegistry;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt.isWhitespace(str.charAt(i))) {
                MutableScatterMap mutableScatterMap = this.valueProviders;
                Object obj = mutableScatterMap.get(str);
                if (obj == null) {
                    obj = new ArrayList();
                    mutableScatterMap.set(str, obj);
                }
                ((List) obj).add(function0);
                return new SaveableStateRegistryImpl$registerProvider$3(this, str, function0);
            }
        }
        throw new IllegalArgumentException("Registered key is empty or blank");
    }
}
