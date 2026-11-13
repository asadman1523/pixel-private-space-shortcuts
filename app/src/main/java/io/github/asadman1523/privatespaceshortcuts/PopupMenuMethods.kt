package io.github.asadman1523.privatespaceshortcuts

import java.lang.reflect.Method

/** Only select native SystemShortcut-list entrypoints, never Compose PopupItem lists. */
internal object PopupMenuMethods {
    fun resolve(type: Class<*>): Method? {
        for (name in listOf("populateAndShowRows", "addSystemShortcuts")) {
            var current: Class<*>? = type
            while (current != null) {
                val matches = current.declaredMethods.filter {
                    it.name == name && it.returnType == Void.TYPE &&
                        it.parameterTypes.lastOrNull() == List::class.java &&
                        if (name == "addSystemShortcuts") it.parameterCount == 1
                        else it.parameterCount in 2..3 &&
                            it.parameterTypes[it.parameterCount - 2] == Int::class.javaPrimitiveType
                }
                if (matches.size > 1) return null
                matches.singleOrNull()?.let { return it }
                current = current.superclass
            }
        }
        return null
    }
}
