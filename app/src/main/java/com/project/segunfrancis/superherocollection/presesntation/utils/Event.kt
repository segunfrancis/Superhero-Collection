package com.project.segunfrancis.superherocollection.presesntation.utils

/**
 * Created by SegunFrancis
 *
 * Utility class for LiveDate that handles single event scenarios
 */

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevent its use again
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}