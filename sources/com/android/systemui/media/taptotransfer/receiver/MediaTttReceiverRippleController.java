package com.android.systemui.media.taptotransfer.receiver;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.view.WindowManager;
import com.android.settingslib.Utils;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTttReceiverRippleController {
    public final Context context;
    public float maxRippleHeight;
    public float maxRippleWidth;
    public final MediaTttReceiverLogger mediaTttReceiverLogger;
    public final WindowManager windowManager;

    public MediaTttReceiverRippleController(Context context, WindowManager windowManager, MediaTttReceiverLogger mediaTttReceiverLogger) {
        this.context = context;
        this.windowManager = windowManager;
        this.mediaTttReceiverLogger = mediaTttReceiverLogger;
    }

    public static final void access$layoutIconRipple(MediaTttReceiverRippleController mediaTttReceiverRippleController, ReceiverChipRippleView receiverChipRippleView) {
        Rect bounds = mediaTttReceiverRippleController.windowManager.getCurrentWindowMetrics().getBounds();
        float height = bounds.height();
        float width = bounds.width();
        float dimensionPixelSize = mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver) * 0.8f;
        RippleShader rippleShader = receiverChipRippleView.rippleShader;
        if (rippleShader == null) {
            rippleShader = null;
        }
        RippleShader.RippleSize rippleSize = rippleShader.rippleSize;
        rippleSize.getClass();
        rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, dimensionPixelSize, dimensionPixelSize));
        receiverChipRippleView.setCenter(width * 0.5f, (height - (mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver) * 0.5f)) - mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_receiver_icon_bottom_margin));
        receiverChipRippleView.setColor(ColorStateList.valueOf(Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColorAccent, 0, mediaTttReceiverRippleController.context)).withLStar(95.0f).getDefaultColor(), 70);
    }

    public final void layoutRipple(ReceiverChipRippleView receiverChipRippleView, boolean z) {
        Rect bounds = this.windowManager.getCurrentWindowMetrics().getBounds();
        float height = bounds.height();
        float width = bounds.width();
        if (z) {
            this.maxRippleHeight = height * 2.0f;
            this.maxRippleWidth = 2.0f * width;
        } else {
            this.maxRippleHeight = this.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver) * 4.0f;
            this.maxRippleWidth = this.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver) * 4.0f;
        }
        float f = this.maxRippleWidth;
        float f2 = this.maxRippleHeight;
        RippleShader rippleShader = receiverChipRippleView.rippleShader;
        if (rippleShader == null) {
            rippleShader = null;
        }
        RippleShader.RippleSize rippleSize = rippleShader.rippleSize;
        rippleSize.getClass();
        rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, f, f2));
        receiverChipRippleView.setCenter(width * 0.5f, height);
        receiverChipRippleView.setColor(ColorStateList.valueOf(Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColorAccent, 0, this.context)).withLStar(95.0f).getDefaultColor(), 70);
    }
}
