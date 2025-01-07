package com.android.systemui.qs.footer.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SecurityButtonConfig {
    public final Icon icon;
    public final boolean isClickable;
    public final String text;

    public SecurityButtonConfig(Icon icon, String str, boolean z) {
        this.icon = icon;
        this.text = str;
        this.isClickable = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SecurityButtonConfig)) {
            return false;
        }
        SecurityButtonConfig securityButtonConfig = (SecurityButtonConfig) obj;
        return Intrinsics.areEqual(this.icon, securityButtonConfig.icon) && Intrinsics.areEqual(this.text, securityButtonConfig.text) && this.isClickable == securityButtonConfig.isClickable;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isClickable) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.text, this.icon.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SecurityButtonConfig(icon=");
        sb.append(this.icon);
        sb.append(", text=");
        sb.append(this.text);
        sb.append(", isClickable=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isClickable, ")");
    }
}
