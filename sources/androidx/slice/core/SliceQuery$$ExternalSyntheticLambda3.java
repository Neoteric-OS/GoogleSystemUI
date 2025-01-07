package androidx.slice.core;

import androidx.slice.SliceItem;
import androidx.slice.core.SliceQuery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class SliceQuery$$ExternalSyntheticLambda3 implements SliceQuery.Filter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SliceQuery$$ExternalSyntheticLambda3(String str, String str2, int i) {
        this.$r8$classId = i;
        this.f$0 = str;
        this.f$1 = str2;
    }

    @Override // androidx.slice.core.SliceQuery.Filter
    public final boolean filter(SliceItem sliceItem) {
        switch (this.$r8$classId) {
            case 0:
                if (!SliceQuery.checkFormat(sliceItem, this.f$0) || !this.f$1.equals(sliceItem.mSubType)) {
                }
                break;
            default:
                if (!SliceQuery.checkFormat(sliceItem, this.f$0) || !this.f$1.equals(sliceItem.mSubType)) {
                }
                break;
        }
        return false;
    }
}
