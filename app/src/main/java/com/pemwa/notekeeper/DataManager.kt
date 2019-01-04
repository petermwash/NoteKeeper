package com.pemwa.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo> ()
    val notes = ArrayList<NoteInfo> ()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses () {
        var course = CourseInfo(courseId = "android_intents", courseTitle = "Android programming with Intents")
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(courseTitle = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes () {
        var note = NoteInfo(
            CourseInfo(courseId = "android_intents", courseTitle = "Android programming with Intents"),
            "Android",
            "Android programming with Intents Android programming with Intents Android programming with Intents"
        )
        notes.add(note)
    }
}