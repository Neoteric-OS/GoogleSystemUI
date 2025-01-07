package com.android.systemui.media.taptotransfer.common;

import android.graphics.drawable.Drawable;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface MediaTttIcon {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loaded implements MediaTttIcon {
        public final Drawable drawable;

        public Loaded(Drawable drawable) {
            this.drawable = drawable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Loaded) && Intrinsics.areEqual(this.drawable, ((Loaded) obj).drawable);
        }

        public final int hashCode() {
            return this.drawable.hashCode();
        }

        public final String toString() {
            return "Loaded(drawable=" + this.drawable + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resource implements MediaTttIcon {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resource)) {
                return false;
            }
            ((Resource) obj).getClass();
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(R.drawable.ic_cast);
        }

        public final String toString() {
            return GenericDocument$$ExternalSyntheticOutline0.m("Resource(res=", ")", R.drawable.ic_cast);
        }
    }
}
