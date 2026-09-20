package io.github.asadman1523.privatespaceshortcuts

data class TargetKey(val profileSerial: Long, val component: String)

/** A request is transient, profile-specific, expires, and can only be consumed once. */
class LaunchGate(private val now: () -> Long, private val ttlMillis: Long = 120_000) {
    private data class Request(val target: TargetKey, val started: Long,
        var departed: Boolean = false, var returned: Boolean = false, var authorized: Boolean = false)
    private var request: Request? = null

    fun begin(target: TargetKey): Boolean {
        expire()
        if (request != null) return false
        request = Request(target, now())
        return true
    }
    fun departed() {
        expire()
        if (request?.returned == true) cancel() else request?.departed = true
    }
    fun interacted() { expire(); if (request?.returned == true) cancel() }
    fun approvedWithoutPrompt(unlocked: Boolean): TargetKey? {
        expire()
        val r = request ?: return null
        r.returned = true
        r.authorized = true
        return if (unlocked) consume() else null
    }
    fun returned(quiet: Boolean, unlocked: Boolean): TargetKey? {
        expire()
        val r = request ?: return null
        if (!r.departed) return null
        r.returned = true
        if (quiet) { cancel(); return null }
        r.authorized = true
        return if (unlocked) consume() else null
    }
    fun ready(target: TargetKey, quiet: Boolean, unlocked: Boolean): TargetKey? {
        expire()
        val r = request ?: return null
        if (r.target == target && r.authorized && quiet) { cancel(); return null }
        return if (r.target == target && r.returned && r.authorized && !quiet && unlocked) consume() else null
    }
    fun current(): TargetKey? { expire(); return request?.target }
    fun cancel() { request = null }
    private fun consume(): TargetKey? = request?.target.also { request = null }
    private fun expire() { request?.let { if (now() - it.started >= ttlMillis) cancel() } }
}
