package com.android.systemui.animation;

import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.util.LruCache;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceVariantCacheImpl {
    public final Typeface baseTypeface;
    public final LruCache cache = new LruCache(5);

    public TypefaceVariantCacheImpl(Typeface typeface) {
        this.baseTypeface = typeface;
    }

    public final Typeface getTypefaceForVariant(String str) {
        if (str == null) {
            return this.baseTypeface;
        }
        Typeface typeface = (Typeface) this.cache.get(str);
        if (typeface != null) {
            return typeface;
        }
        final Typeface typeface2 = this.baseTypeface;
        if (str.length() != 0) {
            FontVariationAxis[] fromFontVariationSettings = FontVariationAxis.fromFontVariationSettings(str);
            List mutableList = fromFontVariationSettings != null ? ArraysKt.toMutableList(fromFontVariationSettings) : new ArrayList();
            mutableList.removeIf(new Predicate() { // from class: com.android.systemui.animation.TypefaceVariantCache$Companion$createVariantTypeface$1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return !typeface2.isSupportedAxes(((FontVariationAxis) obj).getOpenTypeTagValue());
                }
            });
            if (!mutableList.isEmpty()) {
                typeface2 = Typeface.createFromTypefaceWithVariation(typeface2, mutableList);
            }
        }
        this.cache.put(str, typeface2);
        return typeface2;
    }
}
