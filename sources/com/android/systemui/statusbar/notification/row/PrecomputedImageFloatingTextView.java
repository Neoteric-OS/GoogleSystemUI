package com.android.systemui.statusbar.notification.row;

import android.text.PrecomputedText;
import android.util.Log;
import com.android.internal.widget.ImageFloatingTextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrecomputedImageFloatingTextView extends ImageFloatingTextView {
    /* JADX WARN: Multi-variable type inference failed */
    public final Runnable setTextAsync(final CharSequence charSequence) {
        final PrecomputedText create = charSequence != null ? PrecomputedText.create(charSequence, getTextMetricsParams()) : null;
        return new Runnable() { // from class: com.android.systemui.statusbar.notification.row.TextPrecomputer$precompute$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    PrecomputedImageFloatingTextView.this.setText(create);
                } catch (IllegalArgumentException e) {
                    Log.wtf("TextPrecomputer", "PrecomputedText setText failed for TextView:" + PrecomputedImageFloatingTextView.this, e);
                    PrecomputedImageFloatingTextView.this.setText(charSequence);
                }
            }
        };
    }
}
