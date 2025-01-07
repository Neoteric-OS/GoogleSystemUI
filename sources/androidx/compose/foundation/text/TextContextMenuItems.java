package androidx.compose.foundation.text;

import android.R;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.res.StringResources_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum TextContextMenuItems {
    Cut(R.string.cut),
    Copy(R.string.copy),
    Paste(R.string.paste),
    SelectAll(R.string.selectAll);

    private final int stringId;

    TextContextMenuItems(int i) {
        this.stringId = i;
    }

    public final String resolvedString(ComposerImpl composerImpl) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return StringResources_androidKt.stringResource(this.stringId, composerImpl);
    }
}
