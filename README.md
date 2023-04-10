## How to Run
[Maven](https://maven.apache.org/download.cgi?.) is required to run.
After installing, run these commands from your terminal.

`mvn install` will install all the dependencies required to run.

`mvn javafx:run` is the correct way to run it.

To verify the compilation of the source run `mvn verify`.

To clean the target folder, run `mvn clean`.

These commands can be chained for example `mvn install javafx:run` will install dependencies first then run the program.
