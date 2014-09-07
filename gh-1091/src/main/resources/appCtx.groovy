import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groovy.util.logging.Slf4j;

import org.springframework.boot.CommandLineRunner;

Logger logger = LoggerFactory.getLogger( this.getClass() );
logger.info "### appCtx.groovy" ;

@Slf4j
class TestBean1 implements CommandLineRunner {
    def gi = groovy.inspect.swingui.ObjectBrowser.&inspect;
    
	public void run(String... args) {
	    log.info "###########"
		log.info "### run ###"
		log.info "###########"
		
		Thread.start {
            Thread.sleep 1000;
            
            Integer logbackGroovyInitCtr = System.getProperty( "logback.groovy.init.ctr", "1" ).toInteger();
            boolean ok = logbackGroovyInitCtr == 1;
            int exitValue = ok ? 0 : 1 ;
            String sMsg;
            def lg;
            lg = ok ? log.&info : log.&error ;
            
            lg( "#"*40 );
            sMsg = "### logbackGroovyInitCtr: ${logbackGroovyInitCtr}";
            lg( sMsg );
            lg( "#"*40 );
            sMsg = "### Exiting vm with exitValue: ${exitValue}";
            ok ? lg( "############" ) : lg( "!!!!!!!!!!!!" ) ; 
            ok ? lg( "### PASS ###" ) : lg( "!!! FAIL !!!" ) ; 
            ok ? lg( "############" ) : lg( "!!!!!!!!!!!!" ) ; 
            lg sMsg ;
            if ( !ok ) {
                lg( "### The logback groovy config file should only be evaluated once." );
            }
            
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.exit(exitValue); // !!!
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!		    
		}
	}
}

beans {
	testBean1( TestBean1 )
}

