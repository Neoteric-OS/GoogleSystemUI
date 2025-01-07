package com.android.systemui.statusbar.notification.icon;

import android.app.Notification;
import android.app.Person;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.widget.ImageView;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.NotificationContentDescription;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.wm.shell.R;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.collections.EmptySet;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconManager {
    public final CoroutineContext bgCoroutineContext;
    public final IconManager$entryListener$1 entryListener;
    public final IconBuilder iconBuilder;
    public final LauncherApps launcherApps;
    public final ConcurrentHashMap launcherPeopleAvatarIconJobs;
    public final CoroutineContext mainCoroutineContext;
    public final CommonNotifCollection notifCollection;
    public final IconManager$sensitivityListener$1 sensitivityListener;
    public Set unimportantConversationKeys = EmptySet.INSTANCE;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.notification.icon.IconManager$entryListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.statusbar.notification.icon.IconManager$sensitivityListener$1] */
    public IconManager(CommonNotifCollection commonNotifCollection, LauncherApps launcherApps, IconBuilder iconBuilder, CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        this.notifCollection = commonNotifCollection;
        this.launcherApps = launcherApps;
        this.iconBuilder = iconBuilder;
        new ConcurrentHashMap();
        this.entryListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.icon.IconManager$entryListener$1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryCleanUp(NotificationEntry notificationEntry) {
                notificationEntry.mOnSensitivityChangedListeners.remove(IconManager.this.sensitivityListener);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryInit(NotificationEntry notificationEntry) {
                notificationEntry.mOnSensitivityChangedListeners.addIfAbsent(IconManager.this.sensitivityListener);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onRankingApplied() {
                IconManager.this.recalculateForImportantConversationChange();
            }
        };
        this.sensitivityListener = new NotificationEntry.OnSensitivityChangedListener() { // from class: com.android.systemui.statusbar.notification.icon.IconManager$sensitivityListener$1
            @Override // com.android.systemui.statusbar.notification.collection.NotificationEntry.OnSensitivityChangedListener
            public final void onSensitivityChanged(NotificationEntry notificationEntry) {
                IconManager iconManager = IconManager.this;
                iconManager.getClass();
                try {
                    iconManager.updateIcons(notificationEntry, false);
                } catch (InflationException e) {
                    Log.e("IconManager", "Unable to update icon", e);
                }
            }
        };
    }

    public final void createIcons(NotificationEntry notificationEntry) {
        IconBuilder iconBuilder = this.iconBuilder;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("IconManager.createIcons");
        }
        try {
            StatusBarIconView createIconView = iconBuilder.createIconView(notificationEntry);
            ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;
            createIconView.setScaleType(scaleType);
            StatusBarIconView createIconView2 = iconBuilder.createIconView(notificationEntry);
            createIconView2.setScaleType(scaleType);
            StatusBarIconView createIconView3 = iconBuilder.createIconView(notificationEntry);
            createIconView3.setScaleType(scaleType);
            createIconView3.setVisibility(4);
            StatusBarIconView createIconView4 = iconBuilder.createIconView(notificationEntry);
            createIconView4.setScaleType(scaleType);
            createIconView4.mIncreasedSize = true;
            createIconView4.maybeUpdateIconScaleDimens();
            Pair iconDescriptors = getIconDescriptors(notificationEntry);
            StatusBarIcon statusBarIcon = (StatusBarIcon) iconDescriptors.component1();
            StatusBarIcon statusBarIcon2 = (StatusBarIcon) iconDescriptors.component2();
            try {
                setIcon(notificationEntry, statusBarIcon, createIconView);
                setIcon(notificationEntry, statusBarIcon, createIconView2);
                setIcon(notificationEntry, statusBarIcon2, createIconView3);
                setIcon(notificationEntry, statusBarIcon2, createIconView4);
                notificationEntry.mIcons = new IconPack(true, createIconView, createIconView2, createIconView3, createIconView4, notificationEntry.mIcons);
            } catch (InflationException e) {
                notificationEntry.mIcons = new IconPack(false, null, null, null, null, notificationEntry.mIcons);
                throw e;
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public final StatusBarIcon getIconDescriptor(NotificationEntry notificationEntry, boolean z) {
        Pair pair;
        boolean z2 = !z && isImportantConversation(notificationEntry);
        IconPack iconPack = notificationEntry.mIcons;
        StatusBarIcon statusBarIcon = iconPack.mPeopleAvatarDescriptor;
        StatusBarIcon statusBarIcon2 = iconPack.mSmallIconDescriptor;
        if (!z2 || statusBarIcon == null) {
            statusBarIcon = statusBarIcon2 == null ? null : statusBarIcon2;
        }
        if (statusBarIcon != null) {
            return statusBarIcon;
        }
        Notification notification = notificationEntry.mSbn.getNotification();
        if (z2) {
            ShortcutInfo conversationShortcutInfo = notificationEntry.mRanking.getConversationShortcutInfo();
            Icon shortcutIcon = conversationShortcutInfo != null ? this.launcherApps.getShortcutIcon(conversationShortcutInfo) : null;
            if (shortcutIcon == null) {
                Bundle bundle = notificationEntry.mSbn.getNotification().extras;
                List<Notification.MessagingStyle.Message> messagesFromBundleArray = Notification.MessagingStyle.Message.getMessagesFromBundleArray(bundle.getParcelableArray("android.messages"));
                Person person = (Person) bundle.getParcelable("android.messagingUser");
                int size = messagesFromBundleArray.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i = size - 1;
                        Notification.MessagingStyle.Message message = messagesFromBundleArray.get(size);
                        Person senderPerson = message.getSenderPerson();
                        if (senderPerson != null && senderPerson != person) {
                            Person senderPerson2 = message.getSenderPerson();
                            Intrinsics.checkNotNull(senderPerson2);
                            shortcutIcon = senderPerson2.getIcon();
                            break;
                        }
                        if (i < 0) {
                            break;
                        }
                        size = i;
                    }
                }
            }
            if (shortcutIcon == null) {
                shortcutIcon = notificationEntry.mSbn.getNotification().getLargeIcon();
            }
            if (shortcutIcon == null) {
                shortcutIcon = notificationEntry.mSbn.getNotification().getSmallIcon();
            }
            if (shortcutIcon == null) {
                throw new InflationException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("No icon in notification from ", notificationEntry.mSbn.getPackageName()));
            }
            pair = new Pair(shortcutIcon, StatusBarIcon.Type.PeopleAvatar);
        } else {
            pair = new Pair(notification.getSmallIcon(), StatusBarIcon.Type.NotifSmallIcon);
        }
        Icon icon = (Icon) pair.component1();
        StatusBarIcon.Type type = (StatusBarIcon.Type) pair.component2();
        if (icon == null) {
            throw new InflationException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("No icon in notification from ", notificationEntry.mSbn.getPackageName()));
        }
        Notification notification2 = notificationEntry.mSbn.getNotification();
        StatusBarIcon statusBarIcon3 = new StatusBarIcon(notificationEntry.mSbn.getUser(), notificationEntry.mSbn.getPackageName(), icon, notification2.iconLevel, notification2.number, NotificationContentDescription.contentDescForNotification(this.iconBuilder.context, notification2), type);
        if (isImportantConversation(notificationEntry)) {
            if (statusBarIcon3.type == StatusBarIcon.Type.PeopleAvatar) {
                notificationEntry.mIcons.mPeopleAvatarDescriptor = statusBarIcon3;
            } else {
                notificationEntry.mIcons.mSmallIconDescriptor = statusBarIcon3;
            }
        }
        return statusBarIcon3;
    }

    public final Pair getIconDescriptors(NotificationEntry notificationEntry) {
        StatusBarIcon iconDescriptor = getIconDescriptor(notificationEntry, false);
        return new Pair(iconDescriptor, ((Boolean) notificationEntry.mSensitive.getValue()).booleanValue() ? getIconDescriptor(notificationEntry, true) : iconDescriptor);
    }

    public final boolean isImportantConversation(NotificationEntry notificationEntry) {
        return notificationEntry.mRanking.getChannel() != null && notificationEntry.mRanking.getChannel().isImportantConversation() && notificationEntry.mSbn.getNotification().isStyle(Notification.MessagingStyle.class) && !this.unimportantConversationKeys.contains(notificationEntry.mKey);
    }

    public final void recalculateForImportantConversationChange() {
        for (NotificationEntry notificationEntry : ((NotifPipeline) this.notifCollection).getAllNotifs()) {
            Intrinsics.checkNotNull(notificationEntry);
            boolean isImportantConversation = isImportantConversation(notificationEntry);
            IconPack iconPack = notificationEntry.mIcons;
            if (iconPack.mAreIconsAvailable && isImportantConversation != iconPack.mIsImportantConversation) {
                try {
                    updateIcons(notificationEntry, false);
                } catch (InflationException e) {
                    Log.e("IconManager", "Unable to update icon", e);
                }
            }
            notificationEntry.mIcons.mIsImportantConversation = isImportantConversation;
        }
    }

    public final void setIcon(NotificationEntry notificationEntry, StatusBarIcon statusBarIcon, StatusBarIconView statusBarIconView) {
        IconPack iconPack = notificationEntry.mIcons;
        boolean z = (!isImportantConversation(notificationEntry) || statusBarIcon.icon.equals(notificationEntry.mSbn.getNotification().getSmallIcon()) || ((statusBarIconView == iconPack.mShelfIcon || statusBarIconView == iconPack.mAodIcon) && ((Boolean) notificationEntry.mSensitive.getValue()).booleanValue())) ? false : true;
        if (statusBarIconView.mShowsConversation != z) {
            statusBarIconView.mShowsConversation = z;
            statusBarIconView.updateIconColor();
        }
        statusBarIconView.setTag(R.id.icon_is_pre_L, Boolean.valueOf(notificationEntry.targetSdk < 21));
        if (statusBarIconView.set(statusBarIcon)) {
            return;
        }
        throw new InflationException("Couldn't create icon " + statusBarIcon);
    }

    public final void updateIcons(NotificationEntry notificationEntry, boolean z) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("IconManager.updateIcons");
        }
        try {
            if (notificationEntry.mIcons.mAreIconsAvailable) {
                if (z) {
                    Log.wtf("IconManager", "Updating using the cache is not supported when the notifications_background_icons flag is off");
                }
                IconPack iconPack = notificationEntry.mIcons;
                iconPack.mSmallIconDescriptor = null;
                iconPack.mPeopleAvatarDescriptor = null;
                Pair iconDescriptors = getIconDescriptors(notificationEntry);
                StatusBarIcon statusBarIcon = (StatusBarIcon) iconDescriptors.component1();
                StatusBarIcon statusBarIcon2 = (StatusBarIcon) iconDescriptors.component2();
                Notification notification = notificationEntry.mSbn.getNotification();
                CharSequence contentDescForNotification = notification != null ? NotificationContentDescription.contentDescForNotification(this.iconBuilder.context, notification) : null;
                StatusBarIconView statusBarIconView = notificationEntry.mIcons.mStatusBarIcon;
                if (statusBarIconView != null) {
                    statusBarIconView.setNotification(notificationEntry.mSbn, contentDescForNotification);
                    setIcon(notificationEntry, statusBarIcon, statusBarIconView);
                }
                StatusBarIconView statusBarIconView2 = notificationEntry.mIcons.mStatusBarChipIcon;
                if (statusBarIconView2 != null) {
                    statusBarIconView2.setNotification(notificationEntry.mSbn, contentDescForNotification);
                    setIcon(notificationEntry, statusBarIcon, statusBarIconView2);
                }
                StatusBarIconView statusBarIconView3 = notificationEntry.mIcons.mShelfIcon;
                if (statusBarIconView3 != null) {
                    statusBarIconView3.setNotification(notificationEntry.mSbn, contentDescForNotification);
                    setIcon(notificationEntry, statusBarIcon2, statusBarIconView3);
                }
                StatusBarIconView statusBarIconView4 = notificationEntry.mIcons.mAodIcon;
                if (statusBarIconView4 != null) {
                    statusBarIconView4.setNotification(notificationEntry.mSbn, contentDescForNotification);
                    setIcon(notificationEntry, statusBarIcon2, statusBarIconView4);
                }
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
