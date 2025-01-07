package com.android.systemui.statusbar.notification.collection.render;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SectionHeaderNodeControllerImpl implements NodeController {
    public SectionHeaderView _view;
    public final ActivityStarter activityStarter;
    public boolean clearAllButtonEnabled;
    public View.OnClickListener clearAllClickListener;
    public final String clickIntentAction;
    public final int headerTextResId;
    public final LayoutInflater layoutInflater;
    public final String nodeLabel;
    public final SectionHeaderNodeControllerImpl$onHeaderClickListener$1 onHeaderClickListener = new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl$onHeaderClickListener$1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SectionHeaderNodeControllerImpl.this.activityStarter.startActivity(new Intent(SectionHeaderNodeControllerImpl.this.clickIntentAction), true, true, 536870912);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl$onHeaderClickListener$1] */
    public SectionHeaderNodeControllerImpl(String str, LayoutInflater layoutInflater, int i, ActivityStarter activityStarter, String str2) {
        this.nodeLabel = str;
        this.layoutInflater = layoutInflater;
        this.headerTextResId = i;
        this.activityStarter = activityStarter;
        this.clickIntentAction = str2;
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final String getNodeLabel() {
        return this.nodeLabel;
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final View getView() {
        SectionHeaderView sectionHeaderView = this._view;
        Intrinsics.checkNotNull(sectionHeaderView);
        return sectionHeaderView;
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final boolean offerToKeepInParentForAnimation() {
        return false;
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final void onViewAdded() {
        SectionHeaderView sectionHeaderView = this._view;
        if (sectionHeaderView != null) {
            sectionHeaderView.setContentVisible(true, true, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void reinflateView(android.view.ViewGroup r7) {
        /*
            r6 = this;
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r0 = r6._view
            r1 = -1
            if (r0 == 0) goto L16
            r0.removeFromTransientContainer()
            android.view.ViewParent r2 = r0.getParent()
            if (r2 != r7) goto L16
            int r2 = r7.indexOfChild(r0)
            r7.removeView(r0)
            goto L17
        L16:
            r2 = r1
        L17:
            android.view.LayoutInflater r0 = r6.layoutInflater
            r3 = 2131559081(0x7f0d02a9, float:1.8743496E38)
            r4 = 0
            android.view.View r0 = r0.inflate(r3, r7, r4)
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r0 = (com.android.systemui.statusbar.notification.stack.SectionHeaderView) r0
            int r3 = r6.headerTextResId
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r0.mLabelTextId = r5
            android.widget.TextView r5 = r0.mLabelView
            r5.setText(r3)
            com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl$onHeaderClickListener$1 r3 = r6.onHeaderClickListener
            r0.mLabelClickListener = r3
            android.widget.TextView r5 = r0.mLabelView
            r5.setOnClickListener(r3)
            android.view.View$OnClickListener r3 = r6.clearAllClickListener
            if (r3 == 0) goto L44
            r0.mOnClearClickListener = r3
            android.widget.ImageView r5 = r0.mClearAllButton
            r5.setOnClickListener(r3)
        L44:
            if (r2 == r1) goto L49
            r7.addView(r0, r2)
        L49:
            r6._view = r0
            boolean r6 = r6.clearAllButtonEnabled
            android.widget.ImageView r7 = r0.mClearAllButton
            if (r6 == 0) goto L52
            goto L54
        L52:
            r4 = 8
        L54:
            r7.setVisibility(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl.reinflateView(android.view.ViewGroup):void");
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final boolean removeFromParentIfKeptForAnimation() {
        return false;
    }

    @Override // com.android.systemui.statusbar.notification.collection.render.NodeController
    public final void resetKeepInParentForAnimation() {
    }
}
