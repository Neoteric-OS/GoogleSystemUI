package com.android.keyguard;

import android.widget.ImageView;
import com.android.settingslib.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardSimInputView extends KeyguardPinBasedInputView {
    public KeyguardEsimArea disableESimButton;
    public ImageView simImageView;

    @Override // com.android.keyguard.KeyguardPinBasedInputView, com.android.keyguard.KeyguardAbsKeyInputView, com.android.keyguard.KeyguardInputView, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.simImageView = (ImageView) findViewById(R.id.keyguard_sim);
        this.disableESimButton = (KeyguardEsimArea) findViewById(R.id.keyguard_esim_area);
        super.onFinishInflate();
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputView
    public final void reloadColors() {
        super.reloadColors();
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorTertiaryFixed, 0, getContext());
        ImageView imageView = this.simImageView;
        if (imageView != null) {
            imageView.getDrawable().setTint(colorAttrDefaultColor);
        }
    }

    public final void setESimLocked(int i, boolean z) {
        KeyguardEsimArea keyguardEsimArea = this.disableESimButton;
        if (keyguardEsimArea != null) {
            keyguardEsimArea.mSubscriptionId = i;
        }
        if (keyguardEsimArea != null) {
            keyguardEsimArea.setVisibility(z ? 0 : 8);
        }
        ImageView imageView = this.simImageView;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(z ? 8 : 0);
    }
}
