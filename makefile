JFLAGS = -g -d $(CLASS_PATH)
JC = javac
CLASS_PATH = classes/
PKG_ECHO = echo
PKG_SOCKET = socket
SRC_PATH = src/
ALL_SRC_PATH = $(SRC_PATH)$(PKG_ECHO)/ $(SRC_PATH)$(PKG_SOCKET)/
.SUFFIXES: .java .class
CLEAN = clean
BUILD = build
ECHO  = @echo
.PHONY: $(CLEAN)

CLASSES = \
    	$(SRC_PATH)$(PKG_ECHO)/EchoClient.java \
    	$(SRC_PATH)$(PKG_ECHO)/EchoServer.java \
    	$(SRC_PATH)$(PKG_ECHO)/ClientThread.java \
    	$(SRC_PATH)$(PKG_ECHO)/EchoServerMultiThreaded.java \
    	$(SRC_PATH)$(PKG_SOCKET)/Client.java \
    	$(SRC_PATH)$(PKG_SOCKET)/Server.java \
    	$(SRC_PATH)$(PKG_SOCKET)/ClientRequest.java \
    	$(SRC_PATH)$(PKG_SOCKET)/ServerRequest.java \
    	$(SRC_PATH)$(PKG_SOCKET)/ClientThread.java

$(BUILD) : $(CLASSES)
	$(JC) $(JFLAGS) $(CLASSES)

$(CLASSPATH)$(PKG_ECHO)/%.class : $(SRC_PATH)$(PKG_ECHO)/%.java
	$(ECHO) "compilation de <$<>"
	$(JC) $(JFLAGS) $<
$(CLASSPATH)$(PKG_SOCKET)/%.class : $(SRC_PATH)$(PKG_SOCKET)/%.java
	$(ECHO) "compilation de <$<>"
	$(JC) $(JFLAGS) $<


default : classes

classes : $(CLASSES:.java=.class)

clean :
	$(RM) $(CLASS_PATH)/*.class *~
