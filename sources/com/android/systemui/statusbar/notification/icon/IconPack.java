package com.android.systemui.statusbar.notification.icon;

import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.statusbar.StatusBarIconView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconPack {
    public final StatusBarIconView mAodIcon;
    public final boolean mAreIconsAvailable;
    public boolean mIsImportantConversation;
    public StatusBarIcon mPeopleAvatarDescriptor;
    public final StatusBarIconView mShelfIcon;
    public StatusBarIcon mSmallIconDescriptor;
    public final StatusBarIconView mStatusBarChipIcon;
    public final StatusBarIconView mStatusBarIcon;

    public IconPack(boolean z, StatusBarIconView statusBarIconView, StatusBarIconView statusBarIconView2, StatusBarIconView statusBarIconView3, StatusBarIconView statusBarIconView4, IconPack iconPack) {
        this.mAreIconsAvailable = z;
        this.mStatusBarIcon = statusBarIconView;
        this.mStatusBarChipIcon = statusBarIconView2;
        this.mShelfIcon = statusBarIconView3;
        this.mAodIcon = statusBarIconView4;
        if (iconPack != null) {
            this.mIsImportantConversation = iconPack.mIsImportantConversation;
        }
    }
}
