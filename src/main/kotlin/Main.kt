fun main() {
    TestRunnerImpl().runTest(Steps()) {
        test()
    }
}


fun test() {
    println("test init")
}
