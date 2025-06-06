/*
 * Copyright (C) 2023-2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package vendor.oplus.hardware.radio.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public interface IOplusRadio extends IBase {

    public static final String kInterfaceName = "vendor.oplus.hardware.radio@1.0::IOplusRadio";

    @Override
    IHwBinder asBinder();

    @Override
    void debug(NativeHandle fd, ArrayList<String> options) throws RemoteException;

    @Override
    DebugInfo getDebugInfo() throws RemoteException;

    @Override
    ArrayList<byte[]> getHashChain() throws RemoteException;

    @Override
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override
    String interfaceDescriptor() throws RemoteException;

    @Override
    boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException;

    @Override
    void notifySyspropsChanged() throws RemoteException;

    @Override
    void ping() throws RemoteException;

    @Override
    void setHALInstrumentation() throws RemoteException;

    @Override
    boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException;

    void setCallback(IOplusRadioResponse responseCallback, IOplusRadioIndication indicationCallback) throws RemoteException;

    void setNrMode(int serial, int mode) throws RemoteException;

    static IOplusRadio asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        final IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IOplusRadio)) {
            return (IOplusRadio) iface;
        }
        final IOplusRadio proxy = new Proxy(binder);
        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IOplusRadio castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IOplusRadio getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IOplusRadio getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    static IOplusRadio getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IOplusRadio getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IOplusRadio {

        private final IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        @Override
        public IHwBinder asBinder() {
            return mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of " + kInterfaceName + "]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override
        public void setCallback(IOplusRadioResponse responseCallback, IOplusRadioIndication indicationCallback) throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(kInterfaceName);
            _hidl_request.writeStrongBinder(responseCallback == null ? null : responseCallback.asBinder());
            _hidl_request.writeStrongBinder(indicationCallback != null ? indicationCallback.asBinder() : null);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void setNrMode(int serial, int mode) throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(kInterfaceName);
            _hidl_request.writeInt32(serial);
            _hidl_request.writeInt32(mode);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(28, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public ArrayList<String> interfaceChain() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                final ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void debug(NativeHandle fd, ArrayList<String> options) throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            _hidl_request.writeNativeHandle(fd);
            _hidl_request.writeStringVector(options);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256131655, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public String interfaceDescriptor() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                final String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                final ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                final HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                final int _hidl_vec_size = _hidl_blob.getInt32(8L);
                final HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    final byte[] _hidl_vec_element = new byte[32];
                    final long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    childBlob.copyToInt8Array(_hidl_array_offset_1, _hidl_vec_element, 32);
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void setHALInstrumentation() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return mRemote.linkToDeath(recipient, cookie);
        }

        @Override
        public void ping() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public DebugInfo getDebugInfo() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                final DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public void notifySyspropsChanged() throws RemoteException {
            final HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            final HwParcel _hidl_reply = new HwParcel();
            try {
                mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IOplusRadio {
        @Override
        public IHwBinder asBinder() {
            return this;
        }

        @Override
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(kInterfaceName, IBase.kInterfaceName));
        }

        @Override
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override
        public final String interfaceDescriptor() {
            return kInterfaceName;
        }

        @Override
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(
                    new byte[]{25, -88, -53, 35, -69, 84, 88, -60, 82, -119, 5, 13, 119, 40, -124, 36, -14, 65, 58, 60, 61, -39, 58, 80, -95, 2, -98, -62, 116, 26, -57, -36},
                    new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override
        public final void setHALInstrumentation() {
        }

        @Override
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override
        public final void ping() {
        }

        @Override
        public final DebugInfo getDebugInfo() {
            final DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(kInterfaceName);
                    final IOplusRadioResponse responseCallback =
                            IOplusRadioResponse.asInterface(_hidl_request.readStrongBinder());
                    final IOplusRadioIndication indicationCallback =
                            IOplusRadioIndication.asInterface(_hidl_request.readStrongBinder());
                    setCallback(responseCallback, indicationCallback);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 28:
                    _hidl_request.enforceInterface(kInterfaceName);
                    final int serial = _hidl_request.readInt32();
                    final int mode = _hidl_request.readInt32();
                    setNrMode(serial, mode);
                    return;
                case 256067662:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    final ArrayList<String> _hidl_out_descriptors = interfaceChain();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeStringVector(_hidl_out_descriptors);
                    _hidl_reply.send();
                    return;
                case 256131655:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    final NativeHandle fd = _hidl_request.readNativeHandle();
                    final ArrayList<String> options = _hidl_request.readStringVector();
                    debug(fd, options);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 256136003:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    final String _hidl_out_descriptor = interfaceDescriptor();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeString(_hidl_out_descriptor);
                    _hidl_reply.send();
                    return;
                case 256398152:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    final ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                    _hidl_reply.writeStatus(0);
                    final HwBlob _hidl_blob = new HwBlob(16);
                    final int _hidl_vec_size = _hidl_out_hashchain.size();
                    _hidl_blob.putInt32(8L, _hidl_vec_size);
                    _hidl_blob.putBool(12L, false);
                    final HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                    for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                        final long _hidl_array_offset_1 = _hidl_index_0 * 32;
                        final byte[] _hidl_array_item_1 = _hidl_out_hashchain.get(_hidl_index_0);
                        if (_hidl_array_item_1 == null || _hidl_array_item_1.length != 32) {
                            throw new IllegalArgumentException("Array element is not of the expected length");
                        }
                        childBlob.putInt8Array(_hidl_array_offset_1, _hidl_array_item_1);
                    }
                    _hidl_blob.putBlob(0L, childBlob);
                    _hidl_reply.writeBuffer(_hidl_blob);
                    _hidl_reply.send();
                    return;
                case 256462420:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    setHALInstrumentation();
                    return;
                case 256921159:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    ping();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.send();
                    return;
                case 257049926:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    final DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 257120595:
                    _hidl_request.enforceInterface(IBase.kInterfaceName);
                    notifySyspropsChanged();
                    return;
            }
        }
    }
}
