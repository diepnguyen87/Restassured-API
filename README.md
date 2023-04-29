## Run testcase with maven commandline
```
mvn clean test -DxmlSuite="src/test/resources/test-suites/Regression.xml"
mvn clean test -DxmlSuite="src/test/resources/test-suites/Regression.xml" -Demail=${email} -Dtoken=${token}
```
