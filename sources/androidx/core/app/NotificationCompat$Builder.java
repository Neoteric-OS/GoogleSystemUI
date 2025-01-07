package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NotificationCompat$Builder {
    public final boolean mAllowSystemGeneratedContextualActions;
    public final String mChannelId;
    public PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public final Context mContext;
    public Bundle mExtras;
    public final Notification mNotification;
    public final ArrayList mPeople;
    public boolean mSilent;
    public NotificationCompat$BigTextStyle mStyle;
    public final ArrayList mActions = new ArrayList();
    public final ArrayList mPersonList = new ArrayList();
    public final ArrayList mInvisibleActions = new ArrayList();
    public boolean mShowWhen = true;
    public boolean mLocalOnly = false;
    public int mVisibility = 0;

    public NotificationCompat$Builder(Context context, String str) {
        Notification notification = new Notification();
        this.mNotification = notification;
        this.mContext = context;
        this.mChannelId = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.mPeople = new ArrayList();
        this.mAllowSystemGeneratedContextualActions = true;
    }

    public static CharSequence limitCharSequenceLength(CharSequence charSequence) {
        return charSequence == null ? charSequence : charSequence.length() > 5120 ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public final void addAction(CharSequence charSequence, PendingIntent pendingIntent) {
        this.mActions.add(new NotificationCompat$Action(charSequence, pendingIntent));
    }

    public final void addExtras(Bundle bundle) {
        Bundle bundle2 = this.mExtras;
        if (bundle2 == null) {
            this.mExtras = new Bundle(bundle);
        } else {
            bundle2.putAll(bundle);
        }
    }

    public final Notification build() {
        Bundle bundle;
        new ArrayList();
        Bundle bundle2 = new Bundle();
        Context context = this.mContext;
        String str = this.mChannelId;
        Notification.Builder builder = new Notification.Builder(context, str);
        Notification notification = this.mNotification;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(this.mContentTitle).setContentText(this.mContentText).setContentInfo(null).setContentIntent(this.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(0, 0, false);
        builder.setLargeIcon((Icon) null);
        builder.setSubText(null).setUsesChronometer(false).setPriority(0);
        Iterator it = this.mActions.iterator();
        while (it.hasNext()) {
            NotificationCompat$Action notificationCompat$Action = (NotificationCompat$Action) it.next();
            IconCompat iconCompat = notificationCompat$Action.mIcon;
            IconCompat iconCompat2 = notificationCompat$Action.mIcon;
            Notification.Action.Builder builder2 = new Notification.Action.Builder(iconCompat2 != null ? iconCompat2.toIcon$1() : null, notificationCompat$Action.title, notificationCompat$Action.actionIntent);
            Bundle bundle3 = notificationCompat$Action.mExtras != null ? new Bundle(notificationCompat$Action.mExtras) : new Bundle();
            boolean z = notificationCompat$Action.mAllowGeneratedReplies;
            bundle3.putBoolean("android.support.allowGeneratedReplies", z);
            builder2.setAllowGeneratedReplies(z);
            bundle3.putInt("android.support.action.semanticAction", 0);
            builder2.setSemanticAction(0);
            builder2.setContextual(false);
            builder2.setAuthenticationRequired(false);
            bundle3.putBoolean("android.support.action.showsUserInterface", notificationCompat$Action.mShowsUserInterface);
            builder2.addExtras(bundle3);
            builder.addAction(builder2.build());
        }
        Bundle bundle4 = this.mExtras;
        if (bundle4 != null) {
            bundle2.putAll(bundle4);
        }
        builder.setShowWhen(this.mShowWhen);
        builder.setLocalOnly(this.mLocalOnly);
        builder.setGroup(null);
        builder.setSortKey(null);
        builder.setGroupSummary(false);
        builder.setCategory(null);
        builder.setColor(0);
        builder.setVisibility(this.mVisibility);
        builder.setPublicVersion(null);
        builder.setSound(notification.sound, notification.audioAttributes);
        ArrayList arrayList = this.mPeople;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                builder.addPerson((String) it2.next());
            }
        }
        if (this.mInvisibleActions.size() > 0) {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            Bundle bundle5 = this.mExtras.getBundle("android.car.EXTENSIONS");
            if (bundle5 == null) {
                bundle5 = new Bundle();
            }
            Bundle bundle6 = new Bundle(bundle5);
            Bundle bundle7 = new Bundle();
            for (int i = 0; i < this.mInvisibleActions.size(); i++) {
                String num = Integer.toString(i);
                NotificationCompat$Action notificationCompat$Action2 = (NotificationCompat$Action) this.mInvisibleActions.get(i);
                Bundle bundle8 = new Bundle();
                IconCompat iconCompat3 = notificationCompat$Action2.mIcon;
                IconCompat iconCompat4 = notificationCompat$Action2.mIcon;
                bundle8.putInt("icon", iconCompat4 != null ? iconCompat4.getResId() : 0);
                bundle8.putCharSequence("title", notificationCompat$Action2.title);
                bundle8.putParcelable("actionIntent", notificationCompat$Action2.actionIntent);
                Bundle bundle9 = notificationCompat$Action2.mExtras != null ? new Bundle(notificationCompat$Action2.mExtras) : new Bundle();
                bundle9.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action2.mAllowGeneratedReplies);
                bundle8.putBundle("extras", bundle9);
                bundle8.putParcelableArray("remoteInputs", null);
                bundle8.putBoolean("showsUserInterface", notificationCompat$Action2.mShowsUserInterface);
                bundle8.putInt("semanticAction", 0);
                bundle7.putBundle(num, bundle8);
            }
            bundle5.putBundle("invisible_actions", bundle7);
            bundle6.putBundle("invisible_actions", bundle7);
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle5);
            bundle2.putBundle("android.car.EXTENSIONS", bundle6);
        }
        builder.setExtras(this.mExtras);
        builder.setRemoteInputHistory(null);
        builder.setBadgeIconType(0);
        builder.setSettingsText(null);
        builder.setShortcutId(null);
        builder.setTimeoutAfter(0L);
        builder.setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty(str)) {
            builder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        Iterator it3 = this.mPersonList.iterator();
        if (it3.hasNext()) {
            throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it3);
        }
        builder.setAllowSystemGeneratedContextualActions(this.mAllowSystemGeneratedContextualActions);
        builder.setBubbleMetadata(null);
        if (this.mSilent) {
            builder.setVibrate(null);
            builder.setSound(null);
            int i2 = notification.defaults & (-4);
            notification.defaults = i2;
            builder.setDefaults(i2);
            if (TextUtils.isEmpty(null)) {
                builder.setGroup("silent");
            }
            builder.setGroupAlertBehavior(1);
        }
        NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = this.mStyle;
        if (notificationCompat$BigTextStyle != null) {
            new Notification.BigTextStyle(builder).setBigContentTitle(null).bigText(notificationCompat$BigTextStyle.mBigText);
        }
        Notification build = builder.build();
        if (notificationCompat$BigTextStyle != null) {
            this.mStyle.getClass();
        }
        if (notificationCompat$BigTextStyle != null && (bundle = build.extras) != null) {
            bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", "androidx.core.app.NotificationCompat$BigTextStyle");
        }
        return build;
    }

    public final void setFlag(int i, boolean z) {
        if (z) {
            Notification notification = this.mNotification;
            notification.flags = i | notification.flags;
        } else {
            Notification notification2 = this.mNotification;
            notification2.flags = (~i) & notification2.flags;
        }
    }

    public final void setStyle(NotificationCompat$BigTextStyle notificationCompat$BigTextStyle) {
        if (this.mStyle != notificationCompat$BigTextStyle) {
            this.mStyle = notificationCompat$BigTextStyle;
            if (notificationCompat$BigTextStyle.mBuilder != this) {
                notificationCompat$BigTextStyle.mBuilder = this;
                setStyle(notificationCompat$BigTextStyle);
            }
        }
    }
}
