import kotlin.reflect.full.declaredMemberFunctions

class TestRunnerImpl: TestRunner {

    override fun <T> runTest(steps: T, test: () -> Unit) {
        val before = steps!!::class.declaredMemberFunctions.filter { it.name.contains("before") }
        val after = steps!!::class.declaredMemberFunctions.filter { it.name.contains("after") }
        before.forEach { x -> x.call(steps) }
        steps.apply { test() }
        after.reversed().forEach { x -> x.call(steps) }
    }

}