SUMMARY = "C++ mathematics library for graphics programming"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://copying.txt;md5=6ba02d5f908587c6f3942e76bf6d92d6"

inherit cmake

SRC_URI = "git://github.com/g-truc/glm.git"
# 0.9.7.1 tag
SRCREV = "8f39bb8730d45570384f3156eb0126b835024d69"
S = "${WORKDIR}/git"

do_configure() {
    # for some reason simple tailored configure runs best
    mkdir -p ${B}
    cd ${B}
    # -DCMAKE_INSTALL_LIBDIR:PATH=${libdir} causes configur break with
    # | CMake Error: Could not open file for write in copy operation /usr/lib/cmake/glm/glmConfig.cmake.tmp
    # | CMake Error: : System Error: No such file or directory
    cmake \
        -DCMAKE_INSTALL_PREFIX:PATH=${prefix} \
        -DCMAKE_INSTALL_INCLUDEDIR:PATH=${includedir} \
        ${OECMAKE_SOURCEPATH}
}

FILES_${PN}-dev += "${libdir}/cmake"

BBCLASSEXTEND = "native"
