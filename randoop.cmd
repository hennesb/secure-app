export RANDOOP_PATH=/Users/hennesb/Downloads/randoop-3.1.4
export RANDOOP_JAR=/Users/hennesb/Downloads/randoop-3.1.4/randoop-all-3.1.4.jar

java -ea -classpath $RANDOOP_JAR randoop.main.Main help
java -ea -classpath $RANDOOP_JAR randoop.main.Main help gentests

export APP_JARS=/Users/hennesb/apps/work-hackathon/build/libs/work-hackathon.jar:/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-webmvc/4.1.6.RELEASE/74e437c914201b70db2022052bc35611ad4beb9a/spring-webmvc-4.1.6.RELEASE-sources.jar:/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/4.1.6.RELEASE/38eefb4626db79c8939b58da803476864028f9d0/spring-web-4.1.6.RELEASE-sources.jar:/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/4.1.6.RELEASE/90d95c820dabd0187ff5be4a854b912741e8fd36/spring-context-4.1.6.RELEASE-sources.jar




export APP_JARS=/Users/hennesb/apps/work-hackathon/build/libs/work-hackathon.jar:
/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-webmvc/4.1.6.RELEASE/74e437c914201b70db2022052bc35611ad4beb9a/spring-webmvc-4.1.6.RELEASE-sources.jar
:/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/4.1.6.RELEASE/38eefb4626db79c8939b58da803476864028f9d0/spring-web-4.1.6.RELEASE-sources.jar
:/Users/hennesb/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/4.1.6.RELEASE/90d95c820dabd0187ff5be4a854b912741e8fd36/spring-context-4.1.6.RELEASE-sources.jar



java -ea -classpath $APP_JARS:$RANDOOP_JAR randoop.main.Main gentests --testclass=ie.components.controllers.featureflip.FeatureFlipController --timelimit=6