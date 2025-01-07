package com.android.systemui.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConvenienceExtensionsKt {
    public static final Rect getBoundsOnScreen(View view) {
        Rect rect = new Rect();
        view.getBoundsOnScreen(rect);
        return rect;
    }

    public static final SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 getChildren(ViewGroup viewGroup) {
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new ConvenienceExtensionsKt$children$1(viewGroup, null));
    }

    public static final Lazy toKotlinLazy(final dagger.Lazy lazy) {
        return LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.util.ConvenienceExtensionsKt$toKotlinLazy$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return dagger.Lazy.this.get();
            }
        });
    }
}
