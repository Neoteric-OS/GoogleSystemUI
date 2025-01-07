package com.android.systemui.statusbar.notification.collection;

import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ListDumper {
    public static void dumpEntry(ListEntry listEntry, String str, String str2, StringBuilder sb, boolean z, boolean z2) {
        sb.append(str2);
        sb.append("[");
        sb.append(str);
        sb.append("] ");
        sb.append(str.length() == 1 ? " " : "");
        sb.append(NotificationUtils.logKey(listEntry));
        if (z) {
            sb.append(" (parent=");
            sb.append(NotificationUtils.logKey(listEntry.mAttachState.parent));
            sb.append(")");
            NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
            if (representativeEntry != null) {
                sb.append(" rank=");
                sb.append(representativeEntry.mRanking.getRank());
            }
        }
        if (listEntry.mAttachState.section != null) {
            sb.append(" section=");
            sb.append(listEntry.mAttachState.section.label);
        }
        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
        Objects.requireNonNull(representativeEntry2);
        StringBuilder sb2 = new StringBuilder();
        if (!representativeEntry2.mLifetimeExtenders.isEmpty()) {
            int size = ((ArrayList) representativeEntry2.mLifetimeExtenders).size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = ((NotifLifetimeExtender) ((ArrayList) representativeEntry2.mLifetimeExtenders).get(i)).getName();
            }
            sb2.append("lifetimeExtenders=");
            sb2.append(Arrays.toString(strArr));
            sb2.append(" ");
        }
        if (!representativeEntry2.mDismissInterceptors.isEmpty()) {
            int size2 = ((ArrayList) representativeEntry2.mDismissInterceptors).size();
            String[] strArr2 = new String[size2];
            for (int i2 = 0; i2 < size2; i2++) {
                ((BubbleCoordinator.AnonymousClass2) ((ArrayList) representativeEntry2.mDismissInterceptors).get(i2)).getClass();
                strArr2[i2] = "BubbleCoordinator";
            }
            sb2.append("dismissInterceptors=");
            sb2.append(Arrays.toString(strArr2));
            sb2.append(" ");
        }
        ListAttachState listAttachState = representativeEntry2.mAttachState;
        if (listAttachState.excludingFilter != null) {
            sb2.append("filter=");
            sb2.append(listAttachState.excludingFilter.mName);
            sb2.append(" ");
        }
        if (listAttachState.promoter != null) {
            sb2.append("promoter=");
            sb2.append(listAttachState.promoter.mName);
            sb2.append(" ");
        }
        if (representativeEntry2.mCancellationReason != -1) {
            sb2.append("cancellationReason=");
            sb2.append(representativeEntry2.mCancellationReason);
            sb2.append(" ");
        }
        if (representativeEntry2.mDismissState != NotificationEntry.DismissState.NOT_DISMISSED) {
            sb2.append("dismissState=");
            sb2.append(representativeEntry2.mDismissState);
            sb2.append(" ");
        }
        if (listAttachState.suppressedChanges.parent != null) {
            sb2.append("suppressedParent=");
            sb2.append(NotificationUtils.logKey(listAttachState.suppressedChanges.parent));
            sb2.append(" ");
        }
        if (listAttachState.suppressedChanges.section != null) {
            sb2.append("suppressedSection=");
            sb2.append(listAttachState.suppressedChanges.section.label);
            sb2.append(" ");
        }
        if (z2) {
            sb2.append("interacted=yes ");
        }
        String sb3 = sb2.toString();
        if (!sb3.isEmpty()) {
            sb.append("\n\t");
            sb.append(str2);
            sb.append(sb3);
        }
        sb.append("\n");
    }
}
