SRC=TAREA3_LP


SOURCES=$(shell find $(SRC) -name "*.java")

BIN=bin

JAVAC=javac
JAVA=java

MAIN=Main.Main

all: $(SOURCES)
	@mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(SOURCES)

run: all
	$(JAVA) -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)