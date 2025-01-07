package androidx.compose.ui.text.input;

import android.R;
import android.graphics.Rect;
import android.view.Choreographer;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Deprecated
/* loaded from: classes.dex */
public final class TextInputServiceAndroid implements PlatformTextInputService {
    public final Lazy baseInputConnection$delegate;
    public final CursorAnchorInfoController cursorAnchorInfoController;
    public boolean editorHasFocus;
    public Rect focusedRect;
    public TextInputServiceAndroid$$ExternalSyntheticLambda0 frameCallback;
    public final List ics;
    public ImeOptions imeOptions;
    public final TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0 inputCommandProcessorExecutor;
    public final InputMethodManagerImpl inputMethodManager;
    public Lambda onEditCommand;
    public Lambda onImeActionPerformed;
    public TextFieldValue state;
    public final MutableVector textInputCommandQueue;
    public final AndroidComposeView view;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TextInputCommand {
        public static final /* synthetic */ TextInputCommand[] $VALUES;
        public static final TextInputCommand HideKeyboard;
        public static final TextInputCommand ShowKeyboard;
        public static final TextInputCommand StartInput;
        public static final TextInputCommand StopInput;

        static {
            TextInputCommand textInputCommand = new TextInputCommand("StartInput", 0);
            StartInput = textInputCommand;
            TextInputCommand textInputCommand2 = new TextInputCommand("StopInput", 1);
            StopInput = textInputCommand2;
            TextInputCommand textInputCommand3 = new TextInputCommand("ShowKeyboard", 2);
            ShowKeyboard = textInputCommand3;
            TextInputCommand textInputCommand4 = new TextInputCommand("HideKeyboard", 3);
            HideKeyboard = textInputCommand4;
            $VALUES = new TextInputCommand[]{textInputCommand, textInputCommand2, textInputCommand3, textInputCommand4};
        }

        public static TextInputCommand valueOf(String str) {
            return (TextInputCommand) Enum.valueOf(TextInputCommand.class, str);
        }

        public static TextInputCommand[] values() {
            return (TextInputCommand[]) $VALUES.clone();
        }
    }

    public TextInputServiceAndroid(AndroidComposeView androidComposeView, AndroidComposeView androidComposeView2) {
        InputMethodManagerImpl inputMethodManagerImpl = new InputMethodManagerImpl(androidComposeView);
        TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0 textInputServiceAndroid_androidKt$$ExternalSyntheticLambda0 = new TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0(Choreographer.getInstance());
        this.view = androidComposeView;
        this.inputMethodManager = inputMethodManagerImpl;
        this.inputCommandProcessorExecutor = textInputServiceAndroid_androidKt$$ExternalSyntheticLambda0;
        this.onEditCommand = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$onEditCommand$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$onImeActionPerformed$1
            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Object invoke(Object obj) {
                int i = ((ImeAction) obj).value;
                return Unit.INSTANCE;
            }
        };
        this.state = new TextFieldValue(4, TextRange.Zero, "");
        this.imeOptions = ImeOptions.Default;
        this.ics = new ArrayList();
        this.baseInputConnection$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$baseInputConnection$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new BaseInputConnection(TextInputServiceAndroid.this.view, false);
            }
        });
        this.cursorAnchorInfoController = new CursorAnchorInfoController(androidComposeView2, inputMethodManagerImpl);
        this.textInputCommandQueue = new MutableVector(new TextInputCommand[16]);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void hideSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.HideKeyboard);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void notifyFocusedRect(androidx.compose.ui.geometry.Rect rect) {
        Rect rect2;
        this.focusedRect = new Rect(MathKt.roundToInt(rect.left), MathKt.roundToInt(rect.top), MathKt.roundToInt(rect.right), MathKt.roundToInt(rect.bottom));
        if (!this.ics.isEmpty() || (rect2 = this.focusedRect) == null) {
            return;
        }
        this.view.requestRectangleOnScreen(new Rect(rect2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.text.input.TextInputServiceAndroid$$ExternalSyntheticLambda0, java.lang.Runnable] */
    public final void sendInputCommand(TextInputCommand textInputCommand) {
        this.textInputCommandQueue.add(textInputCommand);
        if (this.frameCallback == null) {
            ?? r2 = new Runnable() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    View view;
                    TextInputServiceAndroid textInputServiceAndroid = TextInputServiceAndroid.this;
                    textInputServiceAndroid.frameCallback = null;
                    Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                    Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
                    MutableVector mutableVector = textInputServiceAndroid.textInputCommandQueue;
                    int i = mutableVector.size;
                    if (i > 0) {
                        Object[] objArr = mutableVector.content;
                        int i2 = 0;
                        do {
                            TextInputServiceAndroid.TextInputCommand textInputCommand2 = (TextInputServiceAndroid.TextInputCommand) objArr[i2];
                            int ordinal = textInputCommand2.ordinal();
                            if (ordinal != 0) {
                                if (ordinal == 1) {
                                    Boolean bool = Boolean.FALSE;
                                    ref$ObjectRef.element = bool;
                                    ref$ObjectRef2.element = bool;
                                } else if ((ordinal == 2 || ordinal == 3) && !Intrinsics.areEqual(ref$ObjectRef.element, Boolean.FALSE)) {
                                    ref$ObjectRef2.element = Boolean.valueOf(textInputCommand2 == TextInputServiceAndroid.TextInputCommand.ShowKeyboard);
                                }
                            } else {
                                Boolean bool2 = Boolean.TRUE;
                                ref$ObjectRef.element = bool2;
                                ref$ObjectRef2.element = bool2;
                            }
                            i2++;
                        } while (i2 < i);
                    }
                    mutableVector.clear();
                    boolean areEqual = Intrinsics.areEqual(ref$ObjectRef.element, Boolean.TRUE);
                    InputMethodManagerImpl inputMethodManagerImpl = textInputServiceAndroid.inputMethodManager;
                    if (areEqual) {
                        ((InputMethodManager) inputMethodManagerImpl.imm$delegate.getValue()).restartInput(inputMethodManagerImpl.view);
                    }
                    Boolean bool3 = (Boolean) ref$ObjectRef2.element;
                    if (bool3 != null) {
                        if (bool3.booleanValue()) {
                            SoftwareKeyboardControllerCompat.Impl30 impl30 = inputMethodManagerImpl.softwareKeyboardControllerCompat.mImpl;
                            View view2 = impl30.mView;
                            WindowInsetsController windowInsetsController = view2 != null ? view2.getWindowInsetsController() : null;
                            if (windowInsetsController != null) {
                                windowInsetsController.show(WindowInsets.Type.ime());
                            }
                            final View view3 = impl30.mView$1;
                            if (view3 != null) {
                                if (view3.isInEditMode() || view3.onCheckIsTextEditor()) {
                                    view3.requestFocus();
                                } else {
                                    view3 = view3.getRootView().findFocus();
                                }
                                if (view3 == null) {
                                    view3 = impl30.mView$1.getRootView().findViewById(R.id.content);
                                }
                                if (view3 != null && view3.hasWindowFocus()) {
                                    view3.post(new Runnable() { // from class: androidx.core.view.SoftwareKeyboardControllerCompat$Impl20$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            View view4 = view3;
                                            ((InputMethodManager) view4.getContext().getSystemService("input_method")).showSoftInput(view4, 0);
                                        }
                                    });
                                }
                            }
                        } else {
                            SoftwareKeyboardControllerCompat.Impl30 impl302 = inputMethodManagerImpl.softwareKeyboardControllerCompat.mImpl;
                            View view4 = impl302.mView;
                            WindowInsetsController windowInsetsController2 = view4 != null ? view4.getWindowInsetsController() : null;
                            if (windowInsetsController2 != null) {
                                final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                                WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0
                                    @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                                    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController3, int i3) {
                                        atomicBoolean.set((i3 & 8) != 0);
                                    }
                                };
                                windowInsetsController2.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
                                if (!atomicBoolean.get() && (view = impl302.mView) != null) {
                                    ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(impl302.mView.getWindowToken(), 0);
                                }
                                windowInsetsController2.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
                                windowInsetsController2.hide(WindowInsets.Type.ime());
                            } else {
                                View view5 = impl302.mView$1;
                                if (view5 != null) {
                                    ((InputMethodManager) view5.getContext().getSystemService("input_method")).hideSoftInputFromWindow(impl302.mView$1.getWindowToken(), 0);
                                }
                            }
                        }
                    }
                    if (Intrinsics.areEqual(ref$ObjectRef.element, Boolean.FALSE)) {
                        ((InputMethodManager) inputMethodManagerImpl.imm$delegate.getValue()).restartInput(inputMethodManagerImpl.view);
                    }
                }
            };
            this.inputCommandProcessorExecutor.execute(r2);
            this.frameCallback = r2;
        }
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void showSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.ShowKeyboard);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void startInput(TextFieldValue textFieldValue, ImeOptions imeOptions, Function1 function1, Function1 function12) {
        this.editorHasFocus = true;
        this.state = textFieldValue;
        this.imeOptions = imeOptions;
        this.onEditCommand = (Lambda) function1;
        this.onImeActionPerformed = (Lambda) function12;
        sendInputCommand(TextInputCommand.StartInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void stopInput() {
        this.editorHasFocus = false;
        this.onEditCommand = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$stopInput$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$stopInput$2
            @Override // kotlin.jvm.functions.Function1
            public final /* synthetic */ Object invoke(Object obj) {
                int i = ((ImeAction) obj).value;
                return Unit.INSTANCE;
            }
        };
        this.focusedRect = null;
        sendInputCommand(TextInputCommand.StopInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void updateState(TextFieldValue textFieldValue, TextFieldValue textFieldValue2) {
        boolean z = (TextRange.m597equalsimpl0(this.state.selection, textFieldValue2.selection) && Intrinsics.areEqual(this.state.composition, textFieldValue2.composition)) ? false : true;
        this.state = textFieldValue2;
        int size = ((ArrayList) this.ics).size();
        for (int i = 0; i < size; i++) {
            RecordingInputConnection recordingInputConnection = (RecordingInputConnection) ((WeakReference) ((ArrayList) this.ics).get(i)).get();
            if (recordingInputConnection != null) {
                recordingInputConnection.mTextFieldValue = textFieldValue2;
            }
        }
        CursorAnchorInfoController cursorAnchorInfoController = this.cursorAnchorInfoController;
        synchronized (cursorAnchorInfoController.lock) {
            cursorAnchorInfoController.textFieldValue = null;
            cursorAnchorInfoController.offsetMapping = null;
            cursorAnchorInfoController.textLayoutResult = null;
            cursorAnchorInfoController.textFieldToRootTransform = new Function1() { // from class: androidx.compose.ui.text.input.CursorAnchorInfoController$invalidate$1$1
                @Override // kotlin.jvm.functions.Function1
                public final /* synthetic */ Object invoke(Object obj) {
                    float[] fArr = ((Matrix) obj).values;
                    return Unit.INSTANCE;
                }
            };
            cursorAnchorInfoController.innerTextFieldBounds = null;
            cursorAnchorInfoController.decorationBoxBounds = null;
        }
        if (Intrinsics.areEqual(textFieldValue, textFieldValue2)) {
            if (z) {
                InputMethodManagerImpl inputMethodManagerImpl = this.inputMethodManager;
                int m601getMinimpl = TextRange.m601getMinimpl(textFieldValue2.selection);
                int m600getMaximpl = TextRange.m600getMaximpl(textFieldValue2.selection);
                TextRange textRange = this.state.composition;
                int m601getMinimpl2 = textRange != null ? TextRange.m601getMinimpl(textRange.packedValue) : -1;
                TextRange textRange2 = this.state.composition;
                ((InputMethodManager) inputMethodManagerImpl.imm$delegate.getValue()).updateSelection(inputMethodManagerImpl.view, m601getMinimpl, m600getMaximpl, m601getMinimpl2, textRange2 != null ? TextRange.m600getMaximpl(textRange2.packedValue) : -1);
                return;
            }
            return;
        }
        if (textFieldValue != null && (!Intrinsics.areEqual(textFieldValue.annotatedString.text, textFieldValue2.annotatedString.text) || (TextRange.m597equalsimpl0(textFieldValue.selection, textFieldValue2.selection) && !Intrinsics.areEqual(textFieldValue.composition, textFieldValue2.composition)))) {
            InputMethodManagerImpl inputMethodManagerImpl2 = this.inputMethodManager;
            ((InputMethodManager) inputMethodManagerImpl2.imm$delegate.getValue()).restartInput(inputMethodManagerImpl2.view);
            return;
        }
        int size2 = ((ArrayList) this.ics).size();
        for (int i2 = 0; i2 < size2; i2++) {
            RecordingInputConnection recordingInputConnection2 = (RecordingInputConnection) ((WeakReference) ((ArrayList) this.ics).get(i2)).get();
            if (recordingInputConnection2 != null) {
                TextFieldValue textFieldValue3 = this.state;
                InputMethodManagerImpl inputMethodManagerImpl3 = this.inputMethodManager;
                if (recordingInputConnection2.isActive) {
                    recordingInputConnection2.mTextFieldValue = textFieldValue3;
                    if (recordingInputConnection2.extractedTextMonitorMode) {
                        ((InputMethodManager) inputMethodManagerImpl3.imm$delegate.getValue()).updateExtractedText(inputMethodManagerImpl3.view, recordingInputConnection2.currentExtractedTextRequestToken, InputState_androidKt.toExtractedText(textFieldValue3));
                    }
                    TextRange textRange3 = textFieldValue3.composition;
                    int m601getMinimpl3 = textRange3 != null ? TextRange.m601getMinimpl(textRange3.packedValue) : -1;
                    TextRange textRange4 = textFieldValue3.composition;
                    int m600getMaximpl2 = textRange4 != null ? TextRange.m600getMaximpl(textRange4.packedValue) : -1;
                    long j = textFieldValue3.selection;
                    ((InputMethodManager) inputMethodManagerImpl3.imm$delegate.getValue()).updateSelection(inputMethodManagerImpl3.view, TextRange.m601getMinimpl(j), TextRange.m600getMaximpl(j), m601getMinimpl3, m600getMaximpl2);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Function1 function1, androidx.compose.ui.geometry.Rect rect, androidx.compose.ui.geometry.Rect rect2) {
        CursorAnchorInfoController cursorAnchorInfoController = this.cursorAnchorInfoController;
        synchronized (cursorAnchorInfoController.lock) {
            try {
                cursorAnchorInfoController.textFieldValue = textFieldValue;
                cursorAnchorInfoController.offsetMapping = offsetMapping;
                cursorAnchorInfoController.textLayoutResult = textLayoutResult;
                cursorAnchorInfoController.textFieldToRootTransform = (Lambda) function1;
                cursorAnchorInfoController.innerTextFieldBounds = rect;
                cursorAnchorInfoController.decorationBoxBounds = rect2;
                if (!cursorAnchorInfoController.hasPendingImmediateRequest) {
                    if (cursorAnchorInfoController.monitorEnabled) {
                    }
                }
                cursorAnchorInfoController.updateCursorAnchorInfo();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void startInput() {
        sendInputCommand(TextInputCommand.StartInput);
    }
}
