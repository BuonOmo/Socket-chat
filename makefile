JFLAGS = -g -d $(CLASS_PATH)
JC = javac
CLASS_PATH = classes/
PKG_ECHO = echo
PKG_SOCKET = socket
SRC_PATH = src/
.SUFFIXES: .java .class
CLEAN = clean

.PHONY: $(CLEAN)
.java.class :
	$(JC) $(JFLAGS) $(CLASSES)

CLASSES = \
    	$(SRC_PATH)$(PKG_ECHO)/EchoClient.java \
    	$(SRC_PATH)$(PKG_ECHO)/EchoServer.java \
    	$(SRC_PATH)$(PKG_ECHO)/ClientThread.java \
    	$(SRC_PATH)$(PKG_ECHO)/EchoServerMultiThreaded.java \
    	$(SRC_PATH)$(PKG_SOCKET)/Client.java \
    	$(SRC_PATH)$(PKG_SOCKET)/Server.java \
    	$(SRC_PATH)$(PKG_SOCKET)/ClientRequest.java \
    	$(SRC_PATH)$(PKG_SOCKET)/ServerRequest.java


default : classes

classes : $(CLASSES:.java=.class)

clean :
	$(RM) $(CLASS_PATH)/*.class *~
