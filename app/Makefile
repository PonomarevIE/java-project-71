.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 9.0.0

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

lint:
	./gradlew checkstyleMain

build-run: clean build run

.PHONY: build
