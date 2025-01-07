package com.android.systemui.statusbar.pipeline.shared.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataActivityModel implements Diffable {
    public final boolean hasActivityIn;
    public final boolean hasActivityOut;

    public DataActivityModel(boolean z, boolean z2) {
        this.hasActivityIn = z;
        this.hasActivityOut = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataActivityModel)) {
            return false;
        }
        DataActivityModel dataActivityModel = (DataActivityModel) obj;
        return this.hasActivityIn == dataActivityModel.hasActivityIn && this.hasActivityOut == dataActivityModel.hasActivityOut;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.hasActivityOut) + (Boolean.hashCode(this.hasActivityIn) * 31);
    }

    @Override // com.android.systemui.log.table.Diffable
    public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        DataActivityModel dataActivityModel = (DataActivityModel) obj;
        boolean z = dataActivityModel.hasActivityIn;
        boolean z2 = this.hasActivityIn;
        if (z != z2) {
            tableRowLoggerImpl.logChange("in", z2);
        }
        boolean z3 = dataActivityModel.hasActivityOut;
        boolean z4 = this.hasActivityOut;
        if (z3 != z4) {
            tableRowLoggerImpl.logChange("out", z4);
        }
    }

    @Override // com.android.systemui.log.table.Diffable
    public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        tableRowLoggerImpl.logChange("in", this.hasActivityIn);
        tableRowLoggerImpl.logChange("out", this.hasActivityOut);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DataActivityModel(hasActivityIn=");
        sb.append(this.hasActivityIn);
        sb.append(", hasActivityOut=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.hasActivityOut, ")");
    }
}
