package com.android.systemui.controls.ui;

import android.content.ComponentName;
import com.android.systemui.controls.controller.StructureInfo;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SelectedItem {
    public static final StructureItem EMPTY_SELECTION = new StructureItem(StructureInfo.EMPTY_STRUCTURE);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PanelItem extends SelectedItem {
        public final CharSequence appName;
        public final ComponentName componentName;
        public final CharSequence name;

        public PanelItem(ComponentName componentName, CharSequence charSequence) {
            this.appName = charSequence;
            this.componentName = componentName;
            this.name = charSequence;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PanelItem)) {
                return false;
            }
            PanelItem panelItem = (PanelItem) obj;
            return Intrinsics.areEqual(this.appName, panelItem.appName) && Intrinsics.areEqual(this.componentName, panelItem.componentName);
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final ComponentName getComponentName() {
            return this.componentName;
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final boolean getHasControls() {
            return true;
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final CharSequence getName() {
            return this.name;
        }

        public final int hashCode() {
            return this.componentName.hashCode() + (this.appName.hashCode() * 31);
        }

        public final String toString() {
            CharSequence charSequence = this.appName;
            return "PanelItem(appName=" + ((Object) charSequence) + ", componentName=" + this.componentName + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StructureItem extends SelectedItem {
        public final ComponentName componentName;
        public final boolean hasControls;
        public final CharSequence name;
        public final StructureInfo structure;

        public StructureItem(StructureInfo structureInfo) {
            this.structure = structureInfo;
            this.name = structureInfo.structure;
            this.hasControls = !structureInfo.controls.isEmpty();
            this.componentName = structureInfo.componentName;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof StructureItem) && Intrinsics.areEqual(this.structure, ((StructureItem) obj).structure);
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final ComponentName getComponentName() {
            return this.componentName;
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final boolean getHasControls() {
            return this.hasControls;
        }

        @Override // com.android.systemui.controls.ui.SelectedItem
        public final CharSequence getName() {
            return this.name;
        }

        public final int hashCode() {
            return this.structure.hashCode();
        }

        public final String toString() {
            return "StructureItem(structure=" + this.structure + ")";
        }
    }

    public abstract ComponentName getComponentName();

    public abstract boolean getHasControls();

    public abstract CharSequence getName();
}
