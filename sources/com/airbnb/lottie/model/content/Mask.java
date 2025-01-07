package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Mask {
    public final boolean inverted;
    public final MaskMode maskMode;
    public final AnimatableShapeValue maskPath;
    public final AnimatableIntegerValue opacity;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MaskMode {
        public static final /* synthetic */ MaskMode[] $VALUES;
        public static final MaskMode MASK_MODE_ADD;
        public static final MaskMode MASK_MODE_INTERSECT;
        public static final MaskMode MASK_MODE_NONE;
        public static final MaskMode MASK_MODE_SUBTRACT;

        static {
            MaskMode maskMode = new MaskMode("MASK_MODE_ADD", 0);
            MASK_MODE_ADD = maskMode;
            MaskMode maskMode2 = new MaskMode("MASK_MODE_SUBTRACT", 1);
            MASK_MODE_SUBTRACT = maskMode2;
            MaskMode maskMode3 = new MaskMode("MASK_MODE_INTERSECT", 2);
            MASK_MODE_INTERSECT = maskMode3;
            MaskMode maskMode4 = new MaskMode("MASK_MODE_NONE", 3);
            MASK_MODE_NONE = maskMode4;
            $VALUES = new MaskMode[]{maskMode, maskMode2, maskMode3, maskMode4};
        }

        public static MaskMode valueOf(String str) {
            return (MaskMode) Enum.valueOf(MaskMode.class, str);
        }

        public static MaskMode[] values() {
            return (MaskMode[]) $VALUES.clone();
        }
    }

    public Mask(MaskMode maskMode, AnimatableShapeValue animatableShapeValue, AnimatableIntegerValue animatableIntegerValue, boolean z) {
        this.maskMode = maskMode;
        this.maskPath = animatableShapeValue;
        this.opacity = animatableIntegerValue;
        this.inverted = z;
    }
}
