package androidx.appsearch.usagereporting;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TakenAction {
    public final long mActionTimestampMillis;
    public final int mActionType;
    public final long mDocumentTtlMillis;
    public final String mId;
    public final String mNamespace;

    public TakenAction(String str, String str2, long j, long j2, int i) {
        str.getClass();
        this.mNamespace = str;
        str2.getClass();
        this.mId = str2;
        this.mDocumentTtlMillis = j;
        this.mActionTimestampMillis = j2;
        this.mActionType = i;
    }
}
