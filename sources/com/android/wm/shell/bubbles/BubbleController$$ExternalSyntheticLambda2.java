package com.android.wm.shell.bubbles;

import android.os.Parcel;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda2 implements Bubbles.BubbleExpandListener, SingleInstanceRemoteListener.RemoteCall {
    public final /* synthetic */ Object f$0;

    @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
    public void accept(Object obj) {
        BubbleBarLocation bubbleBarLocation = (BubbleBarLocation) this.f$0;
        IBubblesListener$Stub$Proxy iBubblesListener$Stub$Proxy = (IBubblesListener$Stub$Proxy) obj;
        Parcel obtain = Parcel.obtain(iBubblesListener$Stub$Proxy.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.bubbles.IBubblesListener");
            obtain.writeTypedObject(bubbleBarLocation, 0);
            iBubblesListener$Stub$Proxy.mRemote.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.android.wm.shell.bubbles.Bubbles.BubbleExpandListener
    public void onBubbleExpandChanged(String str, boolean z) {
        Bubbles.BubbleExpandListener bubbleExpandListener = (Bubbles.BubbleExpandListener) this.f$0;
        if (bubbleExpandListener != null) {
            bubbleExpandListener.onBubbleExpandChanged(str, z);
        }
    }
}
