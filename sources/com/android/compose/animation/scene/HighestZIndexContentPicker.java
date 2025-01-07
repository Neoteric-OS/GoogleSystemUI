package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HighestZIndexContentPicker implements ElementContentPicker {
    public static final HighestZIndexContentPicker INSTANCE = new HighestZIndexContentPicker();

    @Override // com.android.compose.animation.scene.ElementContentPicker
    public final ContentKey contentDuringTransition(TransitionState.Transition transition, float f, float f2) {
        return f > f2 ? transition.fromContent : transition.toContent;
    }
}
