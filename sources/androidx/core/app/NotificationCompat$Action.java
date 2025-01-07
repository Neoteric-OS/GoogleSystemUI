package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationCompat$Action {
    public final PendingIntent actionIntent;
    public final boolean mAllowGeneratedReplies;
    public final Bundle mExtras;
    public IconCompat mIcon;
    public final boolean mShowsUserInterface;
    public final CharSequence title;

    public NotificationCompat$Action(CharSequence charSequence, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        this.mShowsUserInterface = true;
        this.mIcon = null;
        this.title = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
        this.actionIntent = pendingIntent;
        this.mExtras = bundle;
        this.mAllowGeneratedReplies = true;
        this.mShowsUserInterface = true;
    }
}
