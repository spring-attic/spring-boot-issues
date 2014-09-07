
import ch.qos.logback.classic.Level;

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO


import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static System.err;

// for InfoFilter
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
// ACCEPT
// DENY
// NEUTRAL

// As of 9/2/2014:
// http://logback.qos.ch/manual/groovy.html
// We highly recommended that you always add a status listener just
// after the last import statement and before all other statements

statusListener( OnConsoleStatusListener );

// ################################################################
// ### all config code should be added below this comment block ###
// ################################################################

def flush = {
    System.out.flush();
    System.err.flush();
} ;

flush();
Thread.sleep 100; // trying to reduce sporadic dropped println outputs

flush();
String d1 = '-'
String d3 = d1 * 3;
String msg = "$d3 inside logback groovy configuration script $d3"
err.println( "### " + "$d1"*(msg.size()) );
err.println( "### $msg" );
err.println( "### " + "$d1"*(msg.size()) );
flush();

public class InfoFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        def accept = event.level.isGreaterOrEqual( Level.INFO );
        return accept ? FilterReply.ACCEPT : FilterReply.DENY ;        
    }
}

public class WarnFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        def accept = event.level.isGreaterOrEqual( Level.WARN );
        return accept ? FilterReply.ACCEPT : FilterReply.DENY ;        
    }
}

public class CustomFilter1 extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        def c1 = event.level.isGreaterOrEqual( Level.INFO );
        def c2 = "${event}".contains( "#" )
        def accept = c1 && c2;
        return accept ? FilterReply.ACCEPT : FilterReply.DENY ;        
    }
}

def propKeys = [ 
    // "java.class.path" , 
    "sun.java.command" ]

propKeys.each { String propKey ->
	err.println "### ${propKey}: " + System.getProperty( propKey )
}

String calcJarName() {
	String res1 = "org/springframework/boot/loader/JarLauncher.class"; // will not work for gradlew bootRun
	String s1   = """    ${ Thread.currentThread().contextClassLoader.getResource( res1 ).toString() - ( "!/" + res1 ) }    """.trim();
	s1.split("/").toList().last();
}

String jarName = calcJarName();

err.println "### jarName: ${jarName}"

// ### count and show how many times this file was evaluated
	String CTR_KEY           = "logback.groovy.init.ctr";
	int logbackGroovyInitCtr = System.getProperty( CTR_KEY, "0" ).toInteger() + 1;
	System.setProperty( CTR_KEY, "${logbackGroovyInitCtr}" ); // will be "1" on first pass
	err.println( "### logback groovy init count ${logbackGroovyInitCtr} ###" );

// System.setProperty("logbackGroovyInitCtr", "${logbackGroovyInitCtr}".trim() );

// assert logbackGroovyInitCtr == 1;

String thePid         = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split('@')[0];
// String theLogFilePath = "log/${thePid}_${jarName}_${logbackGroovyInitCtr}_${ this.hashCode() }.log";
String theLogFilePath    = "log/${jarName}_logbackInitCtr_${logbackGroovyInitCtr}_${this.hashCode()}_pid_${thePid}.log";

// String sPattern1 = "%-5level %-6relative [%thread] %class{40} - %msg%n"
String sPattern1    = "%-5level %-6relative %msg%n"

appender("CONSOLE", ConsoleAppender) {
    filter( InfoFilter )
    encoder(PatternLayoutEncoder) {
        pattern = sPattern1
    }
}

appender("FILE", FileAppender) {
    file = theLogFilePath;
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = sPattern1
    }
}

root(INFO, ["CONSOLE", "FILE"])

