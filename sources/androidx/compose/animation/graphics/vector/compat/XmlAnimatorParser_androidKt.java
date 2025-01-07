package androidx.compose.animation.graphics.vector.compat;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.PathInterpolator;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.graphics.res.AnimatorResources_androidKt;
import androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda0;
import androidx.compose.animation.graphics.vector.AnimatorSet;
import androidx.compose.animation.graphics.vector.Keyframe;
import androidx.compose.animation.graphics.vector.Ordering;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder1D;
import androidx.compose.animation.graphics.vector.PropertyValuesHolderColor;
import androidx.compose.animation.graphics.vector.PropertyValuesHolderFloat;
import androidx.compose.animation.graphics.vector.PropertyValuesHolderInt;
import androidx.compose.animation.graphics.vector.PropertyValuesHolderPath;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.core.graphics.PathParser;
import java.util.ArrayList;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class XmlAnimatorParser_androidKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Easing getInterpolator(TypedArray typedArray, Resources resources, Resources.Theme theme, int i, Easing easing) {
        TypedArray typedArray2;
        Easing animatorResources_androidKt$$ExternalSyntheticLambda0;
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId == 0) {
            return easing;
        }
        Easing easing2 = (Easing) AnimatorResources_androidKt.builtinInterpolators.get(Integer.valueOf(resourceId));
        if (easing2 != null) {
            return easing2;
        }
        XmlResourceParser xml = resources.getXml(resourceId);
        XmlPullParserUtils_androidKt.seekToStartTag(xml);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        String name = xml.getName();
        if (name != null) {
            int hashCode = name.hashCode();
            int[] iArr = AndroidVectorResources.STYLEABLE_ANTICIPATEOVERSHOOT_INTERPOLATOR;
            switch (hashCode) {
                case -2140409460:
                    if (name.equals("pathInterpolator")) {
                        int[] iArr2 = AndroidVectorResources.STYLEABLE_PATH_INTERPOLATOR;
                        if (theme == null || (r8 = theme.obtainStyledAttributes(asAttributeSet, iArr2, 0, 0)) == null) {
                            TypedArray obtainAttributes = resources.obtainAttributes(asAttributeSet, iArr2);
                        }
                        try {
                            String string = typedArray2.getString(4);
                            if (string != null) {
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = new AnimatorResources_androidKt$$ExternalSyntheticLambda0(new PathInterpolator(PathParser.createPathFromPathData(string)));
                            } else {
                                if (typedArray2.hasValue(2) && typedArray2.hasValue(3)) {
                                    animatorResources_androidKt$$ExternalSyntheticLambda0 = new CubicBezierEasing(typedArray2.getFloat(0, 0.0f), typedArray2.getFloat(1, 0.0f), typedArray2.getFloat(2, 1.0f), typedArray2.getFloat(3, 1.0f));
                                }
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = new AnimatorResources_androidKt$$ExternalSyntheticLambda0(new PathInterpolator(typedArray2.getFloat(0, 0.0f), typedArray2.getFloat(1, 0.0f)));
                            }
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case -2120889007:
                    if (name.equals("anticipateInterpolator")) {
                        if (theme == null || (r6 = theme.obtainStyledAttributes(asAttributeSet, iArr, 0, 0)) == null) {
                            TypedArray obtainAttributes2 = resources.obtainAttributes(asAttributeSet, iArr);
                        }
                        try {
                            final float f = typedArray2.getFloat(0, 2.0f);
                            final int i2 = 0;
                            Easing easing3 = new Easing() { // from class: androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda1
                                @Override // androidx.compose.animation.core.Easing
                                public final float transform(float f2) {
                                    float f3 = f;
                                    switch (i2) {
                                        case 0:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (((1 + f3) * f2) - f3) * f2 * f2;
                                        case 1:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda32 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.pow(f2, f3 * 2);
                                        case 2:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda33 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            float f4 = f2 - 1.0f;
                                            return ((((f3 + 1.0f) * f4) + f3) * f4 * f4) + 1.0f;
                                        case 3:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda34 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.sin(2 * f3 * 3.141592653589793d * f2);
                                        default:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda35 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return 1.0f - ((float) Math.pow(1.0f - f2, 2 * f3));
                                    }
                                }
                            };
                            typedArray2.recycle();
                            animatorResources_androidKt$$ExternalSyntheticLambda0 = easing3;
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case -1248486260:
                    if (name.equals("linearInterpolator")) {
                        animatorResources_androidKt$$ExternalSyntheticLambda0 = EasingKt.LinearEasing;
                        return animatorResources_androidKt$$ExternalSyntheticLambda0;
                    }
                    break;
                case -935873468:
                    if (name.equals("accelerateInterpolator")) {
                        int[] iArr3 = AndroidVectorResources.STYLEABLE_ACCELERATE_INTERPOLATOR;
                        if (theme == null || (typedArray2 = theme.obtainStyledAttributes(asAttributeSet, iArr3, 0, 0)) == null) {
                            typedArray2 = resources.obtainAttributes(asAttributeSet, iArr3);
                        }
                        try {
                            final float f2 = typedArray2.getFloat(0, 1.0f);
                            if (f2 == 1.0f) {
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = AnimatorResources_androidKt.AccelerateEasing;
                            } else {
                                final int i3 = 1;
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = new Easing() { // from class: androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda1
                                    @Override // androidx.compose.animation.core.Easing
                                    public final float transform(float f22) {
                                        float f3 = f2;
                                        switch (i3) {
                                            case 0:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (((1 + f3) * f22) - f3) * f22 * f22;
                                            case 1:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda32 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (float) Math.pow(f22, f3 * 2);
                                            case 2:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda33 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                float f4 = f22 - 1.0f;
                                                return ((((f3 + 1.0f) * f4) + f3) * f4 * f4) + 1.0f;
                                            case 3:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda34 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (float) Math.sin(2 * f3 * 3.141592653589793d * f22);
                                            default:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda35 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return 1.0f - ((float) Math.pow(1.0f - f22, 2 * f3));
                                        }
                                    }
                                };
                            }
                            typedArray2.recycle();
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case -425326737:
                    if (name.equals("bounceInterpolator")) {
                        animatorResources_androidKt$$ExternalSyntheticLambda0 = AnimatorResources_androidKt.BounceEasing;
                        return animatorResources_androidKt$$ExternalSyntheticLambda0;
                    }
                    break;
                case 1192587314:
                    if (name.equals("overshootInterpolator")) {
                        int[] iArr4 = AndroidVectorResources.STYLEABLE_OVERSHOOT_INTERPOLATOR;
                        if (theme == null || (r8 = theme.obtainStyledAttributes(asAttributeSet, iArr4, 0, 0)) == null) {
                            TypedArray obtainAttributes3 = resources.obtainAttributes(asAttributeSet, iArr4);
                        }
                        try {
                            final float f3 = typedArray2.getFloat(0, 2.0f);
                            final int i4 = 2;
                            animatorResources_androidKt$$ExternalSyntheticLambda0 = new Easing() { // from class: androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda1
                                @Override // androidx.compose.animation.core.Easing
                                public final float transform(float f22) {
                                    float f32 = f3;
                                    switch (i4) {
                                        case 0:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (((1 + f32) * f22) - f32) * f22 * f22;
                                        case 1:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda32 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.pow(f22, f32 * 2);
                                        case 2:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda33 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            float f4 = f22 - 1.0f;
                                            return ((((f32 + 1.0f) * f4) + f32) * f4 * f4) + 1.0f;
                                        case 3:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda34 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.sin(2 * f32 * 3.141592653589793d * f22);
                                        default:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda35 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return 1.0f - ((float) Math.pow(1.0f - f22, 2 * f32));
                                    }
                                }
                            };
                            typedArray2.recycle();
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case 1472030440:
                    if (name.equals("anticipateOvershootInterpolator")) {
                        if (theme == null || (r6 = theme.obtainStyledAttributes(asAttributeSet, iArr, 0, 0)) == null) {
                            TypedArray obtainAttributes4 = resources.obtainAttributes(asAttributeSet, iArr);
                        }
                        try {
                            animatorResources_androidKt$$ExternalSyntheticLambda0 = new AnimatorResources_androidKt$$ExternalSyntheticLambda0(new AnticipateOvershootInterpolator(typedArray2.getFloat(0, 2.0f), typedArray2.getFloat(1, 1.5f)));
                            typedArray2.recycle();
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case 1962594083:
                    if (name.equals("decelerateInterpolator")) {
                        int[] iArr5 = AndroidVectorResources.STYLEABLE_DECELERATE_INTERPOLATOR;
                        if (theme == null || (r8 = theme.obtainStyledAttributes(asAttributeSet, iArr5, 0, 0)) == null) {
                            TypedArray obtainAttributes5 = resources.obtainAttributes(asAttributeSet, iArr5);
                        }
                        try {
                            final float f4 = typedArray2.getFloat(0, 1.0f);
                            if (f4 == 1.0f) {
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = AnimatorResources_androidKt.DecelerateEasing;
                            } else {
                                final int i5 = 4;
                                animatorResources_androidKt$$ExternalSyntheticLambda0 = new Easing() { // from class: androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda1
                                    @Override // androidx.compose.animation.core.Easing
                                    public final float transform(float f22) {
                                        float f32 = f4;
                                        switch (i5) {
                                            case 0:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (((1 + f32) * f22) - f32) * f22 * f22;
                                            case 1:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda32 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (float) Math.pow(f22, f32 * 2);
                                            case 2:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda33 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                float f42 = f22 - 1.0f;
                                                return ((((f32 + 1.0f) * f42) + f32) * f42 * f42) + 1.0f;
                                            case 3:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda34 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return (float) Math.sin(2 * f32 * 3.141592653589793d * f22);
                                            default:
                                                AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda35 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                                return 1.0f - ((float) Math.pow(1.0f - f22, 2 * f32));
                                        }
                                    }
                                };
                            }
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
                case 2019672672:
                    if (name.equals("accelerateDecelerateInterpolator")) {
                        animatorResources_androidKt$$ExternalSyntheticLambda0 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                        return animatorResources_androidKt$$ExternalSyntheticLambda0;
                    }
                    break;
                case 2038238413:
                    if (name.equals("cycleInterpolator")) {
                        int[] iArr6 = AndroidVectorResources.STYLEABLE_CYCLE_INTERPOLATOR;
                        if (theme == null || (r8 = theme.obtainStyledAttributes(asAttributeSet, iArr6, 0, 0)) == null) {
                            TypedArray obtainAttributes6 = resources.obtainAttributes(asAttributeSet, iArr6);
                        }
                        try {
                            final float f5 = typedArray2.getFloat(0, 1.0f);
                            final int i6 = 3;
                            animatorResources_androidKt$$ExternalSyntheticLambda0 = new Easing() { // from class: androidx.compose.animation.graphics.res.AnimatorResources_androidKt$$ExternalSyntheticLambda1
                                @Override // androidx.compose.animation.core.Easing
                                public final float transform(float f22) {
                                    float f32 = f5;
                                    switch (i6) {
                                        case 0:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda3 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (((1 + f32) * f22) - f32) * f22 * f22;
                                        case 1:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda32 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.pow(f22, f32 * 2);
                                        case 2:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda33 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            float f42 = f22 - 1.0f;
                                            return ((((f32 + 1.0f) * f42) + f32) * f42 * f42) + 1.0f;
                                        case 3:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda34 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return (float) Math.sin(2 * f32 * 3.141592653589793d * f22);
                                        default:
                                            AnimatorResources_androidKt$$ExternalSyntheticLambda3 animatorResources_androidKt$$ExternalSyntheticLambda35 = AnimatorResources_androidKt.AccelerateDecelerateEasing;
                                            return 1.0f - ((float) Math.pow(1.0f - f22, 2 * f32));
                                    }
                                }
                            };
                            typedArray2.recycle();
                            return animatorResources_androidKt$$ExternalSyntheticLambda0;
                        } finally {
                        }
                    }
                    break;
            }
        }
        throw new RuntimeException("Unknown interpolator: " + xml.getName());
    }

    public static final Keyframe getKeyframe(TypedArray typedArray, float f, Easing easing, ValueType valueType, int i) {
        int ordinal = valueType.ordinal();
        if (ordinal == 0) {
            return new Keyframe(f, Float.valueOf(typedArray.getFloat(i, 0.0f)), easing);
        }
        if (ordinal == 1) {
            return new Keyframe(f, Integer.valueOf(typedArray.getInt(i, 0)), easing);
        }
        if (ordinal == 2) {
            return new Keyframe(f, new Color(ColorKt.Color(typedArray.getColor(i, 0))), easing);
        }
        if (ordinal == 3) {
            return new Keyframe(f, VectorKt.addPathNodes(typedArray.getString(i)), easing);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final PropertyValuesHolder1D getPropertyValuesHolder1D(TypedArray typedArray, String str, int i, int i2, int i3, Easing easing, Function2 function2) {
        int i4 = typedArray.getInt(i, 4);
        TypedValue peekValue = typedArray.peekValue(i2);
        boolean z = peekValue != null;
        int i5 = peekValue != null ? peekValue.type : 4;
        TypedValue peekValue2 = typedArray.peekValue(i3);
        boolean z2 = peekValue2 != null;
        ValueType inferValueType = inferValueType(new int[]{i5, peekValue2 != null ? peekValue2.type : 4}, i4);
        ArrayList arrayList = new ArrayList();
        if (inferValueType == null && (z || z2)) {
            inferValueType = ValueType.Float;
        }
        if (z) {
            Intrinsics.checkNotNull(inferValueType);
            arrayList.add(getKeyframe(typedArray, 0.0f, easing, inferValueType, i2));
        }
        if (z2) {
            Intrinsics.checkNotNull(inferValueType);
            arrayList.add(getKeyframe(typedArray, 1.0f, easing, inferValueType, i3));
        }
        ValueType valueType = (ValueType) function2.invoke(inferValueType, arrayList);
        if (arrayList.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, new XmlAnimatorParser_androidKt$getPropertyValuesHolder1D$$inlined$sortBy$1());
        }
        int ordinal = valueType.ordinal();
        if (ordinal == 0) {
            return new PropertyValuesHolderFloat(str, arrayList);
        }
        if (ordinal == 1) {
            return new PropertyValuesHolderInt(str);
        }
        if (ordinal == 2) {
            return new PropertyValuesHolderColor(str, arrayList);
        }
        if (ordinal == 3) {
            return new PropertyValuesHolderPath(str, arrayList);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ValueType inferValueType(int[] iArr, int i) {
        if (i == 0) {
            return ValueType.Float;
        }
        if (i == 1) {
            return ValueType.Int;
        }
        if (i == 2) {
            return ValueType.Path;
        }
        ValueType valueType = ValueType.Color;
        if (i == 3) {
            return valueType;
        }
        for (int i2 : iArr) {
            if (28 > i2 || i2 >= 32) {
                return null;
            }
        }
        return valueType;
    }

    public static final AnimatorSet parseAnimatorSet(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        int[] iArr = AndroidVectorResources.STYLEABLE_ANIMATOR_SET;
        if (theme == null || (obtainAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 0, 0)) == null) {
            obtainAttributes = resources.obtainAttributes(attributeSet, iArr);
        }
        try {
            int i = obtainAttributes.getInt(0, 0);
            ArrayList arrayList = new ArrayList();
            xmlPullParser.next();
            while (!XmlPullParserUtils_androidKt.isAtEnd(xmlPullParser) && (xmlPullParser.getEventType() != 3 || !Intrinsics.areEqual(xmlPullParser.getName(), "set"))) {
                if (xmlPullParser.getEventType() == 2) {
                    String name = xmlPullParser.getName();
                    if (Intrinsics.areEqual(name, "set")) {
                        arrayList.add(parseAnimatorSet(resources, xmlPullParser, attributeSet, theme));
                    } else if (Intrinsics.areEqual(name, "objectAnimator")) {
                        arrayList.add(parseObjectAnimator(resources, xmlPullParser, attributeSet, theme));
                    }
                }
                xmlPullParser.next();
            }
            AnimatorSet animatorSet = new AnimatorSet(arrayList, i != 0 ? Ordering.Sequentially : Ordering.Together);
            obtainAttributes.recycle();
            return animatorSet;
        } catch (Throwable th) {
            obtainAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043 A[Catch: all -> 0x0065, TryCatch #0 {all -> 0x0065, blocks: (B:15:0x002f, B:17:0x0043, B:28:0x006b, B:30:0x0071, B:31:0x0085, B:32:0x008a, B:34:0x0090, B:36:0x0096, B:40:0x00a8, B:42:0x00ae, B:44:0x00ba, B:46:0x00be, B:77:0x00c8), top: B:14:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0148 A[Catch: all -> 0x0106, TryCatch #1 {all -> 0x0106, blocks: (B:18:0x012f, B:20:0x0148, B:22:0x014f, B:27:0x014c, B:59:0x00ff, B:61:0x0120, B:69:0x0113, B:70:0x0116), top: B:58:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x014c A[Catch: all -> 0x0106, TryCatch #1 {all -> 0x0106, blocks: (B:18:0x012f, B:20:0x0148, B:22:0x014f, B:27:0x014c, B:59:0x00ff, B:61:0x0120, B:69:0x0113, B:70:0x0116), top: B:58:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006b A[Catch: all -> 0x0065, TryCatch #0 {all -> 0x0065, blocks: (B:15:0x002f, B:17:0x0043, B:28:0x006b, B:30:0x0071, B:31:0x0085, B:32:0x008a, B:34:0x0090, B:36:0x0096, B:40:0x00a8, B:42:0x00ae, B:44:0x00ba, B:46:0x00be, B:77:0x00c8), top: B:14:0x002f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.animation.graphics.vector.ObjectAnimator parseObjectAnimator(final android.content.res.Resources r19, final org.xmlpull.v1.XmlPullParser r20, final android.util.AttributeSet r21, final android.content.res.Resources.Theme r22) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.graphics.vector.compat.XmlAnimatorParser_androidKt.parseObjectAnimator(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.compose.animation.graphics.vector.ObjectAnimator");
    }
}
