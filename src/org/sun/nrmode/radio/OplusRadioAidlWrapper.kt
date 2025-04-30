/*
 * Copyright (C) 2023-2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.sun.nrmode.radio

import android.os.Binder
import android.os.ServiceManager

import org.sun.nrmode.util.Constants.SIM_CARD_1
import org.sun.nrmode.util.Constants.SIM_CARD_2
import org.sun.nrmode.util.Constants.logE

import vendor.oplus.hardware.radio.IOplusRadio
import vendor.oplus.hardware.radio.IOplusRadioIndication
import vendor.oplus.hardware.radio.IOplusRadioResponse

class OplusRadioAidlWrapper : BaseOplusRadioWrapper<IOplusRadio>() {

    override val tag = "OplusRadioAidlWrapper"

    private var oplusRadioSim1: IOplusRadio? = null
    private var oplusRadioSim2: IOplusRadio? = null

    private val oplusRadioIndication = IOplusRadioIndication.Default()
    private val oplusRadioResponse = IOplusRadioResponse.Default()

    override fun getService(simId: Int): IOplusRadio? {
        try {
            when (simId) {
                SIM_CARD_1 -> {
                    if (oplusRadioSim1 == null) {
                        ServiceManager.getService(AIDL_SERVICE_NAME + SUFFIX_SLOT_1)?.let { service ->
                            oplusRadioSim1 = IOplusRadio.Stub.asInterface(Binder.allowBlocking(service))
                            oplusRadioSim1?.setCallback(oplusRadioResponse, oplusRadioIndication)
                        }
                    }
                    return oplusRadioSim1
                }
                SIM_CARD_2 -> {
                    if (oplusRadioSim2 == null) {
                        ServiceManager.getService(AIDL_SERVICE_NAME + SUFFIX_SLOT_2)?.let { service ->
                            oplusRadioSim2 = IOplusRadio.Stub.asInterface(Binder.allowBlocking(service))
                            oplusRadioSim2?.setCallback(oplusRadioResponse, oplusRadioIndication)
                        }
                    }
                    return oplusRadioSim2
                }
                else -> return null
            }
        } catch (_: Exception) {
            logE(tag, "Failed to get oplus radio aidl for simId $simId")
            return null
        }
    }

    override fun setNrModeInternal(oplusRadio: IOplusRadio, mode: Int): Boolean {
        try {
            oplusRadio.setNrMode(OPLUS_RIL_SERIAL, mode)
            return true
        } catch (_: Exception) {
            logE(tag, "Failed to set nr mode")
            return false
        }
    }

    companion object {
        private const val AIDL_SERVICE_NAME = "vendor.oplus.hardware.radio.IRadioStable/OplusRadio"
        private const val SUFFIX_SLOT_1 = "0"
        private const val SUFFIX_SLOT_2 = "1"
    }
}
