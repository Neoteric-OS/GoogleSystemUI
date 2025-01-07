package com.android.systemui.unfold.util;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.provider.Settings;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScaleAwareTransitionProgressProvider implements UnfoldTransitionProgressProvider {
    public final ContentResolver contentResolver;
    public final ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider;

    public ScaleAwareTransitionProgressProvider(UnfoldTransitionProgressProvider unfoldTransitionProgressProvider, ContentResolver contentResolver) {
        Float floatOrNull;
        this.contentResolver = contentResolver;
        ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider = new ScopedUnfoldTransitionProgressProvider(unfoldTransitionProgressProvider);
        this.scopedUnfoldTransitionProgressProvider = scopedUnfoldTransitionProgressProvider;
        contentResolver.registerContentObserver(Settings.Global.getUriFor("animator_duration_scale"), false, new ContentObserver() { // from class: com.android.systemui.unfold.util.ScaleAwareTransitionProgressProvider$animatorDurationScaleObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                Float floatOrNull2;
                ScaleAwareTransitionProgressProvider scaleAwareTransitionProgressProvider = ScaleAwareTransitionProgressProvider.this;
                String string = Settings.Global.getString(scaleAwareTransitionProgressProvider.contentResolver, "animator_duration_scale");
                scaleAwareTransitionProgressProvider.scopedUnfoldTransitionProgressProvider.setReadyToHandleTransition(!(((string == null || (floatOrNull2 = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string)) == null) ? 1.0f : floatOrNull2.floatValue()) == 0.0f));
            }
        });
        String string = Settings.Global.getString(contentResolver, "animator_duration_scale");
        scopedUnfoldTransitionProgressProvider.setReadyToHandleTransition(!(((string == null || (floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string)) == null) ? 1.0f : floatOrNull.floatValue()) == 0.0f));
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.scopedUnfoldTransitionProgressProvider.listeners.add((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.scopedUnfoldTransitionProgressProvider.listeners.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }
}
