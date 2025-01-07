package com.android.systemui.biometrics.dagger;

import android.R;
import android.content.res.Resources;
import com.android.systemui.biometrics.EllipseOverlapDetectorParams;
import com.android.systemui.biometrics.udfps.BoundingBoxOverlapDetector;
import com.android.systemui.biometrics.udfps.EllipseOverlapDetector;
import com.android.systemui.biometrics.udfps.OverlapDetector;
import dagger.internal.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricsModule_Companion_ProvidesOverlapDetectorFactory implements Provider {
    public static OverlapDetector providesOverlapDetector() {
        List split$default = StringsKt.split$default(Resources.getSystem().getStringArray(R.array.config_vvmSmsFilterRegexes)[Resources.getSystem().getInteger(R.integer.config_shutdownBatteryTemperature)], new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(Float.parseFloat((String) it.next())));
        }
        if (((Number) arrayList.get(0)).floatValue() != 1.0f) {
            return new BoundingBoxOverlapDetector(((Number) arrayList.get(2)).floatValue());
        }
        return new EllipseOverlapDetector(new EllipseOverlapDetectorParams((int) ((Number) arrayList.get(4)).floatValue(), ((Number) arrayList.get(3)).floatValue(), ((Number) arrayList.get(2)).floatValue()));
    }
}
