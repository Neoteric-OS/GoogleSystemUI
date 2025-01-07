package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.shared.EnRouteContentModel;
import com.android.systemui.statusbar.notification.row.shared.IconModel;
import com.android.systemui.statusbar.notification.row.shared.RichOngoingContentModel;
import com.android.systemui.statusbar.notification.row.shared.TimerContentModel;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RichOngoingNotificationContentExtractorImpl implements RichOngoingNotificationContentExtractor {
    public static Notification.Action findAddMinuteAction(Notification notification) {
        String action;
        for (Notification.Action action2 : notification.actions) {
            Intent intent = action2.actionIntent.getIntent();
            if (intent != null && (action = intent.getAction()) != null && action.endsWith(".ADD_MINUTE_TIMER")) {
                return action2;
            }
        }
        return null;
    }

    public static Notification.Action findResetAction(Notification notification) {
        String action;
        for (Notification.Action action2 : notification.actions) {
            Intent intent = action2.actionIntent.getIntent();
            if (intent != null && (action = intent.getAction()) != null && action.endsWith(".RESET_TIMER")) {
                return action2;
            }
        }
        return null;
    }

    public static Duration parseTimeDelta(String str) {
        List split$default = StringsKt.split$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(str, "Σ", ""), "⏳", ""), new String[]{":"}, 0, 6);
        return Duration.ofHours(Long.parseLong((String) split$default.get(0))).plusMinutes(Long.parseLong((String) split$default.get(1))).plusSeconds(Long.parseLong((String) split$default.get(2)));
    }

    public static TimerContentModel parseTimerNotification(Notification notification, IconModel iconModel) {
        Notification.Action action;
        String action2;
        Notification.Action action3;
        String action4;
        String sortKey = notification.getSortKey();
        Intrinsics.checkNotNull(sortKey);
        List split$default = StringsKt.split$default(sortKey, new String[]{"|"}, 4, 2);
        String str = (String) split$default.get(2);
        String str2 = (String) split$default.get(3);
        int i = 0;
        if (Intrinsics.areEqual(str, "PAUSED")) {
            List split$default2 = StringsKt.split$default(str2, new String[]{"|"}, 0, 6);
            String str3 = (String) split$default2.get(0);
            Duration parseTimeDelta = parseTimeDelta((String) split$default2.get(2));
            String concat = StringsKt__StringsJVMKt.replace$default(str3, "Σ", "").concat(" Timer");
            Notification.Action[] actionArr = notification.actions;
            int length = actionArr.length;
            while (true) {
                if (i >= length) {
                    action3 = null;
                    break;
                }
                action3 = actionArr[i];
                Intent intent = action3.actionIntent.getIntent();
                if (intent != null && (action4 = intent.getAction()) != null && action4.endsWith(".START_TIMER")) {
                    break;
                }
                i++;
            }
            return new TimerContentModel(iconModel, concat, new TimerContentModel.TimerState.Paused(parseTimeDelta, action3 != null ? action3.actionIntent : null, findAddMinuteAction(notification), findResetAction(notification)));
        }
        if (!Intrinsics.areEqual(str, "RUNNING")) {
            throw new IllegalStateException(("unknown state (" + str + ") in sortKey=" + sortKey).toString());
        }
        List split$default3 = StringsKt.split$default(str2, new String[]{"|"}, 0, 6);
        String str4 = (String) split$default3.get(0);
        String str5 = (String) split$default3.get(1);
        String str6 = (String) split$default3.get(3);
        List split$default4 = StringsKt.split$default(StringsKt__StringsJVMKt.replace$default(str4, "▶", ""), new String[]{":", "."}, 0, 6);
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(Integer.parseInt((String) split$default4.get(0)), Integer.parseInt((String) split$default4.get(1)), Integer.parseInt((String) split$default4.get(2)), Integer.parseInt((String) split$default4.get(3)) * 1000000));
        long millis = parseTimeDelta(str6).toMillis() + of.toInstant(ZoneId.systemDefault().getRules().getOffset(of)).toEpochMilli();
        String concat2 = StringsKt__StringsJVMKt.replace$default(str5, "Σ", "").concat(" Timer");
        Notification.Action[] actionArr2 = notification.actions;
        int length2 = actionArr2.length;
        while (true) {
            if (i >= length2) {
                action = null;
                break;
            }
            action = actionArr2[i];
            Intent intent2 = action.actionIntent.getIntent();
            if (intent2 != null && (action2 = intent2.getAction()) != null && action2.endsWith(".PAUSE_TIMER")) {
                break;
            }
            i++;
        }
        return new TimerContentModel(iconModel, concat2, new TimerContentModel.TimerState.Running(millis, action != null ? action.actionIntent : null, findAddMinuteAction(notification), findResetAction(notification)));
    }

    @Override // com.android.systemui.statusbar.notification.row.RichOngoingNotificationContentExtractor
    public final RichOngoingContentModel extractContentModel(NotificationEntry notificationEntry, Notification.Builder builder) {
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        Notification notification = statusBarNotification.getNotification();
        IconModel iconModel = new IconModel(notification.getSmallIcon());
        try {
            if (!Intrinsics.areEqual(statusBarNotification.getPackageName(), "com.google.android.deskclock")) {
                if (builder.getStyle() instanceof Notification.EnRouteStyle) {
                    return new EnRouteContentModel(iconModel, notification.extras.getCharSequence("android.title"), notification.extras.getCharSequence("android.text"));
                }
                return null;
            }
            String channelId = notification.getChannelId();
            if (Intrinsics.areEqual(channelId, "Timers v2")) {
                return parseTimerNotification(notification, iconModel);
            }
            if (Intrinsics.areEqual(channelId, "Stopwatch v2")) {
                Log.i("RONs", "Can't process stopwatch yet");
                return null;
            }
            Log.i("RONs", "Can't process channel '" + notification.getChannelId() + "'");
            return null;
        } catch (Exception e) {
            Log.e("RONs", "Error parsing RON", e);
            return null;
        }
    }
}
