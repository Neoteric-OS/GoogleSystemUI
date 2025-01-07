package com.android.systemui.qs.pipeline.shared;

import android.content.ComponentName;
import android.text.TextUtils;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileSpec {
    public final String spec;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static TileSpec create(String str) {
            boolean isEmpty = TextUtils.isEmpty(str);
            Invalid invalid = Invalid.INSTANCE;
            if (isEmpty) {
                return invalid;
            }
            if (!str.startsWith("custom(")) {
                return new PlatformTileSpec(str);
            }
            ComponentName componentName = null;
            if (str.startsWith("custom(") && str.endsWith(")")) {
                componentName = ComponentName.unflattenFromString(str.substring(7, str.length() - 1));
            }
            return componentName != null ? new CustomTileSpec(componentName, str) : invalid;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomTileSpec extends TileSpec {
        public final ComponentName componentName;
        public final String spec;

        public CustomTileSpec(ComponentName componentName, String str) {
            super(str);
            this.spec = str;
            this.componentName = componentName;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CustomTileSpec)) {
                return false;
            }
            CustomTileSpec customTileSpec = (CustomTileSpec) obj;
            return Intrinsics.areEqual(this.spec, customTileSpec.spec) && Intrinsics.areEqual(this.componentName, customTileSpec.componentName);
        }

        @Override // com.android.systemui.qs.pipeline.shared.TileSpec
        public final String getSpec() {
            return this.spec;
        }

        public final int hashCode() {
            return this.componentName.hashCode() + (this.spec.hashCode() * 31);
        }

        public final String toString() {
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("C(", this.componentName.flattenToShortString(), ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Invalid extends TileSpec {
        public static final Invalid INSTANCE = new Invalid("");

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Invalid);
        }

        public final int hashCode() {
            return 1236272636;
        }

        public final String toString() {
            return "Invalid";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PlatformTileSpec extends TileSpec {
        public final String spec;

        public PlatformTileSpec(String str) {
            super(str);
            this.spec = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof PlatformTileSpec) && Intrinsics.areEqual(this.spec, ((PlatformTileSpec) obj).spec);
        }

        @Override // com.android.systemui.qs.pipeline.shared.TileSpec
        public final String getSpec() {
            return this.spec;
        }

        public final int hashCode() {
            return this.spec.hashCode();
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("P("), this.spec, ")");
        }
    }

    public TileSpec(String str) {
        this.spec = str;
    }

    public String getSpec() {
        return this.spec;
    }
}
