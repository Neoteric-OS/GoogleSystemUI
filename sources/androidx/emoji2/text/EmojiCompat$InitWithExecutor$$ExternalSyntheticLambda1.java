package androidx.emoji2.text;

import androidx.emoji2.text.EmojiCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class EmojiCompat$InitWithExecutor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ EmojiCompat.InitWithExecutor f$0;
    public final /* synthetic */ Throwable f$1;

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.mInitCallback.onFailed();
    }
}
