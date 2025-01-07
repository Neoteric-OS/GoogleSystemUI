package com.android.systemui.statusbar.notification.row;

import android.R;
import android.app.NotificationChannel;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChannelRow extends LinearLayout {
    public NotificationChannel channel;
    public TextView channelDescription;
    public TextView channelName;
    public ChannelEditorDialogController controller;
    public final int highlightColor;

    /* renamed from: switch, reason: not valid java name */
    public Switch f45switch;

    public ChannelRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.highlightColor = Utils.getColorAttrDefaultColor(R.attr.colorControlHighlight, 0, getContext());
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.channelName = (TextView) requireViewById(com.android.wm.shell.R.id.channel_name);
        this.channelDescription = (TextView) requireViewById(com.android.wm.shell.R.id.channel_description);
        Switch r0 = (Switch) requireViewById(com.android.wm.shell.R.id.toggle);
        this.f45switch = r0;
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.systemui.statusbar.notification.row.ChannelRow$onFinishInflate$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int i;
                ChannelRow channelRow = ChannelRow.this;
                NotificationChannel notificationChannel = channelRow.channel;
                if (notificationChannel != null) {
                    ChannelEditorDialogController channelEditorDialogController = channelRow.controller;
                    if (channelEditorDialogController == null) {
                        channelEditorDialogController = null;
                    }
                    if (z) {
                        i = notificationChannel.getOriginalImportance();
                        if (i < 2) {
                            i = 2;
                        }
                    } else {
                        i = 0;
                    }
                    channelEditorDialogController.getClass();
                    if (notificationChannel.getImportance() == i) {
                        channelEditorDialogController.edits.remove(notificationChannel);
                    } else {
                        channelEditorDialogController.edits.put(notificationChannel, Integer.valueOf(i));
                    }
                    ChannelEditorDialog channelEditorDialog = channelEditorDialogController.dialog;
                    ChannelEditorDialog channelEditorDialog2 = channelEditorDialog != null ? channelEditorDialog : null;
                    boolean z2 = (channelEditorDialogController.edits.isEmpty() && Boolean.valueOf(channelEditorDialogController.appNotificationsEnabled).equals(channelEditorDialogController.appNotificationsCurrentlyEnabled)) ? false : true;
                    TextView textView = (TextView) channelEditorDialog2.findViewById(com.android.wm.shell.R.id.done_button);
                    if (textView != null) {
                        textView.setText(z2 ? com.android.wm.shell.R.string.inline_ok_button : com.android.wm.shell.R.string.inline_done_button);
                    }
                }
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.row.ChannelRow$onFinishInflate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Switch r02 = ChannelRow.this.f45switch;
                if (r02 == null) {
                    r02 = null;
                }
                r02.toggle();
            }
        });
    }
}
