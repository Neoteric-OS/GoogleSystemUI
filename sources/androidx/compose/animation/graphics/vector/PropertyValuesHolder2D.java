package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.Easing;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PropertyValuesHolder2D extends PropertyValuesHolder {
    public final Easing interpolator;
    public final List pathData;
    public final String xPropertyName;
    public final String yPropertyName;

    public PropertyValuesHolder2D(String str, String str2, List list, Easing easing) {
        this.xPropertyName = str;
        this.yPropertyName = str2;
        this.pathData = list;
        this.interpolator = easing;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PropertyValuesHolder2D)) {
            return false;
        }
        PropertyValuesHolder2D propertyValuesHolder2D = (PropertyValuesHolder2D) obj;
        return Intrinsics.areEqual(this.xPropertyName, propertyValuesHolder2D.xPropertyName) && Intrinsics.areEqual(this.yPropertyName, propertyValuesHolder2D.yPropertyName) && Intrinsics.areEqual(this.pathData, propertyValuesHolder2D.pathData) && Intrinsics.areEqual(this.interpolator, propertyValuesHolder2D.interpolator);
    }

    public final int hashCode() {
        return this.interpolator.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.yPropertyName, this.xPropertyName.hashCode() * 31, 31), 31, this.pathData);
    }

    public final String toString() {
        return "PropertyValuesHolder2D(xPropertyName=" + this.xPropertyName + ", yPropertyName=" + this.yPropertyName + ", pathData=" + this.pathData + ", interpolator=" + this.interpolator + ')';
    }
}
