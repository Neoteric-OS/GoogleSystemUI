package com.android.systemui.communal.shared.model;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.UserHandle;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface CommunalWidgetContentModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Available implements CommunalWidgetContentModel {
        public final int appWidgetId;
        public final AppWidgetProviderInfo providerInfo;
        public final int rank;

        public Available(int i, AppWidgetProviderInfo appWidgetProviderInfo, int i2) {
            this.appWidgetId = i;
            this.providerInfo = appWidgetProviderInfo;
            this.rank = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Available)) {
                return false;
            }
            Available available = (Available) obj;
            return this.appWidgetId == available.appWidgetId && Intrinsics.areEqual(this.providerInfo, available.providerInfo) && this.rank == available.rank;
        }

        @Override // com.android.systemui.communal.shared.model.CommunalWidgetContentModel
        public final int getAppWidgetId() {
            return this.appWidgetId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.rank) + ((this.providerInfo.hashCode() + (Integer.hashCode(this.appWidgetId) * 31)) * 31);
        }

        public final String toString() {
            AppWidgetProviderInfo appWidgetProviderInfo = this.providerInfo;
            StringBuilder sb = new StringBuilder("Available(appWidgetId=");
            sb.append(this.appWidgetId);
            sb.append(", providerInfo=");
            sb.append(appWidgetProviderInfo);
            sb.append(", rank=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.rank, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Pending implements CommunalWidgetContentModel {
        public final int appWidgetId;
        public final ComponentName componentName;
        public final Bitmap icon;
        public final int rank;
        public final UserHandle user;

        public Pending(int i, int i2, ComponentName componentName, Bitmap bitmap, UserHandle userHandle) {
            this.appWidgetId = i;
            this.rank = i2;
            this.componentName = componentName;
            this.icon = bitmap;
            this.user = userHandle;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pending)) {
                return false;
            }
            Pending pending = (Pending) obj;
            return this.appWidgetId == pending.appWidgetId && this.rank == pending.rank && Intrinsics.areEqual(this.componentName, pending.componentName) && Intrinsics.areEqual(this.icon, pending.icon) && Intrinsics.areEqual(this.user, pending.user);
        }

        @Override // com.android.systemui.communal.shared.model.CommunalWidgetContentModel
        public final int getAppWidgetId() {
            return this.appWidgetId;
        }

        public final int hashCode() {
            int hashCode = (this.componentName.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rank, Integer.hashCode(this.appWidgetId) * 31, 31)) * 31;
            Bitmap bitmap = this.icon;
            return this.user.hashCode() + ((hashCode + (bitmap == null ? 0 : bitmap.hashCode())) * 31);
        }

        public final String toString() {
            return "Pending(appWidgetId=" + this.appWidgetId + ", rank=" + this.rank + ", componentName=" + this.componentName + ", icon=" + this.icon + ", user=" + this.user + ")";
        }
    }

    int getAppWidgetId();
}
