package com.android.systemui.animation;

import android.graphics.fonts.Font;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import android.util.LruCache;
import android.util.MathUtils;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontInterpolator {
    public static final boolean DEBUG = Log.isLoggable("FontInterpolator", 3);
    public static final FontVariationAxis[] EMPTY_AXES = new FontVariationAxis[0];
    public final LruCache interpCache = new LruCache(60);
    public final LruCache verFontCache = new LruCache(60);
    public final InterpKey tmpInterpKey = new InterpKey(null, null, 0.0f);
    public final VarFontKey tmpVarFontKey = new VarFontKey(0, 0, new ArrayList());

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static boolean canInterpolate(Font font, Font font2) {
            return font.getTtcIndex() == font2.getTtcIndex() && font.getSourceIdentifier() == font2.getSourceIdentifier();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InterpKey {
        public Font l;
        public float progress;
        public Font r;

        public InterpKey(Font font, Font font2, float f) {
            this.l = font;
            this.r = font2;
            this.progress = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InterpKey)) {
                return false;
            }
            InterpKey interpKey = (InterpKey) obj;
            return Intrinsics.areEqual(this.l, interpKey.l) && Intrinsics.areEqual(this.r, interpKey.r) && Float.compare(this.progress, interpKey.progress) == 0;
        }

        public final int hashCode() {
            Font font = this.l;
            int hashCode = (font == null ? 0 : font.hashCode()) * 31;
            Font font2 = this.r;
            return Float.hashCode(this.progress) + ((hashCode + (font2 != null ? font2.hashCode() : 0)) * 31);
        }

        public final String toString() {
            Font font = this.l;
            Font font2 = this.r;
            float f = this.progress;
            StringBuilder sb = new StringBuilder("InterpKey(l=");
            sb.append(font);
            sb.append(", r=");
            sb.append(font2);
            sb.append(", progress=");
            return DpCornerSize$$ExternalSyntheticOutline0.m(sb, f, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VarFontKey {
        public int index;
        public final List sortedAxes;
        public int sourceId;

        public VarFontKey(int i, int i2, List list) {
            this.sourceId = i;
            this.index = i2;
            this.sortedAxes = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VarFontKey)) {
                return false;
            }
            VarFontKey varFontKey = (VarFontKey) obj;
            return this.sourceId == varFontKey.sourceId && this.index == varFontKey.index && Intrinsics.areEqual(this.sortedAxes, varFontKey.sortedAxes);
        }

        public final int hashCode() {
            return this.sortedAxes.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.index, Integer.hashCode(this.sourceId) * 31, 31);
        }

        public final String toString() {
            int i = this.sourceId;
            int i2 = this.index;
            List list = this.sortedAxes;
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "VarFontKey(sourceId=", ", index=", ", sortedAxes=");
            m.append(list);
            m.append(")");
            return m.toString();
        }
    }

    public final Font lerp(Font font, Font font2, final float f) {
        int i;
        FontVariationAxis fontVariationAxis;
        final FontInterpolator fontInterpolator = this;
        if (f == 0.0f) {
            return font;
        }
        if (f == 1.0f) {
            return font2;
        }
        FontVariationAxis[] axes = font.getAxes();
        if (axes == null) {
            axes = EMPTY_AXES;
        }
        FontVariationAxis[] axes2 = font2.getAxes();
        if (axes2 == null) {
            axes2 = EMPTY_AXES;
        }
        if (axes.length == 0 && axes2.length == 0) {
            return font;
        }
        InterpKey interpKey = fontInterpolator.tmpInterpKey;
        interpKey.l = font;
        interpKey.r = font2;
        interpKey.progress = f;
        Font font3 = (Font) fontInterpolator.interpCache.get(interpKey);
        boolean z = DEBUG;
        if (font3 != null) {
            if (z) {
                Log.d("FontInterpolator", "[" + f + "] Interp. cache hit for " + interpKey);
            }
            return font3;
        }
        Function3 function3 = new Function3() { // from class: com.android.systemui.animation.FontInterpolator$lerp$newAxes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                float lerp;
                String str = (String) obj;
                Float f2 = (Float) obj2;
                Float f3 = (Float) obj3;
                if (str.equals("wght")) {
                    lerp = MathUtils.lerp(f2 != null ? f2.floatValue() : 400.0f, f3 != null ? f3.floatValue() : 400.0f, f);
                } else if (str.equals("ital")) {
                    FontInterpolator fontInterpolator2 = fontInterpolator;
                    float lerp2 = MathUtils.lerp(f2 != null ? f2.floatValue() : 0.0f, f3 != null ? f3.floatValue() : 0.0f, f);
                    fontInterpolator2.getClass();
                    lerp = ((int) (RangesKt.coerceIn(lerp2, 0.0f, 1.0f) / 0.1f)) * 0.1f;
                } else {
                    if (f2 == null || f3 == null) {
                        throw new IllegalArgumentException("Unable to interpolate due to unknown default axes value : ".concat(str).toString());
                    }
                    lerp = MathUtils.lerp(f2.floatValue(), f3.floatValue(), f);
                }
                return Float.valueOf(lerp);
            }
        };
        int i2 = 1;
        if (axes.length > 1) {
            final int i3 = 0;
            Comparator comparator = new Comparator() { // from class: com.android.systemui.animation.FontInterpolator$lerp$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    switch (i3) {
                    }
                    return ComparisonsKt___ComparisonsJvmKt.compareValues(((FontVariationAxis) obj).getTag(), ((FontVariationAxis) obj2).getTag());
                }
            };
            if (axes.length > 1) {
                Arrays.sort(axes, comparator);
            }
        }
        if (axes2.length > 1) {
            final int i4 = 3;
            Comparator comparator2 = new Comparator() { // from class: com.android.systemui.animation.FontInterpolator$lerp$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    switch (i4) {
                    }
                    return ComparisonsKt___ComparisonsJvmKt.compareValues(((FontVariationAxis) obj).getTag(), ((FontVariationAxis) obj2).getTag());
                }
            };
            if (axes2.length > 1) {
                Arrays.sort(axes2, comparator2);
            }
        }
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i5 >= axes.length && i6 >= axes2.length) {
                break;
            }
            String tag = i5 < axes.length ? axes[i5].getTag() : null;
            String tag2 = i6 < axes2.length ? axes2[i6].getTag() : null;
            int compareTo = tag == null ? 1 : tag2 == null ? -1 : tag.compareTo(tag2);
            if (compareTo == 0) {
                Intrinsics.checkNotNull(tag);
                int i7 = i6 + 1;
                fontVariationAxis = new FontVariationAxis(tag, ((Number) function3.invoke(tag, Float.valueOf(axes[i5].getStyleValue()), Float.valueOf(axes2[i6].getStyleValue()))).floatValue());
                i5++;
                i = i7;
            } else if (compareTo < 0) {
                Intrinsics.checkNotNull(tag);
                i = i6;
                fontVariationAxis = new FontVariationAxis(tag, ((Number) function3.invoke(tag, Float.valueOf(axes[i5].getStyleValue()), null)).floatValue());
                i5++;
            } else {
                Intrinsics.checkNotNull(tag2);
                i = i6 + 1;
                fontVariationAxis = new FontVariationAxis(tag2, ((Number) function3.invoke(tag2, null, Float.valueOf(axes2[i6].getStyleValue()))).floatValue());
            }
            arrayList.add(fontVariationAxis);
            i6 = i;
            i2 = 1;
            fontInterpolator = this;
        }
        VarFontKey varFontKey = fontInterpolator.tmpVarFontKey;
        varFontKey.getClass();
        varFontKey.sourceId = font.getSourceIdentifier();
        varFontKey.index = font.getTtcIndex();
        varFontKey.sortedAxes.clear();
        varFontKey.sortedAxes.addAll(arrayList);
        List list = varFontKey.sortedAxes;
        if (((ArrayList) list).size() > i2) {
            final int i8 = 2;
            CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new Comparator() { // from class: com.android.systemui.animation.FontInterpolator$lerp$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    switch (i8) {
                    }
                    return ComparisonsKt___ComparisonsJvmKt.compareValues(((FontVariationAxis) obj).getTag(), ((FontVariationAxis) obj2).getTag());
                }
            });
        }
        Font font4 = (Font) fontInterpolator.verFontCache.get(varFontKey);
        if (font4 != null) {
            fontInterpolator.interpCache.put(new InterpKey(font, font2, f), font4);
            if (z) {
                Log.d("FontInterpolator", "[" + f + "] Axis cache hit for " + varFontKey);
            }
            return font4;
        }
        Font build = new Font.Builder(font).setFontVariationSettings((FontVariationAxis[]) arrayList.toArray(new FontVariationAxis[0])).build();
        fontInterpolator.interpCache.put(new InterpKey(font, font2, f), build);
        LruCache lruCache = fontInterpolator.verFontCache;
        int sourceIdentifier = font.getSourceIdentifier();
        int ttcIndex = font.getTtcIndex();
        ArrayList arrayList2 = new ArrayList(arrayList);
        if (arrayList2.size() > i2) {
            final int i9 = 1;
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList2, new Comparator() { // from class: com.android.systemui.animation.FontInterpolator$lerp$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    switch (i9) {
                    }
                    return ComparisonsKt___ComparisonsJvmKt.compareValues(((FontVariationAxis) obj).getTag(), ((FontVariationAxis) obj2).getTag());
                }
            });
        }
        lruCache.put(new VarFontKey(sourceIdentifier, ttcIndex, arrayList2), build);
        Log.e("FontInterpolator", "[" + f + "] Cache MISS for " + interpKey + " / " + varFontKey);
        return build;
    }
}
