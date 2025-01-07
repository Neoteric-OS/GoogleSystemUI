package com.android.systemui.screenshot.resources;

import android.content.Context;
import com.android.wm.shell.R;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Messages {
    public final Context context;
    public final Lazy savingScreenshotAnnouncement$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.screenshot.resources.Messages$savingScreenshotAnnouncement$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            String string = Messages.this.context.getResources().getString(R.string.screenshot_saving_title);
            if (string != null) {
                return string;
            }
            throw new IllegalArgumentException("Required value was null.");
        }
    });
    public final Lazy savingToWorkProfileAnnouncement$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.screenshot.resources.Messages$savingToWorkProfileAnnouncement$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            String string = Messages.this.context.getResources().getString(R.string.screenshot_saving_work_profile_title);
            if (string != null) {
                return string;
            }
            throw new IllegalArgumentException("Required value was null.");
        }
    });
    public final Lazy savingToPrivateProfileAnnouncement$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.screenshot.resources.Messages$savingToPrivateProfileAnnouncement$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            String string = Messages.this.context.getResources().getString(R.string.screenshot_saving_private_profile);
            if (string != null) {
                return string;
            }
            throw new IllegalArgumentException("Required value was null.");
        }
    });

    public Messages(Context context) {
        this.context = context;
    }
}
