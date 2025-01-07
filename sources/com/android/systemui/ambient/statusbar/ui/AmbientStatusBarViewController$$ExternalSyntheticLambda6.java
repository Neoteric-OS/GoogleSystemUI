package com.android.systemui.ambient.statusbar.ui;

import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AmbientStatusBarViewController$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AmbientStatusBarViewController f$0;

    public /* synthetic */ AmbientStatusBarViewController$$ExternalSyntheticLambda6(AmbientStatusBarViewController ambientStatusBarViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = ambientStatusBarViewController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        AmbientStatusBarViewController ambientStatusBarViewController = this.f$0;
        switch (i) {
            case 0:
                if (obj != null) {
                    throw new ClassCastException();
                }
                ambientStatusBarViewController.getClass();
                throw null;
            case 1:
                ambientStatusBarViewController.getClass();
                ambientStatusBarViewController.updateWifiUnavailableStatusIcon(((WifiNetworkModel) obj) instanceof WifiNetworkModel.Active);
                return;
            case 2:
                ambientStatusBarViewController.mCommunalVisible = ((Boolean) obj).booleanValue();
                ambientStatusBarViewController.updateVisibility$1();
                return;
            default:
                if (obj != null) {
                    throw new ClassCastException();
                }
                ambientStatusBarViewController.getClass();
                throw null;
        }
    }
}
