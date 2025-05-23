/*
 * Copyright (C) 2023 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.sun.nrmode.util.Constants

import android.os.SystemProperties
import android.util.Log

import org.sun.os.DebugConstants.DEBUG_NR_MODE

const val SIM_CARD_1 = 1
const val SIM_CARD_2 = 2

const val MODE_NSA_PRE = 0
const val MODE_NSA_ONLY = 1
const val MODE_SA_ONLY = 2
const val MODE_SA_PRE = 3
const val MODE_AUTO = 4

const val KEY_NR_MODE_SIM_1 = "preferred_nr_mode_sim1"
const val KEY_NR_MODE_SIM_2 = "preferred_nr_mode_sim2"

const val SETTINGS_SIM_1 = "user_preferred_nr_mode_sim1"
const val SETTINGS_SIM_2 = "user_preferred_nr_mode_sim2"

const val PROP_AUTO_MODE = "persist.sys.sun.radio.auto_nr_mode"

const val INTENT_SIM_STATE_CHANGED_CUSTOM = "org.sun.nrmode.intent.SIM_STATE_CHANGED"

fun logD(tag: String, message: String) {
    if (DEBUG_NR_MODE) {
        Log.d(tag, message)
    }
}

fun logE(tag: String, message: String) {
    Log.e(tag, message)
}

fun nrmodeToString(mode: Int): String {
    return when (mode) {
        MODE_NSA_PRE -> "nsa_preferred"
        MODE_NSA_ONLY -> "nsa_only"
        MODE_SA_ONLY -> "sa_only"
        MODE_SA_PRE -> "sa_preferred"
        else -> "unknown"
    }
}
