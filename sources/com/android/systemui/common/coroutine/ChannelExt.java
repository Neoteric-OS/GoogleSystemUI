package com.android.systemui.common.coroutine;

import android.util.Log;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChannelExt {
    public static final ChannelExt INSTANCE = new ChannelExt();

    public static void trySendWithFailureLogging$default(ChannelExt channelExt, SendChannel sendChannel, Object obj, String str) {
        channelExt.getClass();
        Object mo1790trySendJP2dKIU = sendChannel.mo1790trySendJP2dKIU(obj);
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            Log.e(str, "Failed to send updated state - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
    }
}
