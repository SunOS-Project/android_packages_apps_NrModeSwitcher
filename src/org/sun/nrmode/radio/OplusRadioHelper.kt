/*
 * Copyright (C) 2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.sun.nrmode.radio

object OplusRadioHelper {

    private val aidlWrapper = OplusRadioAidlWrapper()
    private val hidlWrapper = OplusRadioHidlWrapper()

    fun setNrMode(simId: Int, mode: Int, fromUser: Boolean = false): Boolean {
        return aidlWrapper.setNrMode(simId, mode, fromUser) ||
                hidlWrapper.setNrMode(simId, mode, fromUser)
    }
}
