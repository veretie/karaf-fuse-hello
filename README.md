# karaf-fuse-hello

### Overview
Small project to build OSGI bundle using Camel routes and deploy it on local Fuse Karaf.

### Routes

![Routes](docs/todo.jpeg)

## Run locally
#### Prereqs
 1. Install and start Fuse Karaf locally as described [here](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/html/installing_on_apache_karaf/install-fuse-on-karaf-for-developer#install-fuse-on-karaf).
 2. Set up `FUSE_HOME` env variable
 3. Start ActiveMQ docker container with ```make broker```

#### Build and deploy
 * Run `make deploy`

#### Test

## Local environament 
  
| Component | Endpoint | 
|--------------------------|----------------------------------------------------------------|
| ActiveMQ broker       | tcp://localhost:61616                                |
| ActiveMQ broker GUI   | http://localhost:8161/admin/        |
| Fuse Karaf console    | http://localhost:8181/              |



