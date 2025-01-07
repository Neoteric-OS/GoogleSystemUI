package com.android.systemui.clipboardoverlay;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardOverlayUtils {
    public final TextClassifier mTextClassifier;

    public ClipboardOverlayUtils(TextClassificationManager textClassificationManager) {
        this.mTextClassifier = textClassificationManager.getTextClassifier();
    }
}
