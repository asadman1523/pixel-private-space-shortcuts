package io.github.asadman1523.privatespaceshortcuts

import org.junit.Assert.*
import org.junit.Test

class LaunchGateTest {
    private var time = 0L
    private val gate = LaunchGate({ time }, 100)
    private val target = TargetKey(10, "example/.Main")

    @Test fun successfulUnlockIsConsumedOnce() {
        assertTrue(gate.begin(target)); gate.departed()
        assertEquals(target, gate.returned(false, true))
        assertNull(gate.ready(target, false, true))
    }
    @Test fun cancellationNeverLaunchesOnLaterUnlock() {
        gate.begin(target); gate.departed()
        assertNull(gate.returned(true, false))
        assertNull(gate.ready(target, false, true))
        assertNull(gate.current())
    }
    @Test fun unlockBroadcastAloneCannotAuthorizeLaunch() {
        gate.begin(target)
        assertNull(gate.ready(target, false, true))
        gate.departed()
        assertNull(gate.ready(target, false, true))
    }
    @Test fun returnBeforePromptCannotLaunch() {
        gate.begin(target)
        assertNull(gate.returned(false, true))
    }
    @Test fun waitsForProfileAfterAuthorizedReturn() {
        gate.begin(target); gate.departed()
        assertNull(gate.returned(false, false))
        assertEquals(target, gate.ready(target, false, true))
    }
    @Test fun anotherProfileCannotCompleteRequest() {
        gate.begin(target); gate.departed(); gate.returned(false, false)
        assertNull(gate.ready(TargetKey(11, target.component), false, true))
        assertEquals(target, gate.ready(target, false, true))
    }
    @Test fun repeatTapDoesNotReplacePendingTarget() {
        assertTrue(gate.begin(target))
        assertFalse(gate.begin(TargetKey(11, "other/.Main")))
        assertEquals(target, gate.current())
    }
    @Test fun timeoutAndExplicitCancellationClearRequests() {
        gate.begin(target); time = 100
        assertNull(gate.current())
        assertTrue(gate.begin(target)); gate.cancel()
        assertNull(gate.current())
    }
    @Test fun profileRelockedBeforeReadyCannotLaunch() {
        gate.begin(target); gate.departed(); gate.returned(false, false)
        assertNull(gate.ready(target, true, true))
        assertNull(gate.ready(target, false, true))
    }
    @Test fun systemCanApproveWithoutShowingCredentials() {
        gate.begin(target)
        assertNull(gate.approvedWithoutPrompt(false))
        assertEquals(target, gate.ready(target, false, true))
        assertNull(gate.ready(target, false, true))
    }
    @Test fun oldExpiryDoesNotCancelNewRequest() {
        gate.begin(target); gate.approvedWithoutPrompt(true)
        time = 90; gate.begin(target)
        time = 100
        assertEquals(target, gate.current())
    }
    @Test fun readinessPollingCompletesWithoutAnotherBroadcast() {
        gate.begin(target); gate.departed()
        assertNull(gate.returned(false, false))
        repeat(5) { time += 5; assertNull(gate.ready(target, false, false)) }
        assertEquals(target, gate.ready(target, false, true))
        assertNull(gate.ready(target, false, true))
    }
    @Test fun leavingLauncherWhileProfileStartsCancels() {
        gate.begin(target); gate.departed(); gate.returned(false, false)
        gate.departed()
        assertNull(gate.ready(target, false, true))
    }
    @Test fun newInteractionAfterReturningCancelsPendingLaunch() {
        gate.begin(target); gate.departed(); gate.returned(false, false)
        gate.interacted()
        assertNull(gate.ready(target, false, true))
    }
    @Test fun initiatingTapDoesNotCancelAuthentication() {
        gate.begin(target); gate.interacted()
        assertEquals(target, gate.current())
    }
}
