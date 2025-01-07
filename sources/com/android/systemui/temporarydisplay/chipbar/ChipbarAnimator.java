package com.android.systemui.temporarydisplay.chipbar;

import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.util.ConvenienceExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarAnimator {
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.coroutines.jvm.internal.RestrictedSuspendLambda, kotlin.jvm.functions.Function2] */
    public static void forceDisplayView(View view) {
        view.setAlpha(1.0f);
        if (view instanceof ViewGroup) {
            SequenceBuilderIterator it = SequencesKt__SequenceBuilderKt.iterator(ConvenienceExtensionsKt.getChildren((ViewGroup) view).$block$inlined);
            while (it.hasNext()) {
                View view2 = (View) it.next();
                Intrinsics.checkNotNull(view2);
                forceDisplayView(view2);
            }
        }
    }
}
