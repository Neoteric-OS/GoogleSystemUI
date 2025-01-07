package com.android.systemui.keyboard.shortcut.ui.view;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposableSingletons$ShortcutHelperActivityKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f35lambda1 = new ComposableLambdaImpl(135843382, false, new Function2() { // from class: com.android.systemui.keyboard.shortcut.ui.view.ComposableSingletons$ShortcutHelperActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Composer composer = (Composer) obj;
            if ((((Number) obj2).intValue() & 11) == 2) {
                ComposerImpl composerImpl = (ComposerImpl) composer;
                if (composerImpl.getSkipping()) {
                    composerImpl.skipToGroupEnd();
                    return Unit.INSTANCE;
                }
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            BoxKt.Box(SizeKt.m114sizeVpY3zN4(Modifier.Companion.$$INSTANCE, 32, 4), composer, 6);
            return Unit.INSTANCE;
        }
    });
}
