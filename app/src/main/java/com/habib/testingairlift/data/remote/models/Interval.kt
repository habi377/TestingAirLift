package com.habib.testingairlift.data.remote.models

data class Interval(
    val market_cap_change: String,
    val market_cap_change_pct: String,
    val price_change: String,
    val price_change_pct: String,
    val volume: String,
    val volume_change: String,
    val volume_change_pct: String
)