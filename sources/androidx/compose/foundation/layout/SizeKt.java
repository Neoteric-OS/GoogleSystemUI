package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import com.android.systemui.bouncer.ui.composable.PinBouncerKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SizeKt {
    public static final FillElement FillWholeMaxHeight;
    public static final FillElement FillWholeMaxSize;
    public static final FillElement FillWholeMaxWidth;
    public static final WrapContentElement WrapContentHeightCenter;
    public static final WrapContentElement WrapContentHeightTop;
    public static final WrapContentElement WrapContentSizeCenter;
    public static final WrapContentElement WrapContentSizeTopStart;
    public static final WrapContentElement WrapContentWidthCenter;
    public static final WrapContentElement WrapContentWidthStart;

    static {
        Direction direction = Direction.Horizontal;
        FillWholeMaxWidth = new FillElement(direction, 1.0f);
        Direction direction2 = Direction.Vertical;
        FillWholeMaxHeight = new FillElement(direction2, 1.0f);
        Direction direction3 = Direction.Both;
        FillWholeMaxSize = new FillElement(direction3, 1.0f);
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        WrapContentWidthCenter = new WrapContentElement(direction, false, new WrapContentElement$Companion$width$1(horizontal), horizontal);
        BiasAlignment.Horizontal horizontal2 = Alignment.Companion.Start;
        WrapContentWidthStart = new WrapContentElement(direction, false, new WrapContentElement$Companion$width$1(horizontal2), horizontal2);
        BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
        WrapContentHeightCenter = new WrapContentElement(direction2, false, new WrapContentElement$Companion$height$1(vertical), vertical);
        BiasAlignment.Vertical vertical2 = Alignment.Companion.Top;
        WrapContentHeightTop = new WrapContentElement(direction2, false, new WrapContentElement$Companion$height$1(vertical2), vertical2);
        BiasAlignment biasAlignment = Alignment.Companion.Center;
        WrapContentSizeCenter = new WrapContentElement(direction3, false, new WrapContentElement$Companion$size$1(biasAlignment), biasAlignment);
        BiasAlignment biasAlignment2 = Alignment.Companion.TopStart;
        WrapContentSizeTopStart = new WrapContentElement(direction3, false, new WrapContentElement$Companion$size$1(biasAlignment2), biasAlignment2);
    }

    /* renamed from: defaultMinSize-VpY3zN4, reason: not valid java name */
    public static final Modifier m107defaultMinSizeVpY3zN4(Modifier modifier, float f, float f2) {
        return modifier.then(new UnspecifiedConstraintsElement(f, f2));
    }

    public static final Modifier fillMaxWidth(Modifier modifier, float f) {
        return modifier.then(f == 1.0f ? FillWholeMaxWidth : new FillElement(Direction.Horizontal, f));
    }

    /* renamed from: height-3ABfNKs, reason: not valid java name */
    public static final Modifier m108height3ABfNKs(Modifier modifier, float f) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(0.0f, f, 0.0f, f, 5));
    }

    /* renamed from: heightIn-VpY3zN4, reason: not valid java name */
    public static final Modifier m109heightInVpY3zN4(Modifier modifier, float f, float f2) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(0.0f, f, 0.0f, f2, 5));
    }

    /* renamed from: heightIn-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m110heightInVpY3zN4$default(Modifier modifier, float f, float f2, int i) {
        if ((i & 1) != 0) {
            f = Float.NaN;
        }
        if ((i & 2) != 0) {
            f2 = Float.NaN;
        }
        return m109heightInVpY3zN4(modifier, f, f2);
    }

    /* renamed from: requiredSize-VpY3zN4, reason: not valid java name */
    public static final Modifier m111requiredSizeVpY3zN4(Modifier modifier, float f, float f2) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, f2, f, f2, false));
    }

    /* renamed from: requiredSizeIn-qDBjuR0$default, reason: not valid java name */
    public static Modifier m112requiredSizeInqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i) {
        float f5 = (i & 2) != 0 ? Float.NaN : f2;
        float f6 = (i & 4) != 0 ? Float.NaN : f3;
        float f7 = (i & 8) != 0 ? Float.NaN : f4;
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, f5, f6, f7, false));
    }

    /* renamed from: size-3ABfNKs, reason: not valid java name */
    public static final Modifier m113size3ABfNKs(Modifier modifier, float f) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, f, f, f, true));
    }

    /* renamed from: size-VpY3zN4, reason: not valid java name */
    public static final Modifier m114sizeVpY3zN4(Modifier modifier, float f, float f2) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, f2, f, f2, true));
    }

    /* renamed from: sizeIn-qDBjuR0, reason: not valid java name */
    public static final Modifier m115sizeInqDBjuR0(Modifier modifier, float f, float f2, float f3, float f4) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, f2, f3, f4, true));
    }

    /* renamed from: sizeIn-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m116sizeInqDBjuR0$default(Modifier modifier, float f, float f2, float f3, int i) {
        float f4 = PinBouncerKt.pinButtonMaxSize;
        if ((i & 1) != 0) {
            f = Float.NaN;
        }
        if ((i & 2) != 0) {
            f2 = Float.NaN;
        }
        if ((i & 8) != 0) {
            f4 = Float.NaN;
        }
        return m115sizeInqDBjuR0(modifier, f, f2, f3, f4);
    }

    /* renamed from: width-3ABfNKs, reason: not valid java name */
    public static final Modifier m117width3ABfNKs(Modifier modifier, float f) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f, 0.0f, f, 0.0f, 10));
    }

    /* renamed from: widthIn-VpY3zN4$default, reason: not valid java name */
    public static Modifier m118widthInVpY3zN4$default(Modifier modifier, float f, float f2, int i) {
        float f3 = (i & 1) != 0 ? Float.NaN : f;
        float f4 = (i & 2) != 0 ? Float.NaN : f2;
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new SizeElement(f3, 0.0f, f4, 0.0f, 10));
    }

    public static Modifier wrapContentHeight$default(Modifier modifier, int i) {
        BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
        boolean z = (i & 2) == 0;
        return modifier.then((!Intrinsics.areEqual(vertical, vertical) || z) ? (!Intrinsics.areEqual(vertical, Alignment.Companion.Top) || z) ? new WrapContentElement(Direction.Vertical, z, new WrapContentElement$Companion$height$1(vertical), vertical) : WrapContentHeightTop : WrapContentHeightCenter);
    }

    public static Modifier wrapContentSize$default(Modifier modifier, int i) {
        BiasAlignment biasAlignment = Alignment.Companion.Center;
        boolean z = (i & 2) == 0;
        return modifier.then((!biasAlignment.equals(biasAlignment) || z) ? (!biasAlignment.equals(Alignment.Companion.TopStart) || z) ? new WrapContentElement(Direction.Both, z, new WrapContentElement$Companion$size$1(biasAlignment), biasAlignment) : WrapContentSizeTopStart : WrapContentSizeCenter);
    }

    public static Modifier wrapContentWidth$default(Modifier modifier, int i) {
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        boolean z = (i & 2) == 0;
        return modifier.then((!Intrinsics.areEqual(horizontal, horizontal) || z) ? (!Intrinsics.areEqual(horizontal, Alignment.Companion.Start) || z) ? new WrapContentElement(Direction.Horizontal, z, new WrapContentElement$Companion$width$1(horizontal), horizontal) : WrapContentWidthStart : WrapContentWidthCenter);
    }
}
