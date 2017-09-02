package tutorial

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class StudentController {
    /** example for inline function **/
    @RequestMapping("/totalstudent")
    fun totalStudents() = 50

    /** Get the details of a student */
    @RequestMapping("/student")
    fun getStudent() : Student {
        val student = Student(
                firstName = "John",
                lastName = "David",
                grade = "8th",
                email = "johndavid@example.com"
        )
        return student
    }

    /**
     * Pretend to create a new student
     * @param student The details of the new student to create
     */
    @RequestMapping(value = "/student", method = arrayOf(RequestMethod.POST))
    fun createStudent(@RequestBody student: Student): Student {
        return Student(
                firstName = student.firstName,
                lastName = student.lastName,
                grade = student.grade,
                email = student.email
        )
    }

    /** Cause an error to occur */
    @RequestMapping("/raiseError")
    fun raiseError() {
        throw IllegalArgumentException("This shouldn't have happened")
    }

    /** Handle the error */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleError(e: IllegalArgumentException) = e.message
}
