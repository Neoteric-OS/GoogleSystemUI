package com.google.android.systemui.elmyra.sensors;

import android.content.Context;
import android.hardware.location.ContextHubClient;
import android.hardware.location.ContextHubClientCallback;
import android.hardware.location.ContextHubInfo;
import android.hardware.location.ContextHubManager;
import android.hardware.location.NanoAppMessage;
import android.util.Log;
import android.util.TypedValue;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.SnapshotConfiguration;
import com.google.android.systemui.elmyra.SnapshotController;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis;
import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$GestureDetected;
import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$GestureProgress;
import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$RecognizerStart;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.PrintWriter;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CHREGestureSensor implements Dumpable, GestureSensor {
    public final Context mContext;
    public ContextHubClient mContextHubClient;
    public final AnonymousClass1 mContextHubClientCallback = new ContextHubClientCallback() { // from class: com.google.android.systemui.elmyra.sensors.CHREGestureSensor.1
        public final void onHubReset(ContextHubClient contextHubClient) {
            Log.d("Elmyra/GestureSensor", "HubReset: " + contextHubClient.getAttachedHub().getId());
        }

        public final void onMessageFromNanoApp(ContextHubClient contextHubClient, NanoAppMessage nanoAppMessage) {
            if (nanoAppMessage.getNanoAppId() != 5147455389092024334L) {
                return;
            }
            try {
                int messageType = nanoAppMessage.getMessageType();
                if (messageType != 1) {
                    switch (messageType) {
                        case 300:
                            CHREGestureSensor.this.mController.onGestureProgress(ContextHubMessages$GestureProgress.parseFrom(nanoAppMessage.getMessageBody()).progress);
                            break;
                        case 301:
                            ContextHubMessages$GestureDetected parseFrom = ContextHubMessages$GestureDetected.parseFrom(nanoAppMessage.getMessageBody());
                            CHREGestureSensor.this.mController.onGestureDetected(new GestureSensor.DetectionProperties(parseFrom.hapticConsumed, parseFrom.hostSuspended));
                            break;
                        case 302:
                            SnapshotProtos$Snapshot snapshotProtos$Snapshot = (SnapshotProtos$Snapshot) MessageNano.mergeFrom(new SnapshotProtos$Snapshot(), nanoAppMessage.getMessageBody());
                            snapshotProtos$Snapshot.sensitivitySetting = CHREGestureSensor.this.mGestureConfiguration.getSensitivity();
                            CHREGestureSensor.this.mController.onSnapshotReceived(snapshotProtos$Snapshot);
                            break;
                        case 303:
                            ChassisProtos$Chassis chassisProtos$Chassis = (ChassisProtos$Chassis) MessageNano.mergeFrom(new ChassisProtos$Chassis(), nanoAppMessage.getMessageBody());
                            AssistGestureController assistGestureController = CHREGestureSensor.this.mController;
                            assistGestureController.mChassis = chassisProtos$Chassis;
                            assistGestureController.mWestworldLogger.mChassis = chassisProtos$Chassis;
                            break;
                        case 304:
                        case 305:
                            break;
                        default:
                            Log.e("Elmyra/GestureSensor", "Unknown message type: " + nanoAppMessage.getMessageType());
                            break;
                    }
                } else {
                    CHREGestureSensor cHREGestureSensor = CHREGestureSensor.this;
                    if (cHREGestureSensor.mIsListening) {
                        cHREGestureSensor.startRecognizer$1();
                    }
                }
            } catch (InvalidProtocolBufferNanoException e) {
                Log.e("Elmyra/GestureSensor", "Invalid protocol buffer", e);
            }
        }

        public final void onNanoAppAborted(ContextHubClient contextHubClient, long j, int i) {
            if (j == 5147455389092024334L) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Nanoapp aborted, code: ", "Elmyra/GestureSensor", i);
            }
        }
    };
    public int mContextHubRetryCount;
    public final AssistGestureController mController;
    public final GestureConfiguration mGestureConfiguration;
    public boolean mIsListening;
    public final float mProgressDetectThreshold;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.elmyra.sensors.CHREGestureSensor$1] */
    public CHREGestureSensor(Context context, GestureConfiguration gestureConfiguration, SnapshotConfiguration snapshotConfiguration) {
        this.mContext = context;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(R.dimen.elmyra_progress_detect_threshold, typedValue, true);
        this.mProgressDetectThreshold = typedValue.getFloat();
        AssistGestureController assistGestureController = new AssistGestureController(context, this, gestureConfiguration, snapshotConfiguration);
        this.mController = assistGestureController;
        CHREGestureSensor$$ExternalSyntheticLambda0 cHREGestureSensor$$ExternalSyntheticLambda0 = new CHREGestureSensor$$ExternalSyntheticLambda0(this);
        SnapshotController snapshotController = assistGestureController.mSnapshotController;
        if (snapshotController != null) {
            snapshotController.mSnapshotListener = cHREGestureSensor$$ExternalSyntheticLambda0;
        }
        this.mGestureConfiguration = gestureConfiguration;
        gestureConfiguration.mListener = new CHREGestureSensor$$ExternalSyntheticLambda0(this);
        initializeContextHubClientIfNull$1();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "CHREGestureSensor state:", "  mIsListening: "), this.mIsListening, printWriter);
        if (this.mContextHubClient == null) {
            printWriter.println("  mContextHubClient is null. Likely no context hubs were found");
        }
        printWriter.println("  mContextHubRetryCount: " + this.mContextHubRetryCount);
        this.mController.dump(printWriter, strArr);
    }

    public final void initializeContextHubClientIfNull$1() {
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.context_hub") && this.mContextHubClient == null) {
            ContextHubManager contextHubManager = (ContextHubManager) this.mContext.getSystemService("contexthub");
            List contextHubs = contextHubManager.getContextHubs();
            if (contextHubs.size() == 0) {
                Log.e("Elmyra/GestureSensor", "No context hubs found");
            } else {
                this.mContextHubClient = contextHubManager.createClient((ContextHubInfo) contextHubs.get(0), this.mContextHubClientCallback);
                this.mContextHubRetryCount++;
            }
        }
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public final boolean isListening() {
        return this.mIsListening;
    }

    public final void sendMessageToNanoApp(int i, byte[] bArr) {
        initializeContextHubClientIfNull$1();
        if (this.mContextHubClient == null) {
            Log.e("Elmyra/GestureSensor", "ContextHubClient null");
            return;
        }
        int sendMessageToNanoApp = this.mContextHubClient.sendMessageToNanoApp(NanoAppMessage.createMessageToNanoApp(5147455389092024334L, i, bArr));
        if (sendMessageToNanoApp != 0) {
            Log.e("Elmyra/GestureSensor", String.format("Unable to send message %d to nanoapp, error code %d", Integer.valueOf(i), Integer.valueOf(sendMessageToNanoApp)));
        }
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public final void setGestureListener(GestureSensor.Listener listener) {
        this.mController.mGestureListener = listener;
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public final void startListening() {
        this.mIsListening = true;
        startRecognizer$1();
    }

    public final void startRecognizer$1() {
        ContextHubMessages$RecognizerStart contextHubMessages$RecognizerStart = new ContextHubMessages$RecognizerStart();
        contextHubMessages$RecognizerStart.progressReportThreshold = this.mProgressDetectThreshold;
        contextHubMessages$RecognizerStart.sensitivity = this.mGestureConfiguration.getSensitivity();
        sendMessageToNanoApp(200, MessageNano.toByteArray(contextHubMessages$RecognizerStart));
        if (this.mController.mChassis == null) {
            sendMessageToNanoApp(204, new byte[0]);
        }
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public final void stopListening() {
        sendMessageToNanoApp(201, new byte[0]);
        this.mIsListening = false;
    }
}
