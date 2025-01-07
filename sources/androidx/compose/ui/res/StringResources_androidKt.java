package androidx.compose.ui.res;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class StringResources_androidKt {
    public static final String stringResource(int i, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration);
        return ((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)).getResources().getString(i);
    }
}
