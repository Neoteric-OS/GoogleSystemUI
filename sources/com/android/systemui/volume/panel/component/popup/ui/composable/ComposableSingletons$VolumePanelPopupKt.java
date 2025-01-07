package com.android.systemui.volume.panel.component.popup.ui.composable;

import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ComposableSingletons$VolumePanelPopupKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f55lambda1 = new ComposableLambdaImpl(-331316521, false, new Function2() { // from class: com.android.systemui.volume.panel.component.popup.ui.composable.ComposableSingletons$VolumePanelPopupKt$lambda-1$1
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
            IconKt.m213Iconww6aTOc(PainterResources_androidKt.painterResource(R.drawable.ic_close, 0, composer), StringResources_androidKt.stringResource(R.string.accessibility_desc_close, composer), (Modifier) null, 0L, composer, 8, 12);
            return Unit.INSTANCE;
        }
    });
}
