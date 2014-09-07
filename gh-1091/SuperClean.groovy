#!/usr/bin/env groovy

ant = new AntBuilder();

def isWin = { System.properties.'os.name'.contains('Windows') }

ant.exec( executable: isWin() ? 'cmd' : 'bash' ) {
	if (isWin()) { ant.arg( value: '/c' ) }
	ant.arg( value: 'gradlew' )
	ant.arg( value: 'clean' )
}

ant.delete( dir: '.gradle' )
