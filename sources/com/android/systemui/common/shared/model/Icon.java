package com.android.systemui.common.shared.model;

import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Icon {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loaded extends Icon {
        public final ContentDescription contentDescription;
        public final Drawable drawable;

        public Loaded(Drawable drawable, ContentDescription contentDescription) {
            this.drawable = drawable;
            this.contentDescription = contentDescription;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Loaded)) {
                return false;
            }
            Loaded loaded = (Loaded) obj;
            return Intrinsics.areEqual(this.drawable, loaded.drawable) && Intrinsics.areEqual(this.contentDescription, loaded.contentDescription);
        }

        @Override // com.android.systemui.common.shared.model.Icon
        public final ContentDescription getContentDescription() {
            return this.contentDescription;
        }

        public final int hashCode() {
            int hashCode = this.drawable.hashCode() * 31;
            ContentDescription contentDescription = this.contentDescription;
            return hashCode + (contentDescription == null ? 0 : contentDescription.hashCode());
        }

        public final String toString() {
            return "Loaded(drawable=" + this.drawable + ", contentDescription=" + this.contentDescription + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resource extends Icon {
        public final ContentDescription contentDescription;
        public final int res;

        public Resource(int i, ContentDescription contentDescription) {
            this.res = i;
            this.contentDescription = contentDescription;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resource)) {
                return false;
            }
            Resource resource = (Resource) obj;
            return this.res == resource.res && Intrinsics.areEqual(this.contentDescription, resource.contentDescription);
        }

        @Override // com.android.systemui.common.shared.model.Icon
        public final ContentDescription getContentDescription() {
            return this.contentDescription;
        }

        public final int hashCode() {
            int hashCode = Integer.hashCode(this.res) * 31;
            ContentDescription contentDescription = this.contentDescription;
            return hashCode + (contentDescription == null ? 0 : contentDescription.hashCode());
        }

        public final String toString() {
            return "Resource(res=" + this.res + ", contentDescription=" + this.contentDescription + ")";
        }
    }

    public abstract ContentDescription getContentDescription();
}
