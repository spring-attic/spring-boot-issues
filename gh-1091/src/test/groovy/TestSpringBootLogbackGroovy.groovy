//

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Properties;

public class TestSpringBootLogbackGroovy {
    
    // static def gi = groovy.inspect.swingui.ObjectBrowser.&inspect;
    // static ClassLoader getCl() { Thread.currentThread().contextClassLoader }
    
    def theDir   = new File( System.properties.'test.buildLibsDirPath' ).canonicalFile;
    def jarFiles = theDir.listFiles().findAll { it.isFile() && it.name.endsWith('.jar') };
    
    def mainJar  = jarFiles.find { !it.name.contains('-fixed') }
    def fixedJar = jarFiles.find {  it.name.contains('-fixed') }
        
    def runJarFile( File aJarFile ) {
        assert aJarFile.exists();
		def ant = new AntBuilder();
		ant.exec( failonerror: "true"
		    // , output: "${ aJarFile.name }.out.txt"
		    // , error: "${ aJarFile.name }.err.txt"
		    , executable: "java" ) {
			ant.arg( value: "-jar" )
			ant.arg( value: aJarFile.path )
		}
    }
    	    
	@Test
	public void testMainJar() throws Exception {
		runJarFile( mainJar );
	}
	
	@Test
	public void testFixedJar() throws Exception {		
		runJarFile( fixedJar );	    
	}
	
}

