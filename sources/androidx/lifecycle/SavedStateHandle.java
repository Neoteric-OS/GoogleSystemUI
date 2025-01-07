package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SavedStateHandle {
    public static final List ACCEPTABLE_CLASSES = ArraysKt.filterNotNull(new Class[]{Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class});
    public final Map flows;
    public final Map liveDatas;
    public final Map regular;
    public final SavedStateRegistry.SavedStateProvider savedStateProvider;
    public final Map savedStateProviders;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String str : bundle2.keySet()) {
                    hashMap.put(str, bundle2.get(str));
                }
                return new SavedStateHandle(hashMap);
            }
            ClassLoader classLoader = SavedStateHandle.class.getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            bundle.setClassLoader(classLoader);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i = 0; i < size; i++) {
                linkedHashMap.put((String) parcelableArrayList.get(i), parcelableArrayList2.get(i));
            }
            return new SavedStateHandle(linkedHashMap);
        }
    }

    public static Bundle $r8$lambda$eeLDsk5Qp_lgSAYrhUViF2PFB0k(SavedStateHandle savedStateHandle) {
        Bundle saveState;
        Iterator it = MapsKt.toMap(savedStateHandle.savedStateProviders).entrySet().iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                Pair[] pairArr = {new Pair("keys", new ArrayList(savedStateHandle.regular.keySet())), new Pair("values", new ArrayList(savedStateHandle.regular.values()))};
                Bundle bundle = new Bundle(2);
                for (int i = 0; i < 2; i++) {
                    Pair pair = pairArr[i];
                    String str = (String) pair.component1();
                    Object component2 = pair.component2();
                    if (component2 == null) {
                        bundle.putString(str, null);
                    } else if (component2 instanceof Boolean) {
                        bundle.putBoolean(str, ((Boolean) component2).booleanValue());
                    } else if (component2 instanceof Byte) {
                        bundle.putByte(str, ((Number) component2).byteValue());
                    } else if (component2 instanceof Character) {
                        bundle.putChar(str, ((Character) component2).charValue());
                    } else if (component2 instanceof Double) {
                        bundle.putDouble(str, ((Number) component2).doubleValue());
                    } else if (component2 instanceof Float) {
                        bundle.putFloat(str, ((Number) component2).floatValue());
                    } else if (component2 instanceof Integer) {
                        bundle.putInt(str, ((Number) component2).intValue());
                    } else if (component2 instanceof Long) {
                        bundle.putLong(str, ((Number) component2).longValue());
                    } else if (component2 instanceof Short) {
                        bundle.putShort(str, ((Number) component2).shortValue());
                    } else if (component2 instanceof Bundle) {
                        bundle.putBundle(str, (Bundle) component2);
                    } else if (component2 instanceof CharSequence) {
                        bundle.putCharSequence(str, (CharSequence) component2);
                    } else if (component2 instanceof Parcelable) {
                        bundle.putParcelable(str, (Parcelable) component2);
                    } else if (component2 instanceof boolean[]) {
                        bundle.putBooleanArray(str, (boolean[]) component2);
                    } else if (component2 instanceof byte[]) {
                        bundle.putByteArray(str, (byte[]) component2);
                    } else if (component2 instanceof char[]) {
                        bundle.putCharArray(str, (char[]) component2);
                    } else if (component2 instanceof double[]) {
                        bundle.putDoubleArray(str, (double[]) component2);
                    } else if (component2 instanceof float[]) {
                        bundle.putFloatArray(str, (float[]) component2);
                    } else if (component2 instanceof int[]) {
                        bundle.putIntArray(str, (int[]) component2);
                    } else if (component2 instanceof long[]) {
                        bundle.putLongArray(str, (long[]) component2);
                    } else if (component2 instanceof short[]) {
                        bundle.putShortArray(str, (short[]) component2);
                    } else if (component2 instanceof Object[]) {
                        Class<?> componentType = component2.getClass().getComponentType();
                        Intrinsics.checkNotNull(componentType);
                        if (Parcelable.class.isAssignableFrom(componentType)) {
                            bundle.putParcelableArray(str, (Parcelable[]) component2);
                        } else if (String.class.isAssignableFrom(componentType)) {
                            bundle.putStringArray(str, (String[]) component2);
                        } else if (CharSequence.class.isAssignableFrom(componentType)) {
                            bundle.putCharSequenceArray(str, (CharSequence[]) component2);
                        } else {
                            if (!Serializable.class.isAssignableFrom(componentType)) {
                                throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                            }
                            bundle.putSerializable(str, (Serializable) component2);
                        }
                    } else if (component2 instanceof Serializable) {
                        bundle.putSerializable(str, (Serializable) component2);
                    } else if (component2 instanceof IBinder) {
                        bundle.putBinder(str, (IBinder) component2);
                    } else if (component2 instanceof Size) {
                        bundle.putSize(str, (Size) component2);
                    } else {
                        if (!(component2 instanceof SizeF)) {
                            throw new IllegalArgumentException("Illegal value type " + component2.getClass().getCanonicalName() + " for key \"" + str + '\"');
                        }
                        bundle.putSizeF(str, (SizeF) component2);
                    }
                }
                return bundle;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            saveState = ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState();
            if (saveState != null) {
                List list = ACCEPTABLE_CLASSES;
                if (list != null && list.isEmpty()) {
                    break;
                }
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    if (((Class) it2.next()).isInstance(saveState)) {
                    }
                }
                break loop0;
            }
            Object obj = savedStateHandle.liveDatas.get(str2);
            MutableLiveData mutableLiveData = obj instanceof MutableLiveData ? (MutableLiveData) obj : null;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(saveState);
            } else {
                savedStateHandle.regular.put(str2, saveState);
            }
            MutableStateFlow mutableStateFlow = (MutableStateFlow) savedStateHandle.flows.get(str2);
            if (mutableStateFlow != null) {
                ((StateFlowImpl) mutableStateFlow).setValue(saveState);
            }
        }
        throw new IllegalArgumentException(("Can't put value with type " + saveState.getClass() + " into saved state").toString());
    }

    public SavedStateHandle(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.$r8$lambda$eeLDsk5Qp_lgSAYrhUViF2PFB0k(SavedStateHandle.this);
            }
        };
        linkedHashMap.putAll(map);
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.$r8$lambda$eeLDsk5Qp_lgSAYrhUViF2PFB0k(SavedStateHandle.this);
            }
        };
    }
}
