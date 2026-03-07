Spring Boot Actuator is the "Dashboard" for your running application. 
It provides built-in endpoints that let you see what’s happening inside the app without looking at the code.
 
1. What is Actuator?
   It is a sub-project of Spring Boot that adds production-ready features. Once you add the dependency, it exposes special URLs (endpoints) like /actuator/health.

2. Health Checks (/actuator/health)
   Purpose: Tells you if the app is "Up" or "Down".
   How it works: It doesn't just check if the server is on; it checks dependencies. If your Database or Redis cache is down, the health check will return DOWN.
   Cloud Use: AWS/Kubernetes calls this URL every few seconds. If it gets a "Down" response, it automatically restarts your container.

   /actuator/health    : Heartbeat check; confirms if the app and database are "UP" or "DOWN".
   /actuator/metrics   : Performance data; tracks CPU, memory usage,Memory (JVM Heap) and HTTP request counts.
   /actuator/loggers   : Log control; views or changes logging levels (e.g., INFO to DEBUG) without restarting.
   /actuator/env       : Config map; lists all active environment variables, properties, and profiles.
   /actuator/info      : App identity; displays static data like version number or Git commit details.

   For security, you must enable them in your application.properties using -
    - management.endpoints.web.exposure.include=health,metrics,info,loggers,env

3. Metrics (/actuator/metrics)
   Purpose: Shows the "vital signs" of your app.
   What it tracks: CPU usage, Memory (JVM Heap), how many people are logged in, and how long your API calls are taking.
   Analogy: It's like the Task Manager on your computer, but for your Java app.

4. Monitoring (External Tools)
   Actuator provides the raw data, but you need a "Screen" to see it.
   Prometheus: Periodically "scrapes" (collects) the data from Actuator.
   Grafana: Turns that data into beautiful graphs and alerts (e.g., "Alert me if Memory > 90%").
   Security Warning
   By default, most Actuator endpoints are hidden because they reveal sensitive info (like your database version). You must enable them in application.properties:
   properties
   management.endpoints.web.exposure.include=health,info,metrics

View app level data -

    Actuator > Prometheus server > Grafana

