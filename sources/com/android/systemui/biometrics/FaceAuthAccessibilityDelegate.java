package com.android.systemui.biometrics;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceAuthAccessibilityDelegate extends View.AccessibilityDelegate {
    public final DeviceEntryFaceAuthInteractor faceAuthInteractor;
    public final Resources resources;

    public FaceAuthAccessibilityDelegate(Resources resources, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        this.resources = resources;
        this.faceAuthInteractor = deviceEntryFaceAuthInteractor;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        if (this.faceAuthInteractor.canFaceAuthRun()) {
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), this.resources.getString(R.string.retry_face)));
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (i != AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId()) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        this.faceAuthInteractor.onAccessibilityAction();
        return true;
    }
}
