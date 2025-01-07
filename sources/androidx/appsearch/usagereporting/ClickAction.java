package androidx.appsearch.usagereporting;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClickAction extends TakenAction {
    public final String mQuery;
    public final String mReferencedQualifiedId;
    public final int mResultRankGlobal;
    public final int mResultRankInBlock;
    public final long mTimeStayOnResultMillis;

    public ClickAction(String str, String str2, long j, long j2, int i, String str3, String str4, int i2, int i3, long j3) {
        super(str, str2, j, j2, i);
        this.mQuery = str3;
        this.mReferencedQualifiedId = str4;
        this.mResultRankInBlock = i2;
        this.mResultRankGlobal = i3;
        this.mTimeStayOnResultMillis = j3;
    }
}
