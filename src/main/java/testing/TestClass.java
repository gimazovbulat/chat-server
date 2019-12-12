package testing;

public class TestClass implements TestInt{
    TestInt2 testClass2;

    public TestClass(TestInt2 testClass2) {
        this.testClass2 = testClass2;
    }

    public TestClass() {
    }

    public TestInt2 getTestClass2() {
        return testClass2;
    }

    public void setTestClass2(TestInt2 testClass2) {
        this.testClass2 = testClass2;
    }
}
