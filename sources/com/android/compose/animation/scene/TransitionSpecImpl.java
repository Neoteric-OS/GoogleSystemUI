package com.android.compose.animation.scene;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionSpecImpl {
    public final ContentKey from;
    public final TransitionKey key;
    public final Lambda previewTransformationSpec;
    public final Lambda reversePreviewTransformationSpec;
    public final ContentKey to;
    public final Lambda transformationSpec;

    /* JADX WARN: Multi-variable type inference failed */
    public TransitionSpecImpl(TransitionKey transitionKey, ContentKey contentKey, ContentKey contentKey2, Function0 function0, Function0 function02, Function0 function03) {
        this.key = transitionKey;
        this.from = contentKey;
        this.to = contentKey2;
        this.previewTransformationSpec = (Lambda) function0;
        this.reversePreviewTransformationSpec = (Lambda) function02;
        this.transformationSpec = (Lambda) function03;
    }
}
