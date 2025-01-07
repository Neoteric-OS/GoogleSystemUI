package com.android.systemui.haptics.msdl.dagger;

import android.content.Context;
import android.os.Vibrator;
import android.os.VibratorManager;
import com.google.android.msdl.data.model.HapticComposition;
import com.google.android.msdl.data.model.HapticCompositionPrimitive;
import com.google.android.msdl.data.model.MSDLToken;
import com.google.android.msdl.data.repository.MSDLHapticData;
import com.google.android.msdl.data.repository.MSDLRepositoryImpl;
import com.google.android.msdl.domain.MSDLPlayer;
import com.google.android.msdl.domain.MSDLPlayerImpl;
import dagger.internal.Provider;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.collections.AbstractList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MSDLModule_ProvideMSDLPlayerFactory implements Provider {
    public static MSDLPlayerImpl provideMSDLPlayer(Context context) {
        boolean z;
        VibratorManager vibratorManager = (VibratorManager) context.getSystemService("vibrator_manager");
        MSDLPlayer.Companion companion = MSDLPlayer.Companion;
        Vibrator defaultVibrator = vibratorManager.getDefaultVibrator();
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        companion.getClass();
        MSDLRepositoryImpl mSDLRepositoryImpl = new MSDLRepositoryImpl();
        List list = MSDLPlayerImpl.REQUIRED_PRIMITIVES;
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Object obj : list) {
            boolean[] arePrimitivesSupported = defaultVibrator.arePrimitivesSupported(((Number) obj).intValue());
            if (arePrimitivesSupported.length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            linkedHashMap.put(obj, Boolean.valueOf(arePrimitivesSupported[0]));
        }
        List list2 = MSDLToken.$ENTRIES;
        int mapCapacity2 = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(mapCapacity2 >= 16 ? mapCapacity2 : 16);
        Iterator it = ((AbstractList) list2).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            MSDLHapticData mSDLHapticData = (MSDLHapticData) MSDLRepositoryImpl.HAPTIC_DATA.get(((MSDLToken) next).getHapticToken());
            HapticComposition hapticComposition = mSDLHapticData != null ? mSDLHapticData.get() : null;
            HapticComposition hapticComposition2 = hapticComposition != null ? hapticComposition : null;
            if (hapticComposition2 != null) {
                Iterator it2 = hapticComposition2.primitives.iterator();
                while (it2.hasNext()) {
                    Boolean bool = (Boolean) linkedHashMap.get(Integer.valueOf(((HapticCompositionPrimitive) it2.next()).primitiveId));
                    if (bool == null || bool.equals(Boolean.FALSE)) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            linkedHashMap2.put(next, Boolean.valueOf(z));
        }
        return new MSDLPlayerImpl(mSDLRepositoryImpl, defaultVibrator, newSingleThreadExecutor, linkedHashMap2);
    }
}
