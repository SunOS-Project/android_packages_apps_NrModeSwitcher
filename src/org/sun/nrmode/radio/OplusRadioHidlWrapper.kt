/*
 * Copyright (C) 2023-2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.sun.nrmode.radio

import org.sun.nrmode.util.Constants.SIM_CARD_1
import org.sun.nrmode.util.Constants.SIM_CARD_2
import org.sun.nrmode.util.Constants.logE

import vendor.oplus.hardware.radio.V1_0.IOplusRadio
import vendor.oplus.hardware.radio.V1_0.IOplusRadioIndication
import vendor.oplus.hardware.radio.V1_0.IOplusRadioResponse

class OplusRadioHidlWrapper : BaseOplusRadioWrapper<IOplusRadio>() {

    override val tag = "OplusRadioHidlWrapper"

    private var oplusRadioSim1: IOplusRadio? = null
    private var oplusRadioSim2: IOplusRadio? = null

    private val oplusRadioIndicationStub = object : IOplusRadioIndication.Stub() {}
    private val oplusRadioResponseStub = object : IOplusRadioResponse.Stub() {}

    override fun getService(simId: Int): IOplusRadio? {
        try {
            when (simId) {
                SIM_CARD_1 -> {
                    if (oplusRadioSim1 == null) {
                        oplusRadioSim1 = IOplusRadio.getService(OPLUS_SLOT_1)
                        oplusRadioSim1?.setCallback(oplusRadioResponseStub, oplusRadioIndicationStub)
                    }
                    return oplusRadioSim1
                }
                SIM_CARD_2 -> {
                    if (oplusRadioSim2 == null) {
                        oplusRadioSim2 = IOplusRadio.getService(OPLUS_SLOT_2)
                        oplusRadioSim2?.setCallback(oplusRadioResponseStub, oplusRadioIndicationStub)
                    }
                    return oplusRadioSim2
                }
                else -> return null
            }
        } catch (_: Exception) {
            logE(tag, "Failed to get oplus radio hidl for simId $simId")
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
        private const val OPLUS_SLOT_1 = "oplus_slot1"
        private const val OPLUS_SLOT_2 = "oplus_slot2"
    }
}
