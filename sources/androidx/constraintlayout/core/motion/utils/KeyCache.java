package androidx.constraintlayout.core.motion.utils;

import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyCache {
    public final HashMap mMap = new HashMap();

    public final float getFloatValue(Object obj, String str) {
        HashMap hashMap;
        float[] fArr;
        if (this.mMap.containsKey(obj) && (hashMap = (HashMap) this.mMap.get(obj)) != null && hashMap.containsKey(str) && (fArr = (float[]) hashMap.get(str)) != null && fArr.length > 0) {
            return fArr[0];
        }
        return Float.NaN;
    }
}
