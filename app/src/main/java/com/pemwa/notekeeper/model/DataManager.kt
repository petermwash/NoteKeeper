package com.pemwa.notekeeper.model

object DataManager {
    val courses = HashMap<String, CourseInfo> ()
    val notes = ArrayList<NoteInfo> ()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses () {
        var course = CourseInfo(
            courseId = "android_intents",
            courseTitle = "Android programming with Intents"
        )
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(
            courseTitle = "Java Fundamentals: The Java Language",
            courseId = "java_lang"
        )
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes () {
        var note = NoteInfo(
            CourseInfo(
                courseId = "android_intents",
                courseTitle = "Android programming with Intents"
            ),
            "Android",
            "Android programming with Intents Android programming with Intents Android programming with Intents"
        )
        notes.add(note)

        note = NoteInfo(
            CourseInfo(
                courseId = "android_async",
                courseTitle = "Android Async programming and Services"
            ),
            "Android Async",
            "Android programming with Async Services"
        )
        notes.add(note)
        note = NoteInfo(
            CourseInfo(
                courseId = "java_lang",
                courseTitle = "Java Fundamentals: The Java Language"
            ),
            "Java Fundamentals",
            "Java Fundamentals Java Fundamentals Java Fundamentals Java Fundamentals"
        )
        notes.add(note)
        note = NoteInfo(
            CourseInfo(
                courseId = "java_core",
                courseTitle = "Java Fundamentals: The core Platform"
            ),
            "Java The core Platform",
            "Java Fundamentals: The core Platform Java Fundamentals: The core Platform Java Fundamentals: The core Platform"
        )
        notes.add(note)
    }

    fun loadNotes(): List<NoteInfo> {
        return notes
    }
}