# plant-uml-viewer

![image](https://user-images.githubusercontent.com/6317652/62855627-b9ce6500-bd2d-11e9-8b19-d9a9f26becc6.png)

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
