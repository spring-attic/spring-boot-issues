* Test project for issue spring-projects/spring-boot#898
* See also: spring-io/sagan#377

This project is a repackaged-version of spring-boot-sample-web-ui with the web project moved one level down.

spring-boot-sample-web-ui starts up fine with Spring Loaded (or: not with the current Spring Boot 1.1 snapshot, but with the M1 (dependency issues)):



```
./gradlew :myproject-a:bootRun

```

Gives:

```
Execution failed for task ':myproject-a:bootRun'.
> The JVM must be started with -noverify for this agent to work. You can use JAVA_OPTS to add that flag.
```

```
GRADLE_OPTS=-noverify ./gradlew :myproject-a:bootRun --stacktrace
```

Gives:

```
* What went wrong:
Failed to notify build listener.

* Try:
Run with --info or --debug option to get more log output.

* Exception is:
org.gradle.listener.ListenerNotificationException: Failed to notify build listener.
	at org.gradle.listener.BroadcastDispatch.dispatch(BroadcastDispatch.java:94)
	at org.gradle.listener.BroadcastDispatch.dispatch(BroadcastDispatch.java:31)
	at org.gradle.messaging.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:93)
	at com.sun.proxy.$Proxy12.buildFinished(Unknown Source)
	at org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)
	at org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.listener.BroadcastDispatch.dispatch(BroadcastDispatch.java:83)
	at org.gradle.listener.BroadcastDispatch.dispatch(BroadcastDispatch.java:31)
	at org.gradle.messaging.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:93)
	at com.sun.proxy.$Proxy12.buildFinished(Unknown Source)
	at org.gradle.initialization.DefaultGradleLauncher.doBuild(DefaultGradleLauncher.java:118)
	at org.gradle.initialization.DefaultGradleLauncher.run(DefaultGradleLauncher.java:81)
	at org.gradle.launcher.exec.InProcessBuildActionExecuter$DefaultBuildController.run(InProcessBuildActionExecuter.java:64)
	at org.gradle.launcher.cli.ExecuteBuildAction.run(ExecuteBuildAction.java:33)
	at org.gradle.launcher.cli.ExecuteBuildAction.run(ExecuteBuildAction.java:24)
	at org.gradle.launcher.exec.InProcessBuildActionExecuter.execute(InProcessBuildActionExecuter.java:35)
	at org.gradle.launcher.exec.InProcessBuildActionExecuter.execute(InProcessBuildActionExecuter.java:26)
	at org.gradle.launcher.cli.RunBuildAction.run(RunBuildAction.java:50)
	at org.gradle.internal.Actions$RunnableActionAdapter.execute(Actions.java:171)
	at org.gradle.launcher.cli.CommandLineActionFactory$ParseAndBuildAction.execute(CommandLineActionFactory.java:201)
	at org.gradle.launcher.cli.CommandLineActionFactory$ParseAndBuildAction.execute(CommandLineActionFactory.java:174)
	at org.gradle.launcher.cli.CommandLineActionFactory$WithLogging.execute(CommandLineActionFactory.java:170)
	at org.gradle.launcher.cli.CommandLineActionFactory$WithLogging.execute(CommandLineActionFactory.java:139)
	at org.gradle.launcher.cli.ExceptionReportingAction.execute(ExceptionReportingAction.java:33)
	at org.gradle.launcher.cli.ExceptionReportingAction.execute(ExceptionReportingAction.java:22)
	at org.gradle.launcher.Main.doAction(Main.java:46)
	at org.gradle.launcher.bootstrap.EntryPoint.run(EntryPoint.java:45)
	at org.gradle.launcher.Main.main(Main.java:37)
	at org.gradle.launcher.bootstrap.ProcessBootstrap.runNoExit(ProcessBootstrap.java:50)
	at org.gradle.launcher.bootstrap.ProcessBootstrap.run(ProcessBootstrap.java:32)
	at org.gradle.launcher.GradleMain.main(GradleMain.java:23)
	at org.gradle.wrapper.BootstrapMainStarter.start(BootstrapMainStarter.java:30)
	at org.gradle.wrapper.WrapperExecutor.execute(WrapperExecutor.java:127)
	at org.gradle.wrapper.GradleWrapperMain.main(GradleWrapperMain.java:55)
Caused by: java.lang.NoClassDefFoundError: org/springsource/loaded/ri/ReflectiveInterceptor
	at org.gradle.internal.exceptions.LocationAwareException.findNearestContextualCause(LocationAwareException.java:163)
	at org.gradle.internal.exceptions.LocationAwareException.visitCauses(LocationAwareException.java:145)
	at org.gradle.internal.exceptions.LocationAwareException.visitReportableCauses(LocationAwareException.java:123)
	at org.gradle.BuildExceptionReporter.formatGenericFailure(BuildExceptionReporter.java:139)
	at org.gradle.BuildExceptionReporter.reportBuildFailure(BuildExceptionReporter.java:125)
	at org.gradle.BuildExceptionReporter.constructFailureDetails(BuildExceptionReporter.java:113)
	at org.gradle.BuildExceptionReporter.renderSingleBuildException(BuildExceptionReporter.java:101)
	at org.gradle.BuildExceptionReporter.execute(BuildExceptionReporter.java:72)
	at org.gradle.BuildExceptionReporter.buildFinished(BuildExceptionReporter.java:63)
	at org.gradle.BuildLogger.buildFinished(BuildLogger.java:76)
	at org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)
	at org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.listener.DefaultListenerManager$LoggerDispatch.dispatch(DefaultListenerManager.java:174)
	at org.gradle.listener.DefaultListenerManager$LoggerDispatch.dispatch(DefaultListenerManager.java:163)
	at org.gradle.listener.BroadcastDispatch.dispatch(BroadcastDispatch.java:83)
	... 33 more
Caused by: java.lang.ClassNotFoundException: org.springsource.loaded.ri.ReflectiveInterceptor
	... 48 more

```

My setup:



```
uname -a; java -version; javac -version
Linux bep-laptop 3.13.0-24-generic #47-Ubuntu SMP Fri May 2 23:30:00 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux
java version "1.8.0_05"
Java(TM) SE Runtime Environment (build 1.8.0_05-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.5-b02, mixed mode)
javac 1.8.0_05
```


