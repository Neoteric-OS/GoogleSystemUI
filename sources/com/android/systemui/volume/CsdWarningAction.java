package com.android.systemui.volume;

import android.content.Intent;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CsdWarningAction {
    public final Intent intent;
    public final boolean isActivity;
    public final String label;

    public CsdWarningAction(String str, Intent intent, boolean z) {
        this.label = str;
        this.intent = intent;
        this.isActivity = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CsdWarningAction)) {
            return false;
        }
        CsdWarningAction csdWarningAction = (CsdWarningAction) obj;
        return Intrinsics.areEqual(this.label, csdWarningAction.label) && Intrinsics.areEqual(this.intent, csdWarningAction.intent) && this.isActivity == csdWarningAction.isActivity;
    }

    public final int hashCode() {
        String str = this.label;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Intent intent = this.intent;
        return Boolean.hashCode(this.isActivity) + ((hashCode + (intent != null ? intent.hashCode() : 0)) * 31);
    }

    public final String toString() {
        Intent intent = this.intent;
        StringBuilder sb = new StringBuilder("CsdWarningAction(label=");
        sb.append(this.label);
        sb.append(", intent=");
        sb.append(intent);
        sb.append(", isActivity=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isActivity, ")");
    }
}
