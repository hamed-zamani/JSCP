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
	SCPConnection.onStartButtonClicked(log); //"log" returns by LoggerFactory

###License
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
