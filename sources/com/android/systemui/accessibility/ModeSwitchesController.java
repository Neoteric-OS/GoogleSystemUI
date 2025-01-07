package com.android.systemui.accessibility;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.widget.ImageView;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.systemui.accessibility.MagnificationModeSwitch;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ModeSwitchesController implements MagnificationModeSwitch.ClickListener {
    public MagnificationImpl$$ExternalSyntheticLambda0 mClickListenerDelegate;
    public final DisplayIdIndexSupplier mSwitchSupplier;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SwitchSupplier extends DisplayIdIndexSupplier {
        public final ModeSwitchesController$$ExternalSyntheticLambda0 mClickListener;
        public final Context mContext;
        public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;

        public SwitchSupplier(Context context, DisplayManager displayManager, ModeSwitchesController$$ExternalSyntheticLambda0 modeSwitchesController$$ExternalSyntheticLambda0, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
            super(displayManager);
            this.mContext = context;
            this.mClickListener = modeSwitchesController$$ExternalSyntheticLambda0;
            this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        }

        @Override // com.android.systemui.accessibility.DisplayIdIndexSupplier
        public final Object createInstance(Display display) {
            Context createWindowContext = this.mContext.createWindowContext(display, 2039, null);
            ImageView imageView = new ImageView(createWindowContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setClickable(true);
            imageView.setFocusable(true);
            imageView.setAlpha(0.0f);
            return new MagnificationModeSwitch(createWindowContext, imageView, new SfVsyncFrameCallbackProvider(), this.mClickListener, this.mViewCaptureAwareWindowManager);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.accessibility.ModeSwitchesController$$ExternalSyntheticLambda0] */
    public ModeSwitchesController(Context context, DisplayManager displayManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mSwitchSupplier = new SwitchSupplier(context, displayManager, new MagnificationModeSwitch.ClickListener() { // from class: com.android.systemui.accessibility.ModeSwitchesController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.accessibility.MagnificationModeSwitch.ClickListener
            public final void onClick(int i) {
                ModeSwitchesController.this.onClick(i);
            }
        }, viewCaptureAwareWindowManager);
    }

    @Override // com.android.systemui.accessibility.MagnificationModeSwitch.ClickListener
    public final void onClick(int i) {
        MagnificationImpl$$ExternalSyntheticLambda0 magnificationImpl$$ExternalSyntheticLambda0 = this.mClickListenerDelegate;
        if (magnificationImpl$$ExternalSyntheticLambda0 != null) {
            magnificationImpl$$ExternalSyntheticLambda0.onClick(i);
        }
    }

    public ModeSwitchesController(DisplayIdIndexSupplier displayIdIndexSupplier) {
        this.mSwitchSupplier = displayIdIndexSupplier;
    }
}
