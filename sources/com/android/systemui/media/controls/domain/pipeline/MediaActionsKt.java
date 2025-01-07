package com.android.systemui.media.controls.domain.pipeline;

import android.R;
import android.app.BroadcastOptions;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaNotificationAction;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.NotificationMediaManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;
import kotlin.sequences.TransformingSequence$iterator$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaActionsKt {
    public static final boolean access$sendPendingIntent(PendingIntent pendingIntent) {
        try {
            BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
            makeBasic.setInteractive(true);
            makeBasic.setPendingIntentBackgroundActivityStartMode(1);
            pendingIntent.send(makeBasic.toBundle());
            return true;
        } catch (PendingIntent.CanceledException e) {
            Log.d("MediaActions", "Intent canceled", e);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v12, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.List] */
    public static final Pair createActionsFromNotification(Context context, StatusBarNotification statusBarNotification) {
        ArrayList arrayList;
        ?? arrayList2;
        Icon icon;
        Notification notification = statusBarNotification.getNotification();
        ArrayList arrayList3 = new ArrayList();
        Notification.Action[] actionArr = notification.actions;
        int[] intArray = notification.extras.getIntArray("android.compactActions");
        int i = 0;
        if (intArray != null) {
            arrayList = new ArrayList(intArray.length);
            for (int i2 : intArray) {
                arrayList.add(Integer.valueOf(i2));
            }
        } else {
            arrayList = new ArrayList();
        }
        int size = arrayList.size();
        int i3 = LegacyMediaDataManagerImpl.MAX_NOTIFICATION_ACTIONS;
        ArrayList arrayList4 = arrayList;
        if (size > 3) {
            Log.e("MediaActions", "Too many compact actions for " + statusBarNotification.getKey() + ", limiting to first 3");
            arrayList4 = arrayList.subList(0, 3);
        }
        if (actionArr != null) {
            int length = actionArr.length;
            int i4 = LegacyMediaDataManagerImpl.MAX_NOTIFICATION_ACTIONS;
            if (length > i4) {
                Log.w("MediaActions", "Too many notification actions for " + statusBarNotification.getKey() + ", limiting to first " + i4);
            }
            if (i4 < 0) {
                throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested element count ", " is less than zero.", i4).toString());
            }
            if (i4 == 0) {
                arrayList2 = EmptyList.INSTANCE;
            } else if (i4 >= actionArr.length) {
                arrayList2 = ArraysKt.toList(actionArr);
            } else if (i4 == 1) {
                arrayList2 = Collections.singletonList(actionArr[0]);
            } else {
                arrayList2 = new ArrayList(i4);
                int i5 = 0;
                for (Notification.Action action : actionArr) {
                    arrayList2.add(action);
                    i5++;
                    if (i5 == i4) {
                        break;
                    }
                }
            }
            for (Notification.Action action2 : arrayList2) {
                int i6 = i + 1;
                if (action2.getIcon() == null) {
                    if (Log.isLoggable("MediaActions", 4)) {
                        Log.i("MediaActions", "No icon for action " + i + " " + ((Object) action2.title));
                    }
                    arrayList4.remove(Integer.valueOf(i));
                } else {
                    int defaultColor = Utils.getColorAttr(R.attr.textColorPrimary, context).getDefaultColor();
                    if (action2.getIcon().getType() == 2) {
                        String packageName = statusBarNotification.getPackageName();
                        Icon icon2 = action2.getIcon();
                        Intrinsics.checkNotNull(icon2);
                        icon = Icon.createWithResource(packageName, icon2.getResId());
                    } else {
                        icon = action2.getIcon();
                    }
                    arrayList3.add(new MediaNotificationAction(action2.isAuthenticationRequired(), action2.actionIntent, icon.setTint(defaultColor).loadDrawable(context), action2.title));
                }
                i = i6;
            }
        }
        return new Pair(arrayList3, arrayList4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final MediaButton createActionsFromState(final Context context, final String str, final MediaController mediaController) {
        MediaAction standardAction;
        MediaAction createActionsFromState$nextCustomAction;
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState == null) {
            return null;
        }
        if (NotificationMediaManager.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(playbackState.getState()))) {
            Drawable drawable = context.getDrawable(R.drawable.rate_star_big_half_holo_light);
            ((Animatable) drawable).start();
            standardAction = new MediaAction(drawable, null, context.getString(com.android.wm.shell.R.string.controls_media_button_connecting), context.getDrawable(com.android.wm.shell.R.drawable.ic_media_connecting_container), Integer.valueOf(R.drawable.rate_star_big_half_holo_light));
        } else {
            standardAction = NotificationMediaManager.isPlayingState(playbackState.getState()) ? getStandardAction(context, mediaController, playbackState.getActions(), 2L) : getStandardAction(context, mediaController, playbackState.getActions(), 4L);
        }
        MediaAction mediaAction = standardAction;
        MediaAction standardAction2 = getStandardAction(context, mediaController, playbackState.getActions(), 16L);
        MediaAction standardAction3 = getStandardAction(context, mediaController, playbackState.getActions(), 32L);
        TransformingSequence$iterator$1 transformingSequence$iterator$1 = new TransformingSequence$iterator$1(new TransformingSequence(SequencesKt.filterNotNull(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(playbackState.getCustomActions())), new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$createActionsFromState$customActions$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
                Context context2 = context;
                String str2 = str;
                final MediaController mediaController2 = mediaController;
                return new MediaAction(Icon.createWithResource(str2, customAction.getIcon()).loadDrawable(context2), new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getCustomAction$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaController.TransportControls transportControls = mediaController2.getTransportControls();
                        PlaybackState.CustomAction customAction2 = customAction;
                        transportControls.sendCustomAction(customAction2, customAction2.getExtras());
                    }
                }, customAction.getName(), null, null);
            }
        }));
        Bundle extras = mediaController.getExtras();
        boolean z = extras != null && extras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS");
        Bundle extras2 = mediaController.getExtras();
        boolean z2 = extras2 != null && extras2.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT");
        MediaAction createActionsFromState$nextCustomAction2 = standardAction2 != null ? standardAction2 : !z ? createActionsFromState$nextCustomAction(transformingSequence$iterator$1) : null;
        if (standardAction3 != null) {
            createActionsFromState$nextCustomAction = standardAction3;
        } else {
            createActionsFromState$nextCustomAction = z2 ? null : createActionsFromState$nextCustomAction(transformingSequence$iterator$1);
        }
        return new MediaButton(mediaAction, createActionsFromState$nextCustomAction, createActionsFromState$nextCustomAction2, createActionsFromState$nextCustomAction(transformingSequence$iterator$1), createActionsFromState$nextCustomAction(transformingSequence$iterator$1), z2, z);
    }

    public static final MediaAction createActionsFromState$nextCustomAction(TransformingSequence$iterator$1 transformingSequence$iterator$1) {
        if (transformingSequence$iterator$1.iterator.hasNext()) {
            return (MediaAction) transformingSequence$iterator$1.next();
        }
        return null;
    }

    public static final List getNotificationActions(List list, final ActivityStarter activityStarter) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final MediaNotificationAction mediaNotificationAction = (MediaNotificationAction) it.next();
            final PendingIntent pendingIntent = mediaNotificationAction.actionIntent;
            arrayList.add(new MediaAction(mediaNotificationAction.icon, pendingIntent != null ? new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getNotificationActions$1$runnable$1$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (pendingIntent.isActivity()) {
                        activityStarter.startPendingIntentDismissingKeyguard(mediaNotificationAction.actionIntent);
                        return;
                    }
                    final MediaNotificationAction mediaNotificationAction2 = mediaNotificationAction;
                    if (mediaNotificationAction2.isAuthenticationRequired) {
                        activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getNotificationActions$1$runnable$1$1.1
                            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                            public final boolean onDismiss() {
                                return MediaActionsKt.access$sendPendingIntent(MediaNotificationAction.this.actionIntent);
                            }
                        }, AnonymousClass2.INSTANCE, true);
                    } else {
                        MediaActionsKt.access$sendPendingIntent(pendingIntent);
                    }
                }

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getNotificationActions$1$runnable$1$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements Runnable {
                    public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                    @Override // java.lang.Runnable
                    public final void run() {
                    }
                }
            } : null, mediaNotificationAction.contentDescription, null, null));
        }
        return arrayList;
    }

    public static final MediaAction getStandardAction(Context context, final MediaController mediaController, long j, long j2) {
        if (((j2 != 4 && j2 != 2) || (512 & j) <= 0) && (j & j2) == 0) {
            return null;
        }
        if (j2 == 4) {
            final int i = 0;
            return new MediaAction(context.getDrawable(com.android.wm.shell.R.drawable.ic_media_play), new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getStandardAction$1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            mediaController.getTransportControls().play();
                            break;
                        case 1:
                            mediaController.getTransportControls().pause();
                            break;
                        case 2:
                            mediaController.getTransportControls().skipToPrevious();
                            break;
                        default:
                            mediaController.getTransportControls().skipToNext();
                            break;
                    }
                }
            }, context.getString(com.android.wm.shell.R.string.controls_media_button_play), context.getDrawable(com.android.wm.shell.R.drawable.ic_media_play_container), null);
        }
        if (j2 == 2) {
            final int i2 = 1;
            return new MediaAction(context.getDrawable(com.android.wm.shell.R.drawable.ic_media_pause), new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getStandardAction$1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            mediaController.getTransportControls().play();
                            break;
                        case 1:
                            mediaController.getTransportControls().pause();
                            break;
                        case 2:
                            mediaController.getTransportControls().skipToPrevious();
                            break;
                        default:
                            mediaController.getTransportControls().skipToNext();
                            break;
                    }
                }
            }, context.getString(com.android.wm.shell.R.string.controls_media_button_pause), context.getDrawable(com.android.wm.shell.R.drawable.ic_media_pause_container), null);
        }
        if (j2 == 16) {
            final int i3 = 2;
            return new MediaAction(context.getDrawable(com.android.wm.shell.R.drawable.ic_media_prev), new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getStandardAction$1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i3) {
                        case 0:
                            mediaController.getTransportControls().play();
                            break;
                        case 1:
                            mediaController.getTransportControls().pause();
                            break;
                        case 2:
                            mediaController.getTransportControls().skipToPrevious();
                            break;
                        default:
                            mediaController.getTransportControls().skipToNext();
                            break;
                    }
                }
            }, context.getString(com.android.wm.shell.R.string.controls_media_button_prev), null, null);
        }
        if (j2 != 32) {
            return null;
        }
        final int i4 = 3;
        return new MediaAction(context.getDrawable(com.android.wm.shell.R.drawable.ic_media_next), new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaActionsKt$getStandardAction$1
            @Override // java.lang.Runnable
            public final void run() {
                switch (i4) {
                    case 0:
                        mediaController.getTransportControls().play();
                        break;
                    case 1:
                        mediaController.getTransportControls().pause();
                        break;
                    case 2:
                        mediaController.getTransportControls().skipToPrevious();
                        break;
                    default:
                        mediaController.getTransportControls().skipToNext();
                        break;
                }
            }
        }, context.getString(com.android.wm.shell.R.string.controls_media_button_next), null, null);
    }
}
