package androidx.slice.builders;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.core.SliceActionImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceAction implements androidx.slice.core.SliceAction {
    public final SliceActionImpl mSliceAction;

    public SliceAction(PendingIntent pendingIntent, IconCompat iconCompat, CharSequence charSequence) {
        this.mSliceAction = new SliceActionImpl(pendingIntent, iconCompat, 0, charSequence);
    }

    @Override // androidx.slice.core.SliceAction
    public final int getPriority() {
        return this.mSliceAction.mPriority;
    }

    @Override // androidx.slice.core.SliceAction
    public final boolean isToggle() {
        return this.mSliceAction.isToggle();
    }

    public final void setPrimaryAction(Slice.Builder builder) {
        SliceActionImpl sliceActionImpl = this.mSliceAction;
        PendingIntent pendingIntent = sliceActionImpl.mAction;
        if (pendingIntent == null) {
            pendingIntent = sliceActionImpl.mActionItem.getAction();
        }
        Slice.Builder buildSliceContent = sliceActionImpl.buildSliceContent(builder);
        buildSliceContent.addHints("shortcut", "title");
        builder.addAction(pendingIntent, buildSliceContent.build(), sliceActionImpl.getSubtype());
    }
}
