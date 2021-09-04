# -*- Makefile -*-
#
# Makefile para compilar el proyecto GestionER
# Dependencias: LaTeX

JVC = javac
JVM = java
JDBC_FLAG = /usr/share/java/mysql-connector-java.jar
JVC_FLAGS = -g -d bin -cp $(JDBC_FLAG)
JVM_FLAGS = -cp bin:$(JDBC_FLAG)

SRCDIR = src
JVFLST = jvf.lst
CLASSLST = class.lst

JARPRJ = GestionER.jar
DOCPRJ = GestionER-doc.pdf

MAIN = GUIGestionER/FormularioMVC.java
RUNMAIN = $(basename $(MAIN))


all:
	@echo "Para compilar ejecutar:"
	@echo "  make compile"
	@echo "Para correr ejecutar:"
	@echo "  make run"
	@echo "Para correr desde JAR ejecutar:"
	@echo "  make jarrun"

$(JVFLST):
	find $(SRCDIR) -name '*.java' > $@

compile: $(JVFLST)
	$(JVC) $(JVC_FLAGS) @$<

run: compile
	$(JVM) $(JVM_FLAGS) $(RUNMAIN)

manifest.mf: compile
	@echo "Manifest-Version: 1.0" > $@
	@echo "Created-By: Miguel Molina" >> $@
	@echo "Main-Class: $(RUNMAIN)" >> $@

$(JARPRJ): manifest.mf
	cp $< bin
	( \
	  cd bin; \
	  find . -name '*.class' > $(CLASSLST); \
	  jar cmf $< $@ @$(CLASSLST); \
	  mv $@ ..; \
	)
	jar tf $@

jarbuild: $(JARPRJ)
	@echo "Archivo $(JARPRJ) creado."

jarrun: jarbuild
	$(JVM) -cp $(JDBC_FLAG):$(JARPRJ) GUIGestionER.FormularioMVC

# Comentar dos ultimas lineas para no compilar documentacion LaTeX
doc: Doxyfile
	rm -fR doc/html
	rm -fR doc/latex
	mkdir -p doc
	doxygen $<
	$(MAKE) -C doc/latex
	cp doc/latex/refman.pdf $(DOCPRJ)

clean:
	rm -f jvf.lst
	rm -fR bin/*
	rm -fR doc/*
	rm -f $(JARPRJ) manifest.mf $(DOCPRJ)
	find . -type f -name "*~" -exec rm -f {} \;

.PHONY: doc
