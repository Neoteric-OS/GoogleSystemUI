package com.android.systemui.common.ui.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.res.StringResources_androidKt;
import com.android.systemui.common.shared.model.ContentDescription;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ContentDescriptionKt {
    public static final String load(ContentDescription contentDescription, Composer composer) {
        String stringResource;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(838940219);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (contentDescription instanceof ContentDescription.Loaded) {
            stringResource = ((ContentDescription.Loaded) contentDescription).description;
        } else {
            if (!(contentDescription instanceof ContentDescription.Resource)) {
                throw new NoWhenBranchMatchedException();
            }
            stringResource = StringResources_androidKt.stringResource(((ContentDescription.Resource) contentDescription).res, composerImpl);
        }
        composerImpl.end(false);
        return stringResource;
    }
}
