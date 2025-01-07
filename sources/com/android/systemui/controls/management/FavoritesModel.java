package com.android.systemui.controls.management;

import android.content.ComponentName;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.controls.ControlInterface;
import com.android.systemui.controls.CustomIconCache;
import com.android.systemui.controls.controller.ControlInfo;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FavoritesModel implements ControlsModel {
    public ControlAdapter adapter;
    public final ComponentName componentName;
    public final CustomIconCache customIconCache;
    public int dividerPosition;
    public final List elements;
    public final ControlsEditingActivity$favoritesModelCallback$1 favoritesModelCallback;
    public final FavoritesModel$itemTouchHelperCallback$1 itemTouchHelperCallback;
    public boolean modified;
    public final FavoritesModel$moveHelper$1 moveHelper = new FavoritesModel$moveHelper$1(this);

    /* JADX WARN: Type inference failed for: r10v7, types: [com.android.systemui.controls.management.FavoritesModel$itemTouchHelperCallback$1] */
    public FavoritesModel(CustomIconCache customIconCache, ComponentName componentName, List list, ControlsEditingActivity$favoritesModelCallback$1 controlsEditingActivity$favoritesModelCallback$1) {
        this.customIconCache = customIconCache;
        this.componentName = componentName;
        this.favoritesModelCallback = controlsEditingActivity$favoritesModelCallback$1;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ControlInfoWrapper(this.componentName, (ControlInfo) it.next(), new FavoritesModel$elements$1$1(2, this.customIconCache, CustomIconCache.class, "retrieve", "retrieve(Landroid/content/ComponentName;Ljava/lang/String;)Landroid/graphics/drawable/Icon;", 0)));
        }
        DividerWrapper dividerWrapper = new DividerWrapper();
        dividerWrapper.showNone = false;
        dividerWrapper.showDivider = false;
        this.elements = CollectionsKt.plus(arrayList, dividerWrapper);
        this.dividerPosition = ((ArrayList) r10).size() - 1;
        this.itemTouchHelperCallback = new ItemTouchHelper.Callback() { // from class: com.android.systemui.controls.management.FavoritesModel$itemTouchHelperCallback$1
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final boolean canDropOver(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                return viewHolder2.getBindingAdapterPosition() < FavoritesModel.this.dividerPosition;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final int getMovementFlags(RecyclerView.ViewHolder viewHolder) {
                return viewHolder.getBindingAdapterPosition() < FavoritesModel.this.dividerPosition ? ItemTouchHelper.Callback.makeMovementFlags(15) : ItemTouchHelper.Callback.makeMovementFlags(0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final boolean onMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                FavoritesModel.this.onMoveItemInternal(viewHolder.getBindingAdapterPosition(), viewHolder2.getBindingAdapterPosition());
                return true;
            }
        };
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final void changeFavoriteStatus(String str, boolean z) {
        Iterator it = this.elements.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Object obj = (ElementWrapper) it.next();
            if ((obj instanceof ControlInterface) && Intrinsics.areEqual(((ControlInterface) obj).getControlId(), str)) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        int i2 = this.dividerPosition;
        if (i >= i2 || !z) {
            if (i <= i2 || z) {
                if (z) {
                    onMoveItemInternal(i, i2);
                } else {
                    onMoveItemInternal(i, ((ArrayList) this.elements).size() - 1);
                }
            }
        }
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final List getElements() {
        return this.elements;
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final FavoritesModel$moveHelper$1 getMoveHelper() {
        return this.moveHelper;
    }

    public final void onMoveItemInternal(int i, int i2) {
        ControlAdapter controlAdapter;
        boolean z;
        ControlAdapter controlAdapter2;
        int i3 = this.dividerPosition;
        if (i == i3) {
            return;
        }
        boolean z2 = false;
        if ((i < i3 && i2 >= i3) || (i > i3 && i2 <= i3)) {
            if (i < i3 && i2 >= i3) {
                ((ControlInfoWrapper) ((ArrayList) this.elements).get(i)).favorite = false;
            } else if (i > i3 && i2 <= i3) {
                ((ControlInfoWrapper) ((ArrayList) this.elements).get(i)).favorite = true;
            }
            int i4 = this.dividerPosition;
            if (i >= i4 || i2 < i4) {
                if (i > i4 && i2 <= i4) {
                    int i5 = i4 + 1;
                    this.dividerPosition = i5;
                    if (i5 == 1) {
                        updateDividerNone(i4, false);
                        z = true;
                    } else {
                        z = false;
                    }
                    if (this.dividerPosition == ((ArrayList) this.elements).size() - 1) {
                        ((DividerWrapper) ((ArrayList) this.elements).get(i4)).showDivider = false;
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                }
                if (z2 && (controlAdapter2 = this.adapter) != null) {
                    controlAdapter2.notifyItemChanged(i4);
                }
                z2 = true;
            } else {
                int i6 = i4 - 1;
                this.dividerPosition = i6;
                if (i6 == 0) {
                    updateDividerNone(i4, true);
                    z2 = true;
                }
                if (this.dividerPosition == ((ArrayList) this.elements).size() - 2) {
                    ((DividerWrapper) ((ArrayList) this.elements).get(i4)).showDivider = true;
                    z2 = true;
                }
                if (z2) {
                    controlAdapter2.notifyItemChanged(i4);
                }
                z2 = true;
            }
        }
        if (i < i2) {
            int i7 = i;
            while (i7 < i2) {
                int i8 = i7 + 1;
                Collections.swap(this.elements, i7, i8);
                i7 = i8;
            }
        } else {
            int i9 = i2 + 1;
            if (i9 <= i) {
                int i10 = i;
                while (true) {
                    Collections.swap(this.elements, i10, i10 - 1);
                    if (i10 == i9) {
                        break;
                    } else {
                        i10--;
                    }
                }
            }
        }
        ControlAdapter controlAdapter3 = this.adapter;
        if (controlAdapter3 != null) {
            controlAdapter3.mObservable.notifyItemMoved(i, i2);
        }
        if (z2 && (controlAdapter = this.adapter) != null) {
            controlAdapter.mObservable.notifyItemRangeChanged(i2, 1, new Object());
        }
        if (this.modified) {
            return;
        }
        this.modified = true;
        this.favoritesModelCallback.onFirstChange();
    }

    public final void updateDividerNone(int i, boolean z) {
        TextView textView;
        ((DividerWrapper) ((ArrayList) this.elements).get(i)).showNone = z;
        ControlsEditingActivity controlsEditingActivity = this.favoritesModelCallback.this$0;
        if (z) {
            TextView textView2 = controlsEditingActivity.subtitle;
            textView = textView2 != null ? textView2 : null;
            int i2 = ControlsEditingActivity.$r8$clinit;
            textView.setText(R.string.controls_favorite_removed);
            return;
        }
        TextView textView3 = controlsEditingActivity.subtitle;
        textView = textView3 != null ? textView3 : null;
        int i3 = ControlsEditingActivity.$r8$clinit;
        textView.setText(R.string.controls_favorite_rearrange);
    }
}
