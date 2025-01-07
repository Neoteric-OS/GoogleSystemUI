package com.android.systemui.recordissue;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import com.android.systemui.recordissue.RecordIssueDialogDelegate$beforeCreate$2;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1 implements PopupMenu.OnMenuItemClickListener {
    public final /* synthetic */ RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1 $onIssueTypeSelected;
    public final /* synthetic */ RecordIssueDialogDelegate this$0;

    public RecordIssueDialogDelegate$onIssueTypeClicked$onMenuItemClickListener$1(RecordIssueDialogDelegate recordIssueDialogDelegate, RecordIssueDialogDelegate$beforeCreate$2.AnonymousClass1 anonymousClass1) {
        this.this$0 = recordIssueDialogDelegate;
        this.$onIssueTypeSelected = anonymousClass1;
    }

    @Override // android.widget.PopupMenu.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        Button button = this.this$0.issueTypeButton;
        if (button == null) {
            button = null;
        }
        button.setText(menuItem.getTitle());
        IssueRecordingState issueRecordingState = this.this$0.state;
        Intent intent = menuItem.getIntent();
        int i = -1;
        int intExtra = intent != null ? intent.getIntExtra("extra_issueTypeRes", -1) : -1;
        issueRecordingState.getClass();
        int[] intArray = CollectionsKt.toIntArray(IssueRecordingState.ALL_ISSUE_TYPES.keySet());
        int length = intArray.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (intExtra == intArray[i2]) {
                i = i2;
                break;
            }
            i2++;
        }
        issueRecordingState.prefs.edit().putInt("key_issueTypeIndex", i).apply();
        this.$onIssueTypeSelected.run();
        return true;
    }
}
