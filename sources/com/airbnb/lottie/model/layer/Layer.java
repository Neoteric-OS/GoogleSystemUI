package com.airbnb.lottie.model.layer;

import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.List;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Layer {
    public final LBlendMode blendMode;
    public final BlurEffect blurEffect;
    public final LottieComposition composition;
    public final DropShadowEffect dropShadowEffect;
    public final boolean hidden;
    public final List inOutKeyframes;
    public final long layerId;
    public final String layerName;
    public final LayerType layerType;
    public final List masks;
    public final MatteType matteType;
    public final long parentId;
    public final float preCompHeight;
    public final float preCompWidth;
    public final String refId;
    public final List shapes;
    public final int solidColor;
    public final int solidHeight;
    public final int solidWidth;
    public final float startFrame;
    public final AnimatableTextFrame text;
    public final AnimatableTextProperties textProperties;
    public final AnimatableFloatValue timeRemapping;
    public final float timeStretch;
    public final AnimatableTransform transform;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayerType {
        public static final /* synthetic */ LayerType[] $VALUES;
        public static final LayerType IMAGE;
        public static final LayerType PRE_COMP;
        public static final LayerType UNKNOWN;

        static {
            LayerType layerType = new LayerType("PRE_COMP", 0);
            PRE_COMP = layerType;
            LayerType layerType2 = new LayerType("SOLID", 1);
            LayerType layerType3 = new LayerType("IMAGE", 2);
            IMAGE = layerType3;
            LayerType layerType4 = new LayerType("NULL", 3);
            LayerType layerType5 = new LayerType("SHAPE", 4);
            LayerType layerType6 = new LayerType("TEXT", 5);
            LayerType layerType7 = new LayerType("UNKNOWN", 6);
            UNKNOWN = layerType7;
            $VALUES = new LayerType[]{layerType, layerType2, layerType3, layerType4, layerType5, layerType6, layerType7};
        }

        public static LayerType valueOf(String str) {
            return (LayerType) Enum.valueOf(LayerType.class, str);
        }

        public static LayerType[] values() {
            return (LayerType[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MatteType {
        public static final /* synthetic */ MatteType[] $VALUES;
        public static final MatteType INVERT;
        public static final MatteType NONE;

        static {
            MatteType matteType = new MatteType("NONE", 0);
            NONE = matteType;
            MatteType matteType2 = new MatteType("ADD", 1);
            MatteType matteType3 = new MatteType("INVERT", 2);
            INVERT = matteType3;
            $VALUES = new MatteType[]{matteType, matteType2, matteType3, new MatteType("LUMA", 3), new MatteType("LUMA_INVERTED", 4), new MatteType("UNKNOWN", 5)};
        }

        public static MatteType valueOf(String str) {
            return (MatteType) Enum.valueOf(MatteType.class, str);
        }

        public static MatteType[] values() {
            return (MatteType[]) $VALUES.clone();
        }
    }

    public Layer(List list, LottieComposition lottieComposition, String str, long j, LayerType layerType, long j2, String str2, List list2, AnimatableTransform animatableTransform, int i, int i2, int i3, float f, float f2, float f3, float f4, AnimatableTextFrame animatableTextFrame, AnimatableTextProperties animatableTextProperties, List list3, MatteType matteType, AnimatableFloatValue animatableFloatValue, boolean z, BlurEffect blurEffect, DropShadowEffect dropShadowEffect, LBlendMode lBlendMode) {
        this.shapes = list;
        this.composition = lottieComposition;
        this.layerName = str;
        this.layerId = j;
        this.layerType = layerType;
        this.parentId = j2;
        this.refId = str2;
        this.masks = list2;
        this.transform = animatableTransform;
        this.solidWidth = i;
        this.solidHeight = i2;
        this.solidColor = i3;
        this.timeStretch = f;
        this.startFrame = f2;
        this.preCompWidth = f3;
        this.preCompHeight = f4;
        this.text = animatableTextFrame;
        this.textProperties = animatableTextProperties;
        this.inOutKeyframes = list3;
        this.matteType = matteType;
        this.timeRemapping = animatableFloatValue;
        this.hidden = z;
        this.blurEffect = blurEffect;
        this.dropShadowEffect = dropShadowEffect;
        this.blendMode = lBlendMode;
    }

    public final String toString(String str) {
        int i;
        StringBuilder m = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(str);
        m.append(this.layerName);
        m.append("\n");
        LottieComposition lottieComposition = this.composition;
        Layer layer = (Layer) lottieComposition.layerMap.get(this.parentId);
        if (layer != null) {
            m.append("\t\tParents: ");
            m.append(layer.layerName);
            for (Layer layer2 = (Layer) lottieComposition.layerMap.get(layer.parentId); layer2 != null; layer2 = (Layer) lottieComposition.layerMap.get(layer2.parentId)) {
                m.append("->");
                m.append(layer2.layerName);
            }
            m.append(str);
            m.append("\n");
        }
        if (!this.masks.isEmpty()) {
            m.append(str);
            m.append("\tMasks: ");
            m.append(this.masks.size());
            m.append("\n");
        }
        int i2 = this.solidWidth;
        if (i2 != 0 && (i = this.solidHeight) != 0) {
            m.append(str);
            m.append("\tBackground: ");
            m.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(this.solidColor)));
        }
        if (!this.shapes.isEmpty()) {
            m.append(str);
            m.append("\tShapes:\n");
            for (Object obj : this.shapes) {
                m.append(str);
                m.append("\t\t");
                m.append(obj);
                m.append("\n");
            }
        }
        return m.toString();
    }

    public final String toString() {
        return toString("");
    }
}
