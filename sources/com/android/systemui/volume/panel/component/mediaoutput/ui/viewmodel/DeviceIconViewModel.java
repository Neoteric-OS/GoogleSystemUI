package com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Color$Attribute;
import com.android.systemui.common.shared.model.Icon;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DeviceIconViewModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IsNotPlaying implements DeviceIconViewModel {
        public final Color$Attribute backgroundColor;
        public final Icon icon;
        public final Color$Attribute iconColor;

        public IsNotPlaying(Icon icon, Color$Attribute color$Attribute, Color$Attribute color$Attribute2) {
            this.icon = icon;
            this.iconColor = color$Attribute;
            this.backgroundColor = color$Attribute2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IsNotPlaying)) {
                return false;
            }
            IsNotPlaying isNotPlaying = (IsNotPlaying) obj;
            return this.icon.equals(isNotPlaying.icon) && this.iconColor.equals(isNotPlaying.iconColor) && this.backgroundColor.equals(isNotPlaying.backgroundColor);
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Color$Attribute getBackgroundColor() {
            return this.backgroundColor;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Icon getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Color$Attribute getIconColor() {
            return this.iconColor;
        }

        public final int hashCode() {
            return Integer.hashCode(this.backgroundColor.attribute) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.iconColor.attribute, this.icon.hashCode() * 31, 31);
        }

        public final String toString() {
            return "IsNotPlaying(icon=" + this.icon + ", iconColor=" + this.iconColor + ", backgroundColor=" + this.backgroundColor + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IsPlaying implements DeviceIconViewModel {
        public final Color$Attribute backgroundColor;
        public final Icon icon;
        public final Color$Attribute iconColor;

        public IsPlaying(Icon icon, Color$Attribute color$Attribute, Color$Attribute color$Attribute2) {
            this.icon = icon;
            this.iconColor = color$Attribute;
            this.backgroundColor = color$Attribute2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IsPlaying)) {
                return false;
            }
            IsPlaying isPlaying = (IsPlaying) obj;
            return this.icon.equals(isPlaying.icon) && this.iconColor.equals(isPlaying.iconColor) && this.backgroundColor.equals(isPlaying.backgroundColor);
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Color$Attribute getBackgroundColor() {
            return this.backgroundColor;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Icon getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel
        public final Color$Attribute getIconColor() {
            return this.iconColor;
        }

        public final int hashCode() {
            return Integer.hashCode(this.backgroundColor.attribute) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.iconColor.attribute, this.icon.hashCode() * 31, 31);
        }

        public final String toString() {
            return "IsPlaying(icon=" + this.icon + ", iconColor=" + this.iconColor + ", backgroundColor=" + this.backgroundColor + ")";
        }
    }

    Color$Attribute getBackgroundColor();

    Icon getIcon();

    Color$Attribute getIconColor();
}
