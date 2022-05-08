
# The atom_types.h file is written to the build directory
INCLUDE_DIRECTORIES(${CMAKE_BINARY_DIR})

DECLARE_GUILE_CONFIG_TARGET(SCM_CONFIG "opencog as-config" "ATOMSPACE_TEST")

SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}  -v --target=arm-linux-androideabi -march=armv7-a -fPIC -fno-integrated-as --sysroot /home/cogroid/local/android-ndk-r18b/sysroot -isystem /home/cogroid/local/android-ndk-r18b/sysroot/usr/include/arm-linux-androideabi -D__ANDROID_API__=24 -fexceptions -frtti -ffunction-sections -funwind-tables -fstack-protector-strong -Wno-invalid-command-line-argument -Wno-unused-command-line-argument -no-canonical-prefixes -I/home/cogroid/local/android-ndk-r18b/sources/cxx-stl/llvm-libc++/include -I/home/cogroid/local/android-ndk-r18b/sources/cxx-stl/llvm-libc++abi/include -I/home/cogroid/local/android-ndk-r18b/sources/android/support/include -DANDROID -Wa,--noexecstack -Wformat -Werror=format-security -DNDEBUG -O2 -L/home/cogroid/local/android-ndk-r18b/platforms/android-24/arch-arm/usr/lib -B/home/cogroid/local/android-ndk-r18b/platforms/android-24/arch-arm/usr/lib -L/home/cogroid/local/android-ndk-r18b/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/lib/gcc/arm-linux-androideabi/4.9.x/armv7-a/thumb -B/home/cogroid/local/android-ndk-r18b/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/lib/gcc/arm-linux-androideabi/4.9.x/armv7-a/thumb -L/home/cogroid/local/d-atomspace/armv7/lib -B/home/cogroid/local/d-atomspace/armv7/lib -I/home/cogroid/local/d-atomspace/armv7/include -I/home/cogroid/local/libgmp/armv7/include ")

# The build order used here is loosely in terms of 
# dependencies: the later parts depend on, or may
# someday depend on the earlier parts.
#
ADD_SUBDIRECTORY (atoms)
ADD_SUBDIRECTORY (atomspace)
ADD_SUBDIRECTORY (eval)
ADD_SUBDIRECTORY (query)
ADD_SUBDIRECTORY (persist)

# Extension language support
IF (HAVE_GUILE)
	ADD_SUBDIRECTORY (guile)
	ADD_SUBDIRECTORY (scm)
	ADD_SUBDIRECTORY (matrix)
	ADD_SUBDIRECTORY (sheaf)
ENDIF (HAVE_GUILE)

IF (HAVE_CYTHON)
	ADD_SUBDIRECTORY (cython)
ENDIF (HAVE_CYTHON)

# Haskell bindings.
IF (HAVE_STACK)
	ADD_SUBDIRECTORY (haskell)
ENDIF (HAVE_STACK)

# OCaml bindings.
IF (HAVE_OCAML)
	ADD_SUBDIRECTORY (ocaml)
ENDIF (HAVE_OCAML)

WRITE_GUILE_CONFIG(${GUILE_BIN_DIR}/opencog/as-config.scm SCM_CONFIG TRUE)

WRITE_GUILE_CONFIG(${GUILE_BIN_DIR}/opencog/as-config-installable.scm SCM_CONFIG FALSE)
INSTALL(FILES ${GUILE_BIN_DIR}/opencog/as-config-installable.scm DESTINATION ${GUILE_SITE_DIR}/opencog RENAME as-config.scm)
