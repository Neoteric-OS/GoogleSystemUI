package com.android.systemui.bouncer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.keyguard.BouncerKeyguardMessageArea;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageView extends LinearLayout {
    public KeyguardMessageAreaController primaryMessage;
    public BouncerKeyguardMessageArea primaryMessageView;
    public KeyguardMessageAreaController secondaryMessage;
    public BouncerKeyguardMessageArea secondaryMessageView;

    public BouncerMessageView(Context context) {
        super(context);
        LinearLayout.inflate(getContext(), R.layout.bouncer_message_view, this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.primaryMessageView = (BouncerKeyguardMessageArea) findViewById(R.id.bouncer_primary_message_area);
        this.secondaryMessageView = (BouncerKeyguardMessageArea) findViewById(R.id.bouncer_secondary_message_area);
    }

    public BouncerMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LinearLayout.inflate(getContext(), R.layout.bouncer_message_view, this);
    }
}
