package com.android.systemui.statusbar.pipeline.mobile.domain.model;

import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NetworkTypeIconModel extends Diffable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultIcon implements NetworkTypeIconModel {
        public final int contentDescription;
        public final SignalIcon$MobileIconGroup iconGroup;
        public final int iconId;
        public final String name;

        public DefaultIcon(SignalIcon$MobileIconGroup signalIcon$MobileIconGroup) {
            this.iconGroup = signalIcon$MobileIconGroup;
            this.contentDescription = signalIcon$MobileIconGroup.dataContentDescription;
            this.iconId = signalIcon$MobileIconGroup.dataType;
            this.name = signalIcon$MobileIconGroup.name;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DefaultIcon) && Intrinsics.areEqual(this.iconGroup, ((DefaultIcon) obj).iconGroup);
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel
        public final int getContentDescription() {
            return this.contentDescription;
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel
        public final int getIconId() {
            return this.iconId;
        }

        public final int hashCode() {
            return this.iconGroup.hashCode();
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            NetworkTypeIconModel networkTypeIconModel = (NetworkTypeIconModel) obj;
            boolean z = networkTypeIconModel instanceof DefaultIcon;
            String str = this.name;
            if (z && ((DefaultIcon) networkTypeIconModel).name.equals(str)) {
                return;
            }
            tableRowLoggerImpl.logChange("networkTypeIcon", str);
        }

        public final String toString() {
            return "DefaultIcon(iconGroup=" + this.iconGroup + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OverriddenIcon implements NetworkTypeIconModel {
        public final int contentDescription;
        public final SignalIcon$MobileIconGroup iconGroup;
        public final int iconId;
        public final String name;

        public OverriddenIcon(SignalIcon$MobileIconGroup signalIcon$MobileIconGroup, int i) {
            this.iconGroup = signalIcon$MobileIconGroup;
            this.iconId = i;
            this.contentDescription = signalIcon$MobileIconGroup.dataContentDescription;
            this.name = signalIcon$MobileIconGroup.name;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OverriddenIcon)) {
                return false;
            }
            OverriddenIcon overriddenIcon = (OverriddenIcon) obj;
            return Intrinsics.areEqual(this.iconGroup, overriddenIcon.iconGroup) && this.iconId == overriddenIcon.iconId;
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel
        public final int getContentDescription() {
            return this.contentDescription;
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel
        public final int getIconId() {
            return this.iconId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.iconId) + (this.iconGroup.hashCode() * 31);
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            NetworkTypeIconModel networkTypeIconModel = (NetworkTypeIconModel) obj;
            boolean z = networkTypeIconModel instanceof OverriddenIcon;
            String str = this.name;
            if (z) {
                OverriddenIcon overriddenIcon = (OverriddenIcon) networkTypeIconModel;
                if (overriddenIcon.name.equals(str)) {
                    if (overriddenIcon.iconId == this.iconId) {
                        return;
                    }
                }
            }
            tableRowLoggerImpl.logChange("networkTypeIcon", "Ovrd(" + str + ")");
        }

        public final String toString() {
            return "OverriddenIcon(iconGroup=" + this.iconGroup + ", iconId=" + this.iconId + ")";
        }
    }

    int getContentDescription();

    int getIconId();
}
