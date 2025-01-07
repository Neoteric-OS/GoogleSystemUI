package com.android.systemui.media.taptotransfer.common;

import android.content.Context;
import android.content.pm.PackageManager;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.media.taptotransfer.common.MediaTttIcon;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaTttUtils$Companion {
    public static IconInfo getIconInfoFromPackageName(Context context, String str, boolean z, Function0 function0) {
        if (str != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                String obj = packageManager.getApplicationInfo(str, PackageManager.ApplicationInfoFlags.of(0L)).loadLabel(packageManager).toString();
                return new IconInfo(z ? new ContentDescription.Loaded(context.getString(R.string.media_transfer_receiver_content_description_with_app_name, obj)) : new ContentDescription.Loaded(obj), new MediaTttIcon.Loaded(packageManager.getApplicationIcon(str)), null, true);
            } catch (PackageManager.NameNotFoundException unused) {
                function0.invoke();
            }
        }
        return new IconInfo(z ? new ContentDescription.Resource(R.string.media_transfer_receiver_content_description_unknown_app) : new ContentDescription.Resource(R.string.media_output_dialog_unknown_launch_app_name), new MediaTttIcon.Resource(), Integer.valueOf(android.R.^attr-private.materialColorOnSecondaryFixed), false);
    }
}
