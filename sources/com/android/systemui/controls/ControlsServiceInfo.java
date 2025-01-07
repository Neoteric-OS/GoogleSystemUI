package com.android.systemui.controls;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.IconDrawableFactory;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsServiceInfo {
    public final ComponentName _panelActivity;
    public final ComponentName componentName;
    public final Context context;
    public final PackageManager mPm;
    public ComponentName panelActivity;
    public boolean resolved;
    public final ServiceInfo serviceInfo;
    public final int userId;

    public ControlsServiceInfo(Context context, ServiceInfo serviceInfo) {
        String string;
        PackageManager packageManager = context.getPackageManager();
        int userId = context.getUserId();
        ComponentName componentName = serviceInfo.getComponentName();
        this.mPm = packageManager;
        this.userId = userId;
        this.componentName = componentName;
        this.context = context;
        this.serviceInfo = serviceInfo;
        Bundle bundle = serviceInfo.metaData;
        ComponentName unflattenFromString = ComponentName.unflattenFromString((bundle == null || (string = bundle.getString("android.service.controls.META_DATA_PANEL_ACTIVITY")) == null) ? "" : string);
        if (unflattenFromString == null || !Intrinsics.areEqual(unflattenFromString.getPackageName(), componentName.getPackageName())) {
            this._panelActivity = null;
        } else {
            this._panelActivity = unflattenFromString;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ControlsServiceInfo) {
            ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) obj;
            if (this.userId == controlsServiceInfo.userId && Intrinsics.areEqual(this.componentName, controlsServiceInfo.componentName) && Intrinsics.areEqual(this.panelActivity, controlsServiceInfo.panelActivity)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.userId), this.componentName, this.panelActivity);
    }

    public final Drawable loadIcon() {
        String packageName;
        ComponentName componentName = this.componentName;
        if (componentName == null || (packageName = componentName.getPackageName()) == null) {
            throw new IllegalArgumentException("Package info is missing");
        }
        return IconDrawableFactory.newInstance(this.context).getBadgedIcon(this.mPm.getApplicationInfoAsUser(packageName, 0, this.userId));
    }

    public final CharSequence loadLabel() {
        CharSequence charSequence;
        ComponentName componentName = this.componentName;
        if (componentName == null || (charSequence = this.mPm.getApplicationInfoAsUser(componentName.getPackageName(), 0, this.userId).loadLabel(this.mPm)) == null) {
            charSequence = null;
        }
        if (charSequence != null) {
            return charSequence;
        }
        throw new IllegalArgumentException("Package info is missing");
    }

    public final String toString() {
        return StringsKt__IndentKt.trimIndent("\n            ControlsServiceInfo(serviceInfo=" + this.serviceInfo + ", panelActivity=" + this.panelActivity + ", resolved=" + this.resolved + ")\n        ");
    }
}
