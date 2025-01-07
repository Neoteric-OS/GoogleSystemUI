package androidx.slice.core;

import androidx.slice.SliceItem;
import androidx.slice.core.SliceQuery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class SliceQuery$$ExternalSyntheticLambda1 implements SliceQuery.Filter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ String[] f$2;

    public /* synthetic */ SliceQuery$$ExternalSyntheticLambda1(String str, String[] strArr, String[] strArr2, int i) {
        this.$r8$classId = i;
        this.f$0 = str;
        this.f$1 = strArr;
        this.f$2 = strArr2;
    }

    @Override // androidx.slice.core.SliceQuery.Filter
    public final boolean filter(SliceItem sliceItem) {
        switch (this.$r8$classId) {
            case 0:
                if (!SliceQuery.checkFormat(sliceItem, this.f$0) || !SliceQuery.hasHints(sliceItem, this.f$1) || SliceQuery.hasAnyHints(sliceItem, this.f$2)) {
                }
                break;
            case 1:
                if (!SliceQuery.checkFormat(sliceItem, this.f$0) || !SliceQuery.hasHints(sliceItem, this.f$1) || SliceQuery.hasAnyHints(sliceItem, this.f$2)) {
                }
                break;
            default:
                if (!SliceQuery.checkFormat(sliceItem, this.f$0) || !SliceQuery.hasHints(sliceItem, this.f$1) || SliceQuery.hasAnyHints(sliceItem, this.f$2)) {
                }
                break;
        }
        return false;
    }
}
