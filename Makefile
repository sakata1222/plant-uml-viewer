PHONY: all docker-build clean

all: docker-build

docker-build:
	rm -rf docker_build
	mkdir -p docker_build
	rsync -a ./ docker_build --exclude "docker_build" --exclude "node_modules" --exclude "build"
	docker build docker_build -t plantuml-viewer

clean:
	rm -rf docker_build
