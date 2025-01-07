package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.ArraySet;
import androidx.collection.IntListKt;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.internal.RuntimeHelpersKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    public static final MutableIntList AccessibilityActionsResourceIds;
    public final String ExtraDataTestTraversalAfterVal;
    public final String ExtraDataTestTraversalBeforeVal;
    public final long SendRecurringAccessibilityEventsIntervalMillis;
    public int accessibilityCursorPosition;
    public final AccessibilityManager accessibilityManager;
    public final SparseArrayCompat actionIdToLabel;
    public final BufferedChannel boundsUpdateChannel;
    public boolean checkingForSemanticsChanges;
    public MutableIntObjectMap currentSemanticsNodes;
    public boolean currentSemanticsNodesInvalidated;
    public AccessibilityNodeInfoCompat currentlyFocusedANI;
    public List enabledServices;
    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1 enabledStateListener;
    public int focusedVirtualViewId;
    public final Handler handler;
    public final MutableIntIntMap idToAfterMap;
    public final MutableIntIntMap idToBeforeMap;
    public final SparseArrayCompat labelToActionId;
    public final ComposeAccessibilityNodeProvider nodeProvider;
    public final MutableIntSet paneDisplayed;
    public final MutableIntObjectMap pendingHorizontalScrollEvents;
    public PendingTextTraversedEvent pendingTextTraversedEvent;
    public final MutableIntObjectMap pendingVerticalScrollEvents;
    public final MutableIntObjectMap previousSemanticsNodes;
    public SemanticsNodeCopy previousSemanticsRoot;
    public Integer previousTraversedNode;
    public final Function1 scheduleScrollEventIfNeededLambda;
    public final List scrollObservationScopes;
    public final Comparator[] semanticComparators;
    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda3 semanticsChangeChecker;
    public boolean sendingFocusAffectingEvent;
    public final ArraySet subtreeChangedLayoutNodes;
    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2 touchExplorationStateListener;
    public final URLSpanCache urlSpanCache;
    public final AndroidComposeView view;
    public int hoveredVirtualViewId = Integer.MIN_VALUE;
    public final Function1 onSendAccessibilityEvent = new AndroidComposeViewAccessibilityDelegateCompat$onSendAccessibilityEvent$1(this);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ComposeAccessibilityNodeProvider extends AccessibilityNodeProviderCompat {
        public ComposeAccessibilityNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, Bundle bundle) {
            MutableIntList mutableIntList = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(i, accessibilityNodeInfoCompat, str, bundle);
        }

        /* JADX WARN: Code restructure failed: missing block: B:203:0x04c3, code lost:
        
            if ((r5 != null ? kotlin.jvm.internal.Intrinsics.areEqual(androidx.compose.ui.semantics.SemanticsConfigurationKt.getOrNull(r5, r4), java.lang.Boolean.TRUE) : false) == false) goto L210;
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0933  */
        /* JADX WARN: Removed duplicated region for block: B:197:0x04c9  */
        /* JADX WARN: Removed duplicated region for block: B:365:0x08ee  */
        /* JADX WARN: Removed duplicated region for block: B:376:0x090e  */
        /* JADX WARN: Type inference failed for: r11v17, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        /* JADX WARN: Type inference failed for: r5v70, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.core.view.accessibility.AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r22) {
            /*
                Method dump skipped, instructions count: 2386
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.ComposeAccessibilityNodeProvider.createAccessibilityNodeInfo(int):androidx.core.view.accessibility.AccessibilityNodeInfoCompat");
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public final AccessibilityNodeInfoCompat findFocus(int i) {
            return createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId);
        }

        /* JADX WARN: Code restructure failed: missing block: B:161:0x0170, code lost:
        
            r1 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:432:0x0613, code lost:
        
            if (r0 != 16) goto L399;
         */
        /* JADX WARN: Removed duplicated region for block: B:125:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x01f9  */
        /* JADX WARN: Removed duplicated region for block: B:137:0x0216  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x022d  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x023d  */
        /* JADX WARN: Removed duplicated region for block: B:156:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:488:0x071b  */
        /* JADX WARN: Removed duplicated region for block: B:490:0x071d  */
        /* JADX WARN: Type inference failed for: r0v181, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean performAction(int r18, int r19, android.os.Bundle r20) {
            /*
                Method dump skipped, instructions count: 1976
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.ComposeAccessibilityNodeProvider.performAction(int, int, android.os.Bundle):boolean");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class PendingTextTraversedEvent {
        public final int action;
        public final int fromIndex;
        public final int granularity;
        public final SemanticsNode node;
        public final int toIndex;
        public final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode semanticsNode, int i, int i2, int i3, int i4, long j) {
            this.node = semanticsNode;
            this.action = i;
            this.granularity = i2;
            this.fromIndex = i3;
            this.toIndex = i4;
            this.traverseTime = j;
        }
    }

    static {
        int[] iArr = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
        MutableIntList mutableIntList = IntListKt.EmptyIntList;
        MutableIntList mutableIntList2 = new MutableIntList(32);
        int i = mutableIntList2._size;
        if (i < 0) {
            RuntimeHelpersKt.throwIndexOutOfBoundsException("");
            throw null;
        }
        int i2 = i + 32;
        mutableIntList2.ensureCapacity(i2);
        int[] iArr2 = mutableIntList2.content;
        int i3 = mutableIntList2._size;
        if (i != i3) {
            ArraysKt.copyInto(i2, i, i3, iArr2, iArr2);
        }
        ArraysKt.copyInto$default(i, 0, 12, iArr, iArr2);
        mutableIntList2._size += 32;
        AccessibilityActionsResourceIds = mutableIntList2;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r6v2, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda3] */
    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        AccessibilityManager accessibilityManager = (AccessibilityManager) androidComposeView.getContext().getSystemService("accessibility");
        this.accessibilityManager = accessibilityManager;
        this.SendRecurringAccessibilityEventsIntervalMillis = 100L;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                List<AccessibilityServiceInfo> list;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                if (z) {
                    list = androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1);
                } else {
                    MutableIntList mutableIntList = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
                    list = EmptyList.INSTANCE;
                }
                androidComposeViewAccessibilityDelegateCompat.enabledServices = list;
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                androidComposeViewAccessibilityDelegateCompat.enabledServices = androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new ComposeAccessibilityNodeProvider();
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.pendingHorizontalScrollEvents = new MutableIntObjectMap();
        this.pendingVerticalScrollEvents = new MutableIntObjectMap();
        int i = 0;
        this.actionIdToLabel = new SparseArrayCompat(0);
        this.labelToActionId = new SparseArrayCompat(0);
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet(0);
        this.boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6);
        this.currentSemanticsNodesInvalidated = true;
        MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
        this.currentSemanticsNodes = mutableIntObjectMap;
        this.paneDisplayed = new MutableIntSet();
        this.idToBeforeMap = new MutableIntIntMap();
        this.idToAfterMap = new MutableIntIntMap();
        this.ExtraDataTestTraversalBeforeVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.ExtraDataTestTraversalAfterVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = new MutableIntObjectMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(androidComposeView.semanticsOwner.getUnmergedRootSemanticsNode(), mutableIntObjectMap);
        androidComposeView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                AccessibilityManager accessibilityManager2 = androidComposeViewAccessibilityDelegateCompat.accessibilityManager;
                accessibilityManager2.addAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                accessibilityManager2.addTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                androidComposeViewAccessibilityDelegateCompat.handler.removeCallbacks(androidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker);
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat2 = AndroidComposeViewAccessibilityDelegateCompat.this;
                AccessibilityManager accessibilityManager2 = androidComposeViewAccessibilityDelegateCompat2.accessibilityManager;
                accessibilityManager2.removeAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat2.enabledStateListener);
                accessibilityManager2.removeTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat2.touchExplorationStateListener);
            }
        });
        Comparator[] comparatorArr = new Comparator[2];
        while (i < 2) {
            Comparator comparator = i == 0 ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE;
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            final AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1 androidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1 = new AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1(comparator);
            comparatorArr[i] = new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$2
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare = AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1.this.compare(obj, obj2);
                    return compare != 0 ? compare : ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(((SemanticsNode) obj).id), Integer.valueOf(((SemanticsNode) obj2).id));
                }
            };
            i++;
        }
        this.semanticComparators = comparatorArr;
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                MutableIntList mutableIntList = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
                Trace.beginSection("measureAndLayout");
                try {
                    androidComposeViewAccessibilityDelegateCompat.view.measureAndLayout(true);
                    Trace.endSection();
                    Trace.beginSection("checkForSemanticsChanges");
                    try {
                        androidComposeViewAccessibilityDelegateCompat.checkForSemanticsChanges();
                        Trace.endSection();
                        androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges = false;
                    } finally {
                    }
                } finally {
                }
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.scheduleScrollEventIfNeededLambda = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ScrollObservationScope scrollObservationScope = (ScrollObservationScope) obj;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                MutableIntList mutableIntList = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
                androidComposeViewAccessibilityDelegateCompat.getClass();
                if (scrollObservationScope.allScopes.contains(scrollObservationScope)) {
                    androidComposeViewAccessibilityDelegateCompat.view.snapshotObserver.observeReads$ui_release(scrollObservationScope, androidComposeViewAccessibilityDelegateCompat.scheduleScrollEventIfNeededLambda, new AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1(scrollObservationScope, androidComposeViewAccessibilityDelegateCompat));
                }
                return Unit.INSTANCE;
            }
        };
    }

    public static boolean getInfoIsCheckable(SemanticsNode semanticsNode) {
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.ToggleableState);
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Role;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey);
        boolean z = toggleableState != null;
        if (((Boolean) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.Selected)) != null) {
            return role != null ? Role.m574equalsimpl0(role.value, 4) : false ? z : true;
        }
        return z;
    }

    public static AnnotatedString getInfoText(SemanticsNode semanticsNode) {
        AnnotatedString annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.EditableText);
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.Text);
        return annotatedString == null ? list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null : annotatedString;
    }

    public static String getIterableTextForAccessibility(SemanticsNode semanticsNode) {
        AnnotatedString annotatedString;
        if (semanticsNode == null) {
            return null;
        }
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ContentDescription;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (semanticsConfiguration.props.containsKey(semanticsPropertyKey)) {
            return ListUtilsKt.fastJoinToString$default((List) semanticsConfiguration.get(semanticsPropertyKey), ",", null, 62);
        }
        SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.EditableText;
        if (semanticsConfiguration.props.containsKey(semanticsPropertyKey2)) {
            AnnotatedString annotatedString2 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
            if (annotatedString2 != null) {
                return annotatedString2.text;
            }
            return null;
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.Text);
        if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        return annotatedString.text;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public static final boolean performActionHelper$canScroll(ScrollAxisRange scrollAxisRange, float f) {
        ?? r2 = scrollAxisRange.value;
        return (f < 0.0f && ((Number) r2.invoke()).floatValue() > 0.0f) || (f > 0.0f && ((Number) r2.invoke()).floatValue() < ((Number) scrollAxisRange.maxValue.invoke()).floatValue());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange scrollAxisRange) {
        ?? r0 = scrollAxisRange.value;
        float floatValue = ((Number) r0.invoke()).floatValue();
        boolean z = scrollAxisRange.reverseScrolling;
        return (floatValue > 0.0f && !z) || (((Number) r0.invoke()).floatValue() < ((Number) scrollAxisRange.maxValue.invoke()).floatValue() && z);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange scrollAxisRange) {
        ?? r0 = scrollAxisRange.value;
        float floatValue = ((Number) r0.invoke()).floatValue();
        float floatValue2 = ((Number) scrollAxisRange.maxValue.invoke()).floatValue();
        boolean z = scrollAxisRange.reverseScrolling;
        return (floatValue < floatValue2 && !z) || (((Number) r0.invoke()).floatValue() > 0.0f && z);
    }

    public static /* synthetic */ void sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, int i3) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, null);
    }

    public static CharSequence trimToSize(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            int i = 100000;
            if (charSequence.length() > 100000) {
                if (Character.isHighSurrogate(charSequence.charAt(99999)) && Character.isLowSurrogate(charSequence.charAt(100000))) {
                    i = 99999;
                }
                return charSequence.subSequence(0, i);
            }
        }
        return charSequence;
    }

    public final void addExtraDataToAccessibilityNodeInfoHelper(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, Bundle bundle) {
        SemanticsNode semanticsNode;
        int i2;
        int i3;
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(i);
        if (semanticsNodeWithAdjustedBounds == null || (semanticsNode = semanticsNodeWithAdjustedBounds.semanticsNode) == null) {
            return;
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
        if (Intrinsics.areEqual(str, androidComposeViewAccessibilityDelegateCompat.ExtraDataTestTraversalBeforeVal)) {
            int orDefault = androidComposeViewAccessibilityDelegateCompat.idToBeforeMap.getOrDefault(i);
            if (orDefault != -1) {
                accessibilityNodeInfoCompat.mInfo.getExtras().putInt(str, orDefault);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(str, androidComposeViewAccessibilityDelegateCompat.ExtraDataTestTraversalAfterVal)) {
            int orDefault2 = androidComposeViewAccessibilityDelegateCompat.idToAfterMap.getOrDefault(i);
            if (orDefault2 != -1) {
                accessibilityNodeInfoCompat.mInfo.getExtras().putInt(str, orDefault2);
                return;
            }
            return;
        }
        SemanticsPropertyKey semanticsPropertyKey = SemanticsActions.GetTextLayoutResult;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (!semanticsConfiguration.props.containsKey(semanticsPropertyKey) || bundle == null || !Intrinsics.areEqual(str, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.TestTag;
            if (!semanticsConfiguration.props.containsKey(semanticsPropertyKey2) || bundle == null || !Intrinsics.areEqual(str, "androidx.compose.ui.semantics.testTag")) {
                if (Intrinsics.areEqual(str, "androidx.compose.ui.semantics.id")) {
                    accessibilityNodeInfoCompat.mInfo.getExtras().putInt(str, semanticsNode.id);
                    return;
                }
                return;
            } else {
                String str2 = (String) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
                if (str2 != null) {
                    accessibilityNodeInfoCompat.mInfo.getExtras().putCharSequence(str, str2);
                    return;
                }
                return;
            }
        }
        int i4 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int i5 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if (i5 > 0 && i4 >= 0) {
            if (i4 < (iterableTextForAccessibility != null ? iterableTextForAccessibility.length() : Integer.MAX_VALUE)) {
                TextLayoutResult textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(semanticsConfiguration);
                if (textLayoutResult == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int i6 = 0;
                while (i6 < i5) {
                    int i7 = i4 + i6;
                    RectF rectF = null;
                    if (i7 >= textLayoutResult.layoutInput.text.text.length()) {
                        arrayList.add(null);
                        i2 = i4;
                        i3 = i6;
                    } else {
                        Rect boundingBox = textLayoutResult.getBoundingBox(i7);
                        NodeCoordinator findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
                        long j = 0;
                        if (findCoordinatorToGetBounds$ui_release != null) {
                            if (!findCoordinatorToGetBounds$ui_release.getTail().isAttached) {
                                findCoordinatorToGetBounds$ui_release = null;
                            }
                            if (findCoordinatorToGetBounds$ui_release != null) {
                                j = findCoordinatorToGetBounds$ui_release.mo484localToRootMKHz9U(0L);
                            }
                        }
                        Rect m323translatek4lQ0M = boundingBox.m323translatek4lQ0M(j);
                        Rect boundsInRoot = semanticsNode.getBoundsInRoot();
                        if ((m323translatek4lQ0M.overlaps(boundsInRoot) ? m323translatek4lQ0M.intersect(boundsInRoot) : null) != null) {
                            AndroidComposeView androidComposeView = androidComposeViewAccessibilityDelegateCompat.view;
                            long m555localToScreenMKHz9U = androidComposeView.m555localToScreenMKHz9U((Float.floatToRawIntBits(r10.left) << 32) | (Float.floatToRawIntBits(r10.top) & 4294967295L));
                            i3 = i6;
                            i2 = i4;
                            long m555localToScreenMKHz9U2 = androidComposeView.m555localToScreenMKHz9U((Float.floatToRawIntBits(r10.bottom) & 4294967295L) | (Float.floatToRawIntBits(r10.right) << 32));
                            rectF = new RectF(Float.intBitsToFloat((int) (m555localToScreenMKHz9U >> 32)), Float.intBitsToFloat((int) (m555localToScreenMKHz9U & 4294967295L)), Float.intBitsToFloat((int) (m555localToScreenMKHz9U2 >> 32)), Float.intBitsToFloat((int) (m555localToScreenMKHz9U2 & 4294967295L)));
                        } else {
                            i2 = i4;
                            i3 = i6;
                        }
                        arrayList.add(rectF);
                    }
                    i6 = i3 + 1;
                    androidComposeViewAccessibilityDelegateCompat = this;
                    i4 = i2;
                }
                accessibilityNodeInfoCompat.mInfo.getExtras().putParcelableArray(str, (Parcelable[]) arrayList.toArray(new RectF[0]));
                return;
            }
        }
        Log.e("AccessibilityDelegate", "Invalid arguments for accessibility character locations");
    }

    public final android.graphics.Rect boundsInScreen(SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds) {
        android.graphics.Rect rect = semanticsNodeWithAdjustedBounds.adjustedBounds;
        float f = rect.left;
        float f2 = rect.top;
        long floatToRawIntBits = Float.floatToRawIntBits(f);
        AndroidComposeView androidComposeView = this.view;
        long m555localToScreenMKHz9U = androidComposeView.m555localToScreenMKHz9U((Float.floatToRawIntBits(f2) & 4294967295L) | (floatToRawIntBits << 32));
        float f3 = rect.right;
        float f4 = rect.bottom;
        long m555localToScreenMKHz9U2 = androidComposeView.m555localToScreenMKHz9U((Float.floatToRawIntBits(f3) << 32) | (Float.floatToRawIntBits(f4) & 4294967295L));
        return new android.graphics.Rect((int) Math.floor(Float.intBitsToFloat((int) (m555localToScreenMKHz9U >> 32))), (int) Math.floor(Float.intBitsToFloat((int) (m555localToScreenMKHz9U & 4294967295L))), (int) Math.ceil(Float.intBitsToFloat((int) (m555localToScreenMKHz9U2 >> 32))), (int) Math.ceil(Float.intBitsToFloat((int) (m555localToScreenMKHz9U2 & 4294967295L))));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084 A[Catch: all -> 0x00a3, TRY_LEAVE, TryCatch #0 {all -> 0x00a3, blocks: (B:11:0x006a, B:16:0x007c, B:18:0x0084, B:21:0x008f, B:23:0x0094, B:25:0x00a7, B:27:0x00ae, B:28:0x00b7, B:10:0x005b), top: B:9:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d5 -> B:11:0x006a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui_release(kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop$ui_release(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r5v17, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r6v14, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* renamed from: canScroll-0AR0LA0$ui_release, reason: not valid java name */
    public final boolean m559canScroll0AR0LA0$ui_release(int i, long j, boolean z) {
        SemanticsPropertyKey semanticsPropertyKey;
        int i2;
        ScrollAxisRange scrollAxisRange;
        int i3 = 0;
        if (!Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return false;
        }
        MutableIntObjectMap currentSemanticsNodes = getCurrentSemanticsNodes();
        if (!Offset.m310equalsimpl0(j, 9205357640488583168L) && (((9223372034707292159L & j) - 9187343246269874177L) & (-9223372034707292160L)) == -9223372034707292160L) {
            if (z) {
                semanticsPropertyKey = SemanticsProperties.VerticalScrollAxisRange;
            } else {
                if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                semanticsPropertyKey = SemanticsProperties.HorizontalScrollAxisRange;
            }
            Object[] objArr = currentSemanticsNodes.values;
            long[] jArr = currentSemanticsNodes.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i4 = 0;
                boolean z2 = false;
                while (true) {
                    long j2 = jArr[i4];
                    if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i5 = 8;
                        int i6 = 8 - ((~(i4 - length)) >>> 31);
                        int i7 = i3;
                        while (i7 < i6) {
                            if ((j2 & 255) < 128) {
                                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) objArr[(i4 << 3) + i7];
                                if (RectHelper_androidKt.toComposeRect(semanticsNodeWithAdjustedBounds.adjustedBounds).m319containsk4lQ0M(j) && (scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNodeWithAdjustedBounds.semanticsNode.unmergedConfig, semanticsPropertyKey)) != null) {
                                    boolean z3 = scrollAxisRange.reverseScrolling;
                                    int i8 = z3 ? -i : i;
                                    if (i == 0 && z3) {
                                        i8 = -1;
                                    }
                                    ?? r6 = scrollAxisRange.value;
                                    if (i8 >= 0 ? ((Number) r6.invoke()).floatValue() < ((Number) scrollAxisRange.maxValue.invoke()).floatValue() : ((Number) r6.invoke()).floatValue() > 0.0f) {
                                        z2 = true;
                                    }
                                }
                                i2 = 8;
                            } else {
                                i2 = i5;
                            }
                            j2 >>= i2;
                            i7++;
                            i5 = i2;
                        }
                        if (i6 != i5) {
                            break;
                        }
                    }
                    if (i4 == length) {
                        break;
                    }
                    i4++;
                    i3 = 0;
                }
                return z2;
            }
        }
        return false;
    }

    public final void checkForSemanticsChanges() {
        Trace.beginSection("sendAccessibilitySemanticsStructureChangeEvents");
        try {
            if (isEnabled$ui_release()) {
                sendAccessibilitySemanticsStructureChangeEvents(this.view.semanticsOwner.getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
            }
            Trace.endSection();
            Trace.beginSection("sendSemanticsPropertyChangeEvents");
            try {
                sendSemanticsPropertyChangeEvents(getCurrentSemanticsNodes());
                Trace.endSection();
                Trace.beginSection("updateSemanticsNodesCopyAndPanes");
                try {
                    updateSemanticsNodesCopyAndPanes();
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    public final AccessibilityEvent createEvent(int i, int i2) {
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        obtain.setEnabled(true);
        obtain.setClassName("android.view.View");
        AndroidComposeView androidComposeView = this.view;
        obtain.setPackageName(androidComposeView.getContext().getPackageName());
        obtain.setSource(androidComposeView, i);
        if (isEnabled$ui_release() && (semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(i)) != null) {
            obtain.setPassword(semanticsNodeWithAdjustedBounds.semanticsNode.unmergedConfig.props.containsKey(SemanticsProperties.Password));
        }
        return obtain;
    }

    public final AccessibilityEvent createTextSelectionChangedEvent(int i, Integer num, Integer num2, Integer num3, CharSequence charSequence) {
        AccessibilityEvent createEvent = createEvent(i, 8192);
        if (num != null) {
            createEvent.setFromIndex(num.intValue());
        }
        if (num2 != null) {
            createEvent.setToIndex(num2.intValue());
        }
        if (num3 != null) {
            createEvent.setItemCount(num3.intValue());
        }
        if (charSequence != null) {
            createEvent.getText().add(charSequence);
        }
        return createEvent;
    }

    public final void geometryDepthFirstSearch(SemanticsNode semanticsNode, ArrayList arrayList, MutableIntObjectMap mutableIntObjectMap) {
        boolean access$isRtl = AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isRtl(semanticsNode);
        boolean booleanValue = ((Boolean) semanticsNode.unmergedConfig.getOrElse(SemanticsProperties.IsTraversalGroup, new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$geometryDepthFirstSearch$isTraversalGroup$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.FALSE;
            }
        })).booleanValue();
        int i = semanticsNode.id;
        if ((booleanValue || isScreenReaderFocusable(semanticsNode)) && getCurrentSemanticsNodes().containsKey(i)) {
            arrayList.add(semanticsNode);
        }
        if (booleanValue) {
            mutableIntObjectMap.set(i, subtreeSortedByGeometryGrouping(SemanticsNode.getChildren$ui_release$default(semanticsNode, 7), access$isRtl));
            return;
        }
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 7);
        int size = children$ui_release$default.size();
        for (int i2 = 0; i2 < size; i2++) {
            geometryDepthFirstSearch((SemanticsNode) children$ui_release$default.get(i2), arrayList, mutableIntObjectMap);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        return this.nodeProvider;
    }

    public final int getAccessibilitySelectionEnd(SemanticsNode semanticsNode) {
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (!semanticsConfiguration.props.containsKey(SemanticsProperties.ContentDescription)) {
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.TextSelectionRange;
            SemanticsConfiguration semanticsConfiguration2 = semanticsNode.unmergedConfig;
            if (semanticsConfiguration2.props.containsKey(semanticsPropertyKey)) {
                return (int) (((TextRange) semanticsConfiguration2.get(semanticsPropertyKey)).packedValue & 4294967295L);
            }
        }
        return this.accessibilityCursorPosition;
    }

    public final int getAccessibilitySelectionStart(SemanticsNode semanticsNode) {
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (!semanticsConfiguration.props.containsKey(SemanticsProperties.ContentDescription)) {
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.TextSelectionRange;
            SemanticsConfiguration semanticsConfiguration2 = semanticsNode.unmergedConfig;
            if (semanticsConfiguration2.props.containsKey(semanticsPropertyKey)) {
                return (int) (((TextRange) semanticsConfiguration2.get(semanticsPropertyKey)).packedValue >> 32);
            }
        }
        return this.accessibilityCursorPosition;
    }

    public final MutableIntObjectMap getCurrentSemanticsNodes() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsUtils_androidKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.semanticsOwner);
            if (isEnabled$ui_release()) {
                MutableIntIntMap mutableIntIntMap = this.idToBeforeMap;
                mutableIntIntMap.clear();
                MutableIntIntMap mutableIntIntMap2 = this.idToAfterMap;
                mutableIntIntMap2.clear();
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes().get(-1);
                SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.semanticsNode : null;
                Intrinsics.checkNotNull(semanticsNode);
                List subtreeSortedByGeometryGrouping = subtreeSortedByGeometryGrouping(Collections.singletonList(semanticsNode), AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$isRtl(semanticsNode));
                int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(subtreeSortedByGeometryGrouping);
                int i = 1;
                if (1 <= lastIndex) {
                    while (true) {
                        ArrayList arrayList = (ArrayList) subtreeSortedByGeometryGrouping;
                        int i2 = ((SemanticsNode) arrayList.get(i - 1)).id;
                        int i3 = ((SemanticsNode) arrayList.get(i)).id;
                        mutableIntIntMap.set(i2, i3);
                        mutableIntIntMap2.set(i3, i2);
                        if (i == lastIndex) {
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return this.currentSemanticsNodes;
    }

    public final String getInfoStateDescriptionOrNull(SemanticsNode semanticsNode) {
        Collection collection;
        CharSequence charSequence;
        Object orNull = SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.StateDescription);
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ToggleableState;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey);
        Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.Role);
        AndroidComposeView androidComposeView = this.view;
        if (toggleableState != null) {
            int ordinal = toggleableState.ordinal();
            if (ordinal == 0) {
                if ((role == null ? false : Role.m574equalsimpl0(role.value, 2)) && orNull == null) {
                    orNull = androidComposeView.getContext().getResources().getString(R.string.state_on);
                }
            } else if (ordinal == 1) {
                if ((role == null ? false : Role.m574equalsimpl0(role.value, 2)) && orNull == null) {
                    orNull = androidComposeView.getContext().getResources().getString(R.string.state_off);
                }
            } else if (ordinal == 2 && orNull == null) {
                orNull = androidComposeView.getContext().getResources().getString(R.string.indeterminate);
            }
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.Selected);
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            if (!(role == null ? false : Role.m574equalsimpl0(role.value, 4)) && orNull == null) {
                orNull = booleanValue ? androidComposeView.getContext().getResources().getString(R.string.selected) : androidComposeView.getContext().getResources().getString(R.string.not_selected);
            }
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.ProgressBarRangeInfo);
        if (progressBarRangeInfo != null) {
            if (progressBarRangeInfo != ProgressBarRangeInfo.Indeterminate) {
                if (orNull == null) {
                    ClosedFloatRange closedFloatRange = (ClosedFloatRange) progressBarRangeInfo.range;
                    float f = closedFloatRange._endInclusive;
                    float f2 = closedFloatRange._start;
                    float f3 = ((f - f2) > 0.0f ? 1 : ((f - f2) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (progressBarRangeInfo.current - f2) / (closedFloatRange._endInclusive - f2);
                    if (f3 < 0.0f) {
                        f3 = 0.0f;
                    }
                    if (f3 > 1.0f) {
                        f3 = 1.0f;
                    }
                    if (!(f3 == 0.0f)) {
                        r5 = (f3 == 1.0f ? 1 : 0) != 0 ? 100 : RangesKt.coerceIn(Math.round(f3 * 100), 1, 99);
                    }
                    orNull = androidComposeView.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(r5));
                }
            } else if (orNull == null) {
                orNull = androidComposeView.getContext().getResources().getString(R.string.in_progress);
            }
        }
        SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.EditableText;
        if (semanticsConfiguration.props.containsKey(semanticsPropertyKey2)) {
            SemanticsConfiguration config = new SemanticsNode(semanticsNode.outerSemanticsNode, true, semanticsNode.layoutNode, semanticsConfiguration).getConfig();
            Collection collection2 = (Collection) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.ContentDescription);
            orNull = ((collection2 == null || collection2.isEmpty()) && ((collection = (Collection) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.Text)) == null || collection.isEmpty()) && ((charSequence = (CharSequence) SemanticsConfigurationKt.getOrNull(config, semanticsPropertyKey2)) == null || charSequence.length() == 0)) ? androidComposeView.getContext().getResources().getString(R.string.state_empty) : null;
        }
        return (String) orNull;
    }

    public final boolean isEnabled$ui_release() {
        return this.accessibilityManager.isEnabled() && !this.enabledServices.isEmpty();
    }

    public final boolean isScreenReaderFocusable(SemanticsNode semanticsNode) {
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.ContentDescription);
        boolean z = ((list != null ? (String) CollectionsKt.firstOrNull(list) : null) == null && getInfoText(semanticsNode) == null && getInfoStateDescriptionOrNull(semanticsNode) == null && !getInfoIsCheckable(semanticsNode)) ? false : true;
        if (SemanticsUtils_androidKt.isVisible(semanticsNode)) {
            if (semanticsNode.unmergedConfig.isMergingSemanticsOfDescendants) {
                return true;
            }
            if (semanticsNode.isUnmergedLeafNode$ui_release() && z) {
                return true;
            }
        }
        return false;
    }

    public final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo1790trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int i) {
        if (i == this.view.semanticsOwner.getUnmergedRootSemanticsNode().id) {
            return -1;
        }
        return i;
    }

    public final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        int[] iArr = IntSetKt.EmptyIntArray;
        MutableIntSet mutableIntSet = new MutableIntSet();
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
        int size = children$ui_release$default.size();
        int i = 0;
        while (true) {
            LayoutNode layoutNode = semanticsNode.layoutNode;
            if (i >= size) {
                MutableIntSet mutableIntSet2 = semanticsNodeCopy.children;
                int[] iArr2 = mutableIntSet2.elements;
                long[] jArr = mutableIntSet2.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i2 = 0;
                    while (true) {
                        long j = jArr[i2];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i3 = 8 - ((~(i2 - length)) >>> 31);
                            for (int i4 = 0; i4 < i3; i4++) {
                                if ((255 & j) < 128 && !mutableIntSet.contains(iArr2[(i2 << 3) + i4])) {
                                    notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
                                    return;
                                }
                                j >>= 8;
                            }
                            if (i3 != 8) {
                                break;
                            }
                        }
                        if (i2 == length) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                List children$ui_release$default2 = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
                int size2 = children$ui_release$default2.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    SemanticsNode semanticsNode2 = (SemanticsNode) children$ui_release$default2.get(i5);
                    if (getCurrentSemanticsNodes().contains(semanticsNode2.id)) {
                        Object obj = this.previousSemanticsNodes.get(semanticsNode2.id);
                        Intrinsics.checkNotNull(obj);
                        sendAccessibilitySemanticsStructureChangeEvents(semanticsNode2, (SemanticsNodeCopy) obj);
                    }
                }
                return;
            }
            SemanticsNode semanticsNode3 = (SemanticsNode) children$ui_release$default.get(i);
            if (getCurrentSemanticsNodes().contains(semanticsNode3.id)) {
                MutableIntSet mutableIntSet3 = semanticsNodeCopy.children;
                int i6 = semanticsNode3.id;
                if (!mutableIntSet3.contains(i6)) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
                    return;
                }
                mutableIntSet.add(i6);
            }
            i++;
        }
    }

    public final boolean sendEvent(AccessibilityEvent accessibilityEvent) {
        if (!isEnabled$ui_release()) {
            return false;
        }
        if (accessibilityEvent.getEventType() == 2048 || accessibilityEvent.getEventType() == 32768) {
            this.sendingFocusAffectingEvent = true;
        }
        try {
            return ((Boolean) ((AndroidComposeViewAccessibilityDelegateCompat$onSendAccessibilityEvent$1) this.onSendAccessibilityEvent).invoke(accessibilityEvent)).booleanValue();
        } finally {
            this.sendingFocusAffectingEvent = false;
        }
    }

    public final boolean sendEventForVirtualView(int i, int i2, Integer num, List list) {
        if (i == Integer.MIN_VALUE || !isEnabled$ui_release()) {
            return false;
        }
        AccessibilityEvent createEvent = createEvent(i, i2);
        if (num != null) {
            createEvent.setContentChangeTypes(num.intValue());
        }
        if (list != null) {
            createEvent.setContentDescription(ListUtilsKt.fastJoinToString$default(list, ",", null, 62));
        }
        return sendEvent(createEvent);
    }

    public final void sendPaneChangeEvents(String str, int i, int i2) {
        AccessibilityEvent createEvent = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(i), 32);
        createEvent.setContentChangeTypes(i2);
        if (str != null) {
            createEvent.getText().add(str);
        }
        sendEvent(createEvent);
    }

    public final void sendPendingTextTraversedAtGranularityEvent(int i) {
        PendingTextTraversedEvent pendingTextTraversedEvent = this.pendingTextTraversedEvent;
        if (pendingTextTraversedEvent != null) {
            SemanticsNode semanticsNode = pendingTextTraversedEvent.node;
            if (i != semanticsNode.id) {
                return;
            }
            if (SystemClock.uptimeMillis() - pendingTextTraversedEvent.traverseTime <= 1000) {
                AccessibilityEvent createEvent = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNode.id), 131072);
                createEvent.setFromIndex(pendingTextTraversedEvent.fromIndex);
                createEvent.setToIndex(pendingTextTraversedEvent.toIndex);
                createEvent.setAction(pendingTextTraversedEvent.action);
                createEvent.setMovementGranularity(pendingTextTraversedEvent.granularity);
                createEvent.getText().add(getIterableTextForAccessibility(semanticsNode));
                sendEvent(createEvent);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:183:0x0576, code lost:
    
        if (r1.containsAll(r2) != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0579, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x057f, code lost:
    
        if (r1.isEmpty() == false) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x05b7, code lost:
    
        if (r0 != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x05af, code lost:
    
        if (r0 != null) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x05b4, code lost:
    
        if (r0 == null) goto L209;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0179  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendSemanticsPropertyChangeEvents(androidx.collection.MutableIntObjectMap r54) {
        /*
            Method dump skipped, instructions count: 1784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSemanticsPropertyChangeEvents(androidx.collection.MutableIntObjectMap):void");
    }

    public final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, MutableIntSet mutableIntSet) {
        SemanticsConfiguration collapsedSemantics$ui_release;
        LayoutNode findClosestParentNode;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().layoutNodeToHolder.containsKey(layoutNode)) {
            if (!layoutNode.nodes.m525hasH91voCI$ui_release(8)) {
                layoutNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(((LayoutNode) obj).nodes.m525hasH91voCI$ui_release(8));
                    }
                });
            }
            if (layoutNode == null || (collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release()) == null) {
                return;
            }
            if (!collapsedSemantics$ui_release.isMergingSemanticsOfDescendants && (findClosestParentNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsConfiguration collapsedSemantics$ui_release2 = ((LayoutNode) obj).getCollapsedSemantics$ui_release();
                    boolean z = false;
                    if (collapsedSemantics$ui_release2 != null && collapsedSemantics$ui_release2.isMergingSemanticsOfDescendants) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            })) != null) {
                layoutNode = findClosestParentNode;
            }
            int i = layoutNode.semanticsId;
            if (mutableIntSet.add(i)) {
                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(i), 2048, 1, 8);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r0v18, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r0v8, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final void sendTypeViewScrolledAccessibilityEvent(LayoutNode layoutNode) {
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().layoutNodeToHolder.containsKey(layoutNode)) {
            int i = layoutNode.semanticsId;
            ScrollAxisRange scrollAxisRange = (ScrollAxisRange) this.pendingHorizontalScrollEvents.get(i);
            ScrollAxisRange scrollAxisRange2 = (ScrollAxisRange) this.pendingVerticalScrollEvents.get(i);
            if (scrollAxisRange == null && scrollAxisRange2 == null) {
                return;
            }
            AccessibilityEvent createEvent = createEvent(i, 4096);
            if (scrollAxisRange != null) {
                createEvent.setScrollX((int) ((Number) scrollAxisRange.value.invoke()).floatValue());
                createEvent.setMaxScrollX((int) ((Number) scrollAxisRange.maxValue.invoke()).floatValue());
            }
            if (scrollAxisRange2 != null) {
                createEvent.setScrollY((int) ((Number) scrollAxisRange2.value.invoke()).floatValue());
                createEvent.setMaxScrollY((int) ((Number) scrollAxisRange2.maxValue.invoke()).floatValue());
            }
            sendEvent(createEvent);
        }
    }

    public final boolean setAccessibilitySelection(SemanticsNode semanticsNode, int i, int i2, boolean z) {
        String iterableTextForAccessibility;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsActions.SetSelection;
        if (semanticsConfiguration.props.containsKey(semanticsPropertyKey) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$enabled(semanticsNode)) {
            Function3 function3 = (Function3) ((AccessibilityAction) semanticsNode.unmergedConfig.get(semanticsPropertyKey)).action;
            if (function3 != null) {
                return ((Boolean) function3.invoke(Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
            }
            return false;
        }
        if ((i == i2 && i2 == this.accessibilityCursorPosition) || (iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode)) == null) {
            return false;
        }
        if (i < 0 || i != i2 || i2 > iterableTextForAccessibility.length()) {
            i = -1;
        }
        this.accessibilityCursorPosition = i;
        boolean z2 = iterableTextForAccessibility.length() > 0;
        int i3 = semanticsNode.id;
        sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(i3), z2 ? Integer.valueOf(this.accessibilityCursorPosition) : null, z2 ? Integer.valueOf(this.accessibilityCursorPosition) : null, z2 ? Integer.valueOf(iterableTextForAccessibility.length()) : null, iterableTextForAccessibility));
        sendPendingTextTraversedAtGranularityEvent(i3);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00da A[LOOP:1: B:8:0x0035->B:26:0x00da, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00dd A[EDGE_INSN: B:27:0x00dd->B:34:0x00dd BREAK  A[LOOP:1: B:8:0x0035->B:26:0x00da], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List subtreeSortedByGeometryGrouping(java.util.List r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.subtreeSortedByGeometryGrouping(java.util.List, boolean):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x013b, code lost:
    
        r28 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0149, code lost:
    
        if (((r0 & ((~r0) << 6)) & (-9187201950435737472L)) == 0) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x014b, code lost:
    
        r24 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateSemanticsNodesCopyAndPanes() {
        /*
            Method dump skipped, instructions count: 587
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.updateSemanticsNodesCopyAndPanes():void");
    }
}
