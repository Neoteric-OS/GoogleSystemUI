package com.android.systemui.controls.ui;

import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OverflowMenuAdapter extends ArrayAdapter {
    public final List ids;
    public final Function2 isEnabledInternal;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuItem {
        public final long id;
        public final CharSequence text;

        public MenuItem(CharSequence charSequence, long j) {
            this.text = charSequence;
            this.id = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MenuItem)) {
                return false;
            }
            MenuItem menuItem = (MenuItem) obj;
            return Intrinsics.areEqual(this.text, menuItem.text) && this.id == menuItem.id;
        }

        public final int hashCode() {
            return Long.hashCode(this.id) + (this.text.hashCode() * 31);
        }

        public final String toString() {
            return "MenuItem(text=" + ((Object) this.text) + ", id=" + this.id + ")";
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OverflowMenuAdapter(android.content.Context r7, kotlin.collections.builders.ListBuilder r8, kotlin.jvm.functions.Function2 r9) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, r1)
            r0.<init>(r2)
            r2 = 0
            java.util.ListIterator r3 = r8.listIterator(r2)
        L10:
            r4 = r3
            kotlin.collections.builders.ListBuilder$Itr r4 = (kotlin.collections.builders.ListBuilder.Itr) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L25
            java.lang.Object r4 = r4.next()
            com.android.systemui.controls.ui.OverflowMenuAdapter$MenuItem r4 = (com.android.systemui.controls.ui.OverflowMenuAdapter.MenuItem) r4
            java.lang.CharSequence r4 = r4.text
            r0.add(r4)
            goto L10
        L25:
            r3 = 2131558549(0x7f0d0095, float:1.8742417E38)
            r6.<init>(r7, r3, r0)
            r6.isEnabledInternal = r9
            java.util.ArrayList r7 = new java.util.ArrayList
            int r9 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, r1)
            r7.<init>(r9)
            java.util.ListIterator r8 = r8.listIterator(r2)
        L3a:
            r9 = r8
            kotlin.collections.builders.ListBuilder$Itr r9 = (kotlin.collections.builders.ListBuilder.Itr) r9
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L53
            java.lang.Object r9 = r9.next()
            com.android.systemui.controls.ui.OverflowMenuAdapter$MenuItem r9 = (com.android.systemui.controls.ui.OverflowMenuAdapter.MenuItem) r9
            long r0 = r9.id
            java.lang.Long r9 = java.lang.Long.valueOf(r0)
            r7.add(r9)
            goto L3a
        L53:
            r6.ids = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.ui.OverflowMenuAdapter.<init>(android.content.Context, kotlin.collections.builders.ListBuilder, kotlin.jvm.functions.Function2):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i) {
        return ((Number) ((ArrayList) this.ids).get(i)).longValue();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return ((Boolean) this.isEnabledInternal.invoke(this, Integer.valueOf(i))).booleanValue();
    }
}
