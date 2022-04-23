ROOT_DIR=../../

all: src_update lib_copy

src_update:
	cp -f ${ROOT_DIR}/make/i386/CMakeLists.txt ${ROOT_DIR}/opencog/

lib_copy:
	sudo mkdir -p /home/cogroid/local/lib/i386
	sudo cp -rf ${ROOT_DIR}/make/i386/lib/* /home/cogroid/local/lib/i386/

clean:
	cp -f ${ROOT_DIR}/make/i386/org/CMakeLists.txt ${ROOT_DIR}/opencog/
