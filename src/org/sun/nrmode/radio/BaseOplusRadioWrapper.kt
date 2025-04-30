/*
 * Copyright (C) 2023-2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.sun.nrmode.radio

import android.os.SystemProperties

import org.sun.nrmode.util.Constants.MODE_AUTO
import org.sun.nrmode.util.Constants.MODE_NSA_ONLY
import org.sun.nrmode.util.Constants.MODE_NSA_PRE
import org.sun.nrmode.util.Constants.MODE_SA_ONLY
import org.sun.nrmode.util.Constants.MODE_SA_PRE
import org.sun.nrmode.util.Constants.PROP_AUTO_MODE
import org.sun.nrmode.util.Constants.logD
import org.sun.nrmode.util.Constants.logE
import org.sun.nrmode.util.Constants.nrmodeToString

abstract class BaseOplusRadioWrapper<T> {

    abstract val tag: String

    private fun isModeValid(mode: Int): Boolean {
        return mode == MODE_NSA_ONLY ||
                mode == MODE_NSA_PRE ||
                mode == MODE_SA_ONLY ||
                mode == MODE_SA_PRE
    }

    private fun convertMode(mode: Int): Int {
        if (mode != MODE_AUTO) {
            return mode
        }
        return SystemProperties.getInt(PROP_AUTO_MODE, MODE_NSA_PRE)
    }

    fun setNrMode(simId: Int, mode: Int, fromUser: Boolean): Boolean {
        getService(simId)?.let { oplusRadio ->
            if (mode == MODE_AUTO && !fromUser) {
                logD(tag, "Skip set nr mode for non-user-requested auto mode")
                return true
            }
            val realMode = convertMode(mode)
            if (!isModeValid(realMode)) {
                logE(tag, "Invalid mode $mode")
                return false
            }
            logD(tag, "setNrMode for simId: $simId, mode: ${nrmodeToString(realMode)}")
            return setNrModeInternal(oplusRadio, realMode)
        }
        logE(tag, "Oplus radio for simId $simId is null")
        return false
    }

    abstract fun getService(simId: Int): T?
    abstract fun setNrModeInternal(oplusRadio: T, mode: Int): Boolean

    companion object {
        const val OPLUS_RIL_SERIAL = 1001
    }
}
