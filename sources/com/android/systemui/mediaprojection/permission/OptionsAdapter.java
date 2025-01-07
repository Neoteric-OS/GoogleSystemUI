package com.android.systemui.mediaprojection.permission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.wm.shell.R;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OptionsAdapter extends ArrayAdapter {
    public final List options;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OptionsAdapter(android.content.Context r4, java.util.List r5) {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, r1)
            r0.<init>(r1)
            java.util.Iterator r1 = r5.iterator()
        Lf:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L25
            java.lang.Object r2 = r1.next()
            com.android.systemui.mediaprojection.permission.ScreenShareOption r2 = (com.android.systemui.mediaprojection.permission.ScreenShareOption) r2
            int r2 = r2.spinnerText
            java.lang.String r2 = r4.getString(r2)
            r0.add(r2)
            goto Lf
        L25:
            r1 = 2131558994(0x7f0d0252, float:1.874332E38)
            r3.<init>(r4, r1, r0)
            r3.options = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.mediaprojection.permission.OptionsAdapter.<init>(android.content.Context, java.util.List):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.screen_share_dialog_spinner_item_text, viewGroup, false);
        TextView textView = (TextView) inflate.requireViewById(android.R.id.text1);
        TextView textView2 = (TextView) inflate.requireViewById(android.R.id.text2);
        textView.setText((CharSequence) getItem(i));
        textView2.setText(((ScreenShareOption) this.options.get(i)).spinnerDisabledText);
        if (isEnabled(i)) {
            textView2.setVisibility(8);
            textView.setEnabled(true);
        } else {
            textView2.setVisibility(0);
            textView.setEnabled(false);
        }
        return inflate;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return ((ScreenShareOption) this.options.get(i)).spinnerDisabledText == null;
    }
}
