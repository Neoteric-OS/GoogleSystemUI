package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CollapsedStatusBarViewModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VisibilityModel {
        public final boolean shouldAnimateChange;
        public final int visibility;

        public VisibilityModel(int i, boolean z) {
            this.visibility = i;
            this.shouldAnimateChange = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VisibilityModel)) {
                return false;
            }
            VisibilityModel visibilityModel = (VisibilityModel) obj;
            return this.visibility == visibilityModel.visibility && this.shouldAnimateChange == visibilityModel.shouldAnimateChange;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.shouldAnimateChange) + (Integer.hashCode(this.visibility) * 31);
        }

        public final String toString() {
            return "VisibilityModel(visibility=" + this.visibility + ", shouldAnimateChange=" + this.shouldAnimateChange + ")";
        }
    }
}
