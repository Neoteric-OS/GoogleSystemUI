package androidx.compose.ui.contentcapture;

import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationResponse;
import androidx.collection.ArraySet;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableScatterMap;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.SemanticsNodeCopy;
import androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import androidx.lifecycle.DefaultLifecycleObserver;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidContentCaptureManager implements DefaultLifecycleObserver, View.OnAttachStateChangeListener {
    public boolean checkingForSemanticsChanges;
    public final AndroidContentCaptureManager$$ExternalSyntheticLambda0 contentCaptureChangeChecker;
    public ContentCaptureSessionCompat contentCaptureSession;
    public MutableIntObjectMap currentSemanticsNodes;
    public long currentSemanticsNodesSnapshotTimestampMillis;
    public final Function0 onContentCaptureSession;
    public final MutableIntObjectMap previousSemanticsNodes;
    public SemanticsNodeCopy previousSemanticsRoot;
    public final AndroidComposeView view;
    public final MutableIntObjectMap bufferedAppearedNodes = new MutableIntObjectMap();
    public final MutableIntSet bufferedDisappearedNodes = new MutableIntSet();
    public final long SendRecurringContentCaptureEventsIntervalMillis = 100;
    public TranslateStatus translateStatus = TranslateStatus.SHOW_ORIGINAL;
    public boolean currentSemanticsNodesInvalidated = true;
    public final ArraySet subtreeChangedLayoutNodes = new ArraySet(0);
    public final BufferedChannel boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6);
    public final Handler handler = new Handler(Looper.getMainLooper());

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TranslateStatus {
        public static final /* synthetic */ TranslateStatus[] $VALUES;
        public static final TranslateStatus SHOW_ORIGINAL;
        public static final TranslateStatus SHOW_TRANSLATED;

        static {
            TranslateStatus translateStatus = new TranslateStatus("SHOW_ORIGINAL", 0);
            SHOW_ORIGINAL = translateStatus;
            TranslateStatus translateStatus2 = new TranslateStatus("SHOW_TRANSLATED", 1);
            SHOW_TRANSLATED = translateStatus2;
            $VALUES = new TranslateStatus[]{translateStatus, translateStatus2};
        }

        public static TranslateStatus valueOf(String str) {
            return (TranslateStatus) Enum.valueOf(TranslateStatus.class, str);
        }

        public static TranslateStatus[] values() {
            return (TranslateStatus[]) $VALUES.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ViewTranslationHelperMethods {
        public static void doTranslation(AndroidContentCaptureManager androidContentCaptureManager, LongSparseArray longSparseArray) {
            TranslationResponseValue value;
            CharSequence text;
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
            SemanticsNode semanticsNode;
            Function1 function1;
            int size = longSparseArray.size();
            for (int i = 0; i < size; i++) {
                long keyAt = longSparseArray.keyAt(i);
                ViewTranslationResponse viewTranslationResponse = (ViewTranslationResponse) longSparseArray.get(keyAt);
                if (viewTranslationResponse != null && (value = viewTranslationResponse.getValue("android:text")) != null && (text = value.getText()) != null && (semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) androidContentCaptureManager.getCurrentSemanticsNodes$ui_release().get((int) keyAt)) != null && (semanticsNode = semanticsNodeWithAdjustedBounds.semanticsNode) != null) {
                    AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.unmergedConfig, SemanticsActions.SetTextSubstitution);
                    if (accessibilityAction != null && (function1 = (Function1) accessibilityAction.action) != null) {
                    }
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.ui.contentcapture.AndroidContentCaptureManager$$ExternalSyntheticLambda0] */
    public AndroidContentCaptureManager(AndroidComposeView androidComposeView, Function0 function0) {
        this.view = androidComposeView;
        this.onContentCaptureSession = function0;
        MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
        this.currentSemanticsNodes = mutableIntObjectMap;
        this.previousSemanticsNodes = new MutableIntObjectMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(androidComposeView.semanticsOwner.getUnmergedRootSemanticsNode(), mutableIntObjectMap);
        this.contentCaptureChangeChecker = new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MutableIntObjectMap mutableIntObjectMap2;
                int[] iArr;
                long[] jArr;
                int i;
                MutableIntObjectMap mutableIntObjectMap3;
                int[] iArr2;
                long[] jArr2;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                SemanticsNodeCopy semanticsNodeCopy;
                long[] jArr3;
                Object[] objArr;
                SemanticsNodeCopy semanticsNodeCopy2;
                long[] jArr4;
                Object[] objArr2;
                Object[] objArr3;
                long[] jArr5;
                int i7;
                int i8;
                Object[] objArr4;
                long[] jArr6;
                int i9;
                AndroidContentCaptureManager androidContentCaptureManager = AndroidContentCaptureManager.this;
                if (androidContentCaptureManager.isEnabled$ui_release()) {
                    androidContentCaptureManager.view.measureAndLayout(true);
                    androidContentCaptureManager.sendSemanticsStructureChangeEvents(androidContentCaptureManager.view.semanticsOwner.getUnmergedRootSemanticsNode(), androidContentCaptureManager.previousSemanticsRoot);
                    androidContentCaptureManager.sendContentCaptureStructureChangeEvents(androidContentCaptureManager.view.semanticsOwner.getUnmergedRootSemanticsNode(), androidContentCaptureManager.previousSemanticsRoot);
                    MutableIntObjectMap currentSemanticsNodes$ui_release = androidContentCaptureManager.getCurrentSemanticsNodes$ui_release();
                    int[] iArr3 = currentSemanticsNodes$ui_release.keys;
                    long[] jArr7 = currentSemanticsNodes$ui_release.metadata;
                    int length = jArr7.length - 2;
                    int i10 = 8;
                    long j = -9187201950435737472L;
                    char c = 7;
                    if (length >= 0) {
                        int i11 = 0;
                        while (true) {
                            long j2 = jArr7[i11];
                            if ((((~j2) << c) & j2 & j) != j) {
                                int i12 = 8 - ((~(i11 - length)) >>> 31);
                                int i13 = 0;
                                while (i13 < i12) {
                                    if ((j2 & 255) < 128) {
                                        int i14 = iArr3[(i11 << 3) + i13];
                                        SemanticsNodeCopy semanticsNodeCopy3 = (SemanticsNodeCopy) androidContentCaptureManager.previousSemanticsNodes.get(i14);
                                        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) currentSemanticsNodes$ui_release.get(i14);
                                        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.semanticsNode : null;
                                        if (semanticsNode == null) {
                                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("no value for specified key");
                                            throw null;
                                        }
                                        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
                                        int i15 = semanticsNode.id;
                                        if (semanticsNodeCopy3 == null) {
                                            MutableScatterMap mutableScatterMap = semanticsConfiguration.props;
                                            Object[] objArr5 = mutableScatterMap.keys;
                                            long[] jArr8 = mutableScatterMap.metadata;
                                            int length2 = jArr8.length - 2;
                                            mutableIntObjectMap3 = currentSemanticsNodes$ui_release;
                                            iArr2 = iArr3;
                                            if (length2 >= 0) {
                                                int i16 = 0;
                                                while (true) {
                                                    long j3 = jArr8[i16];
                                                    jArr2 = jArr7;
                                                    i2 = length;
                                                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i17 = 8 - ((~(i16 - length2)) >>> 31);
                                                        int i18 = 0;
                                                        while (i18 < i17) {
                                                            if ((j3 & 255) < 128) {
                                                                objArr4 = objArr5;
                                                                SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) objArr5[(i16 << 3) + i18];
                                                                jArr6 = jArr8;
                                                                SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.Text;
                                                                if (Intrinsics.areEqual(semanticsPropertyKey, semanticsPropertyKey2)) {
                                                                    List list = (List) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
                                                                    String valueOf = String.valueOf(list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null);
                                                                    ContentCaptureSessionCompat contentCaptureSessionCompat = androidContentCaptureManager.contentCaptureSession;
                                                                    if (contentCaptureSessionCompat != null) {
                                                                        i7 = i12;
                                                                        i8 = i13;
                                                                        i9 = i11;
                                                                        ContentCaptureSession contentCaptureSession = (ContentCaptureSession) contentCaptureSessionCompat.mWrappedObj;
                                                                        AutofillId newAutofillId = contentCaptureSession.newAutofillId(contentCaptureSessionCompat.mView.getAutofillId(), i15);
                                                                        if (newAutofillId == null) {
                                                                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid content capture ID");
                                                                            throw null;
                                                                        }
                                                                        contentCaptureSession.notifyViewTextChanged(newAutofillId, valueOf);
                                                                        j3 >>= 8;
                                                                        i18++;
                                                                        jArr8 = jArr6;
                                                                        objArr5 = objArr4;
                                                                        i12 = i7;
                                                                        i13 = i8;
                                                                        i11 = i9;
                                                                    }
                                                                }
                                                                i7 = i12;
                                                                i8 = i13;
                                                            } else {
                                                                i7 = i12;
                                                                i8 = i13;
                                                                objArr4 = objArr5;
                                                                jArr6 = jArr8;
                                                            }
                                                            i9 = i11;
                                                            j3 >>= 8;
                                                            i18++;
                                                            jArr8 = jArr6;
                                                            objArr5 = objArr4;
                                                            i12 = i7;
                                                            i13 = i8;
                                                            i11 = i9;
                                                        }
                                                        i3 = i12;
                                                        i4 = i13;
                                                        objArr3 = objArr5;
                                                        jArr5 = jArr8;
                                                        i5 = i11;
                                                        if (i17 != 8) {
                                                            break;
                                                        }
                                                    } else {
                                                        i3 = i12;
                                                        i4 = i13;
                                                        objArr3 = objArr5;
                                                        jArr5 = jArr8;
                                                        i5 = i11;
                                                    }
                                                    if (i16 == length2) {
                                                        break;
                                                    }
                                                    i16++;
                                                    jArr8 = jArr5;
                                                    objArr5 = objArr3;
                                                    i12 = i3;
                                                    jArr7 = jArr2;
                                                    length = i2;
                                                    i13 = i4;
                                                    i11 = i5;
                                                }
                                            } else {
                                                jArr2 = jArr7;
                                                i2 = length;
                                                i3 = i12;
                                                i4 = i13;
                                                i5 = i11;
                                            }
                                        } else {
                                            mutableIntObjectMap3 = currentSemanticsNodes$ui_release;
                                            iArr2 = iArr3;
                                            jArr2 = jArr7;
                                            i2 = length;
                                            i3 = i12;
                                            i4 = i13;
                                            i5 = i11;
                                            SemanticsNodeCopy semanticsNodeCopy4 = semanticsNodeCopy3;
                                            MutableScatterMap mutableScatterMap2 = semanticsConfiguration.props;
                                            Object[] objArr6 = mutableScatterMap2.keys;
                                            long[] jArr9 = mutableScatterMap2.metadata;
                                            int length3 = jArr9.length - 2;
                                            if (length3 >= 0) {
                                                int i19 = 0;
                                                while (true) {
                                                    long j4 = jArr9[i19];
                                                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i20 = 8 - ((~(i19 - length3)) >>> 31);
                                                        int i21 = 0;
                                                        while (i21 < i20) {
                                                            if ((j4 & 255) < 128) {
                                                                SemanticsPropertyKey semanticsPropertyKey3 = (SemanticsPropertyKey) objArr6[(i19 << 3) + i21];
                                                                SemanticsPropertyKey semanticsPropertyKey4 = SemanticsProperties.Text;
                                                                if (Intrinsics.areEqual(semanticsPropertyKey3, semanticsPropertyKey4)) {
                                                                    List list2 = (List) SemanticsConfigurationKt.getOrNull(semanticsNodeCopy4.unmergedConfig, semanticsPropertyKey4);
                                                                    AnnotatedString annotatedString = list2 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list2) : null;
                                                                    List list3 = (List) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey4);
                                                                    AnnotatedString annotatedString2 = list3 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list3) : null;
                                                                    if (!Intrinsics.areEqual(annotatedString, annotatedString2)) {
                                                                        String valueOf2 = String.valueOf(annotatedString2);
                                                                        ContentCaptureSessionCompat contentCaptureSessionCompat2 = androidContentCaptureManager.contentCaptureSession;
                                                                        if (contentCaptureSessionCompat2 != null) {
                                                                            semanticsNodeCopy2 = semanticsNodeCopy4;
                                                                            jArr4 = jArr9;
                                                                            objArr2 = objArr6;
                                                                            ContentCaptureSession contentCaptureSession2 = (ContentCaptureSession) contentCaptureSessionCompat2.mWrappedObj;
                                                                            AutofillId newAutofillId2 = contentCaptureSession2.newAutofillId(contentCaptureSessionCompat2.mView.getAutofillId(), i15);
                                                                            if (newAutofillId2 == null) {
                                                                                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid content capture ID");
                                                                                throw null;
                                                                            }
                                                                            contentCaptureSession2.notifyViewTextChanged(newAutofillId2, valueOf2);
                                                                            j4 >>= 8;
                                                                            i21++;
                                                                            semanticsNodeCopy4 = semanticsNodeCopy2;
                                                                            jArr9 = jArr4;
                                                                            objArr6 = objArr2;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            semanticsNodeCopy2 = semanticsNodeCopy4;
                                                            jArr4 = jArr9;
                                                            objArr2 = objArr6;
                                                            j4 >>= 8;
                                                            i21++;
                                                            semanticsNodeCopy4 = semanticsNodeCopy2;
                                                            jArr9 = jArr4;
                                                            objArr6 = objArr2;
                                                        }
                                                        semanticsNodeCopy = semanticsNodeCopy4;
                                                        jArr3 = jArr9;
                                                        objArr = objArr6;
                                                        if (i20 != 8) {
                                                            break;
                                                        }
                                                    } else {
                                                        semanticsNodeCopy = semanticsNodeCopy4;
                                                        jArr3 = jArr9;
                                                        objArr = objArr6;
                                                    }
                                                    if (i19 == length3) {
                                                        break;
                                                    }
                                                    i19++;
                                                    semanticsNodeCopy4 = semanticsNodeCopy;
                                                    jArr9 = jArr3;
                                                    objArr6 = objArr;
                                                }
                                            }
                                        }
                                        i6 = 8;
                                    } else {
                                        mutableIntObjectMap3 = currentSemanticsNodes$ui_release;
                                        iArr2 = iArr3;
                                        jArr2 = jArr7;
                                        i2 = length;
                                        i3 = i12;
                                        i4 = i13;
                                        i5 = i11;
                                        i6 = i10;
                                    }
                                    j2 >>= i6;
                                    i13 = i4 + 1;
                                    i10 = i6;
                                    i12 = i3;
                                    currentSemanticsNodes$ui_release = mutableIntObjectMap3;
                                    iArr3 = iArr2;
                                    jArr7 = jArr2;
                                    length = i2;
                                    i11 = i5;
                                }
                                mutableIntObjectMap2 = currentSemanticsNodes$ui_release;
                                iArr = iArr3;
                                jArr = jArr7;
                                int i22 = length;
                                int i23 = i11;
                                if (i12 != i10) {
                                    break;
                                }
                                length = i22;
                                i = i23;
                            } else {
                                mutableIntObjectMap2 = currentSemanticsNodes$ui_release;
                                iArr = iArr3;
                                jArr = jArr7;
                                i = i11;
                            }
                            if (i == length) {
                                break;
                            }
                            i11 = i + 1;
                            currentSemanticsNodes$ui_release = mutableIntObjectMap2;
                            iArr3 = iArr;
                            jArr7 = jArr;
                            i10 = 8;
                            j = -9187201950435737472L;
                            c = 7;
                        }
                    }
                    androidContentCaptureManager.previousSemanticsNodes.clear();
                    MutableIntObjectMap currentSemanticsNodes$ui_release2 = androidContentCaptureManager.getCurrentSemanticsNodes$ui_release();
                    int[] iArr4 = currentSemanticsNodes$ui_release2.keys;
                    Object[] objArr7 = currentSemanticsNodes$ui_release2.values;
                    long[] jArr10 = currentSemanticsNodes$ui_release2.metadata;
                    int length4 = jArr10.length - 2;
                    if (length4 >= 0) {
                        int i24 = 0;
                        while (true) {
                            long j5 = jArr10[i24];
                            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i25 = 8 - ((~(i24 - length4)) >>> 31);
                                for (int i26 = 0; i26 < i25; i26++) {
                                    if ((j5 & 255) < 128) {
                                        int i27 = (i24 << 3) + i26;
                                        androidContentCaptureManager.previousSemanticsNodes.set(iArr4[i27], new SemanticsNodeCopy(((SemanticsNodeWithAdjustedBounds) objArr7[i27]).semanticsNode, androidContentCaptureManager.getCurrentSemanticsNodes$ui_release()));
                                    }
                                    j5 >>= 8;
                                }
                                if (i25 != 8) {
                                    break;
                                }
                            }
                            if (i24 == length4) {
                                break;
                            } else {
                                i24++;
                            }
                        }
                    }
                    androidContentCaptureManager.previousSemanticsRoot = new SemanticsNodeCopy(androidContentCaptureManager.view.semanticsOwner.getUnmergedRootSemanticsNode(), androidContentCaptureManager.getCurrentSemanticsNodes$ui_release());
                    androidContentCaptureManager.checkingForSemanticsChanges = false;
                }
            }
        };
    }

    public static void onVirtualViewTranslationResponses$ui_release(final AndroidContentCaptureManager androidContentCaptureManager, final LongSparseArray longSparseArray) {
        if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            ViewTranslationHelperMethods.doTranslation(androidContentCaptureManager, longSparseArray);
        } else {
            androidContentCaptureManager.view.post(new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$ViewTranslationHelperMethods$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidContentCaptureManager.ViewTranslationHelperMethods.doTranslation(AndroidContentCaptureManager.this, longSparseArray);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0071 A[Catch: all -> 0x007e, TryCatch #1 {all -> 0x007e, blocks: (B:11:0x005c, B:15:0x0069, B:17:0x0071, B:19:0x007a, B:20:0x0082, B:22:0x0086, B:23:0x008f, B:10:0x0052), top: B:9:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a0 -> B:11:0x005c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui_release(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1 r0 = (androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1 r0 = new androidx.compose.ui.contentcapture.AndroidContentCaptureManager$boundsUpdatesEventLoop$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4f
            if (r2 == r4) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r8 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r8
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager r2 = (androidx.compose.ui.contentcapture.AndroidContentCaptureManager) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            r7 = r2
            r2 = r8
            r8 = r7
            goto L5c
        L35:
            r8 = move-exception
            goto Lab
        L38:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L40:
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r8 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r8
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager r2 = (androidx.compose.ui.contentcapture.AndroidContentCaptureManager) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            r7 = r2
            r2 = r8
            r8 = r7
            goto L69
        L4f:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.BufferedChannel r9 = r8.boundsUpdateChannel     // Catch: java.lang.Throwable -> L7e
            r9.getClass()     // Catch: java.lang.Throwable -> L7e
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r2 = new kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator     // Catch: java.lang.Throwable -> L7e
            r2.<init>()     // Catch: java.lang.Throwable -> L7e
        L5c:
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L7e
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L7e
            r0.label = r4     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r9 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L7e
            if (r9 != r1) goto L69
            return r1
        L69:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L7e
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L7e
            if (r9 == 0) goto La3
            r2.next()     // Catch: java.lang.Throwable -> L7e
            boolean r9 = r8.isEnabled$ui_release()     // Catch: java.lang.Throwable -> L7e
            if (r9 == 0) goto L82
            r8.notifyContentCaptureChanges()     // Catch: java.lang.Throwable -> L7e
            goto L82
        L7e:
            r9 = move-exception
            r2 = r8
            r8 = r9
            goto Lab
        L82:
            boolean r9 = r8.checkingForSemanticsChanges     // Catch: java.lang.Throwable -> L7e
            if (r9 != 0) goto L8f
            r8.checkingForSemanticsChanges = r4     // Catch: java.lang.Throwable -> L7e
            android.os.Handler r9 = r8.handler     // Catch: java.lang.Throwable -> L7e
            androidx.compose.ui.contentcapture.AndroidContentCaptureManager$$ExternalSyntheticLambda0 r5 = r8.contentCaptureChangeChecker     // Catch: java.lang.Throwable -> L7e
            r9.post(r5)     // Catch: java.lang.Throwable -> L7e
        L8f:
            androidx.collection.ArraySet r9 = r8.subtreeChangedLayoutNodes     // Catch: java.lang.Throwable -> L7e
            r9.clear()     // Catch: java.lang.Throwable -> L7e
            long r5 = r8.SendRecurringContentCaptureEventsIntervalMillis     // Catch: java.lang.Throwable -> L7e
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L7e
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L7e
            r0.label = r3     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r5, r0)     // Catch: java.lang.Throwable -> L7e
            if (r9 != r1) goto L5c
            return r1
        La3:
            androidx.collection.ArraySet r8 = r8.subtreeChangedLayoutNodes
            r8.clear()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        Lab:
            androidx.collection.ArraySet r9 = r2.subtreeChangedLayoutNodes
            r9.clear()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.boundsUpdatesEventLoop$ui_release(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final MutableIntObjectMap getCurrentSemanticsNodes$ui_release() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsUtils_androidKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.semanticsOwner);
            this.currentSemanticsNodesSnapshotTimestampMillis = System.currentTimeMillis();
        }
        return this.currentSemanticsNodes;
    }

    public final boolean isEnabled$ui_release() {
        return this.contentCaptureSession != null;
    }

    public final void notifyContentCaptureChanges() {
        ContentCaptureSessionCompat contentCaptureSessionCompat = this.contentCaptureSession;
        if (contentCaptureSessionCompat == null) {
            return;
        }
        int i = this.bufferedAppearedNodes._size;
        Object obj = contentCaptureSessionCompat.mWrappedObj;
        char c = 7;
        long j = -9187201950435737472L;
        if (i != 0) {
            ArrayList arrayList = new ArrayList();
            MutableIntObjectMap mutableIntObjectMap = this.bufferedAppearedNodes;
            Object[] objArr = mutableIntObjectMap.values;
            long[] jArr = mutableIntObjectMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j2 = jArr[i2];
                    if ((((~j2) << 7) & j2 & j) != j) {
                        int i3 = 8 - ((~(i2 - length)) >>> 31);
                        for (int i4 = 0; i4 < i3; i4++) {
                            if ((j2 & 255) < 128) {
                                arrayList.add((ViewStructureCompat) objArr[(i2 << 3) + i4]);
                            }
                            j2 >>= 8;
                        }
                        if (i3 != 8) {
                            break;
                        }
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                    j = -9187201950435737472L;
                }
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                arrayList2.add((ViewStructure) ((ViewStructureCompat) arrayList.get(i5)).mWrappedObj);
            }
            ((ContentCaptureSession) obj).notifyViewsAppeared(arrayList2);
            this.bufferedAppearedNodes.clear();
        }
        if (this.bufferedDisappearedNodes._size != 0) {
            ArrayList arrayList3 = new ArrayList();
            MutableIntSet mutableIntSet = this.bufferedDisappearedNodes;
            int[] iArr = mutableIntSet.elements;
            long[] jArr2 = mutableIntSet.metadata;
            int length2 = jArr2.length - 2;
            if (length2 >= 0) {
                int i6 = 0;
                while (true) {
                    long j3 = jArr2[i6];
                    if ((((~j3) << c) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i7 = 8 - ((~(i6 - length2)) >>> 31);
                        for (int i8 = 0; i8 < i7; i8++) {
                            if ((j3 & 255) < 128) {
                                arrayList3.add(Integer.valueOf(iArr[(i6 << 3) + i8]));
                            }
                            j3 >>= 8;
                        }
                        if (i7 != 8) {
                            break;
                        }
                    }
                    if (i6 == length2) {
                        break;
                    }
                    i6++;
                    c = 7;
                }
            }
            ArrayList arrayList4 = new ArrayList(arrayList3.size());
            int size2 = arrayList3.size();
            for (int i9 = 0; i9 < size2; i9++) {
                arrayList4.add(Long.valueOf(((Number) arrayList3.get(i9)).intValue()));
            }
            ((ContentCaptureSession) obj).notifyViewsDisappeared(contentCaptureSessionCompat.mView.getAutofillId(), CollectionsKt.toLongArray(arrayList4));
            this.bufferedDisappearedNodes.clear();
        }
    }

    public final void onClearTranslation$ui_release() {
        AccessibilityAction accessibilityAction;
        Function0 function0;
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        MutableIntObjectMap currentSemanticsNodes$ui_release = getCurrentSemanticsNodes$ui_release();
        Object[] objArr = currentSemanticsNodes$ui_release.values;
        long[] jArr = currentSemanticsNodes$ui_release.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration semanticsConfiguration = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).semanticsNode.unmergedConfig;
                        if (SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.IsShowingTextSubstitution) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.ClearTextSubstitution)) != null && (function0 = (Function0) accessibilityAction.action) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void onHideTranslation$ui_release() {
        AccessibilityAction accessibilityAction;
        Function1 function1;
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        MutableIntObjectMap currentSemanticsNodes$ui_release = getCurrentSemanticsNodes$ui_release();
        Object[] objArr = currentSemanticsNodes$ui_release.values;
        long[] jArr = currentSemanticsNodes$ui_release.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration semanticsConfiguration = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).semanticsNode.unmergedConfig;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.IsShowingTextSubstitution), Boolean.TRUE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.ShowTextSubstitution)) != null && (function1 = (Function1) accessibilityAction.action) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void onShowTranslation$ui_release() {
        AccessibilityAction accessibilityAction;
        Function1 function1;
        this.translateStatus = TranslateStatus.SHOW_TRANSLATED;
        MutableIntObjectMap currentSemanticsNodes$ui_release = getCurrentSemanticsNodes$ui_release();
        Object[] objArr = currentSemanticsNodes$ui_release.values;
        long[] jArr = currentSemanticsNodes$ui_release.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        SemanticsConfiguration semanticsConfiguration = ((SemanticsNodeWithAdjustedBounds) objArr[(i << 3) + i3]).semanticsNode.unmergedConfig;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.IsShowingTextSubstitution), Boolean.FALSE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsActions.ShowTextSubstitution)) != null && (function1 = (Function1) accessibilityAction.action) != null) {
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart$1() {
        this.contentCaptureSession = (ContentCaptureSessionCompat) this.onContentCaptureSession.invoke();
        updateBuffersOnAppeared(this.view.semanticsOwner.getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop$1() {
        updateBuffersOnDisappeared(this.view.semanticsOwner.getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
        this.contentCaptureSession = null;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.handler.removeCallbacks(this.contentCaptureChangeChecker);
        this.contentCaptureSession = null;
    }

    public final void sendContentCaptureStructureChangeEvents(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
        int size = children$ui_release$default.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode2 = (SemanticsNode) children$ui_release$default.get(i);
            if (getCurrentSemanticsNodes$ui_release().contains(semanticsNode2.id) && !semanticsNodeCopy.children.contains(semanticsNode2.id)) {
                updateBuffersOnAppeared(semanticsNode2);
            }
        }
        MutableIntObjectMap mutableIntObjectMap = this.previousSemanticsNodes;
        int[] iArr = mutableIntObjectMap.keys;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = iArr[(i2 << 3) + i4];
                            if (!getCurrentSemanticsNodes$ui_release().contains(i5)) {
                                if (this.bufferedAppearedNodes.containsKey(i5)) {
                                    this.bufferedAppearedNodes.remove(i5);
                                } else {
                                    this.bufferedDisappearedNodes.add(i5);
                                }
                            }
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
        for (int i6 = 0; i6 < size2; i6++) {
            SemanticsNode semanticsNode3 = (SemanticsNode) children$ui_release$default2.get(i6);
            if (getCurrentSemanticsNodes$ui_release().contains(semanticsNode3.id)) {
                MutableIntObjectMap mutableIntObjectMap2 = this.previousSemanticsNodes;
                int i7 = semanticsNode3.id;
                if (mutableIntObjectMap2.contains(i7)) {
                    Object obj = this.previousSemanticsNodes.get(i7);
                    if (obj == null) {
                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                        throw null;
                    }
                    sendContentCaptureStructureChangeEvents(semanticsNode3, (SemanticsNodeCopy) obj);
                } else {
                    continue;
                }
            }
        }
    }

    public final void sendSemanticsStructureChangeEvents(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        MutableIntSet mutableIntSet = new MutableIntSet(SemanticsNode.getChildren$ui_release$default(semanticsNode, 4).size());
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
        int size = children$ui_release$default.size();
        int i = 0;
        while (true) {
            LayoutNode layoutNode = semanticsNode.layoutNode;
            Unit unit = Unit.INSTANCE;
            if (i >= size) {
                MutableIntSet mutableIntSet2 = semanticsNodeCopy.children;
                int[] iArr = mutableIntSet2.elements;
                long[] jArr = mutableIntSet2.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i2 = 0;
                    while (true) {
                        long j = jArr[i2];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i3 = 8 - ((~(i2 - length)) >>> 31);
                            for (int i4 = 0; i4 < i3; i4++) {
                                if ((j & 255) < 128 && !mutableIntSet.contains(iArr[(i2 << 3) + i4])) {
                                    if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
                                        this.boundsUpdateChannel.mo1790trySendJP2dKIU(unit);
                                        return;
                                    }
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
                    if (getCurrentSemanticsNodes$ui_release().contains(semanticsNode2.id)) {
                        Object obj = this.previousSemanticsNodes.get(semanticsNode2.id);
                        if (obj == null) {
                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                            throw null;
                        }
                        sendSemanticsStructureChangeEvents(semanticsNode2, (SemanticsNodeCopy) obj);
                    }
                }
                return;
            }
            SemanticsNode semanticsNode3 = (SemanticsNode) children$ui_release$default.get(i);
            if (getCurrentSemanticsNodes$ui_release().contains(semanticsNode3.id)) {
                MutableIntSet mutableIntSet3 = semanticsNodeCopy.children;
                int i6 = semanticsNode3.id;
                if (!mutableIntSet3.contains(i6)) {
                    if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
                        this.boundsUpdateChannel.mo1790trySendJP2dKIU(unit);
                        return;
                    }
                    return;
                }
                mutableIntSet.add(i6);
            }
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x008d, code lost:
    
        if (r8 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01fb, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01fd, code lost:
    
        r15 = -1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v17, types: [androidx.compose.ui.platform.coreshims.ViewStructureCompat] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateBuffersOnAppeared(androidx.compose.ui.semantics.SemanticsNode r21) {
        /*
            Method dump skipped, instructions count: 555
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.updateBuffersOnAppeared(androidx.compose.ui.semantics.SemanticsNode):void");
    }

    public final void updateBuffersOnDisappeared(SemanticsNode semanticsNode) {
        if (isEnabled$ui_release()) {
            int i = semanticsNode.id;
            if (this.bufferedAppearedNodes.containsKey(i)) {
                this.bufferedAppearedNodes.remove(i);
            } else {
                this.bufferedDisappearedNodes.add(i);
            }
            List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
            int size = children$ui_release$default.size();
            for (int i2 = 0; i2 < size; i2++) {
                updateBuffersOnDisappeared((SemanticsNode) children$ui_release$default.get(i2));
            }
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
