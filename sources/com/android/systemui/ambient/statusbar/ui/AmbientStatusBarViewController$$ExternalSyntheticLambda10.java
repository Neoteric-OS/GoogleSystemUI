package com.android.systemui.ambient.statusbar.ui;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AmbientStatusBarViewController$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ AmbientStatusBarViewController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ AmbientStatusBarViewController$$ExternalSyntheticLambda10(AmbientStatusBarViewController ambientStatusBarViewController, boolean z, int i, String str) {
        this.f$0 = ambientStatusBarViewController;
        this.f$1 = z;
        this.f$2 = i;
        this.f$3 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        AmbientStatusBarViewController ambientStatusBarViewController = this.f$0;
        boolean z = this.f$1;
        int i = this.f$2;
        String str2 = this.f$3;
        if (ambientStatusBarViewController.mIsAttached) {
            int i2 = AmbientStatusBarView.$r8$clinit;
            switch (i) {
                case 0:
                    str = "notifications";
                    break;
                case 1:
                    str = "wifi_unavailable";
                    break;
                case 2:
                    str = "alarm_set";
                    break;
                case 3:
                    str = "camera_disabled";
                    break;
                case 4:
                    str = "mic_disabled";
                    break;
                case 5:
                    str = "mic_camera_disabled";
                    break;
                case 6:
                    str = "priority_mode_on";
                    break;
                case 7:
                    str = "assistant_attention_active";
                    break;
                case 8:
                    str = "location_active";
                    break;
                default:
                    str = i + "(unknown)";
                    break;
            }
            ambientStatusBarViewController.mLogger.logShowOrHideStatusBarItem(str, z);
            AmbientStatusBarView ambientStatusBarView = (AmbientStatusBarView) ambientStatusBarViewController.mView;
            View view = (View) ambientStatusBarView.mStatusIcons.get(Integer.valueOf(i));
            if (view == null) {
                return;
            }
            if (z && str2 != null) {
                view.setContentDescription(str2);
            }
            int i3 = 8;
            view.setVisibility(z ? 0 : 8);
            ViewGroup viewGroup = ambientStatusBarView.mSystemStatusViewGroup;
            int i4 = 0;
            while (true) {
                if (i4 < ambientStatusBarView.mSystemStatusViewGroup.getChildCount()) {
                    if (ambientStatusBarView.mSystemStatusViewGroup.getChildAt(i4).getVisibility() == 0) {
                        i3 = 0;
                    } else {
                        i4++;
                    }
                }
            }
            viewGroup.setVisibility(i3);
        }
    }
}
