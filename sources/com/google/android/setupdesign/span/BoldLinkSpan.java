package com.google.android.setupdesign.span;

import android.content.Context;
import android.text.TextPaint;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BoldLinkSpan extends LinkSpan {
    static final int BOLD_TEXT_ADJUSTMENT = 300;
    public final Context context;

    public BoldLinkSpan(Context context) {
        this.context = context;
    }

    @Override // com.google.android.setupdesign.span.LinkSpan, android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setFakeBoldText(this.context.getResources().getConfiguration().fontWeightAdjustment == BOLD_TEXT_ADJUSTMENT);
        textPaint.setUnderlineText(true);
    }
}
