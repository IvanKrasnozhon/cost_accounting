package com.example.costaccounting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val costAccountingKey = "costAccounting"

    init {
        if (!savedStateHandle.contains(costAccountingKey)) {
            savedStateHandle[costAccountingKey] = CostAccounting()
        }
    }

    var costAccounting: CostAccounting
        get() = savedStateHandle[costAccountingKey] ?: CostAccounting()
        set(value) {
            savedStateHandle[costAccountingKey] = value
        }
}