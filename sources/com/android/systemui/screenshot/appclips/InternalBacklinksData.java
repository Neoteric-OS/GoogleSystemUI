package com.android.systemui.screenshot.appclips;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class InternalBacklinksData {
    public final BacklinkDisplayInfo backlinkDisplayInfo;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BacklinksData extends InternalBacklinksData {
        public final ClipData clipData;
        public final Drawable icon;

        public BacklinksData(ClipData clipData, Drawable drawable) {
            super(new BacklinkDisplayInfo(drawable, clipData.getDescription().getLabel().toString()));
            this.clipData = clipData;
            this.icon = drawable;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BacklinksData)) {
                return false;
            }
            BacklinksData backlinksData = (BacklinksData) obj;
            return Intrinsics.areEqual(this.clipData, backlinksData.clipData) && Intrinsics.areEqual(this.icon, backlinksData.icon);
        }

        public final int hashCode() {
            return this.icon.hashCode() + (this.clipData.hashCode() * 31);
        }

        public final String toString() {
            return "BacklinksData(clipData=" + this.clipData + ", icon=" + this.icon + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CrossProfileError extends InternalBacklinksData {
        public final Drawable icon;
        public final String label;

        public CrossProfileError(Drawable drawable, String str) {
            super(new BacklinkDisplayInfo(drawable, str));
            this.icon = drawable;
            this.label = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CrossProfileError)) {
                return false;
            }
            CrossProfileError crossProfileError = (CrossProfileError) obj;
            return Intrinsics.areEqual(this.icon, crossProfileError.icon) && Intrinsics.areEqual(this.label, crossProfileError.label);
        }

        public final int hashCode() {
            return this.label.hashCode() + (this.icon.hashCode() * 31);
        }

        public final String toString() {
            return "CrossProfileError(icon=" + this.icon + ", label=" + this.label + ")";
        }
    }

    public InternalBacklinksData(BacklinkDisplayInfo backlinkDisplayInfo) {
        this.backlinkDisplayInfo = backlinkDisplayInfo;
    }
}
