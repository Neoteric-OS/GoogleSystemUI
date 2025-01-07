package com.android.systemui.qs.footer.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsForegroundServicesButtonViewModel {
    public final boolean displayText;
    public final int foregroundServicesCount;
    public final boolean hasNewChanges;
    public final Function1 onClick;
    public final String text;

    public FooterActionsForegroundServicesButtonViewModel(int i, String str, boolean z, boolean z2, Function1 function1) {
        this.foregroundServicesCount = i;
        this.text = str;
        this.displayText = z;
        this.hasNewChanges = z2;
        this.onClick = function1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FooterActionsForegroundServicesButtonViewModel)) {
            return false;
        }
        FooterActionsForegroundServicesButtonViewModel footerActionsForegroundServicesButtonViewModel = (FooterActionsForegroundServicesButtonViewModel) obj;
        return this.foregroundServicesCount == footerActionsForegroundServicesButtonViewModel.foregroundServicesCount && Intrinsics.areEqual(this.text, footerActionsForegroundServicesButtonViewModel.text) && this.displayText == footerActionsForegroundServicesButtonViewModel.displayText && this.hasNewChanges == footerActionsForegroundServicesButtonViewModel.hasNewChanges && Intrinsics.areEqual(this.onClick, footerActionsForegroundServicesButtonViewModel.onClick);
    }

    public final int hashCode() {
        return this.onClick.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.text, Integer.hashCode(this.foregroundServicesCount) * 31, 31), 31, this.displayText), 31, this.hasNewChanges);
    }

    public final String toString() {
        return "FooterActionsForegroundServicesButtonViewModel(foregroundServicesCount=" + this.foregroundServicesCount + ", text=" + this.text + ", displayText=" + this.displayText + ", hasNewChanges=" + this.hasNewChanges + ", onClick=" + this.onClick + ")";
    }
}
