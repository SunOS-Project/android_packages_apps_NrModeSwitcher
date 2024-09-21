/*
 * Copyright (C) 2024 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.nameless.nrmode.radio

object OplusRadioHelper {

    private val hidlWrapper = OplusRadioHidlWrapper()

    fun setNrMode(simId: Int, mode: Int, fromUser: Boolean = false): Boolean {
        return hidlWrapper.setNrMode(simId, mode, fromUser)
    }
}
