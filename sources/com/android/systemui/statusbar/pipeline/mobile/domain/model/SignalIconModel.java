package com.android.systemui.statusbar.pipeline.mobile.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SignalIconModel extends Diffable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Cellular implements SignalIconModel {
        public final boolean carrierNetworkChange;
        public final int level;
        public final int numberOfLevels;
        public final boolean showExclamationMark;

        public Cellular(int i, int i2, boolean z, boolean z2) {
            this.level = i;
            this.numberOfLevels = i2;
            this.showExclamationMark = z;
            this.carrierNetworkChange = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Cellular)) {
                return false;
            }
            Cellular cellular = (Cellular) obj;
            return this.level == cellular.level && this.numberOfLevels == cellular.numberOfLevels && this.showExclamationMark == cellular.showExclamationMark && this.carrierNetworkChange == cellular.carrierNetworkChange;
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final int getLevel() {
            return this.level;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.carrierNetworkChange) + TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.numberOfLevels, Integer.hashCode(this.level) * 31, 31), 31, this.showExclamationMark);
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final void logFully(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "c");
            tableRowLoggerImpl.logChange(this.level, "level");
            tableRowLoggerImpl.logChange(this.numberOfLevels, "numLevels");
            tableRowLoggerImpl.logChange("showExclamation", this.showExclamationMark);
            tableRowLoggerImpl.logChange("carrierNetworkChange", this.carrierNetworkChange);
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final void logPartial(SignalIconModel signalIconModel, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            if (!(signalIconModel instanceof Cellular)) {
                logFully(tableRowLoggerImpl);
                return;
            }
            int i = ((Cellular) signalIconModel).level;
            int i2 = this.level;
            if (i != i2) {
                tableRowLoggerImpl.logChange(i2, "level");
            }
            Cellular cellular = (Cellular) signalIconModel;
            int i3 = cellular.numberOfLevels;
            int i4 = this.numberOfLevels;
            if (i3 != i4) {
                tableRowLoggerImpl.logChange(i4, "numLevels");
            }
            boolean z = cellular.showExclamationMark;
            boolean z2 = this.showExclamationMark;
            if (z != z2) {
                tableRowLoggerImpl.logChange("showExclamation", z2);
            }
            boolean z3 = cellular.carrierNetworkChange;
            boolean z4 = this.carrierNetworkChange;
            if (z3 != z4) {
                tableRowLoggerImpl.logChange("carrierNetworkChange", z4);
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Cellular(level=");
            sb.append(this.level);
            sb.append(", numberOfLevels=");
            sb.append(this.numberOfLevels);
            sb.append(", showExclamationMark=");
            sb.append(this.showExclamationMark);
            sb.append(", carrierNetworkChange=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.carrierNetworkChange, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Satellite implements SignalIconModel {
        public final Icon.Resource icon;
        public final int level;

        public Satellite(int i, Icon.Resource resource) {
            this.level = i;
            this.icon = resource;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Satellite)) {
                return false;
            }
            Satellite satellite = (Satellite) obj;
            return this.level == satellite.level && Intrinsics.areEqual(this.icon, satellite.icon);
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final int getLevel() {
            return this.level;
        }

        public final int hashCode() {
            return this.icon.hashCode() + (Integer.hashCode(this.level) * 31);
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final void logFully(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("numLevels", "3");
            tableRowLoggerImpl.logChange("type", "s");
            tableRowLoggerImpl.logChange(this.level, "level");
        }

        @Override // com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel
        public final void logPartial(SignalIconModel signalIconModel, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            if (!(signalIconModel instanceof Satellite)) {
                logFully(tableRowLoggerImpl);
                return;
            }
            int i = ((Satellite) signalIconModel).level;
            int i2 = this.level;
            if (i != i2) {
                tableRowLoggerImpl.logChange(i2, "level");
            }
        }

        public final String toString() {
            return "Satellite(level=" + this.level + ", icon=" + this.icon + ")";
        }
    }

    int getLevel();

    @Override // com.android.systemui.log.table.Diffable
    default void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        logPartial((SignalIconModel) obj, tableRowLoggerImpl);
    }

    @Override // com.android.systemui.log.table.Diffable
    default void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        logFully(tableRowLoggerImpl);
    }

    void logFully(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl);

    void logPartial(SignalIconModel signalIconModel, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl);
}
