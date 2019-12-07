# homework

practice for TW small homework

### Run Project

1. Build project: `gradle build`
2. Run project `java -jar build/libs/homework-1.0-SNAPSHOT.jar`

Inputs for example:
 
- start distance: 2019-12-07T23:00:00;0.1,0.2,0.3 -> cost is 3
- traffic compensation: 
    - 2019-12-07T08:00:00;1,1,1 -> cost is 5, the speed is 3600 km/h
    - 2019-12-07T08:00:00;1,1,0.03 -> cost is 6, the speed is 108 km/h
- Drive At Night: 
    - 2019-12-07T23:00:00;1,1,1 -> cost is 6, the speed is 3600 km/h
    - 2019-12-07T23:00:00;1,1,0.03 -> cost is 7, the speed is 108 km/h