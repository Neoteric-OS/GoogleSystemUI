package com.android.systemui.statusbar.notification.row;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.INotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.row.ChannelEditorDialog;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt$sortedWith$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChannelEditorDialogController {
    public Drawable appIcon;
    public String appName;
    public Boolean appNotificationsCurrentlyEnabled;
    public Integer appUid;
    public NotificationChannel channel;
    public final Context context;
    public ChannelEditorDialog dialog;
    public final ChannelEditorDialog.Builder dialogBuilder;
    public final INotificationManager noMan;
    public OnChannelEditorDialogFinishedListener onFinishListener;
    public NotificationGutsManager$$ExternalSyntheticLambda3 onSettingsClickListener;
    public String packageName;
    public boolean prepared;
    public final List channelList = new ArrayList();
    public final Map edits = new LinkedHashMap();
    public boolean appNotificationsEnabled = true;
    public final HashMap groupNameLookup = new HashMap();
    public final List channelGroupList = new ArrayList();
    public final int wmFlags = -2130444288;

    public ChannelEditorDialogController(Context context, INotificationManager iNotificationManager, ChannelEditorDialog.Builder builder) {
        this.noMan = iNotificationManager;
        this.dialogBuilder = builder;
        this.context = context.getApplicationContext();
    }

    public final void apply() {
        for (Map.Entry entry : this.edits.entrySet()) {
            NotificationChannel notificationChannel = (NotificationChannel) entry.getKey();
            int intValue = ((Number) entry.getValue()).intValue();
            if (notificationChannel.getImportance() != intValue) {
                try {
                    notificationChannel.setImportance(intValue);
                    INotificationManager iNotificationManager = this.noMan;
                    String str = this.packageName;
                    Intrinsics.checkNotNull(str);
                    Integer num = this.appUid;
                    Intrinsics.checkNotNull(num);
                    iNotificationManager.updateNotificationChannelForPackage(str, num.intValue(), notificationChannel);
                } catch (Exception e) {
                    Log.e("ChannelDialogController", "Unable to update notification importance", e);
                }
            }
        }
        if (Boolean.valueOf(this.appNotificationsEnabled).equals(this.appNotificationsCurrentlyEnabled)) {
            return;
        }
        boolean z = this.appNotificationsEnabled;
        try {
            INotificationManager iNotificationManager2 = this.noMan;
            String str2 = this.packageName;
            Intrinsics.checkNotNull(str2);
            Integer num2 = this.appUid;
            Intrinsics.checkNotNull(num2);
            iNotificationManager2.setNotificationsEnabledForPackage(str2, num2.intValue(), z);
        } catch (Exception e2) {
            Log.e("ChannelDialogController", "Error calling NoMan", e2);
        }
    }

    public final void done() {
        this.appIcon = null;
        this.appUid = null;
        this.packageName = null;
        this.appName = null;
        this.appNotificationsCurrentlyEnabled = null;
        this.edits.clear();
        this.channelList.clear();
        this.groupNameLookup.clear();
        ChannelEditorDialog channelEditorDialog = this.dialog;
        (channelEditorDialog != null ? channelEditorDialog : null).dismiss();
    }

    public final void launchSettings(View view) {
        NotificationGutsManager$$ExternalSyntheticLambda3 notificationGutsManager$$ExternalSyntheticLambda3 = this.onSettingsClickListener;
        if (notificationGutsManager$$ExternalSyntheticLambda3 != null) {
            NotificationChannel notificationChannel = this.channel;
            Integer num = this.appUid;
            Intrinsics.checkNotNull(num);
            notificationGutsManager$$ExternalSyntheticLambda3.onClick(notificationChannel, num.intValue());
        }
    }

    public final void prepareDialogForApp(String str, String str2, int i, NotificationChannel notificationChannel, Drawable drawable, NotificationGutsManager$$ExternalSyntheticLambda3 notificationGutsManager$$ExternalSyntheticLambda3) {
        boolean z;
        List list;
        final int i2 = 1;
        final int i3 = 0;
        this.appName = str;
        this.packageName = str2;
        this.appUid = Integer.valueOf(i);
        this.appIcon = drawable;
        try {
            INotificationManager iNotificationManager = this.noMan;
            String str3 = this.packageName;
            Intrinsics.checkNotNull(str3);
            Integer num = this.appUid;
            Intrinsics.checkNotNull(num);
            z = iNotificationManager.areNotificationsEnabledForPackage(str3, num.intValue());
        } catch (Exception e) {
            Log.e("ChannelDialogController", "Error calling NoMan", e);
            z = false;
        }
        this.appNotificationsEnabled = z;
        this.onSettingsClickListener = notificationGutsManager$$ExternalSyntheticLambda3;
        this.channel = notificationChannel;
        this.appNotificationsCurrentlyEnabled = Boolean.valueOf(z);
        this.channelGroupList.clear();
        List list2 = this.channelGroupList;
        try {
            INotificationManager iNotificationManager2 = this.noMan;
            String str4 = this.packageName;
            Intrinsics.checkNotNull(str4);
            Integer num2 = this.appUid;
            Intrinsics.checkNotNull(num2);
            list = iNotificationManager2.getRecentBlockedNotificationChannelGroupsForPackage(str4, num2.intValue()).getList();
            if (list == null) {
                list = null;
            }
            if (list == null) {
                list = EmptyList.INSTANCE;
            }
        } catch (Exception e2) {
            Log.e("ChannelDialogController", "Error fetching channel groups", e2);
            list = EmptyList.INSTANCE;
        }
        list2.addAll(list);
        for (NotificationChannelGroup notificationChannelGroup : this.channelGroupList) {
            if (notificationChannelGroup.getId() != null) {
                this.groupNameLookup.put(notificationChannelGroup.getId(), notificationChannelGroup.getName());
            }
        }
        this.channelList.clear();
        NotificationChannel notificationChannel2 = this.channel;
        Intrinsics.checkNotNull(notificationChannel2);
        if (!"miscellaneous".equals(notificationChannel2.getId())) {
            List list3 = this.channelList;
            NotificationChannel notificationChannel3 = this.channel;
            Intrinsics.checkNotNull(notificationChannel3);
            list3.add(0, notificationChannel3);
            CollectionsKt__MutableCollectionsKt.addAll(this.channelList, SequencesKt.distinct(new FilteringSequence(new SequencesKt___SequencesKt$sortedWith$1(SequencesKt.flatMap(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.channelGroupList), new Function1() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$getDisplayableChannels$channels$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    final NotificationChannelGroup notificationChannelGroup2 = (NotificationChannelGroup) obj;
                    return new FilteringSequence(new SequencesKt___SequencesKt$sortedWith$1(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(notificationChannelGroup2.getChannels()), new Comparator() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$getDisplayableChannels$channels$1$invoke$$inlined$compareBy$1
                        @Override // java.util.Comparator
                        public final int compare(Object obj2, Object obj3) {
                            String id;
                            String id2;
                            CharSequence name = notificationChannelGroup2.getName();
                            if (name == null || (id = name.toString()) == null) {
                                id = notificationChannelGroup2.getId();
                            }
                            CharSequence name2 = notificationChannelGroup2.getName();
                            if (name2 == null || (id2 = name2.toString()) == null) {
                                id2 = notificationChannelGroup2.getId();
                            }
                            return ComparisonsKt___ComparisonsJvmKt.compareValues(id, id2);
                        }
                    }), false, new Function1() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$getDisplayableChannels$channels$1.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Boolean.valueOf(((NotificationChannel) obj2).isImportanceLockedByCriticalDeviceFunction());
                        }
                    });
                }
            }), new ChannelEditorDialogController$getDisplayableChannels$$inlined$compareBy$1()), false, new Function1() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$populateChannelList$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String id = ((NotificationChannel) obj).getId();
                    NotificationChannel notificationChannel4 = ChannelEditorDialogController.this.channel;
                    Intrinsics.checkNotNull(notificationChannel4);
                    return Boolean.valueOf(Intrinsics.areEqual(id, notificationChannel4.getId()));
                }
            })));
        }
        Context context = this.context;
        ChannelEditorDialog.Builder builder = this.dialogBuilder;
        builder.context = context;
        Context context2 = builder.context;
        if (context2 == null) {
            context2 = null;
        }
        ChannelEditorDialog channelEditorDialog = new ChannelEditorDialog(context2, R.style.Theme_SystemUI_Dialog);
        this.dialog = channelEditorDialog;
        Window window = channelEditorDialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
        }
        ChannelEditorDialog channelEditorDialog2 = this.dialog;
        if (channelEditorDialog2 == null) {
            channelEditorDialog2 = null;
        }
        channelEditorDialog2.setTitle(" ");
        ChannelEditorDialog channelEditorDialog3 = this.dialog;
        ChannelEditorDialog channelEditorDialog4 = channelEditorDialog3 != null ? channelEditorDialog3 : null;
        channelEditorDialog4.setContentView(R.layout.notif_half_shelf);
        channelEditorDialog4.setCanceledOnTouchOutside(true);
        channelEditorDialog4.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$initDialog$1$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                OnChannelEditorDialogFinishedListener onChannelEditorDialogFinishedListener = ChannelEditorDialogController.this.onFinishListener;
                if (onChannelEditorDialogFinishedListener != null) {
                    onChannelEditorDialogFinishedListener.onChannelEditorDialogFinished();
                }
            }
        });
        final ChannelEditorListView channelEditorListView = (ChannelEditorListView) channelEditorDialog4.findViewById(R.id.half_shelf_container);
        if (channelEditorListView != null) {
            channelEditorListView.controller = this;
            channelEditorListView.appIcon = this.appIcon;
            channelEditorListView.appName = this.appName;
            channelEditorListView.channels = this.channelList;
            channelEditorListView.updateRows();
        }
        channelEditorDialog4.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$initDialog$1$3
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                ChannelEditorListView channelEditorListView2 = ChannelEditorListView.this;
                if (channelEditorListView2 != null) {
                    NotificationChannel notificationChannel4 = this.channel;
                    Intrinsics.checkNotNull(notificationChannel4);
                    Assert.isMainThread();
                    for (final ChannelRow channelRow : channelEditorListView2.channelRows) {
                        if (Intrinsics.areEqual(channelRow.channel, notificationChannel4)) {
                            ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), 0, Integer.valueOf(channelRow.highlightColor));
                            ofObject.setDuration(200L);
                            ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.row.ChannelRow$playHighlight$1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    ChannelRow.this.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                                }
                            });
                            ofObject.setRepeatMode(2);
                            ofObject.setRepeatCount(5);
                            ofObject.start();
                        }
                    }
                }
            }
        });
        TextView textView = (TextView) channelEditorDialog4.findViewById(R.id.done_button);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$initDialog$1$4
                public final /* synthetic */ ChannelEditorDialogController this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i3) {
                        case 0:
                            this.this$0.apply();
                            this.this$0.done();
                            break;
                        default:
                            ChannelEditorDialogController channelEditorDialogController = this.this$0;
                            Intrinsics.checkNotNull(view);
                            channelEditorDialogController.launchSettings(view);
                            this.this$0.done();
                            break;
                    }
                }
            });
        }
        TextView textView2 = (TextView) channelEditorDialog4.findViewById(R.id.see_more_button);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.statusbar.notification.row.ChannelEditorDialogController$initDialog$1$4
                public final /* synthetic */ ChannelEditorDialogController this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i2) {
                        case 0:
                            this.this$0.apply();
                            this.this$0.done();
                            break;
                        default:
                            ChannelEditorDialogController channelEditorDialogController = this.this$0;
                            Intrinsics.checkNotNull(view);
                            channelEditorDialogController.launchSettings(view);
                            this.this$0.done();
                            break;
                    }
                }
            });
        }
        Window window2 = channelEditorDialog4.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.addFlags(this.wmFlags);
            window2.setType(2017);
            window2.setWindowAnimations(android.R.style.Animation.InputMethod);
            WindowManager.LayoutParams attributes = window2.getAttributes();
            attributes.format = -3;
            attributes.setTitle("ChannelEditorDialogController");
            attributes.gravity = 81;
            attributes.setFitInsetsTypes(window2.getAttributes().getFitInsetsTypes() & (~WindowInsets.Type.statusBars()));
            attributes.width = -1;
            attributes.height = -2;
            window2.setAttributes(attributes);
        }
        this.prepared = true;
    }

    public static /* synthetic */ void getChannelList$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    public static /* synthetic */ void getGroupNameLookup$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
