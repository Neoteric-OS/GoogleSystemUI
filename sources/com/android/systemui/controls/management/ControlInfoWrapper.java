package com.android.systemui.controls.management;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import com.android.systemui.controls.ControlInterface;
import com.android.systemui.controls.controller.ControlInfo;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlInfoWrapper extends ElementWrapper implements ControlInterface {
    public final ComponentName component;
    public final ControlInfo controlInfo;
    public final FunctionReferenceImpl customIconGetter;
    public boolean favorite = true;

    /* JADX WARN: Multi-variable type inference failed */
    public ControlInfoWrapper(ComponentName componentName, ControlInfo controlInfo, Function2 function2) {
        this.component = componentName;
        this.controlInfo = controlInfo;
        int i = ControlInfoWrapper$customIconGetter$1.$r8$clinit;
        this.customIconGetter = (FunctionReferenceImpl) function2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlInfoWrapper)) {
            return false;
        }
        ControlInfoWrapper controlInfoWrapper = (ControlInfoWrapper) obj;
        return Intrinsics.areEqual(this.component, controlInfoWrapper.component) && Intrinsics.areEqual(this.controlInfo, controlInfoWrapper.controlInfo) && this.favorite == controlInfoWrapper.favorite;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final ComponentName getComponent() {
        return this.component;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final String getControlId() {
        return this.controlInfo.controlId;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.FunctionReferenceImpl] */
    @Override // com.android.systemui.controls.ControlInterface
    public final Icon getCustomIcon() {
        return (Icon) this.customIconGetter.invoke(this.component, this.controlInfo.controlId);
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final int getDeviceType() {
        return this.controlInfo.deviceType;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final boolean getFavorite() {
        return this.favorite;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final CharSequence getSubtitle() {
        return this.controlInfo.controlSubtitle;
    }

    @Override // com.android.systemui.controls.ControlInterface
    public final CharSequence getTitle() {
        return this.controlInfo.controlTitle;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.favorite) + ((this.controlInfo.hashCode() + (this.component.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ControlInfoWrapper(component=" + this.component + ", controlInfo=" + this.controlInfo + ", favorite=" + this.favorite + ")";
    }
}
