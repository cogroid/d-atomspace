all:
	sudo mkdir -p /home/cogroid/local/d-atomspace/x64
	sudo cp -rf ./cogutil/* /home/cogroid/local/d-atomspace/x64/
	mkdir -p ../../build
	cd ../../build; \
	cmake \
	-DCMAKE_INSTALL_PREFIX="/home/cogroid/local/d-atomspace/x64" \
	..; \
	make -j4

install:
	sudo sh -c 'cd ../../build; \
	make install;'

clean:
	rm -rf ../../build