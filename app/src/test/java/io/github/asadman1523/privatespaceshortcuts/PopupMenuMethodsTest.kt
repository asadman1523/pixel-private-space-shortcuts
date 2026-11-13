package io.github.asadman1523.privatespaceshortcuts

import org.junit.Assert.*
import org.junit.Test

@Suppress("UNUSED_PARAMETER")
class PopupMenuMethodsTest {
    class OldPopup { fun populateAndShowRows(view: Any, count: Int, rows: List<Any>) {} }
    class PreviousPopup { fun populateAndShowRows(count: Int, rows: List<Any>) {} }
    open class CurrentPopup {
        fun addSystemShortcuts(rows: List<Any>) {}
        fun showComposePopup(count: Int, rows: List<Any>) {}
    }
    class InheritedPopup : CurrentPopup()
    class BothPopup : CurrentPopup() {
        fun populateAndShowRows(count: Int, rows: List<Any>) {}
    }
    class ComposeOnly { fun showComposePopup(count: Int, rows: List<Any>) {} }
    class WrongSignature { fun populateAndShowRows(count: String, rows: List<Any>) {} }

    @Test fun resolvesOlderThreeArgumentPopup() {
        assertEquals(3, PopupMenuMethods.resolve(OldPopup::class.java)!!.parameterCount)
    }
    @Test fun resolvesPreviousTwoArgumentPopup() {
        assertEquals(2, PopupMenuMethods.resolve(PreviousPopup::class.java)!!.parameterCount)
    }
    @Test fun resolvesRenamed907Entrypoint() {
        assertEquals("addSystemShortcuts", PopupMenuMethods.resolve(CurrentPopup::class.java)!!.name)
    }
    @Test fun findsInheritedEntrypoint() {
        assertEquals(CurrentPopup::class.java,
            PopupMenuMethods.resolve(InheritedPopup::class.java)!!.declaringClass)
    }
    @Test fun prefersOriginalEntrypointToAvoidDoubleInsertion() {
        assertEquals("populateAndShowRows", PopupMenuMethods.resolve(BothPopup::class.java)!!.name)
    }
    @Test fun missingOrChangedMenuDoesNotThrowOrSelectComposeItems() {
        assertNull(PopupMenuMethods.resolve(ComposeOnly::class.java))
        assertNull(PopupMenuMethods.resolve(WrongSignature::class.java))
        assertNull(PopupMenuMethods.resolve(Any::class.java))
    }
}
