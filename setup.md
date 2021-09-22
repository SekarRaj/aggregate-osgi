## OSGi mvn project setup documentation

### Pre-requisite

* Install apache karaf and start the karaf container


### Generating maven multimodule project
```sh
# $ Generate parent
$ mvn archetype:generate -DgroupId=com.arachas -DartifactId=aggregate-osgi
# Change path to the parent before generating sub module
$ cd aggregate-osgi
# Generating submodule for service
$ mvn archetype:generate -DgroupId=com.arachas  -DartifactId=service
# Generating submodule for client
$ mvn archetype:generate -DgroupId=com.arachas  -DartifactId=client
```

### Install bundle 

```sh
$ karaf@root()> bundle:install mvn:com.arachas/service/1.0-SNAPSHOT 
Bundle ID: 131
$ karaf@root()> bundle:start 131
Registering service.
$ karaf@root()> bundle:stop 131
Unregistering service.
$ karaf@root()> bundle:uninstall 131

```

```sh
$ karaf@root()> bundle:install mvn:com.arachas/client/1.0-SNAPSHOT          
Bundle ID: 133
$ karaf@root()> bundle:start 133
Error executing command: Error executing command on bundles:
	Error starting bundle 133: Unable to resolve com.arachas.client [133](R 133.0): missing requirement [com.arachas.client [133](R 133.0)] osgi.wiring.package; (&(osgi.wiring.package=com.arachas.definition)(version>=1.0.0)(!(version>=2.0.0))) Unresolved requirements: [[com.arachas.client [133](R 133.0)] osgi.wiring.package; (&(osgi.wiring.package=com.arachas.definition)(version>=1.0.0)(!(version>=2.0.0)))]
$ karaf@root()> bundle:install mvn:com.arachas/service/1.0-SNAPSHOT 
Bundle ID: 134
$ karaf@root()> bundle:start 133
$ karaf@root()> bundle:start 134
Registering service.
Notification of service registered.
Hello Sekar
$ karaf@root()>    

```