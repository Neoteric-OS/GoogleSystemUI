package androidx.compose.foundation.layout;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Arrangement {
    public static final Arrangement$SpaceBetween$1 SpaceBetween;
    public static final Arrangement$Start$1 Start = new Arrangement$Start$1();
    public static final Arrangement$End$1 End = new Arrangement$End$1();
    public static final Arrangement$Top$1 Top = new Arrangement$Top$1();
    public static final Arrangement$Bottom$1 Bottom = new Arrangement$Bottom$1();
    public static final Arrangement$Center$1 Center = new Arrangement$Center$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Absolute {
        public static final Arrangement$Absolute$Left$1 Left = new Arrangement$Absolute$Left$1();
        public static final Arrangement$Absolute$Right$1 Right = new Arrangement$Absolute$Right$1();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Horizontal {
        void arrange(Density density, int i, int[] iArr, LayoutDirection layoutDirection, int[] iArr2);

        /* renamed from: getSpacing-D9Ej5fM */
        default float mo81getSpacingD9Ej5fM() {
            return 0;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Vertical {
        void arrange(Density density, int i, int[] iArr, int[] iArr2);

        /* renamed from: getSpacing-D9Ej5fM */
        default float mo81getSpacingD9Ej5fM() {
            return 0;
        }
    }

    static {
        new Arrangement$SpaceEvenly$1();
        SpaceBetween = new Arrangement$SpaceBetween$1();
        new Arrangement$SpaceAround$1();
    }

    public static void placeCenter$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float f = (i - i3) / 2;
        if (!z) {
            int length = iArr.length;
            int i5 = 0;
            while (i2 < length) {
                int i6 = iArr[i2];
                iArr2[i5] = Math.round(f);
                f += i6;
                i2++;
                i5++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i7 = iArr[length2];
            iArr2[length2] = Math.round(f);
            f += i7;
        }
    }

    public static void placeLeftOrTop$foundation_layout_release(int[] iArr, int[] iArr2, boolean z) {
        int i = 0;
        if (!z) {
            int length = iArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int i4 = iArr[i];
                iArr2[i2] = i3;
                i3 += i4;
                i++;
                i2++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i5 = iArr[length2];
            iArr2[length2] = i;
            i += i5;
        }
    }

    public static void placeRightOrBottom$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        int i5 = i - i3;
        if (!z) {
            int length = iArr.length;
            int i6 = 0;
            while (i2 < length) {
                int i7 = iArr[i2];
                iArr2[i6] = i5;
                i5 += i7;
                i2++;
                i6++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i8 = iArr[length2];
            iArr2[length2] = i5;
            i5 += i8;
        }
    }

    public static void placeSpaceAround$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float length = iArr.length == 0 ? 0.0f : (i - i3) / iArr.length;
        float f = length / 2;
        if (!z) {
            int length2 = iArr.length;
            int i5 = 0;
            while (i2 < length2) {
                int i6 = iArr[i2];
                iArr2[i5] = Math.round(f);
                f += i6 + length;
                i2++;
                i5++;
            }
            return;
        }
        int length3 = iArr.length;
        while (true) {
            length3--;
            if (-1 >= length3) {
                return;
            }
            int i7 = iArr[length3];
            iArr2[length3] = Math.round(f);
            f += i7 + length;
        }
    }

    public static void placeSpaceBetween$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        if (iArr.length == 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float max = (i - i3) / Math.max(iArr.length - 1, 1);
        float f = (z && iArr.length == 1) ? max : 0.0f;
        if (z) {
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i5 = iArr[length];
                iArr2[length] = Math.round(f);
                f += i5 + max;
            }
            return;
        }
        int length2 = iArr.length;
        int i6 = 0;
        while (i2 < length2) {
            int i7 = iArr[i2];
            iArr2[i6] = Math.round(f);
            f += i7 + max;
            i2++;
            i6++;
        }
    }

    public static void placeSpaceEvenly$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float length = (i - i3) / (iArr.length + 1);
        if (z) {
            float f = length;
            for (int length2 = iArr.length - 1; -1 < length2; length2--) {
                int i5 = iArr[length2];
                iArr2[length2] = Math.round(f);
                f += i5 + length;
            }
            return;
        }
        int length3 = iArr.length;
        float f2 = length;
        int i6 = 0;
        while (i2 < length3) {
            int i7 = iArr[i2];
            iArr2[i6] = Math.round(f2);
            f2 += i7 + length;
            i2++;
            i6++;
        }
    }

    /* renamed from: spacedBy-0680j_4, reason: not valid java name */
    public static SpacedAligned m78spacedBy0680j_4(float f) {
        return new SpacedAligned(f, true, new Function2() { // from class: androidx.compose.foundation.layout.Arrangement$spacedBy$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(Alignment.Companion.Start.align(0, ((Number) obj).intValue(), (LayoutDirection) obj2));
            }
        });
    }

    /* renamed from: spacedBy-D5KLDUw, reason: not valid java name */
    public static SpacedAligned m79spacedByD5KLDUw(float f, final BiasAlignment.Horizontal horizontal) {
        return new SpacedAligned(f, true, new Function2() { // from class: androidx.compose.foundation.layout.Arrangement$spacedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(horizontal.align(0, ((Number) obj).intValue(), (LayoutDirection) obj2));
            }
        });
    }

    /* renamed from: spacedBy-D5KLDUw, reason: not valid java name */
    public static SpacedAligned m80spacedByD5KLDUw(float f, final BiasAlignment.Vertical vertical) {
        return new SpacedAligned(f, false, new Function2() { // from class: androidx.compose.foundation.layout.Arrangement$spacedBy$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((BiasAlignment.Vertical) vertical).align(0, ((Number) obj).intValue()));
            }
        });
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SpacedAligned implements Horizontal, Vertical {
        public final Lambda alignment;
        public final boolean rtlMirror;
        public final float space;
        public final float spacing;

        /* JADX WARN: Multi-variable type inference failed */
        public SpacedAligned(float f, boolean z, Function2 function2) {
            this.space = f;
            this.rtlMirror = z;
            this.alignment = (Lambda) function2;
            this.spacing = f;
        }

        /* JADX WARN: Type inference failed for: r10v1, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
        @Override // androidx.compose.foundation.layout.Arrangement.Horizontal
        public final void arrange(Density density, int i, int[] iArr, LayoutDirection layoutDirection, int[] iArr2) {
            int i2;
            int i3;
            if (iArr.length == 0) {
                return;
            }
            int mo45roundToPx0680j_4 = density.mo45roundToPx0680j_4(this.space);
            boolean z = this.rtlMirror && layoutDirection == LayoutDirection.Rtl;
            Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
            if (z) {
                int length = iArr.length - 1;
                i2 = 0;
                i3 = 0;
                while (-1 < length) {
                    int i4 = iArr[length];
                    int min = Math.min(i2, i - i4);
                    iArr2[length] = min;
                    int min2 = Math.min(mo45roundToPx0680j_4, (i - min) - i4);
                    int i5 = iArr2[length] + i4 + min2;
                    length--;
                    i3 = min2;
                    i2 = i5;
                }
            } else {
                int length2 = iArr.length;
                int i6 = 0;
                i2 = 0;
                i3 = 0;
                int i7 = 0;
                while (i6 < length2) {
                    int i8 = iArr[i6];
                    int min3 = Math.min(i2, i - i8);
                    iArr2[i7] = min3;
                    int min4 = Math.min(mo45roundToPx0680j_4, (i - min3) - i8);
                    int i9 = iArr2[i7] + i8 + min4;
                    i6++;
                    i7++;
                    i3 = min4;
                    i2 = i9;
                }
            }
            int i10 = i2 - i3;
            ?? r10 = this.alignment;
            if (r10 == 0 || i10 >= i) {
                return;
            }
            int intValue = ((Number) r10.invoke(Integer.valueOf(i - i10), layoutDirection)).intValue();
            int length3 = iArr2.length;
            for (int i11 = 0; i11 < length3; i11++) {
                iArr2[i11] = iArr2[i11] + intValue;
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpacedAligned)) {
                return false;
            }
            SpacedAligned spacedAligned = (SpacedAligned) obj;
            return Dp.m668equalsimpl0(this.space, spacedAligned.space) && this.rtlMirror == spacedAligned.rtlMirror && Intrinsics.areEqual(this.alignment, spacedAligned.alignment);
        }

        @Override // androidx.compose.foundation.layout.Arrangement.Horizontal, androidx.compose.foundation.layout.Arrangement.Vertical
        /* renamed from: getSpacing-D9Ej5fM */
        public final float mo81getSpacingD9Ej5fM() {
            return this.spacing;
        }

        public final int hashCode() {
            int m = TransitionData$$ExternalSyntheticOutline0.m(Float.hashCode(this.space) * 31, 31, this.rtlMirror);
            Lambda lambda = this.alignment;
            return m + (lambda == null ? 0 : lambda.hashCode());
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.rtlMirror ? "" : "Absolute");
            sb.append("Arrangement#spacedAligned(");
            sb.append((Object) Dp.m669toStringimpl(this.space));
            sb.append(", ");
            sb.append(this.alignment);
            sb.append(')');
            return sb.toString();
        }

        @Override // androidx.compose.foundation.layout.Arrangement.Vertical
        public final void arrange(Density density, int i, int[] iArr, int[] iArr2) {
            arrange(density, i, iArr, LayoutDirection.Ltr, iArr2);
        }
    }
}
