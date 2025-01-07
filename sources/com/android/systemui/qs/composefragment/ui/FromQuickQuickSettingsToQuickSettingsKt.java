package com.android.systemui.qs.composefragment.ui;

import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.TransitionBuilderImpl;
import com.android.compose.animation.scene.transformation.AnchoredTranslate;
import com.android.systemui.qs.shared.ui.ElementKeys;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FromQuickQuickSettingsToQuickSettingsKt {
    public static final void quickQuickSettingsToQuickSettings(TransitionBuilderImpl transitionBuilderImpl) {
        TransitionBuilderImpl.fractionRange$default(transitionBuilderImpl, Float.valueOf(0.5f), null, null, new Function1() { // from class: com.android.systemui.qs.composefragment.ui.FromQuickQuickSettingsToQuickSettingsKt$quickQuickSettingsToQuickSettings$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((TransitionBuilderImpl) obj).fade(ElementKeys.QuickSettingsContent);
                return Unit.INSTANCE;
            }
        }, 6);
        TransitionBuilderImpl.fractionRange$default(transitionBuilderImpl, Float.valueOf(0.9f), null, null, new Function1() { // from class: com.android.systemui.qs.composefragment.ui.FromQuickQuickSettingsToQuickSettingsKt$quickQuickSettingsToQuickSettings$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((TransitionBuilderImpl) obj).fade(ElementKeys.FooterActions);
                return Unit.INSTANCE;
            }
        }, 6);
        ElementKey elementKey = ElementKeys.QuickSettingsContent;
        ElementKey elementKey2 = ElementKeys.GridAnchor;
        transitionBuilderImpl.getClass();
        transitionBuilderImpl.transformation(new AnchoredTranslate(elementKey, elementKey2));
    }
}
