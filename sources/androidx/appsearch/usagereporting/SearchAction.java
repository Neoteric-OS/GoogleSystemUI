package androidx.appsearch.usagereporting;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SearchAction extends TakenAction {
    public final int mFetchedResultCount;
    public final String mQuery;

    public SearchAction(String str, String str2, long j, long j2, int i, String str3, int i2) {
        super(str, str2, j, j2, i);
        this.mQuery = str3;
        this.mFetchedResultCount = i2;
    }
}
