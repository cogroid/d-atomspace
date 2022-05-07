all:
	sudo mkdir -p /home/cogroid/local/d-atomspace/i386
	sudo cp -rf ./cogutil/* /home/cogroid/local/d-atomspace/i386/
	cp -f ./CMakeLists.txt ../../opencog/
	sudo mkdir -p /usr/local/lib/opencog
	sudo cp -f ./cogutil/lib/opencog/libcogutil.so /usr/local/lib/opencog/
	mkdir -p ../../build
	cd ../../build; \
	cmake \
	-DCMAKE_INSTALL_PREFIX="/home/cogroid/local/d-atomspace/i386" \
	..; \
	make -j4

install:
	sudo sh -c 'cd ../../build; \
	make install;'

clean:
	rm -rf ../../build