package com.android.systemui.qs.footer.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsButtonViewModel {
    public final int backgroundColor;
    public final Icon icon;
    public final Integer iconTint;
    public final int id;
    public final FunctionReferenceImpl onClick;

    /* JADX WARN: Multi-variable type inference failed */
    public FooterActionsButtonViewModel(int i, Icon icon, Integer num, int i2, Function1 function1) {
        this.id = i;
        this.icon = icon;
        this.iconTint = num;
        this.backgroundColor = i2;
        this.onClick = (FunctionReferenceImpl) function1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FooterActionsButtonViewModel)) {
            return false;
        }
        FooterActionsButtonViewModel footerActionsButtonViewModel = (FooterActionsButtonViewModel) obj;
        return this.id == footerActionsButtonViewModel.id && this.icon.equals(footerActionsButtonViewModel.icon) && Intrinsics.areEqual(this.iconTint, footerActionsButtonViewModel.iconTint) && this.backgroundColor == footerActionsButtonViewModel.backgroundColor && this.onClick.equals(footerActionsButtonViewModel.onClick);
    }

    public final int hashCode() {
        int hashCode = (this.icon.hashCode() + (Integer.hashCode(this.id) * 31)) * 31;
        Integer num = this.iconTint;
        return this.onClick.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.backgroundColor, (hashCode + (num == null ? 0 : num.hashCode())) * 31, 31);
    }

    public final String toString() {
        return "FooterActionsButtonViewModel(id=" + this.id + ", icon=" + this.icon + ", iconTint=" + this.iconTint + ", backgroundColor=" + this.backgroundColor + ", onClick=" + this.onClick + ")";
    }
}
