JSCP
====

An SCP Server using Apache SSHD.
Language: Java

###Labraries which you need:
####1. sshd-core
    download last version from:
        http://mina.apache.org/downloads-sshd.html
        
####2. mina-core
    download from:
        http://grepcode.com/snapshot/repo1.maven.org/maven2/org.apache.mina/mina-core/2.0.2

####3. org.slf4j.Logger
    download last version from:
        http://www.slf4j.org/download.html


###How to compile and run?
You need to download the above packages and put them into class path.

###How ro use?
    SCPConnection.passwordAuth.setUser("test");
    SCPConnection.passwordAuth.setPassword("test");
	SCPConnection.onStartButtonClicked(log); //it gives from LoggerFactory
