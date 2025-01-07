package androidx.appsearch.exceptions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class AppSearchException extends Exception {
    private final int mResultCode;

    public AppSearchException(int i, String str, Throwable th) {
        super(str, th);
        this.mResultCode = i;
    }
}
