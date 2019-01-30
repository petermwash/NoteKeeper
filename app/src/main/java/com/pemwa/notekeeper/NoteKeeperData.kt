package com.pemwa.notekeeper

data class CourseInfo (val courseId: String, val courseTitle: String) {
    override fun toString(): String {
        return courseTitle
    }
}

data class  NoteInfo (var course: CourseInfo? = null, var noteTitle: String = "No title", var noteText: String? = null) {
    override fun toString(): String {
        return noteTitle
    }
}