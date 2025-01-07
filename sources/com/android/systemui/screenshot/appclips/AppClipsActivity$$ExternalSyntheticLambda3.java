package com.android.systemui.screenshot.appclips;

import android.widget.CompoundButton;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AppClipsActivity$$ExternalSyntheticLambda3 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ AppClipsActivity f$0;

    public /* synthetic */ AppClipsActivity$$ExternalSyntheticLambda3(AppClipsActivity appClipsActivity) {
        this.f$0 = appClipsActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.f$0.mBacklinksDataTextView.setVisibility(z ? 0 : 8);
    }
}
