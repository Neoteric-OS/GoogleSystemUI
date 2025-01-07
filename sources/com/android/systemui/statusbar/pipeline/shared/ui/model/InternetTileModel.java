package com.android.systemui.statusbar.pipeline.shared.ui.model;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface InternetTileModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Active implements InternetTileModel {
        public final ContentDescription contentDescription;
        public final QSTile.Icon icon;
        public final Integer iconId;
        public final Text secondaryLabel;
        public final CharSequence secondaryTitle;
        public final ContentDescription.Loaded stateDescription;

        public Active(CharSequence charSequence, Text text, Integer num, QSTile.Icon icon, ContentDescription.Loaded loaded, ContentDescription contentDescription, int i) {
            charSequence = (i & 1) != 0 ? null : charSequence;
            text = (i & 2) != 0 ? null : text;
            num = (i & 4) != 0 ? null : num;
            icon = (i & 8) != 0 ? null : icon;
            this.secondaryTitle = charSequence;
            this.secondaryLabel = text;
            this.iconId = num;
            this.icon = icon;
            this.stateDescription = loaded;
            this.contentDescription = contentDescription;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Active)) {
                return false;
            }
            Active active = (Active) obj;
            return Intrinsics.areEqual(this.secondaryTitle, active.secondaryTitle) && Intrinsics.areEqual(this.secondaryLabel, active.secondaryLabel) && Intrinsics.areEqual(this.iconId, active.iconId) && Intrinsics.areEqual(this.icon, active.icon) && Intrinsics.areEqual(this.stateDescription, active.stateDescription) && Intrinsics.areEqual(this.contentDescription, active.contentDescription);
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final ContentDescription getContentDescription() {
            return this.contentDescription;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final QSTile.Icon getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final Integer getIconId() {
            return this.iconId;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final Text getSecondaryLabel() {
            return this.secondaryLabel;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final CharSequence getSecondaryTitle() {
            return this.secondaryTitle;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final ContentDescription getStateDescription() {
            return this.stateDescription;
        }

        public final int hashCode() {
            CharSequence charSequence = this.secondaryTitle;
            int hashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
            Text text = this.secondaryLabel;
            int hashCode2 = (hashCode + (text == null ? 0 : text.hashCode())) * 31;
            Integer num = this.iconId;
            int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            QSTile.Icon icon = this.icon;
            int hashCode4 = (hashCode3 + (icon == null ? 0 : icon.hashCode())) * 31;
            ContentDescription.Loaded loaded = this.stateDescription;
            int hashCode5 = (hashCode4 + (loaded == null ? 0 : loaded.hashCode())) * 31;
            ContentDescription contentDescription = this.contentDescription;
            return hashCode5 + (contentDescription != null ? contentDescription.hashCode() : 0);
        }

        public final String toString() {
            return "Active(secondaryTitle=" + ((Object) this.secondaryTitle) + ", secondaryLabel=" + this.secondaryLabel + ", iconId=" + this.iconId + ", icon=" + this.icon + ", stateDescription=" + this.stateDescription + ", contentDescription=" + this.contentDescription + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Inactive implements InternetTileModel {
        public final ContentDescription contentDescription;
        public final QSTile.Icon icon;
        public final Integer iconId;
        public final Text secondaryLabel;
        public final CharSequence secondaryTitle;

        public Inactive(CharSequence charSequence, Text.Resource resource, Integer num, QSTile.Icon icon, ContentDescription contentDescription, int i) {
            charSequence = (i & 1) != 0 ? null : charSequence;
            resource = (i & 2) != 0 ? null : resource;
            num = (i & 4) != 0 ? null : num;
            icon = (i & 8) != 0 ? null : icon;
            this.secondaryTitle = charSequence;
            this.secondaryLabel = resource;
            this.iconId = num;
            this.icon = icon;
            this.contentDescription = contentDescription;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Inactive)) {
                return false;
            }
            Inactive inactive = (Inactive) obj;
            return Intrinsics.areEqual(this.secondaryTitle, inactive.secondaryTitle) && Intrinsics.areEqual(this.secondaryLabel, inactive.secondaryLabel) && Intrinsics.areEqual(this.iconId, inactive.iconId) && Intrinsics.areEqual(this.icon, inactive.icon) && Intrinsics.areEqual((Object) null, (Object) null) && this.contentDescription.equals(inactive.contentDescription);
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final ContentDescription getContentDescription() {
            return this.contentDescription;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final QSTile.Icon getIcon() {
            return this.icon;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final Integer getIconId() {
            return this.iconId;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final Text getSecondaryLabel() {
            return this.secondaryLabel;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final CharSequence getSecondaryTitle() {
            return this.secondaryTitle;
        }

        @Override // com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel
        public final ContentDescription getStateDescription() {
            return null;
        }

        public final int hashCode() {
            CharSequence charSequence = this.secondaryTitle;
            int hashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
            Text text = this.secondaryLabel;
            int hashCode2 = (hashCode + (text == null ? 0 : text.hashCode())) * 31;
            Integer num = this.iconId;
            int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            QSTile.Icon icon = this.icon;
            return this.contentDescription.hashCode() + ((hashCode3 + (icon != null ? icon.hashCode() : 0)) * 961);
        }

        public final String toString() {
            return "Inactive(secondaryTitle=" + ((Object) this.secondaryTitle) + ", secondaryLabel=" + this.secondaryLabel + ", iconId=" + this.iconId + ", icon=" + this.icon + ", stateDescription=null, contentDescription=" + this.contentDescription + ")";
        }
    }

    ContentDescription getContentDescription();

    QSTile.Icon getIcon();

    Integer getIconId();

    Text getSecondaryLabel();

    CharSequence getSecondaryTitle();

    ContentDescription getStateDescription();
}
