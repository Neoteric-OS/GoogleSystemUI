package com.android.systemui.communal.domain.model;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.communal.shared.model.CommunalContentSize;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface CommunalContentModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CtaTileInViewMode implements CommunalContentModel {
        public final CommunalContentSize size = CommunalContentSize.HALF;

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final String getKey() {
            return "cta_tile_in_view_mode";
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final CommunalContentSize getSize() {
            return this.size;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Ongoing extends CommunalContentModel {
        long getCreatedTimestampMillis();

        default CommunalContentSize getMinSize() {
            return CommunalContentSize.THIRD;
        }

        void setSize(CommunalContentSize communalContentSize);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Smartspace implements Ongoing {
        public final long createdTimestampMillis;
        public final String key;
        public final RemoteViews remoteViews;
        public CommunalContentSize size;

        public Smartspace(long j, RemoteViews remoteViews, String str) {
            CommunalContentSize communalContentSize = CommunalContentSize.HALF;
            this.remoteViews = remoteViews;
            this.createdTimestampMillis = j;
            this.size = communalContentSize;
            this.key = "smartspace_".concat(str);
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel.Ongoing
        public final long getCreatedTimestampMillis() {
            return this.createdTimestampMillis;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final String getKey() {
            return this.key;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final CommunalContentSize getSize() {
            return this.size;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel.Ongoing
        public final void setSize(CommunalContentSize communalContentSize) {
            this.size = communalContentSize;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Tutorial implements CommunalContentModel {
        public final String key;
        public final CommunalContentSize size;

        public Tutorial(int i, CommunalContentSize communalContentSize) {
            this.size = communalContentSize;
            this.key = AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "tutorial_");
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final String getKey() {
            return this.key;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final CommunalContentSize getSize() {
            return this.size;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Umo implements Ongoing {
        public final long createdTimestampMillis;
        public final String key;
        public final CommunalContentSize minSize;
        public CommunalContentSize size;

        public Umo(long j) {
            CommunalContentSize communalContentSize = CommunalContentSize.HALF;
            this.createdTimestampMillis = j;
            this.size = communalContentSize;
            this.minSize = communalContentSize;
            this.key = "umo";
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel.Ongoing
        public final long getCreatedTimestampMillis() {
            return this.createdTimestampMillis;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final String getKey() {
            return this.key;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel.Ongoing
        public final CommunalContentSize getMinSize() {
            return this.minSize;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final CommunalContentSize getSize() {
            return this.size;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel.Ongoing
        public final void setSize(CommunalContentSize communalContentSize) {
            this.size = communalContentSize;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface WidgetContent extends CommunalContentModel {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class PendingWidget implements WidgetContent {
            public final int appWidgetId;
            public final ComponentName componentName;
            public final Bitmap icon;
            public final String key;
            public final int rank;
            public final CommunalContentSize size = CommunalContentSize.HALF;

            public PendingWidget(int i, int i2, ComponentName componentName, Bitmap bitmap) {
                this.appWidgetId = i;
                this.rank = i2;
                this.componentName = componentName;
                this.icon = bitmap;
                this.key = AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "pending_widget_");
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PendingWidget)) {
                    return false;
                }
                PendingWidget pendingWidget = (PendingWidget) obj;
                return this.appWidgetId == pendingWidget.appWidgetId && this.rank == pendingWidget.rank && Intrinsics.areEqual(this.componentName, pendingWidget.componentName) && Intrinsics.areEqual(this.icon, pendingWidget.icon);
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final int getAppWidgetId() {
                return this.appWidgetId;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final ComponentName getComponentName() {
                return this.componentName;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel
            public final String getKey() {
                return this.key;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final int getRank() {
                return this.rank;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel
            public final CommunalContentSize getSize() {
                return this.size;
            }

            public final int hashCode() {
                int hashCode = (this.componentName.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rank, Integer.hashCode(this.appWidgetId) * 31, 31)) * 31;
                Bitmap bitmap = this.icon;
                return hashCode + (bitmap == null ? 0 : bitmap.hashCode());
            }

            public final String toString() {
                return "PendingWidget(appWidgetId=" + this.appWidgetId + ", rank=" + this.rank + ", componentName=" + this.componentName + ", icon=" + this.icon + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Widget implements WidgetContent {
            public final CommunalAppWidgetHost appWidgetHost;
            public final int appWidgetId;
            public final ComponentName componentName;
            public final boolean inQuietMode;
            public final String key;
            public final AppWidgetProviderInfo providerInfo;
            public final int rank;
            public final CommunalContentSize size = CommunalContentSize.HALF;

            public Widget(int i, int i2, AppWidgetProviderInfo appWidgetProviderInfo, CommunalAppWidgetHost communalAppWidgetHost, boolean z) {
                this.appWidgetId = i;
                this.rank = i2;
                this.providerInfo = appWidgetProviderInfo;
                this.appWidgetHost = communalAppWidgetHost;
                this.inQuietMode = z;
                this.key = AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "widget_");
                this.componentName = appWidgetProviderInfo.provider;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Widget)) {
                    return false;
                }
                Widget widget = (Widget) obj;
                return this.appWidgetId == widget.appWidgetId && this.rank == widget.rank && Intrinsics.areEqual(this.providerInfo, widget.providerInfo) && Intrinsics.areEqual(this.appWidgetHost, widget.appWidgetHost) && this.inQuietMode == widget.inQuietMode;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final int getAppWidgetId() {
                return this.appWidgetId;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final ComponentName getComponentName() {
                return this.componentName;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel
            public final String getKey() {
                return this.key;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel.WidgetContent
            public final int getRank() {
                return this.rank;
            }

            @Override // com.android.systemui.communal.domain.model.CommunalContentModel
            public final CommunalContentSize getSize() {
                return this.size;
            }

            public final int hashCode() {
                return Boolean.hashCode(this.inQuietMode) + ((this.appWidgetHost.hashCode() + ((this.providerInfo.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rank, Integer.hashCode(this.appWidgetId) * 31, 31)) * 31)) * 31);
            }

            public final String toString() {
                AppWidgetProviderInfo appWidgetProviderInfo = this.providerInfo;
                StringBuilder sb = new StringBuilder("Widget(appWidgetId=");
                sb.append(this.appWidgetId);
                sb.append(", rank=");
                sb.append(this.rank);
                sb.append(", providerInfo=");
                sb.append(appWidgetProviderInfo);
                sb.append(", appWidgetHost=");
                sb.append(this.appWidgetHost);
                sb.append(", inQuietMode=");
                return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.inQuietMode, ")");
            }
        }

        int getAppWidgetId();

        ComponentName getComponentName();

        int getRank();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WidgetPlaceholder implements CommunalContentModel {
        public final String key = "widget_placeholder_" + UUID.randomUUID();
        public final CommunalContentSize size = CommunalContentSize.HALF;

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final String getKey() {
            return this.key;
        }

        @Override // com.android.systemui.communal.domain.model.CommunalContentModel
        public final CommunalContentSize getSize() {
            return this.size;
        }
    }

    String getKey();

    CommunalContentSize getSize();
}
