package com.android.systemui.statusbar.policy;

import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.settings.brightness.BrightnessMirrorHandler$brightnessMirrorListener$1;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda2;
import com.android.wm.shell.R;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BrightnessMirrorController implements CallbackController {
    public FrameLayout mBrightnessMirror;
    public int mBrightnessMirrorBackgroundPadding;
    public final NotificationShadeDepthController mDepthController;
    public final ShadeViewController mNotificationPanel;
    public final NotificationShadeWindowView mStatusBarWindow;
    public BrightnessSliderController mToggleSliderController;
    public final BrightnessSliderController.Factory mToggleSliderFactory;
    public final CentralSurfacesImpl$$ExternalSyntheticLambda2 mVisibilityCallback;
    public final ArraySet mBrightnessMirrorListeners = new ArraySet();
    public final int[] mInt2Cache = new int[2];
    public final int mLastBrightnessSliderWidth = -1;

    public BrightnessMirrorController(NotificationShadeWindowView notificationShadeWindowView, ShadeViewController shadeViewController, NotificationShadeDepthController notificationShadeDepthController, BrightnessSliderController.Factory factory, CentralSurfacesImpl$$ExternalSyntheticLambda2 centralSurfacesImpl$$ExternalSyntheticLambda2) {
        this.mStatusBarWindow = notificationShadeWindowView;
        this.mToggleSliderFactory = factory;
        FrameLayout frameLayout = (FrameLayout) notificationShadeWindowView.findViewById(R.id.brightness_mirror_container);
        this.mBrightnessMirror = frameLayout;
        BrightnessSliderController create = factory.create(frameLayout.getContext(), this.mBrightnessMirror);
        create.init$9();
        this.mBrightnessMirror.addView(create.mView, -1, -2);
        this.mToggleSliderController = create;
        this.mNotificationPanel = shadeViewController;
        this.mDepthController = notificationShadeDepthController;
        shadeViewController.setAlphaChangeAnimationEndAction(new BrightnessMirrorController$$ExternalSyntheticLambda0(this));
        this.mVisibilityCallback = centralSurfacesImpl$$ExternalSyntheticLambda2;
        updateResources();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        BrightnessMirrorHandler$brightnessMirrorListener$1 brightnessMirrorHandler$brightnessMirrorListener$1 = (BrightnessMirrorHandler$brightnessMirrorListener$1) obj;
        Objects.requireNonNull(brightnessMirrorHandler$brightnessMirrorListener$1);
        this.mBrightnessMirrorListeners.add(brightnessMirrorHandler$brightnessMirrorListener$1);
    }

    public final void reinflate$1() {
        FrameLayout frameLayout = this.mBrightnessMirror;
        NotificationShadeWindowView notificationShadeWindowView = this.mStatusBarWindow;
        int indexOfChild = notificationShadeWindowView.indexOfChild(frameLayout);
        notificationShadeWindowView.removeView(this.mBrightnessMirror);
        FrameLayout frameLayout2 = (FrameLayout) LayoutInflater.from(notificationShadeWindowView.getContext()).inflate(R.layout.brightness_mirror_container, (ViewGroup) notificationShadeWindowView, false);
        this.mBrightnessMirror = frameLayout2;
        BrightnessSliderController create = this.mToggleSliderFactory.create(frameLayout2.getContext(), this.mBrightnessMirror);
        create.init$9();
        this.mBrightnessMirror.addView(create.mView, -1, -2);
        this.mToggleSliderController = create;
        notificationShadeWindowView.addView(this.mBrightnessMirror, indexOfChild);
        updateResources();
        for (int i = 0; i < this.mBrightnessMirrorListeners.size(); i++) {
            ((BrightnessMirrorHandler$brightnessMirrorListener$1) this.mBrightnessMirrorListeners.valueAt(i)).this$0.updateBrightnessMirror();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mBrightnessMirrorListeners.remove((BrightnessMirrorHandler$brightnessMirrorListener$1) obj);
    }

    public final void updateResources() {
        int dimensionPixelSize = this.mBrightnessMirror.getResources().getDimensionPixelSize(R.dimen.rounded_slider_background_padding);
        this.mBrightnessMirrorBackgroundPadding = dimensionPixelSize;
        this.mBrightnessMirror.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }
}
