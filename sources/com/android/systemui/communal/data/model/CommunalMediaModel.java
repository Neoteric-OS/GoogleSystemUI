package com.android.systemui.communal.data.model;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalMediaModel implements Diffable {
    public static final CommunalMediaModel INACTIVE = new CommunalMediaModel(0, false);
    public final long createdTimestampMillis;
    public final boolean hasAnyMediaOrRecommendation;

    public CommunalMediaModel(long j, boolean z) {
        this.hasAnyMediaOrRecommendation = z;
        this.createdTimestampMillis = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommunalMediaModel)) {
            return false;
        }
        CommunalMediaModel communalMediaModel = (CommunalMediaModel) obj;
        return this.hasAnyMediaOrRecommendation == communalMediaModel.hasAnyMediaOrRecommendation && this.createdTimestampMillis == communalMediaModel.createdTimestampMillis;
    }

    public final int hashCode() {
        return Long.hashCode(this.createdTimestampMillis) + (Boolean.hashCode(this.hasAnyMediaOrRecommendation) * 31);
    }

    @Override // com.android.systemui.log.table.Diffable
    public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
        CommunalMediaModel communalMediaModel = (CommunalMediaModel) obj;
        boolean z = communalMediaModel.hasAnyMediaOrRecommendation;
        boolean z2 = this.hasAnyMediaOrRecommendation;
        if (z2 != z) {
            tableRowLoggerImpl.logChange("isMediaActive", z2);
        }
        long j = communalMediaModel.createdTimestampMillis;
        long j2 = this.createdTimestampMillis;
        if (j2 != j) {
            tableRowLoggerImpl.logChange("mediaCreationTimestamp", String.valueOf(j2));
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CommunalMediaModel(hasAnyMediaOrRecommendation=");
        sb.append(this.hasAnyMediaOrRecommendation);
        sb.append(", createdTimestampMillis=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.createdTimestampMillis, ")", sb);
    }
}
