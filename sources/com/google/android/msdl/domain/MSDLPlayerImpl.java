package com.google.android.msdl.domain;

import android.os.Vibrator;
import com.google.android.msdl.data.repository.MSDLRepositoryImpl;
import com.google.android.msdl.logging.MSDLHistoryLoggerImpl;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MSDLPlayerImpl implements MSDLPlayer {
    public static final List REQUIRED_PRIMITIVES = CollectionsKt__CollectionsKt.listOf(3, 2, 7, 1);
    public final Executor executor;
    public final MSDLHistoryLoggerImpl historyLogger;
    public final MSDLRepositoryImpl repository;
    public final Map useHapticFallbackForToken;
    public final Vibrator vibrator;

    public MSDLPlayerImpl(MSDLRepositoryImpl mSDLRepositoryImpl, Vibrator vibrator, Executor executor, Map map) {
        new MSDLHistoryLoggerImpl();
    }
}
