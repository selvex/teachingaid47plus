# teachingaid47plus
An app for helping teachers during the lesson.
Add classes, add students, add subjects and manage the participation, homework
and the test grades in a single app.

# Information on Testing and plugins used
We are using SugarORM (http://satyan.github.io/sugar/) for database management.
While the plugin itself should be seamlessly imported by gradle, it is
neccessary to *manually* delete the database before running most testcases since
testdata is widely reused and reusing it will result in the app telling you
things already exist. This can be done by executing the testZKeepDbClean() from
the MainActivityTest.java file.

Because the database needs an application context, all tests are done in
uitests, since unit tests don't provide a useable context.
