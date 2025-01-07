package com.android.systemui.qs.customize;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.qs.customize.TileAdapter;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileAdapterDelegate extends AccessibilityDelegateCompat {
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    @Override // androidx.core.view.AccessibilityDelegateCompat
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onInitializeAccessibilityNodeInfo(android.view.View r10, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r11) {
        /*
            r9 = this;
            r0 = 1
            android.view.View$AccessibilityDelegate r9 = r9.mOriginalDelegate
            android.view.accessibility.AccessibilityNodeInfo r1 = r11.mInfo
            r9.onInitializeAccessibilityNodeInfo(r10, r1)
            java.lang.Object r9 = r10.getTag()
            com.android.systemui.qs.customize.TileAdapter$Holder r9 = (com.android.systemui.qs.customize.TileAdapter.Holder) r9
            r1 = 0
            r11.setCollectionItemInfo(r1)
            android.view.accessibility.AccessibilityNodeInfo r1 = r11.mInfo
            java.lang.String r2 = ""
            r1.setStateDescription(r2)
            if (r9 == 0) goto Lf9
            com.android.systemui.qs.customize.TileAdapter r1 = com.android.systemui.qs.customize.TileAdapter.this
            int r2 = r1.mAccessibilityAction
            if (r2 != 0) goto Lf9
            int r2 = r9.getLayoutPosition()
            int r3 = r1.mEditIndex
            r4 = 0
            if (r2 <= r3) goto L2c
            r2 = r0
            goto L2d
        L2c:
            r2 = r4
        L2d:
            r3 = 16
            if (r2 == 0) goto L3d
            android.content.Context r2 = r10.getContext()
            r4 = 2131951817(0x7f1300c9, float:1.954006E38)
            java.lang.String r2 = r2.getString(r4)
            goto L5c
        L3d:
            int r2 = r9.getLayoutPosition()
            java.util.List r5 = r1.mCurrentSpecs
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r5 = r5.size()
            int r6 = r1.mMinNumTiles
            if (r5 <= r6) goto L6a
            int r5 = r1.mEditIndex
            if (r2 >= r5) goto L6a
            android.content.Context r2 = r10.getContext()
            r4 = 2131951816(0x7f1300c8, float:1.9540057E38)
            java.lang.String r2 = r2.getString(r4)
        L5c:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r4 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat
            r4.<init>(r3, r2)
            r11.addAction(r4)
            android.view.accessibility.AccessibilityNodeInfo r2 = r11.mInfo
            r2.setClickable(r0)
            goto L99
        L6a:
            java.util.List r2 = r11.getActionList()
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r5 = r2.size()
            r6 = r4
        L75:
            if (r6 >= r5) goto L94
            java.lang.Object r7 = r2.get(r6)
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r7 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r7
            int r7 = r7.getId()
            if (r7 != r3) goto L92
            java.lang.Object r7 = r2.get(r6)
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r7 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) r7
            android.view.accessibility.AccessibilityNodeInfo r8 = r11.mInfo
            java.lang.Object r7 = r7.mAction
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r7 = (android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction) r7
            r8.removeAction(r7)
        L92:
            int r6 = r6 + r0
            goto L75
        L94:
            android.view.accessibility.AccessibilityNodeInfo r0 = r11.mInfo
            r0.setClickable(r4)
        L99:
            int r0 = r9.getLayoutPosition()
            int r2 = r1.mEditIndex
            if (r0 <= r2) goto Lb7
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r0 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat
            android.content.Context r2 = r10.getContext()
            r3 = 2131951822(0x7f1300ce, float:1.954007E38)
            java.lang.String r2 = r2.getString(r3)
            r3 = 2131361834(0x7f0a002a, float:1.8343432E38)
            r0.<init>(r3, r2)
            r11.addAction(r0)
        Lb7:
            int r0 = r9.getLayoutPosition()
            int r2 = r1.mEditIndex
            if (r0 >= r2) goto Ld5
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r0 = new androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat
            android.content.Context r2 = r10.getContext()
            r3 = 2131951823(0x7f1300cf, float:1.9540071E38)
            java.lang.String r2 = r2.getString(r3)
            r3 = 2131361835(0x7f0a002b, float:1.8343434E38)
            r0.<init>(r3, r2)
            r11.addAction(r0)
        Ld5:
            int r0 = r9.getLayoutPosition()
            int r1 = r1.mEditIndex
            if (r0 >= r1) goto Lf9
            android.content.Context r10 = r10.getContext()
            int r9 = r9.getLayoutPosition()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            r0 = 2131951815(0x7f1300c7, float:1.9540055E38)
            java.lang.String r9 = r10.getString(r0, r9)
            android.view.accessibility.AccessibilityNodeInfo r10 = r11.mInfo
            r10.setStateDescription(r9)
        Lf9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.customize.TileAdapterDelegate.onInitializeAccessibilityNodeInfo(android.view.View, androidx.core.view.accessibility.AccessibilityNodeInfoCompat):void");
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        TileAdapter.Holder holder = (TileAdapter.Holder) view.getTag();
        if (holder != null) {
            final TileAdapter tileAdapter = TileAdapter.this;
            if (tileAdapter.mAccessibilityAction == 0) {
                if (i == 16) {
                    if (holder.getLayoutPosition() > tileAdapter.mEditIndex) {
                        int layoutPosition = holder.getLayoutPosition();
                        int i2 = tileAdapter.mEditIndex;
                        if (layoutPosition > i2) {
                            tileAdapter.move(layoutPosition, i2, true);
                            View view2 = holder.itemView;
                            view2.announceForAccessibility(view2.getContext().getText(R.string.accessibility_qs_edit_tile_added));
                        }
                    } else {
                        int layoutPosition2 = holder.getLayoutPosition();
                        if (((ArrayList) tileAdapter.mCurrentSpecs).size() > tileAdapter.mMinNumTiles && layoutPosition2 < tileAdapter.mEditIndex) {
                            tileAdapter.move(layoutPosition2, ((TileQueryHelper.TileInfo) ((ArrayList) tileAdapter.mTiles).get(layoutPosition2)).isSystem ? tileAdapter.mEditIndex : tileAdapter.mTileDividerIndex, true);
                            View view3 = holder.itemView;
                            view3.announceForAccessibility(view3.getContext().getText(R.string.accessibility_qs_edit_tile_removed));
                        }
                    }
                    return true;
                }
                if (i == R.id.accessibility_action_qs_move_to_position) {
                    int layoutPosition3 = holder.getLayoutPosition();
                    tileAdapter.mAccessibilityFromIndex = layoutPosition3;
                    tileAdapter.mAccessibilityAction = 2;
                    tileAdapter.mFocusIndex = layoutPosition3;
                    tileAdapter.mNeedsFocus = true;
                    tileAdapter.notifyDataSetChanged();
                    return true;
                }
                if (i != R.id.accessibility_action_qs_add_to_position) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                tileAdapter.mAccessibilityFromIndex = holder.getLayoutPosition();
                tileAdapter.mAccessibilityAction = 1;
                List list = tileAdapter.mTiles;
                int i3 = tileAdapter.mEditIndex;
                tileAdapter.mEditIndex = i3 + 1;
                list.add(i3, null);
                tileAdapter.mTileDividerIndex++;
                final int i4 = tileAdapter.mEditIndex - 1;
                tileAdapter.mFocusIndex = i4;
                tileAdapter.mNeedsFocus = true;
                RecyclerView recyclerView = tileAdapter.mRecyclerView;
                if (recyclerView != null) {
                    recyclerView.post(new Runnable() { // from class: com.android.systemui.qs.customize.TileAdapter$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            TileAdapter tileAdapter2 = TileAdapter.this;
                            int i5 = i4;
                            RecyclerView recyclerView2 = tileAdapter2.mRecyclerView;
                            if (recyclerView2 != null) {
                                recyclerView2.smoothScrollToPosition(i5);
                            }
                        }
                    });
                }
                tileAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return super.performAccessibilityAction(view, i, bundle);
    }
}
