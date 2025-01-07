package com.android.systemui.biometrics;

import com.android.keyguard.logging.BiometricMessageDeferralLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceHelpMessageDeferral implements Dumpable {
    public final Map acquiredInfoToHelpString;
    public final Set acquiredInfoToIgnore;
    public final FaceHelpMessageDebouncer faceHelpMessageDebouncer;
    public final BiometricMessageDeferralLogger logBuffer;
    public final Set messagesToDefer;
    public final Lazy systemClock;
    public final float threshold;
    public int totalFrames;
    public final long windowToAnalyzeLastNFrames;

    public FaceHelpMessageDeferral(Set set, Set set2, float f, long j, BiometricMessageDeferralLogger biometricMessageDeferralLogger, DumpManager dumpManager, String str, Lazy lazy) {
        this.messagesToDefer = set;
        this.acquiredInfoToIgnore = set2;
        this.threshold = f;
        this.windowToAnalyzeLastNFrames = j;
        this.logBuffer = biometricMessageDeferralLogger;
        this.systemClock = lazy;
        this.faceHelpMessageDebouncer = new FaceHelpMessageDebouncer(f, 0, j, 0L);
        new HashMap();
        this.acquiredInfoToHelpString = new HashMap();
        dumpManager.registerNormalDumpable(getClass().getName() + "[" + str + "]", this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("messagesToDefer=" + this.messagesToDefer);
        printWriter.println("totalFrames=" + this.totalFrames);
        printWriter.println("threshold=" + this.threshold);
        printWriter.println("faceMessageDeferUpdateFlagEnabled=true");
        printWriter.println("windowToAnalyzeLastNFrames(ms)=" + this.windowToAnalyzeLastNFrames);
    }

    public final CharSequence getDeferredMessage() {
        FaceHelpMessageDebouncer faceHelpMessageDebouncer = this.faceHelpMessageDebouncer;
        if (faceHelpMessageDebouncer == null) {
            return null;
        }
        ((SystemClockImpl) ((SystemClock) this.systemClock.get())).getClass();
        HelpFaceAuthenticationStatus messageToShow = faceHelpMessageDebouncer.getMessageToShow(android.os.SystemClock.elapsedRealtime());
        return (CharSequence) this.acquiredInfoToHelpString.get(messageToShow != null ? Integer.valueOf(messageToShow.msgId) : null);
    }

    public final void processFrame(int i) {
        if (this.messagesToDefer.isEmpty()) {
            return;
        }
        boolean contains = this.acquiredInfoToIgnore.contains(Integer.valueOf(i));
        BiometricMessageDeferralLogger biometricMessageDeferralLogger = this.logBuffer;
        if (contains) {
            biometricMessageDeferralLogger.logFrameIgnored(i);
            return;
        }
        this.totalFrames++;
        Lazy lazy = this.systemClock;
        Integer num = null;
        FaceHelpMessageDebouncer faceHelpMessageDebouncer = this.faceHelpMessageDebouncer;
        if (faceHelpMessageDebouncer != null) {
            ((SystemClockImpl) ((SystemClock) lazy.get())).getClass();
            long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
            HelpFaceAuthenticationStatus helpFaceAuthenticationStatus = new HelpFaceAuthenticationStatus(i, elapsedRealtime, null);
            if (this.totalFrames == 1) {
                faceHelpMessageDebouncer.startNewFaceAuthSession(elapsedRealtime);
            }
            faceHelpMessageDebouncer.helpFaceAuthStatuses.add(helpFaceAuthenticationStatus);
            helpFaceAuthenticationStatus.toString();
        }
        int i2 = this.totalFrames;
        if (faceHelpMessageDebouncer != null) {
            ((SystemClockImpl) ((SystemClock) lazy.get())).getClass();
            HelpFaceAuthenticationStatus messageToShow = faceHelpMessageDebouncer.getMessageToShow(android.os.SystemClock.elapsedRealtime());
            if (messageToShow != null) {
                num = Integer.valueOf(messageToShow.msgId);
            }
        }
        biometricMessageDeferralLogger.logFrameProcessed(String.valueOf(num), i, i2);
    }

    public final void reset$1() {
        this.totalFrames = 0;
        this.acquiredInfoToHelpString.clear();
        BiometricMessageDeferralLogger biometricMessageDeferralLogger = this.logBuffer;
        biometricMessageDeferralLogger.logBuffer.log(biometricMessageDeferralLogger.tag, LogLevel.DEBUG, "reset", null);
    }

    public final void updateMessage(int i, String str) {
        if (this.messagesToDefer.contains(Integer.valueOf(i)) && !Objects.equals(this.acquiredInfoToHelpString.get(Integer.valueOf(i)), str)) {
            this.logBuffer.logUpdateMessage(i, str);
            this.acquiredInfoToHelpString.put(Integer.valueOf(i), str);
        }
    }
}
