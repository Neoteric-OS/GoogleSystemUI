package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShape;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Shapes {
    public final RoundedCornerShape extraExtraLarge;
    public final CornerBasedShape extraLarge;
    public final RoundedCornerShape extraLargeIncreased;
    public final CornerBasedShape extraSmall;
    public final CornerBasedShape large;
    public final RoundedCornerShape largeIncreased;
    public final CornerBasedShape medium;
    public final CornerBasedShape small;

    public Shapes(CornerBasedShape cornerBasedShape, CornerBasedShape cornerBasedShape2, CornerBasedShape cornerBasedShape3, CornerBasedShape cornerBasedShape4, CornerBasedShape cornerBasedShape5) {
        RoundedCornerShape roundedCornerShape = ShapeDefaults.LargeIncreased;
        RoundedCornerShape roundedCornerShape2 = ShapeDefaults.ExtraLargeIncreased;
        RoundedCornerShape roundedCornerShape3 = ShapeDefaults.ExtraExtraLarge;
        this.extraSmall = cornerBasedShape;
        this.small = cornerBasedShape2;
        this.medium = cornerBasedShape3;
        this.large = cornerBasedShape4;
        this.extraLarge = cornerBasedShape5;
        this.largeIncreased = roundedCornerShape;
        this.extraLargeIncreased = roundedCornerShape2;
        this.extraExtraLarge = roundedCornerShape3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shapes)) {
            return false;
        }
        Shapes shapes = (Shapes) obj;
        return Intrinsics.areEqual(this.extraSmall, shapes.extraSmall) && Intrinsics.areEqual(this.small, shapes.small) && Intrinsics.areEqual(this.medium, shapes.medium) && Intrinsics.areEqual(this.large, shapes.large) && Intrinsics.areEqual(this.extraLarge, shapes.extraLarge) && Intrinsics.areEqual(this.largeIncreased, shapes.largeIncreased) && Intrinsics.areEqual(this.extraLargeIncreased, shapes.extraLargeIncreased) && Intrinsics.areEqual(this.extraExtraLarge, shapes.extraExtraLarge);
    }

    public final int hashCode() {
        return this.extraExtraLarge.hashCode() + ((this.extraLargeIncreased.hashCode() + ((this.largeIncreased.hashCode() + ((this.extraLarge.hashCode() + ((this.large.hashCode() + ((this.medium.hashCode() + ((this.small.hashCode() + (this.extraSmall.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Shapes(extraSmall=" + this.extraSmall + ", small=" + this.small + ", medium=" + this.medium + ", large=" + this.large + ", largeIncreased=" + this.largeIncreased + ", extraLarge=" + this.extraLarge + ", extralargeIncreased=" + this.extraLargeIncreased + ", extraExtraLarge=" + this.extraExtraLarge + ')';
    }
}
