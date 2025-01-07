package com.android.systemui.keyguard.ui.viewmodel;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.common.shared.model.Icon;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceViewModel {
    public final boolean animateReveal;
    public final String configKey;
    public final Icon icon;
    public final boolean isActivated;
    public final boolean isClickable;
    public final boolean isDimmed;
    public final boolean isSelected;
    public final boolean isVisible;
    public final Function1 onClicked;
    public final String slotId;
    public final boolean useLongPress;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnClickedParameters {
        public final String configKey;
        public final Expandable$Companion$fromView$1 expandable;
        public final String slotId;

        public OnClickedParameters(String str, Expandable$Companion$fromView$1 expandable$Companion$fromView$1, String str2) {
            this.configKey = str;
            this.expandable = expandable$Companion$fromView$1;
            this.slotId = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OnClickedParameters)) {
                return false;
            }
            OnClickedParameters onClickedParameters = (OnClickedParameters) obj;
            return Intrinsics.areEqual(this.configKey, onClickedParameters.configKey) && this.expandable.equals(onClickedParameters.expandable) && Intrinsics.areEqual(this.slotId, onClickedParameters.slotId);
        }

        public final int hashCode() {
            return this.slotId.hashCode() + ((this.expandable.hashCode() + (this.configKey.hashCode() * 31)) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("OnClickedParameters(configKey=");
            sb.append(this.configKey);
            sb.append(", expandable=");
            sb.append(this.expandable);
            sb.append(", slotId=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.slotId, ")");
        }
    }

    public KeyguardQuickAffordanceViewModel(String str, boolean z, boolean z2, Icon icon, Function1 function1, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str2) {
        this.configKey = str;
        this.isVisible = z;
        this.animateReveal = z2;
        this.icon = icon;
        this.onClicked = function1;
        this.isClickable = z3;
        this.isActivated = z4;
        this.isSelected = z5;
        this.useLongPress = z6;
        this.isDimmed = z7;
        this.slotId = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyguardQuickAffordanceViewModel)) {
            return false;
        }
        KeyguardQuickAffordanceViewModel keyguardQuickAffordanceViewModel = (KeyguardQuickAffordanceViewModel) obj;
        return Intrinsics.areEqual(this.configKey, keyguardQuickAffordanceViewModel.configKey) && this.isVisible == keyguardQuickAffordanceViewModel.isVisible && this.animateReveal == keyguardQuickAffordanceViewModel.animateReveal && Intrinsics.areEqual(this.icon, keyguardQuickAffordanceViewModel.icon) && Intrinsics.areEqual(this.onClicked, keyguardQuickAffordanceViewModel.onClicked) && this.isClickable == keyguardQuickAffordanceViewModel.isClickable && this.isActivated == keyguardQuickAffordanceViewModel.isActivated && this.isSelected == keyguardQuickAffordanceViewModel.isSelected && this.useLongPress == keyguardQuickAffordanceViewModel.useLongPress && this.isDimmed == keyguardQuickAffordanceViewModel.isDimmed && Intrinsics.areEqual(this.slotId, keyguardQuickAffordanceViewModel.slotId);
    }

    public final int hashCode() {
        String str = this.configKey;
        return this.slotId.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(ChangeSize$$ExternalSyntheticOutline0.m(this.onClicked, (this.icon.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((str == null ? 0 : str.hashCode()) * 31, 31, this.isVisible), 31, this.animateReveal)) * 31, 31), 31, this.isClickable), 31, this.isActivated), 31, this.isSelected), 31, this.useLongPress), 31, this.isDimmed);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KeyguardQuickAffordanceViewModel(configKey=");
        sb.append(this.configKey);
        sb.append(", isVisible=");
        sb.append(this.isVisible);
        sb.append(", animateReveal=");
        sb.append(this.animateReveal);
        sb.append(", icon=");
        sb.append(this.icon);
        sb.append(", onClicked=");
        sb.append(this.onClicked);
        sb.append(", isClickable=");
        sb.append(this.isClickable);
        sb.append(", isActivated=");
        sb.append(this.isActivated);
        sb.append(", isSelected=");
        sb.append(this.isSelected);
        sb.append(", useLongPress=");
        sb.append(this.useLongPress);
        sb.append(", isDimmed=");
        sb.append(this.isDimmed);
        sb.append(", slotId=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.slotId, ")");
    }

    public /* synthetic */ KeyguardQuickAffordanceViewModel(String str) {
        this(null, false, false, new Icon.Resource(0, null), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel.1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }, false, false, false, false, false, str);
    }
}
