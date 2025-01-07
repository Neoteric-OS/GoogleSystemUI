package com.google.android.systemui.columbus.legacy.sensors;

import android.content.Context;
import android.hardware.google.pixel.vendor.PixelAtoms$DoubleTapNanoappEventReported$Type;
import android.hardware.location.ContextHubClient;
import android.hardware.location.ContextHubClientCallback;
import android.hardware.location.ContextHubInfo;
import android.hardware.location.ContextHubManager;
import android.hardware.location.NanoAppMessage;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.StatsEvent;
import android.util.StatsLog;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.RingBuffer;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import com.google.android.systemui.columbus.legacy.sensors.config.GestureConfiguration;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$GestureDetected;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvent;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvents;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$RecognizerStart;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$ScreenStateUpdate;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.PrintWriter;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CHREGestureSensor extends GestureSensor implements Dumpable {
    public final Handler bgHandler;
    public final Context context;
    public ContextHubClient contextHubClient;
    public final GestureConfiguration gestureConfiguration;
    public boolean isAwake;
    public boolean isDozing;
    public boolean isInitialized;
    public boolean isListening;
    public boolean screenOn;
    public boolean screenStateUpdated;
    public final StatusBarStateController statusBarStateController;
    public final UiEventLogger uiEventLogger;
    public final WakefulnessLifecycle wakefulnessLifecycle;
    public final FeatureVectorDumper featureVectorDumper = new FeatureVectorDumper();
    public final CHREGestureSensor$contextHubClientCallback$1 contextHubClientCallback = new ContextHubClientCallback() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$contextHubClientCallback$1
        public final void onHubReset(ContextHubClient contextHubClient) {
            ExifInterface$$ExternalSyntheticOutline0.m("HubReset: ", "Columbus/GestureSensor", contextHubClient.getAttachedHub().getId());
        }

        public final void onMessageFromNanoApp(ContextHubClient contextHubClient, NanoAppMessage nanoAppMessage) {
            if (nanoAppMessage.getNanoAppId() != 5147455389092024345L) {
                return;
            }
            try {
                int messageType = nanoAppMessage.getMessageType();
                if (messageType == 300) {
                    CHREGestureSensor.access$handleGestureDetection(CHREGestureSensor.this, ColumbusProto$GestureDetected.parseFrom(nanoAppMessage.getMessageBody()));
                } else if (messageType != 500) {
                    Log.e("Columbus/GestureSensor", "Unknown message type: " + nanoAppMessage.getMessageType());
                } else {
                    CHREGestureSensor.access$handleNanoappEvents(CHREGestureSensor.this, ColumbusProto$NanoappEvents.parseFrom(nanoAppMessage.getMessageBody()));
                }
            } catch (InvalidProtocolBufferNanoException e) {
                Log.e("Columbus/GestureSensor", "Invalid protocol buffer", e);
            }
        }

        public final void onNanoAppAborted(ContextHubClient contextHubClient, long j, int i) {
            if (j == 5147455389092024345L) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Nanoapp aborted, code: ", "Columbus/GestureSensor", i);
            }
        }

        public final void onNanoAppLoaded(ContextHubClient contextHubClient, long j) {
            if (j == 5147455389092024345L && CHREGestureSensor.this.isListening) {
                Log.d("Columbus/GestureSensor", "Nanoapp loaded");
                CHREGestureSensor.this.updateScreenState();
                CHREGestureSensor.this.startRecognizer();
            }
        }
    };
    public final CHREGestureSensor$statusBarStateListener$1 statusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$statusBarStateListener$1
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(boolean z) {
            CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
            if (cHREGestureSensor.isDozing != z) {
                cHREGestureSensor.isDozing = z;
                cHREGestureSensor.updateScreenState();
            }
        }
    };
    public final CHREGestureSensor$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver = new WakefulnessLifecycle.Observer() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$wakefulnessLifecycleObserver$1
        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
            if (cHREGestureSensor.isAwake) {
                cHREGestureSensor.isAwake = false;
                cHREGestureSensor.updateScreenState();
            }
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedWakingUp() {
            CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
            if (!cHREGestureSensor.isAwake) {
                cHREGestureSensor.isAwake = true;
                cHREGestureSensor.updateScreenState();
            }
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedGoingToSleep() {
            CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
            if (cHREGestureSensor.isAwake) {
                cHREGestureSensor.isAwake = false;
                cHREGestureSensor.updateScreenState();
            }
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedWakingUp() {
            CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
            if (cHREGestureSensor.isAwake) {
                cHREGestureSensor.isAwake = false;
                cHREGestureSensor.updateScreenState();
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FeatureVector implements Dumpable {
        public final int gesture;
        public final long timestamp = SystemClock.elapsedRealtime();
        public final float[] vector;

        public FeatureVector(ColumbusProto$GestureDetected columbusProto$GestureDetected) {
            this.vector = columbusProto$GestureDetected.featureVector;
            this.gesture = columbusProto$GestureDetected.gestureType;
        }

        @Override // com.android.systemui.Dumpable
        public final void dump(PrintWriter printWriter, String[] strArr) {
            printWriter.println("      Gesture: " + this.gesture + " Time: " + (this.timestamp - SystemClock.elapsedRealtime()));
            float[] fArr = this.vector;
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) "[ ");
            int i = 0;
            for (float f : fArr) {
                i++;
                if (i > 1) {
                    sb.append((CharSequence) ", ");
                }
                sb.append((CharSequence) String.valueOf(f));
            }
            sb.append((CharSequence) " ]");
            printWriter.println("      ".concat(sb.toString()));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FeatureVectorDumper implements Dumpable {
        public final RingBuffer featureVectors = new RingBuffer(FeatureVector.class, 10);
        public FeatureVector lastSingleTapFeatureVector;

        @Override // com.android.systemui.Dumpable
        public final void dump(PrintWriter printWriter, String[] strArr) {
            printWriter.println("    Feature Vectors:");
            for (Object obj : this.featureVectors.toArray()) {
                ((FeatureVector) obj).dump(printWriter, strArr);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$contextHubClientCallback$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$statusBarStateListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$wakefulnessLifecycleObserver$1] */
    public CHREGestureSensor(Context context, UiEventLogger uiEventLogger, GestureConfiguration gestureConfiguration, StatusBarStateController statusBarStateController, WakefulnessLifecycle wakefulnessLifecycle, Handler handler) {
        this.context = context;
        this.uiEventLogger = uiEventLogger;
        this.gestureConfiguration = gestureConfiguration;
        this.statusBarStateController = statusBarStateController;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.bgHandler = handler;
        boolean isDozing = statusBarStateController.isDozing();
        this.isDozing = isDozing;
        boolean z = false;
        boolean z2 = wakefulnessLifecycle.mWakefulness == 2;
        this.isAwake = z2;
        if (z2 && !isDozing) {
            z = true;
        }
        this.screenOn = z;
        this.screenStateUpdated = true;
    }

    public static final void access$handleGestureDetection(CHREGestureSensor cHREGestureSensor, ColumbusProto$GestureDetected columbusProto$GestureDetected) {
        cHREGestureSensor.getClass();
        int i = columbusProto$GestureDetected.gestureType;
        int i2 = 0;
        boolean z = i == 2;
        if (i == 1) {
            i2 = 1;
        } else if (i == 2) {
            i2 = 2;
        }
        GestureSensor.DetectionProperties detectionProperties = new GestureSensor.DetectionProperties(z);
        GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = cHREGestureSensor.listener;
        if (gestureController$gestureSensorListener$1 != null) {
            gestureController$gestureSensorListener$1.onGestureDetected(i2, detectionProperties);
        }
        FeatureVectorDumper featureVectorDumper = cHREGestureSensor.featureVectorDumper;
        featureVectorDumper.getClass();
        int i3 = columbusProto$GestureDetected.gestureType;
        if (i3 != 1) {
            if (i3 != 2) {
                return;
            }
            featureVectorDumper.lastSingleTapFeatureVector = new FeatureVector(columbusProto$GestureDetected);
            return;
        }
        FeatureVector featureVector = featureVectorDumper.lastSingleTapFeatureVector;
        featureVectorDumper.lastSingleTapFeatureVector = null;
        if (featureVector == null) {
            Log.w("Columbus/GestureSensor", "Received double tap without single taps, event will not appear in sysdump");
        } else {
            featureVectorDumper.featureVectors.append(featureVector);
            featureVectorDumper.featureVectors.append(new FeatureVector(columbusProto$GestureDetected));
        }
    }

    public static final void access$handleNanoappEvents(CHREGestureSensor cHREGestureSensor, ColumbusProto$NanoappEvents columbusProto$NanoappEvents) {
        int number;
        cHREGestureSensor.getClass();
        for (ColumbusProto$NanoappEvent columbusProto$NanoappEvent : columbusProto$NanoappEvents.batchedEvents) {
            StatsEvent.Builder writeLong = StatsEvent.newBuilder().setAtomId(100051).writeLong(columbusProto$NanoappEvent.timestamp);
            switch (columbusProto$NanoappEvent.type) {
                case 1:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.GATE_START.getNumber();
                    break;
                case 2:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.GATE_STOP.getNumber();
                    break;
                case 3:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.HIGH_IMU_ODR_START.getNumber();
                    break;
                case 4:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.HIGH_IMU_ODR_STOP.getNumber();
                    break;
                case 5:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.ML_PREDICTION_START.getNumber();
                    break;
                case 6:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.ML_PREDICTION_STOP.getNumber();
                    break;
                case 7:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.SINGLE_TAP.getNumber();
                    break;
                case 8:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.DOUBLE_TAP.getNumber();
                    break;
                default:
                    number = PixelAtoms$DoubleTapNanoappEventReported$Type.UNKNOWN.getNumber();
                    break;
            }
            StatsLog.write(writeLong.writeInt(number).build());
        }
    }

    public static void sendMessageToNanoApp$default(CHREGestureSensor cHREGestureSensor, int i, byte[] bArr, Function0 function0, int i2) {
        if ((i2 & 4) != 0) {
            function0 = null;
        }
        Function0 function02 = function0;
        cHREGestureSensor.initializeContextHubClientIfNull();
        if (cHREGestureSensor.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
        } else {
            cHREGestureSensor.bgHandler.post(new CHREGestureSensor$sendMessageToNanoApp$1(cHREGestureSensor, i, bArr, null, function02));
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor close");
        if (this.isInitialized) {
            this.gestureConfiguration.listener = null;
            this.statusBarStateController.removeCallback(this.statusBarStateListener);
            this.wakefulnessLifecycle.removeObserver(this.wakefulnessLifecycleObserver);
            ContextHubClient contextHubClient = this.contextHubClient;
            if (contextHubClient != null) {
                contextHubClient.close();
            }
            this.contextHubClient = null;
            this.isInitialized = false;
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.featureVectorDumper.dump(printWriter, strArr);
    }

    public final void initializeContextHubClientIfNull() {
        if (this.contextHubClient == null) {
            ContextHubManager contextHubManager = (ContextHubManager) this.context.getSystemService("contexthub");
            List contextHubs = contextHubManager != null ? contextHubManager.getContextHubs() : null;
            if ((contextHubs != null ? contextHubs.size() : 0) == 0) {
                Log.e("Columbus/GestureSensor", "No context hubs found");
            } else if (contextHubs != null) {
                this.contextHubClient = contextHubManager.createClient((ContextHubInfo) contextHubs.get(0), this.contextHubClientCallback);
            }
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final boolean isListening() {
        return this.isListening;
    }

    public final void sendScreenState() {
        ColumbusProto$ScreenStateUpdate columbusProto$ScreenStateUpdate = new ColumbusProto$ScreenStateUpdate();
        columbusProto$ScreenStateUpdate.screenState = this.screenOn ? 1 : 2;
        byte[] byteArray = MessageNano.toByteArray(columbusProto$ScreenStateUpdate);
        Function0 function0 = new Function0() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$sendScreenState$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CHREGestureSensor.this.screenStateUpdated = true;
                return Unit.INSTANCE;
            }
        };
        CHREGestureSensor$sendScreenState$2 cHREGestureSensor$sendScreenState$2 = new CHREGestureSensor$sendScreenState$2(this);
        initializeContextHubClientIfNull();
        if (this.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
        } else {
            this.bgHandler.post(new CHREGestureSensor$sendMessageToNanoApp$1(this, 400, byteArray, cHREGestureSensor$sendScreenState$2, function0));
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void startListening() {
        if (!this.isInitialized) {
            Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor initialize");
            this.isInitialized = true;
            this.gestureConfiguration.listener = new CHREGestureSensor$initialize$1(this);
            this.statusBarStateController.addCallback(this.statusBarStateListener);
            this.wakefulnessLifecycle.addObserver(this.wakefulnessLifecycleObserver);
            initializeContextHubClientIfNull();
        }
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor startListening");
        this.isListening = true;
        startRecognizer();
        sendScreenState();
    }

    public final void startRecognizer() {
        ColumbusProto$RecognizerStart columbusProto$RecognizerStart = new ColumbusProto$RecognizerStart(0);
        columbusProto$RecognizerStart.sensitivity = this.gestureConfiguration.sensitivity;
        sendMessageToNanoApp$default(this, 100, MessageNano.toByteArray(columbusProto$RecognizerStart), new Function0() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$startRecognizer$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CHREGestureSensor.this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_LOW_POWER_ACTIVE);
                return Unit.INSTANCE;
            }
        }, 8);
    }

    @Override // com.google.android.systemui.columbus.legacy.sensors.GestureSensor
    public final void stopListening() {
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor stopListening");
        sendMessageToNanoApp$default(this, 101, new byte[0], new Function0() { // from class: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$stopListening$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CHREGestureSensor.this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_INACTIVE);
                return Unit.INSTANCE;
            }
        }, 8);
        this.isListening = false;
    }

    public final void updateScreenState() {
        boolean z = this.isAwake && !this.isDozing;
        if (this.screenOn == z && this.screenStateUpdated) {
            return;
        }
        this.screenOn = z;
        if (this.isListening) {
            sendScreenState();
        }
    }
}
