# plant-uml-viewer

![image](https://user-images.githubusercontent.com/6317652/62855627-b9ce6500-bd2d-11e9-8b19-d9a9f26becc6.png)

## TODO

- Bundle front end into a web server of Spring boot
- Build Container image

## Feature

- Real time rendaring
- PDF export

## How to start

### Production

```shell
./gradlew startApp
```

This task builds both Server and GUI, and bundle GUI into spring boot applicaiton.

### for Developer

- Server

  ```shell
  ./gradlew bootRun
  ```

- GUI

  ```shell
  cd frontend
  npm start
  ```
