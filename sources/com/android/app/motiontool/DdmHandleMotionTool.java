package com.android.app.motiontool;

import android.ddm.DdmHandle;
import android.media.permission.SafeCloseable;
import android.util.Log;
import android.view.View;
import com.android.app.motiontool.BeginTraceResponse;
import com.android.app.motiontool.EndTraceResponse;
import com.android.app.motiontool.ErrorResponse;
import com.android.app.motiontool.HandshakeResponse;
import com.android.app.motiontool.MotionToolsResponse;
import com.android.app.motiontool.PollTraceResponse;
import com.android.app.viewcapture.data.MotionWindowData;
import com.google.protobuf.InvalidProtocolBufferException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DdmHandleMotionTool extends DdmHandle {
    public static DdmHandleMotionTool INSTANCE;
    public final MotionToolManager motionToolManager;
    public static final Companion Companion = new Companion();
    public static final int CHUNK_MOTO = ChunkHandler.type("MOTO");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    public DdmHandleMotionTool(MotionToolManager motionToolManager) {
        this.motionToolManager = motionToolManager;
    }

    public static void tryCatchingMotionToolManagerExceptions(MotionToolsResponse.Builder builder, Function0 function0) {
        try {
            function0.invoke();
        } catch (UnknownTraceIdException e) {
            int traceId = e.getTraceId();
            ErrorResponse.Builder newBuilder = ErrorResponse.newBuilder();
            ErrorResponse.Code code = ErrorResponse.Code.UNKNOWN_TRACE_ID;
            newBuilder.copyOnWrite();
            ErrorResponse.access$100((ErrorResponse) newBuilder.instance, code);
            newBuilder.copyOnWrite();
            ErrorResponse.access$300((ErrorResponse) newBuilder.instance, "No running Trace found with traceId " + traceId);
            builder.setError(newBuilder);
        } catch (WindowNotFoundException e2) {
            String windowId = e2.getWindowId();
            ErrorResponse.Builder newBuilder2 = ErrorResponse.newBuilder();
            ErrorResponse.Code code2 = ErrorResponse.Code.WINDOW_NOT_FOUND;
            newBuilder2.copyOnWrite();
            ErrorResponse.access$100((ErrorResponse) newBuilder2.instance, code2);
            newBuilder2.copyOnWrite();
            ErrorResponse.access$300((ErrorResponse) newBuilder2.instance, "No window found with windowId " + windowId);
            builder.setError(newBuilder2);
        }
    }

    public final Chunk handleChunk(Chunk chunk) {
        boolean z;
        MotionToolsResponse motionToolsResponse;
        try {
            MotionToolsRequest parseFrom = MotionToolsRequest.parseFrom(DdmHandle.wrapChunk(chunk).array());
            int number = parseFrom.getTypeCase().getNumber();
            if (number == 1) {
                HandshakeRequest handshake = parseFrom.getHandshake();
                MotionToolManager motionToolManager = this.motionToolManager;
                WindowIdentifier window = handshake.getWindow();
                synchronized (motionToolManager) {
                    z = motionToolManager.windowManagerGlobal.getRootView(window.getRootWindow()) != null;
                }
                HandshakeResponse.Status status = z ? HandshakeResponse.Status.OK : HandshakeResponse.Status.WINDOW_NOT_FOUND;
                MotionToolsResponse.Builder newBuilder = MotionToolsResponse.newBuilder();
                HandshakeResponse.Builder newBuilder2 = HandshakeResponse.newBuilder();
                newBuilder2.copyOnWrite();
                HandshakeResponse.access$300((HandshakeResponse) newBuilder2.instance);
                newBuilder2.copyOnWrite();
                HandshakeResponse.access$100((HandshakeResponse) newBuilder2.instance, status);
                newBuilder.copyOnWrite();
                MotionToolsResponse.access$500((MotionToolsResponse) newBuilder.instance, (HandshakeResponse) newBuilder2.build());
                motionToolsResponse = (MotionToolsResponse) newBuilder.build();
            } else if (number == 2) {
                final BeginTraceRequest beginTrace = parseFrom.getBeginTrace();
                final MotionToolsResponse.Builder newBuilder3 = MotionToolsResponse.newBuilder();
                Intrinsics.checkNotNull(newBuilder3);
                tryCatchingMotionToolManagerExceptions(newBuilder3, new Function0() { // from class: com.android.app.motiontool.DdmHandleMotionTool$handleBeginTraceRequest$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        int i;
                        MotionToolsResponse.Builder builder = MotionToolsResponse.Builder.this;
                        BeginTraceResponse.Builder newBuilder4 = BeginTraceResponse.newBuilder();
                        MotionToolManager motionToolManager2 = this.motionToolManager;
                        String rootWindow = beginTrace.getWindow().getRootWindow();
                        synchronized (motionToolManager2) {
                            i = motionToolManager2.traceIdCounter + 1;
                            motionToolManager2.traceIdCounter = i;
                            Log.d("MotionToolManager", "Begin Trace for id: " + i);
                            View rootView = motionToolManager2.windowManagerGlobal.getRootView(rootWindow);
                            if (rootView == null) {
                                throw new WindowNotFoundException(rootWindow);
                            }
                            motionToolManager2.traces.put(Integer.valueOf(i), new TraceMetadata(rootWindow, new MotionToolManager$beginTrace$1(0, motionToolManager2.viewCapture.startCapture(rootView, rootWindow), SafeCloseable.class, "close", "close()V", 0)));
                        }
                        newBuilder4.copyOnWrite();
                        BeginTraceResponse.access$100((BeginTraceResponse) newBuilder4.instance, i);
                        builder.copyOnWrite();
                        MotionToolsResponse.access$800((MotionToolsResponse) builder.instance, (BeginTraceResponse) newBuilder4.build());
                        return Unit.INSTANCE;
                    }
                });
                motionToolsResponse = (MotionToolsResponse) newBuilder3.build();
            } else if (number == 3) {
                final EndTraceRequest endTrace = parseFrom.getEndTrace();
                final MotionToolsResponse.Builder newBuilder4 = MotionToolsResponse.newBuilder();
                Intrinsics.checkNotNull(newBuilder4);
                tryCatchingMotionToolManagerExceptions(newBuilder4, new Function0() { // from class: com.android.app.motiontool.DdmHandleMotionTool$handleEndTraceRequest$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MotionWindowData pollTrace;
                        MotionToolsResponse.Builder builder = MotionToolsResponse.Builder.this;
                        EndTraceResponse.Builder newBuilder5 = EndTraceResponse.newBuilder();
                        MotionToolManager motionToolManager2 = this.motionToolManager;
                        int traceId = endTrace.getTraceId();
                        synchronized (motionToolManager2) {
                            Log.d("MotionToolManager", "End Trace for id: " + traceId);
                            Object obj = motionToolManager2.traces.get(Integer.valueOf(traceId));
                            if (obj == null) {
                                throw new UnknownTraceIdException(traceId);
                            }
                            pollTrace = motionToolManager2.pollTrace(traceId);
                            ((MotionToolManager$beginTrace$1) ((TraceMetadata) obj).stopTrace).invoke();
                            motionToolManager2.traces.remove(Integer.valueOf(traceId));
                        }
                        newBuilder5.copyOnWrite();
                        EndTraceResponse.access$100((EndTraceResponse) newBuilder5.instance, pollTrace);
                        builder.copyOnWrite();
                        MotionToolsResponse.access$1100((MotionToolsResponse) builder.instance, (EndTraceResponse) newBuilder5.build());
                        return Unit.INSTANCE;
                    }
                });
                motionToolsResponse = (MotionToolsResponse) newBuilder4.build();
            } else if (number != 4) {
                MotionToolsResponse.Builder newBuilder5 = MotionToolsResponse.newBuilder();
                ErrorResponse.Builder newBuilder6 = ErrorResponse.newBuilder();
                ErrorResponse.Code code = ErrorResponse.Code.INVALID_REQUEST;
                newBuilder6.copyOnWrite();
                ErrorResponse.access$100((ErrorResponse) newBuilder6.instance, code);
                newBuilder6.copyOnWrite();
                ErrorResponse.access$300((ErrorResponse) newBuilder6.instance, "Unknown request type");
                newBuilder5.setError(newBuilder6);
                motionToolsResponse = (MotionToolsResponse) newBuilder5.build();
            } else {
                final PollTraceRequest pollTrace = parseFrom.getPollTrace();
                final MotionToolsResponse.Builder newBuilder7 = MotionToolsResponse.newBuilder();
                Intrinsics.checkNotNull(newBuilder7);
                tryCatchingMotionToolManagerExceptions(newBuilder7, new Function0() { // from class: com.android.app.motiontool.DdmHandleMotionTool$handlePollTraceRequest$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MotionToolsResponse.Builder builder = MotionToolsResponse.Builder.this;
                        PollTraceResponse.Builder newBuilder8 = PollTraceResponse.newBuilder();
                        MotionWindowData pollTrace2 = this.motionToolManager.pollTrace(pollTrace.getTraceId());
                        newBuilder8.copyOnWrite();
                        PollTraceResponse.access$100((PollTraceResponse) newBuilder8.instance, pollTrace2);
                        builder.copyOnWrite();
                        MotionToolsResponse.access$1400((MotionToolsResponse) builder.instance, (PollTraceResponse) newBuilder8.build());
                        return Unit.INSTANCE;
                    }
                });
                motionToolsResponse = (MotionToolsResponse) newBuilder7.build();
            }
            byte[] byteArray = motionToolsResponse.toByteArray();
            return new Chunk(CHUNK_MOTO, byteArray, 0, byteArray.length);
        } catch (InvalidProtocolBufferException unused) {
            MotionToolsResponse.Builder newBuilder8 = MotionToolsResponse.newBuilder();
            ErrorResponse.Builder newBuilder9 = ErrorResponse.newBuilder();
            ErrorResponse.Code code2 = ErrorResponse.Code.INVALID_REQUEST;
            newBuilder9.copyOnWrite();
            ErrorResponse.access$100((ErrorResponse) newBuilder9.instance, code2);
            newBuilder9.copyOnWrite();
            ErrorResponse.access$300((ErrorResponse) newBuilder9.instance, "Invalid request format (Protobuf parse exception)");
            newBuilder8.setError(newBuilder9);
            byte[] byteArray2 = ((MotionToolsResponse) newBuilder8.build()).toByteArray();
            return new Chunk(CHUNK_MOTO, byteArray2, 0, byteArray2.length);
        }
    }

    public final void onConnected() {
    }

    public final void onDisconnected() {
    }
}
