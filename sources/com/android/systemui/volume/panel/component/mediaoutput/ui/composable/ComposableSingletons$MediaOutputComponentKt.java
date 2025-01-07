package com.android.systemui.volume.panel.component.mediaoutput.ui.composable;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import com.android.systemui.common.ui.compose.ColorKt;
import com.android.systemui.common.ui.compose.IconKt;
import com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ComposableSingletons$MediaOutputComponentKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f53lambda1 = new ComposableLambdaImpl(465697057, false, new Function4() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.ComposableSingletons$MediaOutputComponentKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            Composer composer = (Composer) obj3;
            ((Number) obj4).intValue();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            SpacerKt.Spacer(composer, BackgroundKt.m25backgroundbw27NRU(SizeKt.FillWholeMaxSize, ColorKt.toColor(((DeviceIconViewModel) obj2).getBackgroundColor(), composer), RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(12)));
            return Unit.INSTANCE;
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static final ComposableLambdaImpl f54lambda2 = new ComposableLambdaImpl(1530310922, false, new Function4() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.composable.ComposableSingletons$MediaOutputComponentKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            DeviceIconViewModel deviceIconViewModel = (DeviceIconViewModel) obj2;
            Composer composer = (Composer) obj3;
            ((Number) obj4).intValue();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            IconKt.m793IconFNF3uiM(deviceIconViewModel.getIcon(), PaddingKt.m98padding3ABfNKs(Modifier.Companion.$$INSTANCE, 12).then(SizeKt.FillWholeMaxSize), ColorKt.toColor(deviceIconViewModel.getIconColor(), composer), composer, 48, 0);
            return Unit.INSTANCE;
        }
    });
}
