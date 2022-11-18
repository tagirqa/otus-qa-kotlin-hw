import kotlin.reflect.full.declaredMemberFunctions
import mu.KotlinLogging

class TestRunnerImpl : TestRunner {

    private val logger = KotlinLogging.logger {}

    override fun <T> runTest(steps: T, test: () -> Unit) {
        val before = steps!!::class.declaredMemberFunctions.filter { it.name.contains("before") }
        val after = steps!!::class.declaredMemberFunctions.filter { it.name.contains("after") }
        before.forEach { x -> x.call(steps).apply { logger.info("method ${x.name}() called") } }
        steps.apply { test() }
        after.reversed().forEach { x -> x.call(steps).apply { logger.info("method ${x.name}() called") } }
    }

}