APP_NAME = furniture
SRC_DIR = src

GRADLE_CMD = ./gradlew
JAR_FILE=build/libs/furniture-0.0.1-SNAPSHOT.jar

.PHONY: install run
install:
	$(GRADLE_CMD) build
run: install
	java -jar $(JAR_FILE)
clean:
	$(GRADLE_CMD) clean
all: clean install run
.PHONY: install run clean all