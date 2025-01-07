package com.android.systemui.classifier;

import android.net.Uri;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.dreamliner.DockObserver;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BrightLineFalsingManager implements FalsingManager {
    public static final boolean DEBUG = Log.isLoggable("FalsingManager", 3);
    public static final Queue RECENT_INFO_LOG = new ArrayDeque(41);
    public static final Queue RECENT_SWIPES = new ArrayDeque(21);
    public final AccessibilityManager mAccessibilityManager;
    public final AnonymousClass2 mBeliefListener;
    public final Collection mClassifiers;
    public final FalsingDataProvider mDataProvider;
    public boolean mDestroyed;
    public final DoubleTapClassifier mDoubleTapClassifier;
    public final List mFalsingBeliefListeners = new ArrayList();
    public final List mFalsingTapListeners = new ArrayList();
    public final AnonymousClass3 mGestureFinalizedListener;
    public final HistoryTracker mHistoryTracker;
    public final KeyguardStateController mKeyguardStateController;
    public FalsingManager.ProximityEvent mLastProximityEvent;
    public final LongTapClassifier mLongTapClassifier;
    public final MetricsLogger mMetricsLogger;
    public int mPriorInteractionType;
    public Collection mPriorResults;
    public final AnonymousClass1 mSessionListener;
    public final SingleTapClassifier mSingleTapClassifier;
    public final boolean mTestHarness;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.classifier.BrightLineFalsingManager$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.classifier.BrightLineFalsingManager$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.classifier.BrightLineFalsingManager$3, reason: invalid class name */
    public final class AnonymousClass3 {
        public AnonymousClass3() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DebugSwipeRecord {
        public final int mInteractionType;
        public final boolean mIsFalse;
        public final List mRecentMotionEvents;

        public DebugSwipeRecord(int i, List list, boolean z) {
            this.mIsFalse = z;
            this.mInteractionType = i;
            this.mRecentMotionEvents = list;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class XYDt {
        public final int mDT;
        public final int mX;
        public final int mY;

        public XYDt(int i, int i2, int i3) {
            this.mX = i;
            this.mY = i2;
            this.mDT = i3;
        }

        public final String toString() {
            return this.mX + "," + this.mY + "," + this.mDT;
        }
    }

    public BrightLineFalsingManager(FalsingDataProvider falsingDataProvider, MetricsLogger metricsLogger, Set set, SingleTapClassifier singleTapClassifier, LongTapClassifier longTapClassifier, DoubleTapClassifier doubleTapClassifier, HistoryTracker historyTracker, KeyguardStateController keyguardStateController, AccessibilityManager accessibilityManager, boolean z) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mSessionListener = anonymousClass1;
        this.mBeliefListener = new AnonymousClass2();
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mGestureFinalizedListener = anonymousClass3;
        this.mPriorInteractionType = 7;
        this.mDataProvider = falsingDataProvider;
        this.mClassifiers = set;
        this.mSingleTapClassifier = singleTapClassifier;
        this.mLongTapClassifier = longTapClassifier;
        this.mDoubleTapClassifier = doubleTapClassifier;
        this.mHistoryTracker = historyTracker;
        this.mKeyguardStateController = keyguardStateController;
        this.mAccessibilityManager = accessibilityManager;
        this.mTestHarness = z;
        falsingDataProvider.mSessionListeners.add(anonymousClass1);
        falsingDataProvider.mGestureFinalizedListeners.add(anonymousClass3);
    }

    public static Collection getPassedResult(double d) {
        return Collections.singleton(FalsingClassifier.Result.passed(d));
    }

    public static void logDebug(String str) {
        if (DEBUG) {
            Log.d("FalsingManager", str, null);
        }
    }

    public static void logInfo(String str) {
        Log.i("FalsingManager", str);
        RECENT_INFO_LOG.add(str);
        while (true) {
            Queue queue = RECENT_INFO_LOG;
            if (((ArrayDeque) queue).size() <= 40) {
                return;
            } else {
                ((ArrayDeque) queue).remove();
            }
        }
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void addFalsingBeliefListener(FalsingManager.FalsingBeliefListener falsingBeliefListener) {
        this.mFalsingBeliefListeners.add(falsingBeliefListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void addTapListener(FalsingManager.FalsingTapListener falsingTapListener) {
        this.mFalsingTapListeners.add(falsingTapListener);
    }

    public final void checkDestroyed() {
        if (this.mDestroyed) {
            Log.wtf("FalsingManager", "Tried to use FalsingManager after being destroyed!");
        }
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void cleanupInternal() {
        this.mDestroyed = true;
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        falsingDataProvider.mSessionListeners.remove(this.mSessionListener);
        falsingDataProvider.mGestureFinalizedListeners.remove(this.mGestureFinalizedListener);
        this.mClassifiers.forEach(new BrightLineFalsingManager$$ExternalSyntheticLambda0(1));
        this.mFalsingBeliefListeners.clear();
        HistoryTracker historyTracker = this.mHistoryTracker;
        AnonymousClass2 anonymousClass2 = this.mBeliefListener;
        if (anonymousClass2 != null) {
            historyTracker.mBeliefListeners.remove(anonymousClass2);
        } else {
            historyTracker.getClass();
        }
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("BRIGHTLINE FALSING MANAGER");
        indentingPrintWriter.print("classifierEnabled=");
        indentingPrintWriter.println(1);
        indentingPrintWriter.print("mJustUnlockedWithFace=");
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        indentingPrintWriter.println(falsingDataProvider.mJustUnlockedWithFace ? 1 : 0);
        indentingPrintWriter.print("isDocked=");
        indentingPrintWriter.println((((BatteryControllerImpl) falsingDataProvider.mBatteryController).mWirelessCharging || ((DockObserver) falsingDataProvider.mDockManager).isDocked()) ? 1 : 0);
        indentingPrintWriter.print("width=");
        indentingPrintWriter.println(falsingDataProvider.mWidthPixels);
        indentingPrintWriter.print("height=");
        indentingPrintWriter.println(falsingDataProvider.mHeightPixels);
        indentingPrintWriter.println();
        ArrayDeque arrayDeque = (ArrayDeque) RECENT_SWIPES;
        if (arrayDeque.size() != 0) {
            indentingPrintWriter.println("Recent swipes:");
            indentingPrintWriter.increaseIndent();
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                DebugSwipeRecord debugSwipeRecord = (DebugSwipeRecord) it.next();
                debugSwipeRecord.getClass();
                StringJoiner stringJoiner = new StringJoiner(",");
                stringJoiner.add(Integer.toString(1)).add(debugSwipeRecord.mIsFalse ? "1" : "0").add(Integer.toString(debugSwipeRecord.mInteractionType));
                Iterator it2 = debugSwipeRecord.mRecentMotionEvents.iterator();
                while (it2.hasNext()) {
                    stringJoiner.add(((XYDt) it2.next()).toString());
                }
                indentingPrintWriter.println(stringJoiner.toString());
                indentingPrintWriter.println();
            }
            indentingPrintWriter.decreaseIndent();
        } else {
            indentingPrintWriter.println("No recent swipes");
        }
        indentingPrintWriter.println();
        indentingPrintWriter.println("Recent falsing info:");
        indentingPrintWriter.increaseIndent();
        Iterator it3 = ((ArrayDeque) RECENT_INFO_LOG).iterator();
        while (it3.hasNext()) {
            indentingPrintWriter.println((String) it3.next());
        }
        indentingPrintWriter.println();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isClassifierEnabled() {
        return true;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseDoubleTap() {
        checkDestroyed();
        if (skipFalsing(7)) {
            this.mPriorResults = getPassedResult(1.0d);
            logDebug("Skipped falsing");
            return false;
        }
        HistoryTracker historyTracker = this.mHistoryTracker;
        historyTracker.falseBelief();
        historyTracker.falseConfidence();
        FalsingClassifier.Result calculateFalsingResult = this.mDoubleTapClassifier.calculateFalsingResult(7);
        this.mPriorResults = Collections.singleton(calculateFalsingResult);
        StringBuilder sb = new StringBuilder("False Double Tap: ");
        boolean z = calculateFalsingResult.mFalsed;
        sb.append(z);
        sb.append(" reason=");
        sb.append(calculateFalsingResult.getReason());
        logDebug(sb.toString());
        return z;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseLongTap(int i) {
        checkDestroyed();
        if (skipFalsing(7)) {
            this.mPriorResults = getPassedResult(1.0d);
            logDebug("Skipped falsing");
            return false;
        }
        double d = 0.0d;
        if (i != 0) {
            if (i == 1) {
                d = 0.1d;
            } else if (i == 2) {
                d = 0.3d;
            } else if (i == 3) {
                d = 0.6d;
            }
        }
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        FalsingClassifier.Result isTap = this.mLongTapClassifier.isTap(falsingDataProvider.getRecentMotionEvents().isEmpty() ? falsingDataProvider.mPriorMotionEvents : falsingDataProvider.getRecentMotionEvents(), d);
        this.mPriorResults = Collections.singleton(isTap);
        boolean z = isTap.mFalsed;
        if (z) {
            logDebug("False Long Tap: " + z + " (simple)");
            return z;
        }
        if (falsingDataProvider.mJustUnlockedWithFace) {
            this.mPriorResults = getPassedResult(1.0d);
            logDebug("False Long Tap: false (face detected)");
        } else {
            this.mPriorResults = getPassedResult(0.1d);
            logDebug("False Long Tap: false (default)");
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0049  */
    @Override // com.android.systemui.plugins.FalsingManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isFalseTap(int r13) {
        /*
            r12 = this;
            r12.checkDestroyed()
            r0 = 7
            boolean r0 = r12.skipFalsing(r0)
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3 = 0
            if (r0 == 0) goto L19
            java.util.Collection r13 = getPassedResult(r1)
            r12.mPriorResults = r13
            java.lang.String r12 = "Skipped falsing"
            logDebug(r12)
            return r3
        L19:
            r4 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            r0 = 1
            r8 = 0
            if (r13 == 0) goto L2b
            if (r13 == r0) goto L39
            r6 = 2
            if (r13 == r6) goto L33
            r6 = 3
            if (r13 == r6) goto L2d
        L2b:
            r6 = r8
            goto L3a
        L2d:
            r6 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            goto L3a
        L33:
            r6 = 4599075939470750515(0x3fd3333333333333, double:0.3)
            goto L3a
        L39:
            r6 = r4
        L3a:
            com.android.systemui.classifier.FalsingDataProvider r13 = r12.mDataProvider
            java.util.List r10 = r13.getRecentMotionEvents()
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L49
            java.util.List r10 = r13.mPriorMotionEvents
            goto L4d
        L49:
            java.util.List r10 = r13.getRecentMotionEvents()
        L4d:
            com.android.systemui.classifier.SingleTapClassifier r11 = r12.mSingleTapClassifier
            com.android.systemui.classifier.FalsingClassifier$Result r6 = r11.isTap(r10, r6)
            java.util.Set r7 = java.util.Collections.singleton(r6)
            r12.mPriorResults = r7
            boolean r6 = r6.mFalsed
            if (r6 != 0) goto Lb8
            boolean r13 = r13.mJustUnlockedWithFace
            if (r13 == 0) goto L6d
            java.util.Collection r13 = getPassedResult(r1)
            r12.mPriorResults = r13
            java.lang.String r12 = "False Single Tap: false (face detected)"
            logDebug(r12)
            return r3
        L6d:
            boolean r13 = r12.isFalseDoubleTap()
            if (r13 != 0) goto L79
            java.lang.String r12 = "False Single Tap: false (double tapped)"
            logDebug(r12)
            return r3
        L79:
            com.android.systemui.classifier.HistoryTracker r13 = r12.mHistoryTracker
            double r1 = r13.falseBelief()
            r6 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            int r13 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r13 <= 0) goto Lac
            com.android.systemui.classifier.FalsingClassifier$Result r13 = new com.android.systemui.classifier.FalsingClassifier$Result
            r7 = 1
            java.lang.String r10 = "BrightLineFalsingManager"
            java.lang.String r11 = "bad history"
            r6 = r13
            r6.<init>(r7, r8, r10, r11)
            java.util.Set r13 = java.util.Collections.singleton(r13)
            r12.mPriorResults = r13
            java.lang.String r13 = "False Single Tap: true (bad history)"
            logDebug(r13)
            java.util.List r12 = r12.mFalsingTapListeners
            com.android.systemui.classifier.BrightLineFalsingManager$$ExternalSyntheticLambda0 r13 = new com.android.systemui.classifier.BrightLineFalsingManager$$ExternalSyntheticLambda0
            r1 = 0
            r13.<init>(r1)
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            r12.forEach(r13)
            return r0
        Lac:
            java.util.Collection r13 = getPassedResult(r4)
            r12.mPriorResults = r13
            java.lang.String r12 = "False Single Tap: false (default)"
            logDebug(r12)
            return r3
        Lb8:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "False Single Tap: "
            r12.<init>(r13)
            r12.append(r6)
            java.lang.String r13 = " (simple)"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            logDebug(r12)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.classifier.BrightLineFalsingManager.isFalseTap(int):boolean");
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseTouch(final int i) {
        checkDestroyed();
        this.mPriorInteractionType = i;
        if (skipFalsing(i)) {
            this.mPriorResults = getPassedResult(1.0d);
            logDebug("Skipped falsing");
            return false;
        }
        final boolean[] zArr = {false};
        this.mPriorResults = (Collection) this.mClassifiers.stream().map(new Function() { // from class: com.android.systemui.classifier.BrightLineFalsingManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                BrightLineFalsingManager brightLineFalsingManager = BrightLineFalsingManager.this;
                int i2 = i;
                boolean[] zArr2 = zArr;
                HistoryTracker historyTracker = brightLineFalsingManager.mHistoryTracker;
                historyTracker.falseBelief();
                historyTracker.falseConfidence();
                FalsingClassifier.Result calculateFalsingResult = ((FalsingClassifier) obj).calculateFalsingResult(i2);
                zArr2[0] = zArr2[0] | calculateFalsingResult.mFalsed;
                return calculateFalsingResult;
            }
        }).collect(Collectors.toList());
        if (i == 18) {
            zArr[0] = isFalseTap(2) & zArr[0];
        }
        StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("False Gesture (type: ", "): ", i);
        m.append(zArr[0]);
        logDebug(m.toString());
        return zArr[0];
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isProximityNear() {
        FalsingManager.ProximityEvent proximityEvent = this.mLastProximityEvent;
        return proximityEvent != null && proximityEvent.getCovered();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isReportingEnabled() {
        return false;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isSimpleTap() {
        checkDestroyed();
        this.mPriorResults = Collections.singleton(this.mSingleTapClassifier.isTap(this.mDataProvider.getRecentMotionEvents(), 0.0d));
        return !r0.mFalsed;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isUnlockingDisabled() {
        return false;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void onProximityEvent(final FalsingManager.ProximityEvent proximityEvent) {
        this.mLastProximityEvent = proximityEvent;
        this.mClassifiers.forEach(new Consumer() { // from class: com.android.systemui.classifier.BrightLineFalsingManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((FalsingClassifier) obj).onProximityEvent(FalsingManager.ProximityEvent.this);
            }
        });
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void removeFalsingBeliefListener(FalsingManager.FalsingBeliefListener falsingBeliefListener) {
        this.mFalsingBeliefListeners.remove(falsingBeliefListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void removeTapListener(FalsingManager.FalsingTapListener falsingTapListener) {
        this.mFalsingTapListeners.remove(falsingTapListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final Uri reportRejectedTouch() {
        return null;
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean shouldEnforceBouncer() {
        return false;
    }

    public final boolean skipFalsing(int i) {
        int classification;
        if (i == 16 || !((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing || this.mTestHarness) {
            return true;
        }
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        if (falsingDataProvider.mJustUnlockedWithFace) {
            return true;
        }
        if ((((BatteryControllerImpl) falsingDataProvider.mBatteryController).mWirelessCharging || ((DockObserver) falsingDataProvider.mDockManager).isDocked()) || this.mAccessibilityManager.isTouchExplorationEnabled() || falsingDataProvider.mA11YAction) {
            return true;
        }
        if ((!falsingDataProvider.mRecentMotionEvents.mInputEvents.isEmpty() && ((classification = ((MotionEvent) ((InputEvent) ((ArrayList) falsingDataProvider.mRecentMotionEvents.mInputEvents).get(0))).getClassification()) == 4 || classification == 3)) || !falsingDataProvider.mRecentKeyEvents.mInputEvents.isEmpty()) {
            return true;
        }
        return falsingDataProvider.mIsFoldableDevice && Boolean.FALSE.equals(falsingDataProvider.mFoldStateListener.getFolded());
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void onSuccessfulUnlock() {
    }
}
