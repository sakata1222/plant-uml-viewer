# plant-uml-viewer

![plantuml-viewer-demo](https://user-images.githubusercontent.com/6317652/63104410-ebf3f700-bfb9-11e9-8587-168aa5493d88.gif)

## Feature

- Real time rendaring
- PDF export

## How to start

### Container

- build a image

  ```shell
  make
  ```

- run as a container

  ```shell
  docker run --rm -p <your-port>:8080 plantuml-viewer
  ```

### Production

```shell
./gradlew startApp
```

This task builds both Server and GUI, and bundle GUI into spring boot applicaiton.

### for Developer

- Server

  ```shell
  ./gradlew startApi
  ```

- GUI

  ```shell
  cd frontend
  npm start
  ```
