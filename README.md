#Test2
# QA Group - Masterclass, February 2017

Run tests with maven:

`
$ mvn clean test -DPropertyManager.file=src\test\resources\local.properties && mvn site
`

Allure report will be placed in './target/site/' folder. Just open 'allure-maven-plugin.html' file in Firefox browser.
